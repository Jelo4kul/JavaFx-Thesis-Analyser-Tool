package stringProcessor.outputs;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import stringProcessor.database.DBRecordRetriever;
import stringProcessor.utility.FileEngine;

public class SystemEvaluationFolderOutput extends Application {

    private String evaluationType;
    private SystemEvaluationArticlesOutput sysEvalArticleOutput;
    private VBox root = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private final int viewType;

    public SystemEvaluationFolderOutput(int viewType) {
        this.viewType = viewType;
    }

    public void setEvaluationType(String evaluationType) {
        this.evaluationType = evaluationType;
    }

    public String getEvaluationType() {
        return evaluationType;
    }

    @Override
    public void start(Stage primaryStage) {

        VBox divider = new VBox();
        divider.setId("divider");

        HBox researchAreaLayout = new HBox(10);
        researchAreaLayout.setId("header-layout");
        researchAreaLayout.setAlignment(Pos.CENTER);
        Text researchAreaText = new Text("Evaluation Method : ");
        researchAreaText.setId("lit-review-output");
        Text researchAreaValue = new Text(evaluationType);
        researchAreaValue.setId("researchArea-text");
        researchAreaLayout.getChildren().addAll(researchAreaText, researchAreaValue);

        StackPane dummyPane = new StackPane();
        dummyPane.setAlignment(Pos.BOTTOM_LEFT);
        Button articleHolder = new Button("Folders");
        articleHolder.setAlignment(Pos.BOTTOM_LEFT);
        articleHolder.setId("article-id");
        dummyPane.getChildren().add(articleHolder);

        divider.getChildren().addAll(researchAreaLayout, dummyPane);
        divider.setId("divider");
        divider.setAlignment(Pos.TOP_CENTER);

        VBox mainContainer = new VBox();
        mainContainer.setMinWidth(860);

        switch (viewType) {
            case 0:
                mainContainer.getChildren().add(quantitativeView());
                break;
            case 1:
                mainContainer.getChildren().add(qualitativeView());
                break;
        }

        scrollPane.setContent(mainContainer);

        root.setId("base-container");
        root.getChildren().addAll(divider, scrollPane);

        Scene scene = new Scene(root, 920, 500);

        //attaching the css file to the scene
        scene.getStylesheets().add(LiteratureReviewOutput.class.getResource("litReviewStyle.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public VBox quantitativeView() {
        // questionnaire and correlation
        HBox qstionaireBox = new HBox(10);
        qstionaireBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("Questionnaire");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                //get the corresponding articles needed for the search
                new FileEngine().extractFileNames();
                sysEvalArticleOutput = new SystemEvaluationArticlesOutput();
                sysEvalArticleOutput.setFolderType("Questionnaire");
                sysEvalArticleOutput.start(new Stage());
            }

        });
        ImageView imgView = new ImageView();
        Image img = new Image(SystemEvaluationFolderOutput.class.getResourceAsStream("jaydee.PNG"));
        imgView.setFitHeight(25);
        imgView.setFitWidth(25);
        imgView.setImage(img);

        Text qstionaire = new Text("Questionnaire");
        qstionaire.setId("folder");
        qstionaireBox.getChildren().addAll(imgView, qstionaire);

        HBox correlationBox = new HBox(10);
        correlationBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("Correlation");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                //get the corresponding articles needed for the search
                new FileEngine().extractFileNames();
                sysEvalArticleOutput = new SystemEvaluationArticlesOutput();
                sysEvalArticleOutput.setFolderType("Correlation");
                sysEvalArticleOutput.start(new Stage());
            }

        });
        ImageView imgView2 = new ImageView();
        Image img2 = new Image(SystemEvaluationFolderOutput.class.getResourceAsStream("jaydee.PNG"));
        imgView2.setFitHeight(25);
        imgView2.setFitWidth(25);
        imgView2.setImage(img2);

        Text qstAndCorrelation = new Text("Correlation");
        qstAndCorrelation.setId("folder");
        correlationBox.getChildren().addAll(imgView2, qstAndCorrelation);

        BorderPane rowLayout = new BorderPane();
        rowLayout.setId("folder-layout");
        rowLayout.setLeft(qstionaireBox);

        BorderPane rowLayout2 = new BorderPane();
        rowLayout2.setId("folder-layout");
        rowLayout2.setLeft(correlationBox);

        VBox divider2 = new VBox();
        divider2.setId("divider");
        divider2.getChildren().addAll(rowLayout, rowLayout2);

        return divider2;
    }

    public VBox qualitativeView() {
        HBox interviewBox = new HBox(10);
        interviewBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("Interview");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                //get the corresponding articles needed for the search
                new FileEngine().extractFileNames();
                sysEvalArticleOutput = new SystemEvaluationArticlesOutput();
                sysEvalArticleOutput.setFolderType("Interview");
                sysEvalArticleOutput.start(new Stage());
            }

        });
        ImageView imgView = new ImageView();
        Image img = new Image(SystemEvaluationFolderOutput.class.getResourceAsStream("jaydee.PNG"));
        imgView.setFitHeight(25);
        imgView.setFitWidth(25);
        imgView.setImage(img);

        Text interview = new Text("Interview");
        interview.setId("folder");
        interviewBox.getChildren().addAll(imgView, interview);

        HBox caseStudyBox = new HBox(10);
        caseStudyBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("Case Study");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                //get the corresponding articles needed for the search
                new FileEngine().extractFileNames();
                sysEvalArticleOutput = new SystemEvaluationArticlesOutput();
                sysEvalArticleOutput.setFolderType("Case Study");
                sysEvalArticleOutput.start(new Stage());
            }

        });
        ImageView imgView2 = new ImageView();
        Image img2 = new Image(SystemEvaluationFolderOutput.class.getResourceAsStream("jaydee.PNG"));
        imgView2.setFitHeight(25);
        imgView2.setFitWidth(25);
        imgView2.setImage(img2);

        Text caseStudy = new Text("Case Study");
        caseStudy.setId("folder");
        caseStudyBox.getChildren().addAll(imgView2, caseStudy);

        HBox experimentBox = new HBox(10);
        experimentBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("Experiment");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                //get the corresponding articles needed for the search
                new FileEngine().extractFileNames();
                sysEvalArticleOutput = new SystemEvaluationArticlesOutput();
                sysEvalArticleOutput.setFolderType("Experiment");
                sysEvalArticleOutput.start(new Stage());
            }

        });
        ImageView imgView3 = new ImageView();
        Image img3 = new Image(SystemEvaluationFolderOutput.class.getResourceAsStream("jaydee.PNG"));
        imgView3.setFitHeight(25);
        imgView3.setFitWidth(25);
        imgView3.setImage(img3);

        Text experiment = new Text("Experiment");
        experiment.setId("folder");
        experimentBox.getChildren().addAll(imgView3, experiment);

        HBox simulationBox = new HBox(10);
        simulationBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("Simulation");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                //get the corresponding articles needed for the search
                new FileEngine().extractFileNames();
                sysEvalArticleOutput = new SystemEvaluationArticlesOutput();
                sysEvalArticleOutput.setFolderType("Simulation");
                sysEvalArticleOutput.start(new Stage());
            }

        });
        ImageView imgView4 = new ImageView();
        Image img4 = new Image(SystemEvaluationFolderOutput.class.getResourceAsStream("jaydee.PNG"));
        imgView4.setFitHeight(25);
        imgView4.setFitWidth(25);
        imgView4.setImage(img4);

        Text simulation = new Text("Simulation");
        simulation.setId("folder");
        simulationBox.getChildren().addAll(imgView4, simulation);

        HBox focalGroupBox = new HBox(10);
        focalGroupBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("Focal Group");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                //get the corresponding articles needed for the search
                new FileEngine().extractFileNames();
                sysEvalArticleOutput = new SystemEvaluationArticlesOutput();
                sysEvalArticleOutput.setFolderType("Focal Group");
                sysEvalArticleOutput.start(new Stage());
            }

        });
        ImageView imgView5 = new ImageView();
        Image img5 = new Image(SystemEvaluationFolderOutput.class.getResourceAsStream("jaydee.PNG"));
        imgView5.setFitHeight(25);
        imgView5.setFitWidth(25);
        imgView5.setImage(img5);

        Text focalGroup = new Text("Focal Group");
        focalGroup.setId("folder");
        focalGroupBox.getChildren().addAll(imgView5, focalGroup);

        HBox openEQBox = new HBox(10);
        openEQBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("Open Ended Questionnaire");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                //get the corresponding articles needed for the search
                new FileEngine().extractFileNames();
                sysEvalArticleOutput = new SystemEvaluationArticlesOutput();
                sysEvalArticleOutput.setFolderType("Open Ended Questionnaire");
                sysEvalArticleOutput.start(new Stage());
            }

        });
        ImageView imgView6 = new ImageView();
        Image img6 = new Image(SystemEvaluationFolderOutput.class.getResourceAsStream("jaydee.PNG"));
        imgView6.setFitHeight(25);
        imgView6.setFitWidth(25);
        imgView6.setImage(img6);

        Text openEQ = new Text("Open Ended Questionnaire");
        openEQ.setId("folder");
        openEQBox.getChildren().addAll(imgView6, openEQ);

        BorderPane rowLayout = new BorderPane();
        rowLayout.setId("folder-layout");
        rowLayout.setLeft(interviewBox);

        BorderPane rowLayout2 = new BorderPane();
        rowLayout2.setId("folder-layout");
        rowLayout2.setLeft(caseStudyBox);

        BorderPane rowLayout3 = new BorderPane();
        rowLayout3.setId("folder-layout");
        rowLayout3.setLeft(experimentBox);

        BorderPane rowLayout4 = new BorderPane();
        rowLayout4.setId("folder-layout");
        rowLayout4.setLeft(simulationBox);

        BorderPane rowLayout5 = new BorderPane();
        rowLayout5.setId("folder-layout");
        rowLayout5.setLeft(focalGroupBox);

        BorderPane rowLayout6 = new BorderPane();
        rowLayout6.setId("folder-layout");
        rowLayout6.setLeft(openEQBox);

        VBox divider2 = new VBox();
        divider2.setId("divider");
        divider2.getChildren().addAll(rowLayout, rowLayout2, rowLayout3, rowLayout4, rowLayout5, rowLayout6);

        return divider2;
    }

}
