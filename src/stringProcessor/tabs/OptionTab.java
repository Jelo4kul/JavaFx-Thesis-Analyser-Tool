package stringProcessor.tabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import stringProcessor.KeywordsExcluder;
import stringProcessor.ThesisModel;

/**
 *
 * @author Jelo
 */
public class OptionTab {

    private TextArea keywordsText;

    public BorderPane createPane() {
        BorderPane optionContent = new BorderPane();
        optionContent.setId("bulk-tab-content");

        StackPane divider = new StackPane();
        divider.setId("settings");
        divider.setAlignment(Pos.TOP_LEFT);
        Label inpText = new Label("General Settings");
        inpText.setId("bulk-text");
        divider.getChildren().add(inpText);
        optionContent.setTop(divider);

        VBox setnContent = new VBox(10);
        setnContent.setAlignment(Pos.TOP_CENTER);
        Label excludeKeywrd = createLabel("Exclude keywords from Search");
        Label excludeKeywrdHint = createKeywordHint("               Type in keywords that you want to be excluded from the search. \nTo exclude "
                + "more than one keyword, use comma as a delimiter. E.g: Analysis, Design");
        keywordsText = createTextArea();

        Button save = new Button("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (keywordsText.getText().isEmpty()) {
                    popupAlertDialog("Atention!", "Please type in the keywords to be excluded", AlertType.INFORMATION);
                } else {
                    try {
                        String keywords = keywordsText.getText();
                        KeywordsExcluder.trimKeywords(keywords.split(","));
                        popupAlertDialog("Success!", "Keywords excluded successfully", AlertType.INFORMATION);

                    } catch (Exception e) {
                        popupAlertDialog("Error", "Error in input", AlertType.ERROR);
                    }
                }

            }
        });

        setnContent.getChildren().addAll(excludeKeywrd, excludeKeywrdHint, keywordsText, save);
        optionContent.setCenter(setnContent);

        HBox footer = new HBox(20);
        footer.setId("opt-footer");
        footer.setAlignment(Pos.TOP_RIGHT);

        Button clrCache = new Button("Clear Cache");
        clrCache.setId("light-btn");
        clrCache.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reset();
                popupAlertDialog("Success!", "Cache cleared successfully", AlertType.INFORMATION);
            }

        });

        footer.getChildren().add(clrCache);

        optionContent.setBottom(footer);

        return optionContent;
    }

    private void popupAlertDialog(String message, String contentOfMsg, AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setTitle(message);
        alert.setContentText(contentOfMsg);
        alert.showAndWait();
    }

    public void reset() {

        ThesisModel thesisModel = new ThesisModel();
        HomeResultTab resultTab = new HomeResultTab();
        StartTab startTab = new StartTab();
        KeywordFinderTab kwFinderTab = new KeywordFinderTab();
        ConceptualFrameworkTab conceptualFrameworkTab= new ConceptualFrameworkTab();

        keywordsText.clear();

        startTab.setThesisTitle("");

        kwFinderTab.clearKeywordList();
        kwFinderTab.resetKeywordsLayout();

        conceptualFrameworkTab.clearFileNames();
        conceptualFrameworkTab.useDefaultLayout();
        
        thesisModel.setMainResearchArea("No Main Research Area");
        thesisModel.setContatenatedMethods(null);
        thesisModel.setContatenatedTools(null);
        thesisModel.setResearchMethodology("No Research Methodolgy");
        thesisModel.setTitle("No title");
        resultTab.deleteMethodologyBtn();
        resultTab.deleteMethodsBtn();
        resultTab.deleteSummaryBtn();
        resultTab.deleteToolsBtn();

        resultTab.displayMethodologyLayout();

    }

    public Label createLabel(String text) {
        Label inpText = new Label(text);
        inpText.setId("inp-text");
        inpText.setAlignment(Pos.CENTER);
        return inpText;
    }

    public Label createKeywordHint(String text) {
        Label excludeKeywordHint = new Label(text);
        excludeKeywordHint.setId("bulk-text");
        excludeKeywordHint.setAlignment(Pos.CENTER);
        return excludeKeywordHint;
    }

    public TextArea createTextArea() {
        TextArea thesisText = new TextArea();
        thesisText.setMaxHeight(80);
        thesisText.setId("textarea");
        thesisText.setMaxWidth(460);
        thesisText.setWrapText(true);
        return thesisText;
    }
}
