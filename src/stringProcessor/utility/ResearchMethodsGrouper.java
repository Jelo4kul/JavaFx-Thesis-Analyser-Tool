package stringProcessor.utility;

/**
 *
 * @author Jelo
 */
public class ResearchMethodsGrouper {

    private final String[] methodsForTM = {"Design Research", "Mathematical Proof", "Literature Review"};
    private final String[] methodsForEM = {"Design Research", "Evaluation", "Literature Review", "Case Study"};
    private final String[] methodsForMS = {"Design Research", "Evaluation", "Literature Review", "Mathematical Model"};

    public String[] getMethodsForTM() {
        return methodsForTM;
    }

    public String[] getMethodsForEM() {
        return methodsForEM;
    }

    public String[] getMethodsForMS() {
        return methodsForMS;
    }

    public String[] checkMethodGroup(String methodology) {

        switch (methodology) {
            case "Theoritical Methodology":
                return methodsForTM;

            case "Experimental Methodology":
                return methodsForEM;

            case "Modelling and Simulation":
                return methodsForMS;
            default:
                return null;
        }

    }
}
