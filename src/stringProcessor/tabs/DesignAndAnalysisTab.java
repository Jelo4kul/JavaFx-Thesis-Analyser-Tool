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
import stringProcessor.database.DBRecordRetriever;
import stringProcessor.utility.FileEngine;
import stringProcessor.outputs.SystemAnalysisAndDesignOutput;

/**
 *
 * @author Jelo
 */
public class DesignAndAnalysisTab {

    private VBox root = new VBox();
    private SystemAnalysisAndDesignOutput analysisOutput;

    public VBox createLayout() {

        root.setAlignment(Pos.CENTER);

        VBox divider = new VBox();
        divider.setId("divider");
        divider.setAlignment(Pos.TOP_CENTER);

        Text inpText = new Text("Methodologies");
        inpText.setId("bulk-text");

        VBox centerLayout = new VBox(20);
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.setPrefHeight(400);
        centerLayout.setMaxWidth(400);
        centerLayout.setId("archdev-tab");
        centerLayout.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);"); // Shadow effect

        HBox topLayer = new HBox(20);
        topLayer.setPadding(new Insets(0, 50, 0, 50));
        topLayer.setAlignment(Pos.CENTER);
        VBox oneLayerBox = new VBox(5);
        oneLayerBox.setAlignment(Pos.CENTER);
        oneLayerBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DBRecordRetriever.retrieveArticlePaths("SSADM");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                new FileEngine().extractFileNames();
                new SystemAnalysisAndDesignOutput().setMethodologyType("SSADM");
                analysisOutput = new SystemAnalysisAndDesignOutput();
                analysisOutput.setAnalysisType("Structured System Analysis and Design Methodology");
                analysisOutput.start(new Stage());
            }

        });
        Text oneLogo = new Text("SSADM");
        oneLogo.setId("arch-dev-logo-text");
        Text oneLayerArchitecture = new Text("Structured System Analysis and Design Methodology");
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
                DBRecordRetriever.retrieveArticlePaths("OOADM");
                FileEngine fileEngine = new FileEngine();
                fileEngine.getFileNames().clear();
                new FileEngine().extractFileNames();
                new SystemAnalysisAndDesignOutput().setMethodologyType("OOADM");
                analysisOutput = new SystemAnalysisAndDesignOutput();
                analysisOutput.setAnalysisType("Object Oriented Analysis and Design Methodology");
                analysisOutput.start(new Stage());
            }

        });
        Text twoLogo = new Text("OOADM");
        twoLogo.setId("arch-dev-logo-text");
        Text twoLayerArchitecture = new Text("Object Oriented Analysis and Design Methodology");
        twoLayerArchitecture.setId("bulk-text");
        twoLayerBox.getChildren().addAll(twoLogo, twoLayerArchitecture);

        topLayer.getChildren().addAll(oneLayerBox, topVSeparator, twoLayerBox);

        centerLayout.getChildren().add(topLayer);

        divider.getChildren().add(inpText);
        divider.setId("divider");
        divider.setAlignment(Pos.TOP_CENTER);

        root.getChildren().addAll(divider, centerLayout);

        //primaryStage.setTitle("Hello World!");
        return root;
    }

}
