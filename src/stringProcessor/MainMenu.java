package stringProcessor;
import stringProcessor.tabs.OptionTab;
import stringProcessor.database.DBRecordRetriever;
import java.sql.Connection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

    private Connection con;
    private static TabPane tabPane = new TabPane();

    @Override
    public void start(Stage primaryStage) {
        
        new DBRecordRetriever().retrieveArticlePath();

        VBox root = new VBox();

        //create the headerLayout
        BorderPane header = createHeader();

        //create our centerpanel
        StackPane centerPanel = createCenterPanel();
        centerPanel.prefWidthProperty().bind(primaryStage.widthProperty());
        centerPanel.prefHeightProperty().bind(primaryStage.heightProperty());

        //add all the created layout to the root layout
        root.getChildren().addAll(header, centerPanel);

        //add the root layout to the scene
        Scene scene = new Scene(root, 1000, 620);

        //attaching the css file to the scene
        scene.getStylesheets().add(MainMenu.class.getResource("processStyle.css").toExternalForm());

        //attaching the scene to the stage
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //method that creates the header panel
    public BorderPane createHeader() {
        BorderPane header = new BorderPane();
        header.setId("header");

        HBox hTitle = new HBox(12);
        hTitle.setPrefHeight(56);
        hTitle.setPadding(new Insets(23));

        Text title = new Text("RESEARCH SUPPORT TOOL");
        title.getStyleClass().add("htextTitle");
        hTitle.getChildren().add(title);

        header.setLeft(hTitle);

        return header;
    }

    //layout that contains the center elements
    public StackPane createCenterPanel() {

        //in the centerpanel we have a tabpane that contains four tabas vertically
        StackPane centerPanel = new StackPane();

        //the tabpane that holds all tabs horizontally      
        tabPane.setId("nav-tab-pane");
        tabPane.prefHeightProperty().bind(centerPanel.prefHeightProperty());
        tabPane.setSide(Side.LEFT);

        //The first tab
        Tab homeTab = new Tab();
        homeTab.setClosable(false);
        homeTab.setText("Home");
        homeTab.setContent(new InnerPaneCreator().createhomePane());

        //The second tab
        Tab keywordTab = new Tab();
        keywordTab.setClosable(false);
        keywordTab.setText("Keyword Finder");
        keywordTab.setContent(new InnerPaneCreator().createkeyWordPane());

        //The third tab
        Tab furtherAnalysisTab = new Tab();
        furtherAnalysisTab.setClosable(false);
        furtherAnalysisTab.setText("Further Analysis");
        furtherAnalysisTab.setContent(new InnerPaneCreator().createBulkAnalysisPane());

        //The fourth tab
        Tab optionTab = new Tab();
        optionTab.setClosable(false);
        optionTab.setText("Option");
        optionTab.setContent(new OptionTab().createPane());

        tabPane.getTabs().addAll(homeTab, keywordTab, furtherAnalysisTab, optionTab);
        centerPanel.getChildren().add(tabPane);

        return centerPanel;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
