package stringProcessor.utility;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 *
 * @author Jelo
 */
public abstract class TabsCreator {

    private Tab tabHeader1, tabHeader2, tabHeader3, tabHeader4, tabHeader5;

    //creates three tabs
    public void createTabs(String tab1, String tab2, String tab3, String tab4, String tab5) {
        tabHeader1 = new Tab();
        tabHeader1.setClosable(false);
        tabHeader1.setText(tab1);

        tabHeader2 = new Tab();
        tabHeader2.setClosable(false);
        tabHeader2.setText(tab2);

        tabHeader3 = new Tab();
        tabHeader3.setClosable(false);
        tabHeader3.setText(tab3);

        tabHeader4 = new Tab();
        tabHeader4.setClosable(false);
        tabHeader4.setText(tab4);

        tabHeader5 = new Tab();
        tabHeader5.setClosable(false);
        tabHeader5.setText(tab5);
    }

    //this sets the layout of each tab
    public void setTabLayout(Node node1, Node node2, Node node3, Node node4, Node node5) {
        tabHeader1.setContent(node1);
        tabHeader2.setContent(node2);
        tabHeader3.setContent(node3);
        tabHeader4.setContent(node4);
        tabHeader5.setContent(node5);
    }

    public ArrayList<Tab> getTabNames() {

        ArrayList<Tab> tabs = new ArrayList<>();

        tabs.add(tabHeader1);

        tabs.add(tabHeader2);

        tabs.add(tabHeader3);

        tabs.add(tabHeader4);

        tabs.add(tabHeader5);
        return tabs;
    }

}
