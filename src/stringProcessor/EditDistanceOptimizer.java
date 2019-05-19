package stringProcessor;

/**
 *
 * @author Jelo
 */
public class EditDistanceOptimizer {

    public static int distance(String a, String b) {
        
            a = a.toLowerCase();
            b = b.toLowerCase();
            // i == 0
            int[] costs = new int[b.length() + 1];
            for (int j = 0; j < costs.length; j++) {
                costs[j] = j;
            }
            for (int i = 1; i <= a.length(); i++) {
                // j == 0; nw = lev(i - 1, j)
                costs[0] = i;
                int nw = i - 1;
                for (int j = 1; j <= b.length(); j++) {
                    int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                    nw = costs[j];
                    costs[j] = cj;
                }
            }
            return costs[b.length()];
        } 


    public static int valuePhrase(String s1, String s2) {
        return distance(s1, s2);
    }

    public static int valueWords(String s1, String s2) {

        String wordsS1[], wordsS2[] = null;
        wordsS1 = s1.split(" ");
        wordsS2 = s2.split(" ");
        int word1, word2, thisD, wordbest, wordsTotal = 0;
        for (word1 = 0; word1 < wordsS1.length; word1++) {
            wordbest = s2.length();
            for (word2 = 0; word2 < wordsS2.length; word2++) {
                thisD = distance(wordsS1[word1], wordsS2[word2]);
                if (thisD < wordbest) {
                    wordbest = thisD;
                }
                if (thisD == 0) {
                    break;
                }
            }

            wordsTotal = wordsTotal + wordbest;

        }
        return wordsTotal;

    }

    //A test method to check how the optimization works
    public static void main(String[] args) {
        int valuePhrase = valuePhrase("Real-time computing: a new discipline of computer science and engineering",
                "Introduction to net-centric computing science");
        System.out.println(valuePhrase);
        int valueWord = valueWords("Real-time computing: a new discipline of computer science and engineering",
                "Introduction to net-centric computing science");
        System.out.println(valueWord);
        double ans = new StringOptimizer().optimizeString(valuePhrase, valueWord);
        System.out.println(ans);
        
    }
}
