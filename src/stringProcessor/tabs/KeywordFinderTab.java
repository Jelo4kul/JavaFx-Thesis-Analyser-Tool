package stringProcessor.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import stringProcessor.InnerPaneCreator;
import stringProcessor.MainMenu;

/**
 *
 * @author Jelo
 */
public class KeywordFinderTab {

    static ObservableList<String> kywdList = FXCollections.observableArrayList();
    static VBox keywordContents = new VBox(10);
    private ScrollPane scrollPane = new ScrollPane();
    private static int count;

    public void clearKeywordList() {
        kywdList.clear();
    }

    public void resetKeywordsLayout() {
        BorderPane kywdName = createContentView("No keyword found");
        keywordContents.getChildren().clear();
        keywordContents.getChildren().add(kywdName);
        scrollPane.setPrefHeight(400);
        count = 1;
    }

    public VBox createKeywordPane() {
        VBox keywordPane = new VBox();
        keywordPane.setPrefWidth(600);
        keywordPane.setId("tab-content");

        VBox keywordContainer = createKeywordContainer();

        keywordPane.getChildren().add(keywordContainer);

        return keywordPane;
    }

    private VBox createKeywordContainer() {
        VBox keywordContainer = new VBox();
        keywordContainer.getChildren().addAll(createKeywordHeader(), createKeywordContents());
        return keywordContainer;
    }

    private HBox createKeywordHeader() {
        HBox keywdHeader = new HBox();
        keywdHeader.setAlignment(Pos.CENTER);
        keywdHeader.setPadding(new Insets(15));
        keywdHeader.setStyle("-fx-background-color: #0c3447");

        Label labelName = new Label("KEYWORDS EXTRACTED FROM THESIS TITLE");
        labelName.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: #c5d9e2");
        keywdHeader.getChildren().add(labelName);

        return keywdHeader;
    }

    private VBox createKeywordContents() {

        VBox contentBox = new VBox(15);
        scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setMaxHeight(400);
        keywordContents.prefWidthProperty().bind(scrollPane.widthProperty());
        keywordContents.setPadding(new Insets(16, 5, 5, 5));
        keywordContents.setStyle("-fx-background-color: #c5d9e2");

        addKeywordToPane();

        scrollPane.setContent(keywordContents);

        Button conceptFrameworkBtn = new Button("Go to ConceptualFramework");
        conceptFrameworkBtn.setId("button");
        conceptFrameworkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InnerPaneCreator.getChildTabPanes()[2].getSelectionModel().select(1);
                new MainMenu().getTabPane().getSelectionModel().select(0);
            }
        });

        contentBox.getChildren().addAll(scrollPane, conceptFrameworkBtn);
        return contentBox;
    }

    public void addKeywordToPane() {

        if (kywdList.isEmpty()) {
            resetKeywordsLayout();
        }

        for (int i = 0; i < kywdList.size(); i++) {
            //we want to remove "No Keyword found" from the list because the 
            //resetKeywordsLayout() function above set count to 1
            if (count == 1) {
                keywordContents.getChildren().remove(0);
                count++;
            }
            BorderPane kywdName = createContentView(kywdList.get(i));
            keywordContents.getChildren().add(kywdName);
        }

    }

    private BorderPane createContentView(String name) {
        BorderPane divider = new BorderPane();
        divider.setId("divider");

        Label titleName = new Label(name);
        titleName.setPadding(new Insets(15, 15, 0, 15));
        titleName.setPrefWidth(225);
        titleName.setStyle("-fx-font-weight: bold; -fx-text-fill: #0c3447");

        divider.setLeft(titleName);
        return divider;
    }

}
