package stringProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import stringProcessor.database.DatabaseConnection;

/**
 *
 * @author Jelo
 */
public class ResearchTools {

    private PreparedStatement ps;
    private ResultSet results;
    private ArrayList<ArrayList<String>> tools = new ArrayList<>();

    public String methodologyToolGroup(String methodology) {
 
        switch (methodology) {
            case "Theoritical Methodology":
                return "THEORITICALTOOLS";

            case "Experimental Methodology":
                return "EXPERIMENTALTOOLS";

            case "Modelling and Simulation":
                return "MODELLINGANDSIMULATIONTOOLS";
            default:
                return null;
        }

    }

    public ArrayList<String> searchForKeywordsInTools(ArrayList<String> researchfieldKeyword) {

        ArrayList<String> row = null;

        for (ArrayList<String> toolRow : tools) {
            for (String keyword : researchfieldKeyword) {
                if (toolRow.contains(keyword)) {
                    row = toolRow;
                    break;
                }
            }
        }
        if (row == null) {
            row = randomlySelectTool();
        }
        return row;
    }

    private ArrayList<String> randomlySelectTool() {
        Random rand = new Random();
        //the problem here is that tools is returning 0
        int randNumber = rand.nextInt(tools.size());
        return tools.get(randNumber);
    }

    public ArrayList<ArrayList<String>> getResearchTools(String methodologyTool, String researchFieldName) {
        return retrieveResearchTools(methodologyTool, researchFieldName);
    }

    public ArrayList<ArrayList<String>> retrieveResearchTools(String methodologyTool, String researchFieldName) {
       
        String researchFieldNameInUppercase = researchFieldName.substring(0, 1).toUpperCase() + researchFieldName.substring(1);
        Connection con = DatabaseConnection.getConnection();

        try {
            String sql = "SELECT * FROM JELO." + methodologyTool + " WHERE MAINRESEARCHAREA = '" + researchFieldNameInUppercase + "'";
          //  String sql = "SELECT * FROM JELO.THEORITICALTOOLS WHERE MAINRESEARCHAREA = 'DiscreteStructure'";
          //  String sql = "SELECT * FROM JELO.THEORITICALTOOLS WHERE MAINRESEARCHAREA = '" + researchFieldNameInUppercase + "'";
            ps = con.prepareStatement(sql);
            results = ps.executeQuery();

            //looping through all the records in the database
            while (results.next()) {

                ArrayList<String> row = new ArrayList<>();

                row.add(results.getString(2));
                row.add(results.getString(3));
                row.add(results.getString(4));
                
                //the following research areas have just 3 tools in 3 columns in the DB, 
                //there is no need to add the last column               
                if(!researchFieldNameInUppercase.equalsIgnoreCase("DiscreteStructure") &&
                       !researchFieldNameInUppercase.equalsIgnoreCase("AlgorithmsAndComplexity") &&
                        !researchFieldNameInUppercase.equalsIgnoreCase("ArchitectureAndOrganization") &&
                        !researchFieldNameInUppercase.equalsIgnoreCase("InformationManagement") &&
                        !researchFieldNameInUppercase.equalsIgnoreCase("ComputationalScienceAndNumericalMethods")){
                        row.add(results.getString(5));
                }
                tools.add(row);

            }
            results.close();
            ps.close();

        } catch (SQLException sql) {
            sql.getMessage();
        }

        return tools;
    }

}
