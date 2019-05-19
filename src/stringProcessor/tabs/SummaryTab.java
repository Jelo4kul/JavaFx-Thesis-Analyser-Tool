package stringProcessor.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import stringProcessor.ThesisModel;

/**
 *
 * @author Jelo
 */
public class SummaryTab {

    private static ThesisModel thesisModel = new ThesisModel();
    private static VBox summaryContents;
    ScrollPane scrollPane = new ScrollPane();

    public VBox createSummaryPane() {

        VBox summaryPane = new VBox();
        summaryPane.setId("tab-content");

        VBox summaryContainer = createSummaryContainer();

        summaryPane.getChildren().add(summaryContainer);

        return summaryPane;
    }

    public VBox createSummaryContainer() {
        VBox sumContainer = new VBox();
        sumContainer.getChildren().addAll(createSummaryHeader(), createSummaryContents());
        return sumContainer;
    }

    public HBox createSummaryHeader() {
        HBox summaryHeader = new HBox();
        summaryHeader.setAlignment(Pos.CENTER);
        summaryHeader.setPadding(new Insets(15));
        summaryHeader.setStyle("-fx-background-color: #0c3447");

        Label labelName = new Label("SUMMARY OF THESIS TITLE");
        labelName.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: #c5d9e2");
        summaryHeader.getChildren().add(labelName);

        return summaryHeader;
    }

    public ScrollPane createSummaryContents() {
        summaryContents = new VBox(10);
        summaryContents.prefWidthProperty().bind(scrollPane.widthProperty());
        summaryContents.setPadding(new Insets(16, 5, 5, 5));
        summaryContents.setStyle("-fx-background-color: #c5d9e2");

        BorderPane titleName = createContentView("TITLE NAME");
        BorderPane researchArea = createContentView("MAIN RESEARCH AREA");
        BorderPane methodologyName = createContentView("METHODOLOGY");
        BorderPane methodName = createContentView("METHODS");
        BorderPane toolName = createContentView("TOOLS");
        summaryContents.getChildren().addAll(titleName, researchArea, methodologyName, methodName, toolName);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(summaryContents);

        return scrollPane;
    }

    public BorderPane createContentView(String name) {
        BorderPane divider = new BorderPane();
        divider.setId("divider");

        Label titleName = new Label(name);
        titleName.setPadding(new Insets(15, 15, 0, 15));
        titleName.setPrefWidth(225);
        titleName.setStyle("-fx-font-weight: bold; -fx-text-fill: #0c3447");

        divider.setLeft(titleName);

        String titleValue = "";
        switch (name) {
            case "TITLE NAME":
                titleValue = "No title";
                break;
            case "MAIN RESEARCH AREA":
                titleValue = "No Main Research Area";
                break;
            case "METHODOLOGY":
                titleValue = "No Methodology";
                break;
            case "METHODS":
                titleValue = "No Research Methods";
                break;
            case "TOOLS":
                titleValue = "No tools";
                break;
        }

        //a dummy container is used to hold the label so that it can align properly well
        BorderPane dummyContainer = new BorderPane();

        Label titleValueLabel = new Label(titleValue);
        titleValueLabel.setPadding(new Insets(15, 15, 15, 0));
        titleValueLabel.setStyle("-fx-text-fill: #0c3447; -fx-font-size:13;");
        dummyContainer.setLeft(titleValueLabel);

        divider.setCenter(dummyContainer);
        return divider;
    }

    public static void bindModelToView() {

        BorderPane outerBorderPane, innerBorderPane;

        Label titleValueLabel;

        outerBorderPane = (BorderPane) summaryContents.getChildren().get(0);
        innerBorderPane = (BorderPane) outerBorderPane.getCenter();
        titleValueLabel = (Label) innerBorderPane.getLeft();
        titleValueLabel.textProperty().bind(thesisModel.getTitleProperty());
        
        outerBorderPane = (BorderPane) summaryContents.getChildren().get(1);
        innerBorderPane = (BorderPane) outerBorderPane.getCenter();
        titleValueLabel = (Label) innerBorderPane.getLeft();
         titleValueLabel.textProperty().bind(thesisModel.getMainResearchArea());

        outerBorderPane = (BorderPane) summaryContents.getChildren().get(2);
        innerBorderPane = (BorderPane) outerBorderPane.getCenter();
        titleValueLabel = (Label) innerBorderPane.getLeft();
        titleValueLabel.textProperty().bind(thesisModel.getResearchMethodologyProperty());

        outerBorderPane = (BorderPane) summaryContents.getChildren().get(3);
        innerBorderPane = (BorderPane) outerBorderPane.getCenter();
        titleValueLabel = (Label) innerBorderPane.getLeft();
        titleValueLabel.textProperty().bind(thesisModel.getContatenatedMethodsProperty());

        outerBorderPane = (BorderPane) summaryContents.getChildren().get(4);
        innerBorderPane = (BorderPane) outerBorderPane.getCenter();
        titleValueLabel = (Label) innerBorderPane.getLeft();
        titleValueLabel.textProperty().bind(thesisModel.getContatenatedToolsProperty());

    }
}
