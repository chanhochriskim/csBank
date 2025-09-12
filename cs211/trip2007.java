/*
 * Author: Chan-Ho Kim
 * It is ok to share my code anonymously for educational purposes
 */


package topic2;
import java.util.*;
import java.io.*;

public class trip2007 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        boolean trigger = true;

        while ((line = br.readLine()) != null) {
            int n = Integer.parseInt(line); // n value
            if (n == 0) break; // end of line


            if (!trigger) System.out.println();
            trigger = false;

            HashMap<Integer, Integer> map = new HashMap<>();

            String[] strArray = br.readLine().split(" "); // str array will have 6 values (in case: 1 1 2 2 2 3).
            int[] bags = new int[n];
            // conversion.
            for (int i = 0; i < n; i++) {
                bags[i] = Integer.parseInt(strArray[i]);
            }

            Arrays.sort(bags);

            for (int i = 0; i < n; i++) { // setting up the hashmap to get the most frequent value within the array
                map.put(bags[i], map.getOrDefault(bags[i], 0) + 1);
            }

            // map contains all the key with its frequency. get the most frequent one now.
            int mostfrequent = 0;
            for (int freq : map.values()) {
                mostfrequent = Math.max(mostfrequent, freq);
            }

            // now that we have mostfrequent (k), along with int[] array. that's all we need.

            System.out.println(mostfrequent);

            // prepare paring part.
            List<List<Integer>> paring = new ArrayList<>();

            for (int i = 0; i < mostfrequent; i++) {
                paring.add(new ArrayList<>()); // ex: 3 total chains.
            }

            // now, distribute each key accordingly each one goes into each different, then comes around.
            int idx = 0;
            for (int i = 0; i < n; i++) {
                idx = i % mostfrequent;
                paring.get(idx).add(bags[i]); // [1, 2] [1, 2] [2, 3]
            }

            // print each
            for (List<Integer> pair : paring) {
                for (int i = 0; i < pair.size(); i++) {
                   // if (i > 0) System.out.print(" ");
                    System.out.print(pair.get(i) + " ");
                }
                System.out.println();
            }

        }

    }
}
