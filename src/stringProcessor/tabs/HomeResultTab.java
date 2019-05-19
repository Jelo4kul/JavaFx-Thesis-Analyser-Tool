package stringProcessor.tabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import stringProcessor.InnerPaneCreator;
import stringProcessor.ResultsTransition;
import stringProcessor.ThesisModel;

/**
 *
 * @author Jelo
 */
public class HomeResultTab {

    private static VBox methodologyLayout, methodsLayout, toolsLayout;
    private Label methNameLabel;
    static int amtOfMethods, amtOfTools;
    private VBox methodName;
    private VBox toolName;
    private static int previousElementDisplayed;
    public static final int METHODSIZE = 4;
    public static final int TOOLSIZE = 4;
    private ThesisModel thesisModel = new ThesisModel();
    private static Button researchMethodologyBtn, researchmethodsBtn, researchToolBtn, summaryBtn;

    private static StackPane centerLayoutPane = new StackPane();

    public void makeMethodologyBtnVisible() {
        researchMethodologyBtn.setVisible(true);
    }

    public void makeToolsBtnVisible() {
        researchToolBtn.setVisible(true);
    }

    public void makeMethodsBtnVisible() {
        researchmethodsBtn.setVisible(true);
    }

    public void makeSummaryBtnVisible() {
        summaryBtn.setVisible(true);
    }

    public void deleteMethodologyBtn() {
        researchMethodologyBtn.setVisible(false);
    }

    public void deleteToolsBtn() {
        researchToolBtn.setVisible(false);
    }

    public void deleteMethodsBtn() {
        researchmethodsBtn.setVisible(false);
    }

    public void deleteSummaryBtn() {
        summaryBtn.setVisible(false);
    }

    public void displayMethodologyLayout() {
        ResultsTransition rT = new ResultsTransition();

        Node elementOnTopOfStack = centerLayoutPane.getChildren().get(previousElementDisplayed);
        rT.fadeOutTransition(elementOnTopOfStack);
        rT.translateOutTransition(elementOnTopOfStack);

        rT.fadeInTransition(methodologyLayout);
        rT.translateInTransition(methodologyLayout);
        rT.parallelTransition(elementOnTopOfStack, methodologyLayout);
        previousElementDisplayed = centerLayoutPane.getChildren().indexOf(methodologyLayout);
    }

    public void displayMethodsLayout() {
        ResultsTransition rT = new ResultsTransition();
        Node elementOnTopOfStack = centerLayoutPane.getChildren().get(previousElementDisplayed);

        if (!centerLayoutPane.getChildren().contains(methodsLayout)) {
            centerLayoutPane.getChildren().add(methodsLayout);
        }
        rT.fadeOutTransition(elementOnTopOfStack);
        rT.translateOutTransition(elementOnTopOfStack);
        rT.fadeInTransition(methodsLayout);
        rT.translateInTransition(methodsLayout);
        rT.parallelTransition(elementOnTopOfStack, methodsLayout);
        previousElementDisplayed = centerLayoutPane.getChildren().indexOf(methodsLayout);

    }

    public void displayToolsLayout() {
        ResultsTransition rT = new ResultsTransition();

        Node elementOnTopOfStack = centerLayoutPane.getChildren().get(previousElementDisplayed);
        if (!centerLayoutPane.getChildren().contains(toolsLayout)) {
            centerLayoutPane.getChildren().add(toolsLayout);
        }
        System.out.println(elementOnTopOfStack.toString());
        rT.fadeOutTransition(elementOnTopOfStack);
        rT.translateOutTransition(elementOnTopOfStack);
        rT.fadeInTransition(toolsLayout);
        rT.translateInTransition(toolsLayout);
        rT.parallelTransition(elementOnTopOfStack, toolsLayout);
        previousElementDisplayed = centerLayoutPane.getChildren().indexOf(toolsLayout);
    }

    public VBox createBorderPane() {
        VBox resultTabContent = new VBox(30);
        resultTabContent.setId("tab-content");
        resultTabContent.setAlignment(Pos.CENTER);

        StackPane divider = new StackPane();
        divider.setId("divider");
        divider.setAlignment(Pos.TOP_LEFT);

        Label inpText = createLabel("Detected Results");
        divider.getChildren().add(inpText);

        methodologyLayout = createMethodologyLayout("The Methodology for the research is ...", "No Research Methodology Found");
        methodologyLayout.setPadding(new Insets(10));

        methodsLayout = createMethodsLayout("The Methods for this research are ...", "No Research Methods Found");
        methodsLayout.setPadding(new Insets(10));

        toolsLayout = createToolsLayout("The Tools required for the research title are ...", "No Research Tool Found");
        toolsLayout.setPadding(new Insets(10));

        centerLayoutPane.getChildren().add(methodologyLayout);

        HBox footer = new HBox(20);
        footer.setId("opt-footer");

        footer.setAlignment(Pos.CENTER);

        researchMethodologyBtn = new Button("View Research Methodology");
        researchMethodologyBtn.setId("result-button");
        researchMethodologyBtn.setVisible(false);
        researchMethodologyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                displayMethodologyLayout();

            }
        });

        researchmethodsBtn = new Button("View Research Methods");
        researchmethodsBtn.setVisible(false);
        researchmethodsBtn.setId("result-button");
        researchmethodsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                makeMethodologyBtnVisible();
                makeToolsBtnVisible();

                int j = 0;
                for (int i = 0; i < amtOfMethods; i++) {
                    HBox methContainer = (HBox) methodName.getChildren().get(i);
                    Label nameOfMethod = (Label) methContainer.getChildren().get(1);
                    nameOfMethod.textProperty().bind(thesisModel.getMethodsArrayProperty()[j]);
                    System.out.println(j);
                    j++;

                }

                displayMethodsLayout();
            }
        });

        researchToolBtn = new Button("View Research Tool");
        researchToolBtn.setVisible(false);
        researchToolBtn.setId("result-button");
        researchToolBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                makeSummaryBtnVisible();

                int j = 0;
                for (int i = 0; i < amtOfTools; i++) {
                    HBox methContainer = (HBox) toolName.getChildren().get(i);
                    Label nameOfMethod = (Label) methContainer.getChildren().get(1);
                    nameOfMethod.textProperty().bind(thesisModel.getToolsArrayProperty()[j]);
                    j++;

                }

                displayToolsLayout();

            }
        });

        summaryBtn = new Button("View Summary");
        summaryBtn.setVisible(false);
        summaryBtn.setId("result-button");
        summaryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InnerPaneCreator.getChildTabPanes()[0].getSelectionModel().select(1);
            }

        });
        HBox.setMargin(footer, new Insets(110, 0, 0, 0));
        footer.getChildren().addAll(researchMethodologyBtn, researchmethodsBtn, researchToolBtn, summaryBtn);

        //resultTabContent.setBottom(footer);
        resultTabContent.getChildren().addAll(divider, centerLayoutPane, footer);

        return resultTabContent;
    }

    public Label createLabel(String text) {
        Label inpText = new Label(text);
        inpText.setId("bulk-text");
        return inpText;
    }

    public VBox createMethodsLayout(String methoTitle, String methName) {

        VBox centerLayout = new VBox(20);

        centerLayout.setMaxWidth(600);
        centerLayout.setId("method-tab");
        centerLayout.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);"); // Shadow effect

        StackPane divider = new StackPane();
        divider.setId("result-divider");
        divider.setAlignment(Pos.CENTER);

        Label methodTitle = new Label(methoTitle);
        methodTitle.setId("result-text");
        methodTitle.setAlignment(Pos.TOP_CENTER);

        divider.getChildren().add(methodTitle);

        methodName = new VBox(5);
        methodName.setPadding(new Insets(20));
        methodName.setAlignment(Pos.CENTER);

        for (int i = 0; i < METHODSIZE; i++) {
            HBox methodContainer = new HBox(15);
            methodContainer.setAlignment(Pos.CENTER);

            Circle bullet = new Circle();
            bullet.setRadius(5);

            Label nameOfMethod = new Label("");
            nameOfMethod.setId("result-method-text");

            methodContainer.getChildren().addAll(bullet, nameOfMethod);

            methodName.getChildren().add(methodContainer);
        }

        centerLayout.getChildren().addAll(divider, methodName);

        return centerLayout;

    }

    public VBox createMethodologyLayout(String methoTitle, String methName) {
        VBox centerLayout = new VBox(20);

        centerLayout.setPrefHeight(200);
        centerLayout.setMaxWidth(600);
        centerLayout.setId("result-tab");
        centerLayout.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);"); // Shadow effect

        StackPane divider = new StackPane();
        divider.setId("result-divider");
        divider.setAlignment(Pos.CENTER);

        Label methodologyTitle = new Label(methoTitle);
        methodologyTitle.setId("result-text");
        methodologyTitle.setAlignment(Pos.TOP_CENTER);

        divider.getChildren().add(methodologyTitle);

        HBox methodologyName = new HBox();
        methodologyName.setPadding(new Insets(20));

        methNameLabel = new Label(methName);
        methNameLabel.textProperty().bind(thesisModel.getResearchMethodologyProperty());
        methNameLabel.setId("result-name-text");

        methodologyName.getChildren().add(methNameLabel);

        methodologyName.setAlignment(Pos.CENTER);

        centerLayout.getChildren().addAll(divider, methodologyName);

        return centerLayout;
    }

    private VBox createToolsLayout(String toolTitle, String no_Research_Methods_Found) {
        VBox centerLayout = new VBox(20);

        //centerLayout.setPrefHeight(400);
        centerLayout.setMaxWidth(600);
        centerLayout.setId("tool-tab");
        centerLayout.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);"); // Shadow effect

        StackPane divider = new StackPane();
        divider.setId("result-divider");
        divider.setAlignment(Pos.CENTER);

        Label tlTitle = new Label(toolTitle);
        tlTitle.setId("result-text");
        tlTitle.setAlignment(Pos.TOP_CENTER);

        divider.getChildren().add(tlTitle);

        toolName = new VBox(5);
        toolName.setPadding(new Insets(20));
        toolName.setAlignment(Pos.CENTER);

        for (int i = 0; i < TOOLSIZE; i++) {
            HBox method = new HBox(15);
            method.setAlignment(Pos.CENTER);

            Circle bullet = new Circle();
            bullet.setRadius(5);

            Label nameOfMethod = new Label("");
            nameOfMethod.setId("result-method-text");

            method.getChildren().addAll(bullet, nameOfMethod);

            toolName.getChildren().add(method);
        }

        centerLayout.getChildren().addAll(divider, toolName);

        return centerLayout;

    }
}
