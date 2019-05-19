package stringProcessor.outputs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import stringProcessor.utility.ArticleReader;
import stringProcessor.utility.FileEngine;

/**
 *
 * @author Jelo
 */
public class SystemEvaluationArticlesOutput extends Application {

    private VBox root = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private String folderType;
    private FileEngine fileEngine = new FileEngine();
    private ArticleReader artReader = new ArticleReader();

    public void setFolderType(String folderType) {
        this.folderType = folderType;
    }

    public String getFolderType() {
        return folderType;
    }

    @Override
    public void start(Stage primaryStage) {

        VBox divider = new VBox();
        divider.setId("divider");

        HBox researchAreaLayout = new HBox(10);
        researchAreaLayout.setId("header-layout");
        researchAreaLayout.setAlignment(Pos.CENTER);
        Text researchAreaText = new Text("Specific Evaluation Method:");
        researchAreaText.setId("lit-review-output");
        Text researchAreaValue = new Text(folderType);
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
                
                HBox hbx=new HBox(10);
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

        root.setId("base-container");
        root.getChildren().addAll(divider, scrollPane);

        Scene scene = new Scene(root, 920, 500);

        //attaching the css file to the scene
        scene.getStylesheets().add(LiteratureReviewOutput.class.getResource("litReviewStyle.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
