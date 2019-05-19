package stringProcessor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import stringProcessor.tabs.HomeResultTab;

/**
 *
 * @author Jelo
 */
public class ThesisModel {

    private static SimpleStringProperty researchMethodology = new SimpleStringProperty("No Research Methodology Found");
    private static SimpleStringProperty[] methodsArray = new SimpleStringProperty[HomeResultTab.METHODSIZE];
    private static SimpleStringProperty[] toolsArray = new SimpleStringProperty[HomeResultTab.TOOLSIZE];
    private static StringProperty theTitle = new SimpleStringProperty();
    private static StringProperty mainRArea = new SimpleStringProperty();
    private static StringProperty concatenatedMethods = new SimpleStringProperty();
    private static StringProperty concatenatedTools = new SimpleStringProperty();
    private static boolean resetMethods;

    public String getTheTitle() {
        return theTitle.get();
    }

    public void setMainResearchArea(String mainRArea) {
        ThesisModel.mainRArea.set(mainRArea);
    }

    public StringProperty getMainResearchArea() {
        return mainRArea;
    }

    public void setTitle(String thesisTitle) {
        theTitle.set(thesisTitle);
    }

    public StringProperty getTitleProperty() {
        return theTitle;
    }

    public SimpleStringProperty getResearchMethodologyProperty() {
        return researchMethodology;
    }

    public SimpleStringProperty[] getMethodsArrayProperty() {
        return methodsArray;
    }

    public SimpleStringProperty[] getToolsArrayProperty() {
        return toolsArray;
    }

    public void setResearchMethodology(String value) {
        researchMethodology.set(value);
    }

    public String getResearchMethodology() {
        return researchMethodology.get();
    }

    public void resetMethod() {
        resetMethods = true;
    }

    public void unResetMethod() {
        resetMethods = false;
    }

    public void setResearchMethods(String method, int i) {
        methodsArray[i] = new SimpleStringProperty();
        methodsArray[i].set(method);
    }

    public String getResearchMethods(int i) {
        if (i < HomeResultTab.METHODSIZE) {
            System.out.println(methodsArray[i]);
            if (methodsArray[i] == null) {
                return "";
            } else {
                return methodsArray[i].get();
            }
        } else {
            return null;
        }
    }

    public void setContatenatedMethods(String method) {
        if (method == null) {
            concatenatedMethods.set("No Research Methods");
        } else {
            String concatMethod = "";
            for (int i = 0; i < HomeResultTab.METHODSIZE; i++) {
                concatMethod += getResearchMethods(i) + ", ";
            }
            concatenatedMethods.set(concatMethod);
        }

    }

    public String getContatenatedMethods() {
        return concatenatedMethods.get();
    }

    public StringProperty getContatenatedMethodsProperty() {
        return concatenatedMethods;
    }

    public void setContatenatedTools(String tool) {
        if (tool == null) {
            concatenatedTools.set("No Research Tools");
        } else {
            String concatTool = "";
            for (int i = 0; i < HomeResultTab.TOOLSIZE; i++) {
                concatTool += getResearchTools(i) + ", ";
            }
            concatenatedTools.set(concatTool);
        }

    }

    public String getContatenatedTools() {
        return concatenatedTools.get();
    }

    public StringProperty getContatenatedToolsProperty() {
        return concatenatedTools;
    }

    public void setResearchTools(String method, int i) {
        toolsArray[i] = new SimpleStringProperty();
        toolsArray[i].set(method);
    }

    public String getResearchTools(int i) {
        if (i < HomeResultTab.TOOLSIZE) {
            if (toolsArray[i] == null) {
                return "";
            } else {
                return toolsArray[i].get();
            }
        } else {
            return null;
        }
    }

}
