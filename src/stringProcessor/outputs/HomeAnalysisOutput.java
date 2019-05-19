package stringProcessor.outputs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stringProcessor.utility.FileEngine;
import stringProcessor.InnerPaneCreator;
import stringProcessor.MainMenu;
import stringProcessor.database.DBRecordRetriever;
import stringProcessor.tabs.ConceptualFrameworkTab;
import static stringProcessor.tabs.StartTab.getResearchArea;

/**
 *
 * @author Jelo
 */
public class HomeAnalysisOutput extends Application {

    private VBox root = new VBox();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Scene scene = new Scene(createLayout(), 600, 350);
        scene.getStylesheets().add(MainMenu.class.getResource("processStyle.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }

    public VBox createLayout() {
        VBox centerLayout = new VBox(20);

        centerLayout.setPrefHeight(200);
        centerLayout.setMaxWidth(600);
        centerLayout.setId("result-tab");
        centerLayout.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);"); // Shadow effect

        StackPane divider = new StackPane();
        divider.setId("result-divider");
        divider.setAlignment(Pos.CENTER);

        Label methodologyTitle = new Label("Search completed");
        methodologyTitle.setId("result-text");
        methodologyTitle.setAlignment(Pos.TOP_CENTER);

        divider.getChildren().add(methodologyTitle);

        VBox centerPanel = new VBox(40);
        centerPanel.setPadding(new Insets(20));

        VBox leftSide = new VBox(10);

        Label keywordLabel = new Label("Click, to see the keywords used for the search");
        keywordLabel.setId("home-result-output-text");

        Button keywordBtn = new Button("Keywords");
        keywordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                new MainMenu().getTabPane().getSelectionModel().select(1);
            }

        });

        leftSide.getChildren().addAll(keywordLabel, keywordBtn);

        VBox rightSide = new VBox(10);

        Label conceptFrameWork = new Label("Construct a conceptual framework");
        conceptFrameWork.setId("home-result-output-text");

        Button conceptFrameWorkBtn = new Button("Conceptual Framework");
        conceptFrameWorkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                try {

                    DBRecordRetriever.retrieveArticlePaths(getResearchArea());
     
                    //FileEngine class is used to locate and fetch the articles stored in the db
                    FileEngine fileEngine = new FileEngine();
                    fileEngine.getFileNames().clear();

                    //get the corresponding articles needed for the search
                    fileEngine.extractFileNames();

                    InnerPaneCreator.getChildTabPanes()[2].getSelectionModel().select(1);
                    new ConceptualFrameworkTab().addArticlesToScreen();
                } catch (Exception e) {
                   e.printStackTrace();
                }

            }
        });

        rightSide.getChildren().addAll(conceptFrameWork, conceptFrameWorkBtn);

        centerPanel.getChildren().addAll(leftSide, rightSide);

        centerPanel.setAlignment(Pos.CENTER);

        centerLayout.getChildren().addAll(divider, centerPanel);

        return centerLayout;
    }
}
