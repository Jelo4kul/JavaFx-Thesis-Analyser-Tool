package stringProcessor;

import java.util.ArrayList;

/**
 *
 * @author Jelo
 */
public class BMStringMatcher {

    /**
     * ****************************************************************************
     * Compilation: javac BoyerMoore.java Execution: java BoyerMoore pattern
     * text Dependencies: StdOut.java
     *
     * Reads in two strings, the pattern and the input text, and searches for
     * the pattern in the input text using the bad-character rule part of the
     * Boyer-Moore algorithm. (does not implement the strong good suffix rule)
     *
     * % java BoyerMoore abracadabra abacadabrabracabracadabrabrabracad text:
     * abacadabrabracabracadabrabrabracad pattern: abracadabra
     *
     * % java BoyerMoore rab abacadabrabracabracadabrabrabracad text:
     * abacadabrabracabracadabrabrabracad pattern: rab
     *
     * % java BoyerMoore bcara abacadabrabracabracadabrabrabracad text:
     * abacadabrabracabracadabrabrabracad pattern: bcara
     *
     * % java BoyerMoore rabrabracad abacadabrabracabracadabrabrabracad text:
     * abacadabrabracabracadabrabrabracad pattern: rabrabracad
     *
     * % java BoyerMoore abacad abacadabrabracabracadabrabrabracad text:
     * abacadabrabracabracadabrabrabracad pattern: abacad
     *
     *****************************************************************************
     */
    /**
     * The {@code BoyerMoore} class finds the first occurrence of a pattern
     * string in a text string.
     * <p>
     * This implementation uses the Boyer-Moore algorithm (with the
     * bad-character rule, but not the strong good suffix rule).
     * <p>
     * For additional documentation, see
     * <a href="http://algs4.cs.princeton.edu/53substring">Section 5.3</a> of
     * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
     */
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public BMStringMatcher(String pat) {
        this.R = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int j = 0; j < pat.length(); j++) {
            right[pat.charAt(j)] = j;
        }
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param R the alphabet size
     */
    public BMStringMatcher(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++) {
            this.pattern[j] = pattern[j];
        }

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int j = 0; j < pattern.length; j++) {
            right[pattern[j]] = j;
        }
    }

    /**
     * Returns the index of the first occurrrence of the pattern string in the
     * text string.
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string in the
     * text string; n if no such match
     */
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) {
                return i;    // found

            }
        }
        return n;                       // not found
    }
    // return offset of first match; N if no match

    public ArrayList<Integer> search2(String txt) {
        int M = pat.length();
        int N = txt.length();
        ArrayList<Integer> newArrayInt = new ArrayList<Integer>();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) {
                newArrayInt.add(i);    // found
                skip++;
            }
        }
        return newArrayInt;
    }

    /**
     * Returns the index of the first occurrrence of the pattern string in the
     * text string.
     *
     * @param text the text string
     * @return the index of the first occurrence of the pattern string in the
     * text string; n if no such match
     */
    public int search(char[] text) {
        int m = pattern.length;
        int n = text.length;
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pattern[j] != text[i + j]) {
                    skip = Math.max(1, j - right[text[i + j]]);
                    break;
                }
            }
            if (skip == 0) {
                return i;    // found
            }
        }
        return n;                       // not found
    }

    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints the first
     * occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String pat = "artificial";
        String txt = "asdf ghjk klll abc abc qwerty abc and poaslf abc";

        BMStringMatcher boyermoore1 = new BMStringMatcher(pat);

        ArrayList<Integer> offset = boyermoore1.search2(txt);

        // print results
        System.out.println("Offset: " + offset);
    }

    public static boolean checkForOccurrence(String pat, String txt) {

        boolean found =false;
        BMStringMatcher boyermoore1 = new BMStringMatcher(pat.toLowerCase());

        ArrayList<Integer> offset = boyermoore1.search2(txt.toLowerCase());

        if (!offset.isEmpty()) {
            found = true;
            // print results
          //  System.out.println("Location found: " + offset +" "+pat);
            
        }

        //System.out.println(txt.substring(offset, 0));
        return found;
    }

}
