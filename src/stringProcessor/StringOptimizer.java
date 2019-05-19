
package stringProcessor;

/**
 *
 * @author Jelo
 */
public class StringOptimizer {

    private static double phraseWeight = 0.5;
    private static double wordWeight = 1.0;
    private static double minWeight = 10;
    private static double maxWeight = 1;
    private static double lengthWeight = -0.3;

    public StringOptimizer() {

    }

    public StringOptimizer(double pWeight, double wWeight, double minWeight, double maxWeight, double lengthWeight) {
        phraseWeight = pWeight;
        wordWeight = wWeight;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.lengthWeight = lengthWeight;
    }

    public static double optimizeString(int phraseValue, int wordsValue) {
        double value = Math.min(phraseWeight * phraseValue, wordWeight * wordsValue) * minWeight
                + Math.max(phraseWeight * phraseValue, wordWeight * wordsValue) * maxWeight;
               // + lengthWeight * lengthValue;
        return value;
    }
    
  
    
}
