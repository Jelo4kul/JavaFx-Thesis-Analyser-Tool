package stringProcessor.tabs;

import stringProcessor.utility.StopWordsFileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stringProcessor.KeywordTools;
import stringProcessor.KeywordsExcluder;
import stringProcessor.utility.ResearchAreaGrouper;
import stringProcessor.ResearchFields;
import stringProcessor.utility.ResearchFieldsAnalyser;
import stringProcessor.utility.ResearchMethodsGrouper;
import stringProcessor.ResearchTools;
import stringProcessor.ThesisModel;
import stringProcessor.outputs.HomeAnalysisOutput;

/**
 *
 * @author Jelo
 */
public class StartTab {

    private String thesisArticle;
    private static String researchArea;
    private static TextArea thesisText;
    private ObservableList<String> fileNames;
    private ArrayList<String> tempKeywords = new ArrayList<>(), keywords = new ArrayList<>(), stopwordList;

    public String getThesisArticle() {
        return thesisArticle;
    }

    public void setThesisTitle(String title) {
        thesisText.setText(title);
    }

    public static TextArea getThesisTitle() {
        return thesisText;
    }

    public static String getResearchArea() {
        return researchArea;
    }

    public VBox createBorderPane() {
        VBox startTabContent = new VBox();
        startTabContent.setId("tab-content");

        Label inpText = createLabel("Input Thesis Title");
        startTabContent.getChildren().add(inpText);

        thesisText = createTextArea();

        startTabContent.getChildren().add(thesisText);

        BorderPane footer = new BorderPane();
        footer.setMaxWidth(760);
        //footer.prefWidthProperty().bind(startTabContent.prefWidthProperty());
        footer.setId("footer");

        Button analyzeBtn = new Button("Analyze it");

        analyzeBtn.setOnAction((ActionEvent event) -> {

            thesisArticle = thesisText.getText();
            new ThesisModel().setTitle(thesisArticle);
            breakArticleIntoKeywords(thesisArticle);

            if (keywords.size() > 1 && researchArea != null) {
                new HomeAnalysisOutput().start(new Stage());
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Wrong Input");
                alert.setContentText("Please put in a proper thesis title");
                alert.showAndWait();

                thesisText.clear();

                KeywordFinderTab kwFinderTab = new KeywordFinderTab();
                kwFinderTab.clearKeywordList();
                kwFinderTab.resetKeywordsLayout();
            }

            tempKeywords.clear();
            keywords.clear();

        });
        footer.setRight(analyzeBtn);

        startTabContent.getChildren().add(footer);
        return startTabContent;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void addfoundKeywordsToList(String article) {
        StopWordsFileReader fileReader = new StopWordsFileReader();
        fileReader.readFile("/files/stopwords.txt");
        stopwordList = fileReader.getStopWords();

        Pattern pattern = Pattern.compile("\\w{3,40}");
        Matcher matcher = pattern.matcher(article.toLowerCase());

        while (matcher.find()) {
            tempKeywords.add(matcher.group());
        }
    }

    public void breakArticleIntoKeywords(String article) {

        addfoundKeywordsToList(article);

        for (String word : tempKeywords) {
            if (stopwordList.contains(word)) {

            } else if (new KeywordsExcluder().getKeywords().contains(word)) {

            } else {
                keywords.add(word);
                KeywordFinderTab.kywdList.add(word);
            }
        }
        
        KeywordFinderTab kfT = new KeywordFinderTab();
        kfT.resetKeywordsLayout();
        kfT.addKeywordToPane();
        kfT.clearKeywordList();

        //this if condition checks to see if the user provided enough information
        if (keywords.size() <= 1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inadequate Information");
            alert.setContentText("Please provide enough information");
            alert.showAndWait();

        } else {

            ResearchFields researchF = new ResearchFields();
            researchF.retrieveResearchFields();

            ResearchTools researchT = new ResearchTools();

            KeywordTools keywordT = new KeywordTools();
            keywordT.retrieveKeywordTools();

            ResearchAreaGrouper rAreaGrouper = new ResearchAreaGrouper();

            ResearchFieldsAnalyser rFAnalyser = new ResearchFieldsAnalyser(keywords, researchF);

            //we are looping through all the main research area to find the keywords that match
            for (int i = 0; i < researchF.numberOfResearchFields(); i++) {
                rFAnalyser.findMatchedKeywords(researchF.getResearchFields()[i], i);
            }

            //here we check to see if any match was found at all.
            //this is done by checking if the arraylist that contains all the number of occurrence for each main research 
            //area is 0. If it is zero, then it means that no keyword was found
            if (rFAnalyser.getMainResearchAreaWithMaxOccurrence() == 0) {

                //do nothing
            } //here I checked to see if there are multiple main research areas that had thesame number of keywords
            else if (rFAnalyser.stringMatchConflict()) {
                //optimize the researchFields incase of string match conflict
                researchArea = rFAnalyser.optimizeResearchFields(thesisText.getText());

                String methodology = rAreaGrouper.checkResearchAreaGroup(researchArea);

                ThesisModel thesisModel = new ThesisModel();
                thesisModel.setMainResearchArea(researchArea);
                thesisModel.setResearchMethodology(methodology);

                new HomeResultTab().makeMethodsBtnVisible();
                //Methods
                String[] researchMethods = new ResearchMethodsGrouper().checkMethodGroup(methodology);
                HomeResultTab.amtOfMethods = researchMethods.length;
                for (int i = 0; i < researchMethods.length; i++) {
                    thesisModel.setResearchMethods(researchMethods[i], i);
                }
                //ConcatenatedMethods
                thesisModel.setContatenatedMethods(" ");

                //tools
                researchT.getResearchTools(researchT.methodologyToolGroup(methodology), researchArea);
                ArrayList<String> researchTools = researchT.searchForKeywordsInTools(keywordT.getResearchFields(researchArea));
                if (researchTools == null) {
                    HomeResultTab.amtOfTools = 0;
                    thesisModel.setResearchTools("No research Tool available for this Thesis Title", 0);
                } else {
                    HomeResultTab.amtOfTools = researchTools.size();
                    for (int i = 0; i < researchTools.size(); i++) {
                        thesisModel.setResearchTools(researchTools.get(i), i);
                    }
                    //ConcatenatedTools
                    thesisModel.setContatenatedTools(" ");
                }

                SummaryTab.bindModelToView();

            } //the if condition here works if there is just 1 main research area that has the highest keywords
            else {
                researchArea = researchF.getResearchFieldName(rFAnalyser.getMainResearchAreaIndexWithMaxOccurrence());

                String methodology = rAreaGrouper.checkResearchAreaGroup(researchArea);

                ThesisModel thesisModel = new ThesisModel();
                thesisModel.setMainResearchArea(researchArea);
                thesisModel.setResearchMethodology(methodology);

                new HomeResultTab().makeMethodsBtnVisible();
                //Methods
                String[] researchMethods = new ResearchMethodsGrouper().checkMethodGroup(methodology);

                HomeResultTab.amtOfMethods = researchMethods.length;
                for (int i = 0; i < researchMethods.length; i++) {
                    thesisModel.setResearchMethods(researchMethods[i], i);
                }
                //ConcatenatedMethods
                thesisModel.setContatenatedMethods(" ");

                //tools
                researchT.getResearchTools(researchT.methodologyToolGroup(methodology), researchArea);
                ArrayList<String> researchTools = researchT.searchForKeywordsInTools(keywordT.getResearchFields(researchArea));

                if (researchTools == null) {
                    HomeResultTab.amtOfTools = 0;
                    thesisModel.setResearchTools("No research Tool available for this Thesis Title", 0);
                } else {
                    HomeResultTab.amtOfTools = researchTools.size();
                    for (int i = 0; i < researchTools.size(); i++) {
                        thesisModel.setResearchTools(researchTools.get(i), i);
                    }
                    //ConcatenatedTools
                    thesisModel.setContatenatedTools(" ");
                }

                SummaryTab.bindModelToView();
            }
        }

    }

    public Label createLabel(String text) {
        Label inpText = new Label(text);
        inpText.setId("inp-text");
        inpText.setAlignment(Pos.CENTER);
        return inpText;
    }

    public TextArea createTextArea() {
        TextArea thesisTextArea = new TextArea();
        thesisTextArea.setMaxHeight(60);
        thesisTextArea.setId("textarea");
        thesisTextArea.setMaxWidth(760);
        thesisTextArea.setWrapText(true);
        return thesisTextArea;
    }
}
