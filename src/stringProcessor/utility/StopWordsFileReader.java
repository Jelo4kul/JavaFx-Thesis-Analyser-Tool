
package stringProcessor.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Jelo
 */

//clas that reads a file that contains all the stopwords
public class StopWordsFileReader {

    private final ArrayList<String> stopwords=new ArrayList<>();

    public  void readFile(String fileName) {

        try {
            
            InputStream fileUrl=getClass().getResourceAsStream(fileName);
            
            //reads an inputstream
            InputStreamReader reader=new InputStreamReader(fileUrl);
            
            //does the actual reading of the file by making use of the inputstream reader
            BufferedReader bufReader = new BufferedReader(reader);

            String line;
            
            while((line=bufReader.readLine()) != null){
                stopwords.add(line.toLowerCase().trim());
            }
            
            bufReader.close();
           

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<String> getStopWords(){
        return stopwords;
    }
}
