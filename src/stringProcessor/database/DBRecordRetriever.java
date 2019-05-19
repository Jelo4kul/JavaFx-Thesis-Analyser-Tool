
package stringProcessor.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import stringProcessor.EditDistanceOptimizer;
import stringProcessor.utility.FileEngine;

/**
 *
 * @author Jelo
 */
public class DBRecordRetriever {

    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet results;
    private static ResultSetMetaData rsmd;

    public DBRecordRetriever() {
        //create a connection to the database
        con = new DatabaseConnection().connectDB();
    }

    public void retrieveArticlePath() {
        try {

            String sql = "select FILECATEGORY from JELO.THESISARTICLE";
            ps = con.prepareStatement(sql);
            results = ps.executeQuery();

            ArrayList<String> filePath = new ArrayList<>();
            while (results.next()) {
                System.out.println(results.getString(1));
                filePath.add(results.getString(1));
            }
            new FileEngine().setFilePaths(filePath);

        } catch (SQLException e) {

        }
    }

    public static void retrieveArticlePaths(String filecategory) {
        try {

            String sql = "select FILEPATH from JELO.THESISARTICLE WHERE FILECATEGORY = '" + filecategory + "'";
            ps = con.prepareStatement(sql);
            results = ps.executeQuery();

            ArrayList<String> filePath = new ArrayList<>();
            while (results.next()) {
                filePath.add(results.getString(1));
            }
            FileEngine fEngine = new FileEngine();
            fEngine.getFilePaths().clear();
            fEngine.setFilePaths(filePath);

        } catch (SQLException e) {

        }
    }

    //a test algorithm. Not used in the final project
    public void retrieveRecordsAndFindclosestMatch() {
        String testWord = "Function, set and logic";
        try {
            String sql = "select * from JELO.RESEARCHFIELD";
            ps = con.prepareStatement(sql);
            results = ps.executeQuery();
            rsmd = results.getMetaData();
            int noOfCols = rsmd.getColumnCount();
            for (int i = 1; i <= noOfCols; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();
            int min = 100;
            String ans = "";

            //looping through all the records in the database
            while (results.next()) {
                int id = results.getInt(1);
                String ds = results.getString(2);
                if (ds != null && EditDistanceOptimizer.valuePhrase(testWord, ds) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, ds);
                    ans = ds;
                }
                String pf = results.getString(3);
                if (pf != null && EditDistanceOptimizer.valuePhrase(testWord, pf) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, pf);
                    ans = pf;
                }
                String ac = results.getString(4);
                if (ac != null && EditDistanceOptimizer.valuePhrase(testWord, ac) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, ac);
                    ans = ac;
                }
                String ao = results.getString(5);
                if (ao != null && EditDistanceOptimizer.valuePhrase(testWord, ao) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, ao);
                    ans = ao;
                }
                String os = results.getString(6);
                if (os != null && EditDistanceOptimizer.valuePhrase(testWord, os) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, os);
                    ans = os;
                }
                String nc = results.getString(7);
                if (nc != null && EditDistanceOptimizer.valuePhrase(testWord, nc) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, nc);
                    ans = nc;
                }
                String pl = results.getString(8);
                if (pl != null && EditDistanceOptimizer.valuePhrase(testWord, pl) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, pl);
                    ans = pl;
                }
                String hc = results.getString(9);
                if (hc != null && EditDistanceOptimizer.valuePhrase(testWord, hc) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, hc);
                    ans = hc;
                }
                String gv = results.getString(10);
                if (gv != null && EditDistanceOptimizer.valuePhrase(testWord, gv) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, gv);
                    ans = gv;
                }
                String is = results.getString(11);
                if (is != null && EditDistanceOptimizer.valuePhrase(testWord, is) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, is);
                    ans = is;
                }
                String im = results.getString(12);
                if (im != null && EditDistanceOptimizer.valuePhrase(testWord, im) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, im);
                    ans = im;
                }
                String sp = results.getString(13);
                if (sp != null && EditDistanceOptimizer.valuePhrase(testWord, sp) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, sp);
                    ans = sp;
                }
                String se = results.getString(14);
                if (se != null && EditDistanceOptimizer.valuePhrase(testWord, se) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, se);
                    ans = se;
                }
                String cs = results.getString(15);
                if (cs != null && EditDistanceOptimizer.valuePhrase(testWord, cs) < min) {
                    min = EditDistanceOptimizer.valuePhrase(testWord, cs);
                    ans = cs;
                }
            }
            System.out.println(ans + " " + min);
            results.close();
            ps.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
}
