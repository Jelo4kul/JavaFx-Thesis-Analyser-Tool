package stringProcessor.tabs;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import stringProcessor.utility.FileEngine;
import stringProcessor.database.DBRecordRetriever;
import stringProcessor.outputs.ArchitectureOutput;
/**
 *
 * @author Jelo
 */
public class ArchDevelopmentTab {

    private VBox root = new VBox();
    private ArchitectureOutput archOutput;

    public VBox createLayout() {

        root.setAlignment(Pos.CENTER);

        VBox divider = new VBox();
        divider.setId("divider");
        divider.setAlignment(Pos.TOP_CENTER);

        Text inpText = new Text("Select an architecture");
        inpText.setId("bulk-text");

        VBox centerLayout = new VBox(20);
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.setPrefHeight(400);
        centerLayout.setMaxWidth(600);
        centerLayout.setId("archdev-tab");
        centerLayout.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);"); // Shadow effect

        HBox topLayer = new HBox(20);
        topLayer.setAlignment(Pos.CENTER);
        VBox oneLayerBox = new VBox(5);
        oneLayerBox.setAlignment(Pos.CENTER);
        oneLayerBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("OneTierArchitecture");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                //get the corresponding articles needed for the search
                new FileEngine().extractFileNames();
                archOutput = new ArchitectureOutput();
                archOutput.setArchType("One Layer Architecture");
                archOutput.start(new Stage());
            }

        });
        Text oneLogo = new Text("1");
        oneLogo.setId("arch-dev-logo-text");
        Text oneLayerArchitecture = new Text("One Layer Architecture");
        oneLayerArchitecture.setId("bulk-text");
        oneLayerBox.getChildren().addAll(oneLogo, oneLayerArchitecture);

        StackPane topVSeparator = new StackPane();
        topVSeparator.setId("vertical-divider");

        VBox twoLayerBox = new VBox(5);
        twoLayerBox.setId("arch-dev-box");
        twoLayerBox.setAlignment(Pos.CENTER);
        twoLayerBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("TwoTierArchitecture");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                fileEngine.extractFileNames();
                archOutput = new ArchitectureOutput();
                archOutput.setArchType("Two Layer Architecture");
                archOutput.start(new Stage());
            }

        });
        Text twoLogo = new Text("2");
        twoLogo.setId("arch-dev-logo-text");
        Text twoLayerArchitecture = new Text("Two Layer Architecture");
        twoLayerArchitecture.setId("bulk-text");
        twoLayerBox.getChildren().addAll(twoLogo, twoLayerArchitecture);

        topLayer.getChildren().addAll(oneLayerBox, topVSeparator, twoLayerBox);

        StackPane separator = new StackPane();
        separator.setId("divider");

        HBox bottomLayer = new HBox(20);
        bottomLayer.setAlignment(Pos.CENTER);
        VBox threeLayerBox = new VBox(5);
        threeLayerBox.setPadding(new Insets(10, 0, 0, 0));
        threeLayerBox.setAlignment(Pos.CENTER);
        threeLayerBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("ThreeTierArchitecture");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                new FileEngine().extractFileNames();
                archOutput = new ArchitectureOutput();
                archOutput.setArchType("Three Layer Architecture");
                archOutput.start(new Stage());
            }

        });
        Text threeLogo = new Text("3");
        threeLogo.setId("arch-dev-logo-text");
        Text threeLayerArchitecture = new Text("Three Layer Architecture");
        threeLayerArchitecture.setId("bulk-text");
        threeLayerBox.getChildren().addAll(threeLogo, threeLayerArchitecture);

        StackPane bottomVSeparator = new StackPane();
        bottomVSeparator.setId("bottom-vertical-divider");

        VBox nLayerBox = new VBox(5);
        nLayerBox.setPadding(new Insets(10, 24, 0, 30));
        nLayerBox.setAlignment(Pos.CENTER);
        nLayerBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("NTierArchitecture");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                new FileEngine().extractFileNames();
                archOutput = new ArchitectureOutput();
                archOutput.setArchType("N Layer Architecture");
                archOutput.start(new Stage());
            }

        });
        Text nLogo = new Text("N");
        nLogo.setId("arch-dev-logo-text");
        Text nLayerArchitecture = new Text("N Layer Architecture");
        nLayerArchitecture.setId("bulk-text");
        nLayerBox.getChildren().addAll(nLogo, nLayerArchitecture);

        bottomLayer.getChildren().addAll(threeLayerBox, bottomVSeparator, nLayerBox);

        centerLayout.getChildren().addAll(topLayer, separator, bottomLayer);

        divider.getChildren().add(inpText);

        root.getChildren().addAll(divider, centerLayout);

        return root;
    }
}
