package stringProcessor;

import stringProcessor.tabs.ArchDevelopmentTab;
import stringProcessor.utility.TabsCreator;
import stringProcessor.tabs.StartTab;

import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import stringProcessor.tabs.DesignAndAnalysisTab;
import stringProcessor.tabs.ConceptualFrameworkTab;
import stringProcessor.tabs.HomeResultTab;
import stringProcessor.tabs.KeywordFinderTab;
import stringProcessor.tabs.SummaryTab;
import stringProcessor.tabs.SystemEvaluationTab;

/**
 *
 * @author Jelo
 */
public class InnerPaneCreator extends TabsCreator {

    private AnchorPane parentTabLayout;
    private static TabPane childTabpane;
    private static final TabPane[] allChildTabPanes = new TabPane[3];

    
    public AnchorPane createhomePane() {

        return createGeneralPane("Start",
                "Conceptual Framework", 
                "System Architecture Development", 
                "Design and Analysis", 
                "System Build and Evaluation",
                new StartTab().createBorderPane(),
                new ConceptualFrameworkTab().createBorderPane(),  
                new ArchDevelopmentTab().createLayout(),
                new DesignAndAnalysisTab().createLayout(),
                new SystemEvaluationTab().createLayout(),
                2,
                true);
    }
  
    public AnchorPane createkeyWordPane() {

        return createGeneralPane("Keywords",
                null,
                null,
                null,
                null, 
                new KeywordFinderTab().createKeywordPane(), 
                null, 
                null, 
                null, 
                null, 
                1, 
                false);

    }

    //creates the pane for the bulk analysis
      public AnchorPane createBulkAnalysisPane() {

        return createGeneralPane("Result", "Summary", null,
                null,
                null,
                new HomeResultTab().createBorderPane(),
                new SummaryTab().createSummaryPane(),
                null,
                null,
                null,
                0,
                false);

    }


    public static TabPane[] getChildTabPanes() {
        return allChildTabPanes;
    }

    public AnchorPane createGeneralPane(String s1, String s2, String s3, String s4, String s5, Node n1, Node n2, Node n3, Node n4, Node n5, int i, boolean isBulkAnalysis) {

        //this is the root Node that holds a tabPane
        parentTabLayout = new AnchorPane();

        //this is a tabPane that holds all the tabs horizontally on a particular screen
        childTabpane = new TabPane();

        //a check to know if bulkanalysis pane is being called. If this is true a custom tab pane would be used
        if (isBulkAnalysis) {
            childTabpane.setId("bulkanalysis-tab-pane");
        } else {
            childTabpane.setId("inner-tab-pane");
        }

        childTabpane.prefWidthProperty().bind(parentTabLayout.prefWidthProperty());

        //here the tabs to be added to the tabPane are created
        createTabs(s1, s2, s3, s4, s5);

        //we assign layouts for each tab
        setTabLayout(n1, n2, n3, n4, n5);

        //a check is required to determine the amount of tabs that are to be added to the tabpane
        switch (i) {
            case 0:
                childTabpane.getTabs().addAll(getTabNames().subList(0, getTabNames().size() - 3));
                break;
            case 1:
                childTabpane.getTabs().addAll(getTabNames().subList(0, getTabNames().size() - 4));
                break;
            default:
                childTabpane.getTabs().addAll(getTabNames());
                
        }

        //adding each childTabPane created to an array, so that it can be used later on
        allChildTabPanes[i] = childTabpane;

        parentTabLayout.getChildren().add(childTabpane);

        return parentTabLayout;
    }

}
