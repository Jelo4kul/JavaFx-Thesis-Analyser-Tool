package stringProcessor.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jelo
 */
public class FileEngine {

    private static ArrayList<String> filePaths;
    private static ObservableList<String> fileNames = FXCollections.observableArrayList();
    private static File folder;
 
    public void setFileNames(ObservableList<String> fileNames) {
        FileEngine.fileNames = fileNames;
    }

    public void setFilePaths(ArrayList<String> filePaths) {
        FileEngine.filePaths = filePaths;
    }

    public File getFolder() {
        return folder;
    }

    public ObservableList<String> extractFileNames() {

        Pattern pt = Pattern.compile(".*/(.*)$");
        for (String path : filePaths) {
            Matcher matcher = pt.matcher(path);
            if (matcher.find()) {
                fileNames.add(matcher.group(1));
                System.out.println(matcher.group(1));
            }
        }
        return fileNames;
    }

    public ObservableList<String> getFileNames() {
        return fileNames;
    }

    public Boolean areFilesAvailable() {
        return fileNames.isEmpty();
    }

    public ArrayList<String> getFilePaths() {
        return filePaths;
    }

    public int getNoOfFilesInFolder() {
        return filePaths.size();
    }
}
