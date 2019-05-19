package stringProcessor.utility;

import java.util.ArrayList;

/**
 *
 * @author Jelo
 */
public class ResearchAreaGrouper {

    private final ArrayList<String> theoriticalMethodology = new ArrayList<>(5);
    private final ArrayList<String> experimentalMethodology = new ArrayList<>(4);
    private final ArrayList<String> modellingAndSimulation = new ArrayList<>(5);

    private final ArrayList<ArrayList<String>> methodologies = new ArrayList<>(3);

    public ResearchAreaGrouper() {
        addIntoTheoriticalMeth();
        addIntoExperimentalMeth();
        addIntoModellingAndSimul();
        addIntoMethodologies();
    }

    public ArrayList<String> getTheoriticalMethodology() {
        return theoriticalMethodology;
    }

    public ArrayList<String> getExperimentalMethodology() {
        return experimentalMethodology;
    }

    public ArrayList<String> getModellingAndSimulation() {
        return modellingAndSimulation;
    }

    private void addIntoTheoriticalMeth() {
        theoriticalMethodology.add("DiscreteStructure");
        theoriticalMethodology.add("AlgorithmsAndComplexity");
        theoriticalMethodology.add("ArchitectureAndOrganization");
        theoriticalMethodology.add("InformationManagement");
        theoriticalMethodology.add("ComputationalScienceAndNumericalMethods");
    }

    private void addIntoExperimentalMeth() {
        experimentalMethodology.add("Net-Centric");
        experimentalMethodology.add("ProgrammingLanguage");
        experimentalMethodology.add("HumanComputerInteraction");
        experimentalMethodology.add("socialAndProfessionalIssues");
    }

    private void addIntoModellingAndSimul() {
        modellingAndSimulation.add("ProgrammingFundamentals");
        modellingAndSimulation.add("OperatingSystems");
        modellingAndSimulation.add("GraphicsAndVisualComputing");
        modellingAndSimulation.add("IntelligentSystems");
        modellingAndSimulation.add("SoftwareEngineering");
    }

    private void addIntoMethodologies() {
        methodologies.add(theoriticalMethodology);
        methodologies.add(experimentalMethodology);
        methodologies.add(modellingAndSimulation);
    }

    public String checkResearchAreaGroup(String researchArea) {

        String methodology = "";

        for (int i = 0; i < methodologies.size(); i++) {
            if (methodologies.get(i).contains(researchArea) && i == 0) {
                methodology = "Theoritical Methodology";
            } else if (methodologies.get(i).contains(researchArea) && i == 1) {
                methodology = "Experimental Methodology";
            } else if (methodologies.get(i).contains(researchArea) && i == 2) {
                methodology = "Modelling and Simulation";
            }

        }
        return methodology;
    }
}
