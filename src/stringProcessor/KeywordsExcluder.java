
package stringProcessor;

import java.util.ArrayList;

/**
 *
 * @author Jelo
 */
public class KeywordsExcluder {

    private static ArrayList<String> keywords=new ArrayList<>();
    
    public void setKeywords(ArrayList<String> keywords){
        KeywordsExcluder.keywords=keywords;
    }
    
    public ArrayList<String> getKeywords(){
        return keywords;
    }
    
    public static void trimKeywords(String[] keywordsArray) {

        for (int i = 0; i < keywordsArray.length; i++) {
            keywords.add(keywordsArray[i].trim().toLowerCase());
        }
    }
    
}
