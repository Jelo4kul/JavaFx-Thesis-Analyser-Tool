package stringProcessor.tabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jelo
 */
public class ThesisListTab {

    public BorderPane createBorderPane() {
        BorderPane startTabContent = new BorderPane();
        startTabContent.setId("bulk-tab-content");

        StackPane divider = new StackPane();
        divider.setId("divider");
        divider.setAlignment(Pos.TOP_LEFT);
        
        Label inpText = new Label("Load Your Thesis Here");
        inpText.setId("bulk-text");
        
        divider.getChildren().add(inpText);
        startTabContent.setTop(divider);

        VBox contents = new VBox(20);
        contents.setAlignment(Pos.TOP_RIGHT);

        HBox divider2 = new HBox();
        divider2.setId("divider2");
        divider2.setAlignment(Pos.TOP_LEFT);

        // ScrollPane scrlPane=new ScrollPane();
        FlowPane flowPane = new FlowPane();
        TextArea tArea = new TextArea();
        tArea.setId("text-area");
        tArea.setPrefWidth(650);
        tArea.setPrefHeight(20);
        flowPane.getChildren().add(tArea);

        divider2.getChildren().add(flowPane);

        HBox btnBox = new HBox(15);

        Button addBtn = new Button("+");

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HBox flowPane2 = new HBox(10);
                flowPane2.setId("flow-pane");
                TextArea tArea = new TextArea();
                tArea.setId("text-area");
                tArea.setPrefWidth(650);
                tArea.setPrefHeight(20);
                

                Button subBtn = new Button("-");
                subBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    tArea.setVisible(false);
                  //  flowPane.getChildren().remove(tArea);
                    }

                });

                flowPane2.getChildren().addAll(tArea, subBtn);

                flowPane.getChildren().add(flowPane2);
            }

        });

        Button clear = new Button("clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                flowPane.getChildren().clear();
            }

        });

        btnBox.getChildren().addAll(addBtn, clear);

        contents.getChildren().addAll(divider2, btnBox);

        startTabContent.setCenter(contents);

        return startTabContent;
    }
}
