package stringProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import stringProcessor.database.DatabaseConnection;

public class ResearchFields {

    private final ArrayList<String> discreteStructure = new ArrayList<>();
    private final ArrayList<String> programmingFundamentals = new ArrayList<>();
    private final ArrayList<String> algorithmAndComplexity = new ArrayList<>();
    private final ArrayList<String> architectureAndOrganization = new ArrayList<>();
    private final ArrayList<String> operatingSystems = new ArrayList<>();
    private final ArrayList<String> netcentricComputing = new ArrayList<>();
    private final ArrayList<String> programmingLanguages = new ArrayList<>();
    private final ArrayList<String> humanComputerInteraction = new ArrayList<>();
    private final ArrayList<String> graphicsAndVisualComputing = new ArrayList<>();
    private final ArrayList<String> intelligentSystems = new ArrayList<>();
    private final ArrayList<String> informationManagement = new ArrayList<>();
    private final ArrayList<String> socialAndProfessionalIssues = new ArrayList<>();
    private final ArrayList<String> softwareEngineering = new ArrayList<>();
    private final ArrayList<String> computationalScience = new ArrayList<>();

    private ArrayList[] researchFields = {discreteStructure, programmingFundamentals, algorithmAndComplexity, architectureAndOrganization,
        operatingSystems, netcentricComputing, programmingLanguages, humanComputerInteraction, graphicsAndVisualComputing,
        intelligentSystems, informationManagement, socialAndProfessionalIssues, softwareEngineering, computationalScience};

    private String[] researchFieldName = {"DiscreteStructure", "ProgrammingFundamentals", "AlgorithmsAndComplexity", "ArchitectureAndOrganization", "OperatingSystems",
        "Net-Centric", "ProgrammingLanguage", "HumanComputerInteraction", "GraphicsAndVisualComputing", "IntelligentSystems",
        "InformationManagement", "socialAndProfessionalIssues", "SoftwareEngineering", "ComputationalScienceAndNumericalMethods"};

    private PreparedStatement ps;
    private ResultSet results;

    public ArrayList[] getResearchFields() {
        return researchFields;
    }
    
    public String getResearchFieldName(int i) {
        return researchFieldName[i];
    }


    public int numberOfResearchFields() {
        return researchFields.length;
    }

    public ArrayList<String> getDiscreteStructure() {
        return discreteStructure;
    }

    public ArrayList<String> getProgrammingFundamentals() {
        return programmingFundamentals;
    }

    public ArrayList<String> getAlgorithmAndComplexity() {
        return algorithmAndComplexity;
    }

    public ArrayList<String> getArchitectureAndOrganization() {
        return architectureAndOrganization;
    }

    public ArrayList<String> getOperatingSystems() {

        return operatingSystems;
    }

    public ArrayList<String> getNetcentricComputing() {

        return netcentricComputing;
    }

    public ArrayList<String> getProgrammingLanguages() {

        return programmingLanguages;
    }

    public ArrayList<String> getHumanComputerInteraction() {
        return humanComputerInteraction;
    }

    public ArrayList<String> getGraphicsAndVisualComputing() {
        return graphicsAndVisualComputing;
    }

    public ArrayList<String> getIntelligentSystems() {
        return intelligentSystems;
    }

    public ArrayList<String> getInformationManagement() {
        return informationManagement;
    }

    public ArrayList<String> getSocialAndProfessionalIssues() {

        return socialAndProfessionalIssues;
    }

    public ArrayList<String> getSoftwareEngineering() {

        return softwareEngineering;
    }

    public ArrayList<String> getComputationalScience() {

        return computationalScience;
    }

    public void retrieveResearchFields() {

        Connection con = DatabaseConnection.getConnection();

        try {
            String sql = "select * from JELO.RESEARCHFIELD";
            ps = con.prepareStatement(sql);
            results = ps.executeQuery();

            //looping through all the records in the database
            while (results.next()) {

                String ds = results.getString(2);
                if (ds != null) {
                    discreteStructure.add(ds);
                }
                String pf = results.getString(3);
                if (pf != null) {
                    programmingFundamentals.add(pf);
                }
                String ac = results.getString(4);
                if (ac != null) {
                    algorithmAndComplexity.add(ac);
                }
                String ao = results.getString(5);
                if (ao != null) {
                    architectureAndOrganization.add(ao);
                }
                String os = results.getString(6);
                if (os != null) {
                    operatingSystems.add(os);
                }
                String nc = results.getString(7);
                if (nc != null) {
                    netcentricComputing.add(nc);
                }
                String pl = results.getString(8);
                if (pl != null) {
                    programmingLanguages.add(pl);
                }
                String hc = results.getString(9);
                if (hc != null) {
                    humanComputerInteraction.add(hc);
                }
                String gv = results.getString(10);
                if (gv != null) {
                    graphicsAndVisualComputing.add(gv);
                }
                String is = results.getString(11);
                if (is != null) {
                    intelligentSystems.add(is);
                }
                String im = results.getString(12);
                if (im != null) {
                    informationManagement.add(im);
                }
                String sp = results.getString(13);
                if (sp != null) {
                    socialAndProfessionalIssues.add(sp);
                }
                String se = results.getString(14);
                if (se != null) {
                    softwareEngineering.add(se);
                }
                String cs = results.getString(15);
                if (cs != null) {
                    computationalScience.add(cs);
                }
            }

            results.close();
            ps.close();

        } catch (SQLException sql) {
            sql.printStackTrace();
        }

    }

}
