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
import stringProcessor.utility.FileEngine;
import stringProcessor.InnerPaneCreator;
import stringProcessor.utility.ArticleReader;

/**
 *
 * @author Jelo
 */
public class ProgrammingLanguages extends Application {

    private FileEngine fileEngine = new FileEngine();
    private VBox root = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private ArticleReader artReader = new ArticleReader();

    @Override
    public void start(Stage primaryStage) {

        VBox divider = new VBox();
        divider.setId("divider");

        Text inpText = new Text("Suitable Programming Languages");
        inpText.setId("header-text");

        StackPane dummyPane = new StackPane();
        dummyPane.setAlignment(Pos.BOTTOM_LEFT);
        Button articleHolder = new Button("Languages");
        articleHolder.setAlignment(Pos.BOTTOM_LEFT);
        articleHolder.setId("article-id");
        dummyPane.getChildren().add(articleHolder);

        divider.getChildren().addAll(inpText, dummyPane);
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
        Text satisfactoryText = new Text("Satisfied? Go to System Evaluation");
        satisfactoryText.setId("lit-review-output");
        Button systemArchBtn = new Button("System Evaluation");
        systemArchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                InnerPaneCreator.getChildTabPanes()[2].getSelectionModel().select(4);
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
