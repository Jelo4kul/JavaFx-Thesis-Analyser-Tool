package stringProcessor.utility;

import java.util.ArrayList;
import java.util.Collections;
import stringProcessor.BMStringMatcher;
import stringProcessor.EditDistanceOptimizer;
import stringProcessor.ResearchFields;
import stringProcessor.StringOptimizer;

/**
 *
 * @author Jelo
 */
public class ResearchFieldsAnalyser {

    private final ArrayList<String> keywords;
    private final ResearchFields researchF;
    private int indexOfSmallestCount, valueOfSmallestCount;
    private final ArrayList<ArrayList> keywordsRMainAreaList = new ArrayList<>();
    private final ArrayList<String> keywordsResearchFieldName = new ArrayList<>();
    private final ArrayList<Integer> keywordsResearchFieldCount = new ArrayList<>();
    private final ArrayList<Integer> numbOfOccurrences = new ArrayList<>();

    public ResearchFieldsAnalyser(ArrayList<String> keywords, ResearchFields researchF) {
        this.keywords = keywords;
        this.researchF = researchF;
    }

    public ArrayList<String> getkeywordsResearchFieldName() {
        return keywordsResearchFieldName;
    }

    public void findMatchedKeywords(ArrayList<String> researchMainArea, int i) {

        int count = 0, foundIterator = 0;
        boolean found = false;
        String oldkeyword = "";

        for (String keyword : keywords) {
            for (String subResearchArea : researchMainArea) {

                //checks if a keyword is present in a subresearch area
                found = BMStringMatcher.checkForOccurrence(keyword, subResearchArea);

                if (found) {
                    //add the researchMainArea to a list of all the research Main Areas that contains the keyword
                    //if it is not already present in the list
                    if (!keywordsRMainAreaList.contains(researchMainArea)) {
                        keywordsRMainAreaList.add(researchMainArea);
                    }

                    count++;

                    if (count == 1) {
                        System.out.println(researchF.getResearchFieldName(i));
                        keywordsResearchFieldName.add(researchF.getResearchFieldName(i));
                    }

                    if (!oldkeyword.equals(keyword)) {
                        System.out.println(keyword + " found ");
                        oldkeyword = keyword;
                        foundIterator++;
                    }

                }
            }

        }
        System.out.println(keywordsRMainAreaList);
        numbOfOccurrences.add(foundIterator);
        //System.out.println(foundIterator);
    }

    public boolean stringMatchConflict() {

        int count = 0;

        int maxoccurrence = getMainResearchAreaWithMaxOccurrence();
        for (int i : numbOfOccurrences) {
            if (i == maxoccurrence) {
                System.out.println(i);
                count++;
            }
        }

        if (count == 1) {
            return false;
        } else {
            return true;
        }

    }

    //this returns the Main research area that keywords were found the most
    public int getMainResearchAreaWithMaxOccurrence() {
        return Collections.max(numbOfOccurrences);
    }

    //this returns the index of the Main research area that keywords were found the
    public int getMainResearchAreaIndexWithMaxOccurrence() {
        return numbOfOccurrences.indexOf(Collections.max(numbOfOccurrences));
    }

    public String optimizeResearchFields(String thesisTitle) {
        int count = 0;
        for (ArrayList<String> mainResearchArea : keywordsRMainAreaList) {

            for (String subResearchArea : mainResearchArea) {
                int valuePhrase = EditDistanceOptimizer.valuePhrase(subResearchArea, thesisTitle);
                int valueWord = EditDistanceOptimizer.valueWords(subResearchArea, thesisTitle);
                count += StringOptimizer.optimizeString(valuePhrase, valueWord);
            }
            keywordsResearchFieldCount.add(count);
            count = 0;
        }

        try {
            //here we try to get the index of the main research area with the least edit distance or count
            valueOfSmallestCount = Collections.min(keywordsResearchFieldCount);
            indexOfSmallestCount = keywordsResearchFieldCount.indexOf(valueOfSmallestCount);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return keywordsResearchFieldName.get(indexOfSmallestCount);
    }

}
