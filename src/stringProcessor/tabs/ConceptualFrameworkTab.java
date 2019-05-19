package stringProcessor.tabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import stringProcessor.utility.FileEngine;
import stringProcessor.InnerPaneCreator;
import stringProcessor.outputs.LiteratureReviewOutput;
import stringProcessor.utility.ArticleReader;

/**
 *
 * @author Jelo
 */
public class ConceptualFrameworkTab {

    private VBox root = new VBox();
    private static VBox mainContainer = new VBox();
    private static Text researchAreaValue = new Text("");
    private static ScrollPane scrollPane = new ScrollPane();
    private FileEngine fileEngine = new FileEngine();
    private static VBox bottomLayout = new VBox(20);
    private static final String DEFAULT_TITLE = "Research Area not found";
    private ArticleReader artReader = new ArticleReader();
  //  private String  fileName;
    public Text getResearchAreaValue() {
        return researchAreaValue;
    }

    public VBox createBorderPane() {
        VBox divider = new VBox();
        divider.setId("divider");

        Text inpText = new Text("Local Search Results");
        inpText.setId("header-text");

        HBox researchAreaLayout = new HBox(10);
        researchAreaLayout.setId("header-layout");
        researchAreaLayout.setAlignment(Pos.CENTER);
        Text researchAreaText = new Text("Main Research Area : ");
        researchAreaText.setId("lit-review-output");
        researchAreaValue = new Text(DEFAULT_TITLE);
        researchAreaValue.setId("researchArea-text");
        researchAreaLayout.getChildren().addAll(researchAreaText, researchAreaValue);

        StackPane dummyPane = new StackPane();
        dummyPane.setAlignment(Pos.BOTTOM_LEFT);
        Button articleHolder = new Button("Articles");
        articleHolder.setAlignment(Pos.BOTTOM_LEFT);
        articleHolder.setId("article-id");
        dummyPane.getChildren().add(articleHolder);

        divider.getChildren().addAll(inpText, researchAreaLayout, dummyPane);
        divider.setId("divider");
        divider.setAlignment(Pos.TOP_CENTER);

        mainContainer.setMinWidth(860);
        mainContainer.setPrefHeight(300);
        mainContainer.setAlignment(Pos.CENTER_RIGHT);

        addArticlesToScreen();

        scrollPane.setContent(mainContainer);

        bottomLayout.setPadding(new Insets(20));
        bottomLayout.setAlignment(Pos.CENTER);
        Text satisfactoryText = new Text("Satisfied? Go to System Architecture");
        satisfactoryText.setId("lit-review-output");
        Button systemArchBtn = new Button("System Architecture");
        systemArchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                InnerPaneCreator.getChildTabPanes()[2].getSelectionModel().select(2);
            }

        });
        systemArchBtn.setId("button");
        bottomLayout.getChildren().addAll(satisfactoryText, systemArchBtn);

        root.setId("base-container");
        root.getChildren().addAll(divider, scrollPane, bottomLayout);

        root.getStylesheets().add(LiteratureReviewOutput.class.getResource("litReviewStyle.css").toExternalForm());

        return root;
    }

    public void addArticlesToScreen() {
        if (fileNamesIsEmpty()) {
            useDefaultLayout();
        } else {
            removePreviousChild();
            useCustomLayout();
        }

    }

    public Boolean fileNamesIsEmpty() {
        return new FileEngine().areFilesAvailable();
    }

    public void clearFileNames() {
        new FileEngine().getFileNames().clear();
    }

    private void removePreviousChild() {
        mainContainer.getChildren().clear();
    }

    public void useDefaultLayout() {
        bottomLayout.setVisible(false);
        researchAreaValue.setText(DEFAULT_TITLE);
        removePreviousChild();
        Text article = new Text("There is limited data available to enable me construct a conceptual framework");
        article.setStyle("-fx-font-size: 20;");
        mainContainer.getChildren().add(article);
    }

    private void useCustomLayout() {
        bottomLayout.setVisible(true);
        researchAreaValue.setText(StartTab.getResearchArea());
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
    }

}
