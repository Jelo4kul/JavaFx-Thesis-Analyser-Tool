package stringProcessor.outputs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import stringProcessor.database.DBRecordRetriever;
import stringProcessor.utility.FileEngine;
import stringProcessor.utility.ArticleReader;

/**
 *
 * @author Jelo
 */
public class SystemAnalysisAndDesignOutput extends Application {

    private FileEngine fileEngine = new FileEngine();
    private String analysisType;
    private VBox root = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private static String methodologyType;
    private ArticleReader artReader = new ArticleReader();

    public void setMethodologyType(String methodologyType) {
        this.methodologyType = methodologyType;
    }

    public String getMethodologyType() {
        return methodologyType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    @Override
    public void start(Stage primaryStage) {

        VBox divider = new VBox();
        divider.setId("divider");

        HBox researchAreaLayout = new HBox(10);
        researchAreaLayout.setId("header-layout");
        researchAreaLayout.setAlignment(Pos.CENTER);
        Text researchAreaText = new Text("Design and Analysis Method : ");
        researchAreaText.setId("lit-review-output");
        Text researchAreaValue = new Text(analysisType);
        researchAreaValue.setId("researchArea-text");
        researchAreaLayout.getChildren().addAll(researchAreaText, researchAreaValue);

        StackPane dummyPane = new StackPane();
        dummyPane.setAlignment(Pos.BOTTOM_LEFT);
        Button articleHolder = new Button("Articles");
        articleHolder.setAlignment(Pos.BOTTOM_LEFT);
        articleHolder.setId("article-id");
        dummyPane.getChildren().add(articleHolder);

        divider.getChildren().addAll(researchAreaLayout, dummyPane);
        divider.setId("divider");
        divider.setAlignment(Pos.TOP_CENTER);

        VBox mainContainer = new VBox();
        mainContainer.setMinWidth(860);

        try {

            for (int i = 0; i < fileEngine.getNoOfFilesInFolder(); i++) {
                final int j = i;
                Text number = new Text(i + 1 + ".");
                number.setId("number");

                String fileName = fileEngine.getFileNames().get(i);
                Text article = new Text(fileName);
                article.setWrappingWidth(500);
                article.setId("article");

                Button viewBtn = new Button("Read");
                viewBtn.setId("button");
                viewBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        artReader.extractPdfContents(fileEngine.getFilePaths().get(j));
                        artReader.openAndReadPdf();
                    }

                });
                
                Button saveBtn = new Button("Save");
                saveBtn.setId("button");
                saveBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        artReader.extractPdfContents(fileEngine.getFilePaths().get(j));
                        artReader.savePdf(fileName);
                    }

                });
                
                HBox hbx = new HBox(10);
                hbx.getChildren().addAll(viewBtn, saveBtn);
                
                BorderPane rowLayout = new BorderPane();
                rowLayout.setId("row-layout");
                rowLayout.setLeft(number);
                rowLayout.setCenter(article);
                rowLayout.setRight(hbx);

                VBox divider2 = new VBox();
                divider2.setAlignment(Pos.CENTER_RIGHT);
                divider2.setId("divider");
                divider2.getChildren().add(rowLayout);

                mainContainer.getChildren().add(divider2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        scrollPane.setContent(mainContainer);

        VBox bottomLayout = new VBox(20);
        bottomLayout.setPadding(new Insets(20));
        bottomLayout.setAlignment(Pos.CENTER);
        Text satisfactoryText = new Text("View programming languages suited for your methodolgy");
        satisfactoryText.setId("lit-review-output");
        Button systemArchBtn = new Button("Programming Languages");
        systemArchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                if (methodologyType.equalsIgnoreCase("OOADM")) {
                    DBRecordRetriever.retrieveArticlePaths("OOADM Programming Languages");
                    fileEngine.extractFileNames();
                } else {
                    DBRecordRetriever.retrieveArticlePaths("SSADM Programming Languages");
                    fileEngine.extractFileNames();
                }

                new ProgrammingLanguages().start(new Stage());
            }

        });
        systemArchBtn.setId("button");
        bottomLayout.getChildren().addAll(satisfactoryText, systemArchBtn);

        root.setId("base-container");
        root.getChildren().addAll(divider, scrollPane, bottomLayout);

        Scene scene = new Scene(root, 920, 500);

        //attaching the css file to the scene
        scene.getStylesheets().add(LiteratureReviewOutput.class.getResource("litReviewStyle.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
