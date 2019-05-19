package stringProcessor.tabs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import stringProcessor.InnerPaneCreator;

/**
 *
 * @author Jelo
 */
public class BulkAnalysisTab {

    private Image image;
    private final ImageView img = new ImageView();

    public BorderPane createBorderPane() {
        BorderPane startTabContent = new BorderPane();
        startTabContent.setId("bulk-tab-content");

        StackPane divider = new StackPane();
        divider.setId("divider");
        divider.setAlignment(Pos.TOP_LEFT);
        Label inpText = new Label("Choose Analysis Method");
        inpText.setId("bulk-text");
        divider.getChildren().add(inpText);
        startTabContent.setTop(divider);

        VBox contents = new VBox(20);
        contents.setAlignment(Pos.CENTER);

        HBox divider2 = new HBox(20);
        divider2.setId("divider2");
        divider2.setAlignment(Pos.TOP_LEFT);

        ToggleGroup group = new ToggleGroup();

        RadioButton single = new RadioButton("Single Analysis");
        single.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    image = new Image(BulkAnalysisTab.class.getResourceAsStream("one-to-many.jpg"));
                    img.setImage(image);
                } else {
                    image = new Image(BulkAnalysisTab.class.getResourceAsStream("jaydee.PNG"));
                    img.setImage(image);
                }
            }
        });

        single.setToggleGroup(group);
        single.setSelected(true);
        RadioButton multiple = new RadioButton("Multiple Analysis");
        multiple.setToggleGroup(group);
        divider2.getChildren().addAll(single, multiple);

        img.setFitWidth(300);
        img.setFitHeight(300);;
        img.setImage(image);

        contents.getChildren().addAll(divider2, img);
        startTabContent.setCenter(contents);

        BorderPane footer = new BorderPane();
        footer.setPrefHeight(40);
        footer.setPrefWidth(760);
        footer.setId("footer");
        Button analyzeBtn = new Button("Next");
        analyzeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //select the next tab by index(1)
                InnerPaneCreator.getChildTabPanes()[2].getSelectionModel().select(1);
             }

        });

        footer.setRight(analyzeBtn);
        startTabContent.setBottom(footer);
        return startTabContent;
    }
}
