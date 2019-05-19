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
import stringProcessor.InnerPaneCreator;
/**
 *
 * @author Jelo
 */
public class LiteratureReviewOutput extends Application {

    private VBox root = new VBox();
    private ScrollPane scrollPane = new ScrollPane();

    @Override
    public void start(Stage primaryStage) {

        VBox divider = new VBox();
        divider.setId("divider");

        Text inpText = new Text("Local Search Results");
        inpText.setId("header-text");

        HBox researchAreaLayout = new HBox(10);
        researchAreaLayout.setId("header-layout");
        researchAreaLayout.setAlignment(Pos.CENTER);
        Text researchAreaText = new Text("Main Research Area : ");
        researchAreaText.setId("lit-review-output");
        Text researchAreaValue = new Text("Human Computerization");
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

        VBox mainContainer = new VBox();
        mainContainer.setMinWidth(860);

        for (int i = 1; i < 13; i++) {
            Text number = new Text("1.");
            number.setId("number");

            Text article = new Text("History on the Development of a game");
            article.setId("article");

            Button view = new Button("Read");
            view.setId("button");

            BorderPane rowLayout = new BorderPane();
            rowLayout.setId("row-layout");
            rowLayout.setLeft(number);
            rowLayout.setCenter(article);
            rowLayout.setRight(view);

            VBox divider2 = new VBox();
            divider2.setId("divider");
            divider2.getChildren().add(rowLayout);

            mainContainer.getChildren().add(divider2);

        }

        scrollPane.setContent(mainContainer);

        VBox bottomLayout = new VBox(20);
        bottomLayout.setPadding(new Insets(20));
        bottomLayout.setAlignment(Pos.CENTER);
        Text satisfactoryText = new Text("Satisfied? Go to System Architecture");
        satisfactoryText.setId("lit-review-output");
        Button systemArchBtn = new Button("System Architecture");
        systemArchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                InnerPaneCreator.getChildTabPanes()[2].getSelectionModel().select(2);
            }

        });
        systemArchBtn.setId("button");
        bottomLayout.getChildren().addAll(satisfactoryText, systemArchBtn);

        root.setId("base-container");
        root.getChildren().addAll(divider, scrollPane, bottomLayout);

        Scene scene = new Scene(root, 920, 500);
        
        //attaching the css file to the scene
        scene.getStylesheets().add(LiteratureReviewOutput.class.getResource("litReviewStyle.css").toExternalForm());

        //primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
