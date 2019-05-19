package stringProcessor;

/**
 *
 * @author Jelo
 */
import java.awt.Desktop;
import java.sql.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import stringProcessor.database.DatabaseConnection;

public class PDFtoDb {

    public static void main(String[] args) throws URISyntaxException {

        DB db = new DB();
        Connection conn = new DatabaseConnection().connectDB();

        //db.insertPDF(conn, "articles/10-human.pdf");
        //db.getPDFData(conn);
        db.openPdf();
    }

}

class DB {

    public DB() {
    }

    public void insertPDF(Connection conn, String filename) {
        long len;
        String query;
        PreparedStatement pstmt;

        try {
            File file = new File(filename);
            System.out.println(file.getAbsolutePath());
            FileInputStream fis = new FileInputStream(file);

            //retrieving the length of the file
            len = (int) file.length();

            //sql query
            query = ("insert into JELO.ARTICLES(id, filename, article, filesize) VALUES(?,?,?,?)");
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, 1);
            pstmt.setString(2, file.getName());
            //method to insert a stream of bytes
            pstmt.setBinaryStream(3, fis);
            pstmt.setLong(4, len);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPDFData(Connection conn) {

        byte[] fileBytes;
        String query;
        try {
            query = "select article from JELO.ARTICLES";
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                //rs.getBytes(1) retrieves the value in column 1 as an array of bytes
                fileBytes = rs.getBytes(1);
                OutputStream targetFile = new FileOutputStream("articles/10-humantest.pdf");
                targetFile.write(fileBytes);
                targetFile.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openPdf() {
        //Desktop class allows the software to launch native Desktop applications that can be used to open
        //a file or an URI
        if (Desktop.isDesktopSupported()) {
            try {

                File file = null;
                String resource = "myTest";
                URL res = getClass().getResource(resource);
                if (res.toString().startsWith("jar:")) {
                    try {
                        InputStream input = getClass().getResourceAsStream(resource);
                        file = File.createTempFile("test2", ".pdf");
                        OutputStream out = new FileOutputStream(file);
                        int read;
                        byte[] bytes = new byte[1024];

                        while ((read = input.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }

                        out.close();
                        input.close();
                        Desktop.getDesktop().open(file);
                        file.deleteOnExit();
                    } catch (IOException ex) {

                    }
                } else {
                    //this will probably work in your IDE, but not from a JAR
                    file = new File(res.getFile());
                    Desktop.getDesktop().open(file);
                }

                //  File f = new File("articles/10.pdf");
            } catch (Exception ex) {
                System.out.println(ex);
                // no application registered for PDFs
            }
        }
    }
};
