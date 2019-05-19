package stringProcessor.utility;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelo
 */
public class ArticleReader {

    private static DirectoryChooser dChooser = new DirectoryChooser();
    //   private File ;
    private static File tempFile;

    public void extractPdfContents(String filePath) {
        //Desktop class allows the software to launch native Desktop applications that can be used to open
        //a file or an URI
        if (Desktop.isDesktopSupported()) {
            try {

                String resource = filePath;
                URL res = getClass().getResource(resource);
                if (res.toString().startsWith("jar:")) {
                    try {
                        InputStream input = getClass().getResourceAsStream(resource);
                        tempFile = File.createTempFile("Article", retrieveFileExtension(filePath));
                        OutputStream out = new FileOutputStream(tempFile);
                        int read;
                        byte[] bytes = new byte[1024];

                        //-1 here means the end of the file has been reached
                        while ((read = input.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }

                        out.close();
                        input.close();
                    } catch (IOException ex) {

                    }
                } else {
                    //this will probably work in your IDE, but not from a JAR
                    tempFile = new File(res.getFile());
                    Desktop.getDesktop().open(tempFile);
                }

                //  File f = new File("articles/10.pdf");
            } catch (Exception ex) {
                ex.printStackTrace();
                // no application registered for PDFs
            }
        }
    }

    public void openAndReadPdf() {
        try {
            Desktop.getDesktop().open(tempFile);
            tempFile.deleteOnExit();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Can not open file");
        }
    }

    public void savePdf(String fileName) {
        File selectedDirectory = dChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            OutputStream outStream = null;
            String path = selectedDirectory.getAbsolutePath() + "/" + fileName;

            try {
                File savedFile = new File(path);
                savedFile.getParentFile().mkdirs();
                savedFile.createNewFile();
                outStream = new FileOutputStream(savedFile);
                byte fileContent[] = Files.readAllBytes(tempFile.toPath());
                outStream.write(fileContent);
                outStream.close();
                JOptionPane.showMessageDialog(null, "FileSaved");
            } catch (FileNotFoundException fex) {

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not save file");
            }

        }
        dChooser.setInitialDirectory(selectedDirectory);
    }

    public String retrieveFileExtension(String filePath) {

        Pattern pt = Pattern.compile(".*\\.(.*)$");
        Matcher matcher = pt.matcher("artic.les/babes.ss.pdf");
        if (matcher.find()) {
            return (matcher.group(1));
        }
        return null;
    }
}
