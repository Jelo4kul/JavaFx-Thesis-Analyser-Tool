package stringProcessor;

import java.util.ArrayList;

/**
 *
 * @author Jelo
 * @param <E> 
 */

public class Generics<E extends Number> {

    public void push(E s){
        
    }
    
    public static void main(String[] args) {
        
       Generics<Integer> s=new Generics<>();
       s.push(Integer.SIZE);
        
    }

    public static <E extends ArrayList<Integer>> void max(E s1, E s2) {
        s1.add(7);
        s2.add(9);
        System.out.println(s1.get(0) + " " + s1.get(1));
        System.out.println(s2.get(0) + " " + s2.get(1));
        // System.out.println(s1+s2);
    }
}
