/*
 * Author: Chan-Ho Kim
 * It is ok to share my code anonymously for educational purposes
 */

package topic2;
import java.util.*;
import java.io.*;

public class zigzag {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double n = Integer.parseInt(br.readLine().trim()); // number

        int pairs = (int) Math.ceil(n / 25); // get the # of necessary pairs. ex: 77 --> 4 pairs. [x,x,x,x]

        StringBuilder sb = new StringBuilder();
        sb.append('a'); // answer always starts with 'a'.
        int current = 1;
        int remaining = (int) n; // first, start with value 77.

        int letter;
        for (int i = 1; i <= pairs; i++) { // a + 4 more letters
            letter = -1; // 1 ~ 26 (letter value)

            // within this loop, the first one being triggered is the smallest lexicographical one.
            int j = 1;
            while (j <= 26) {
                int score = Math.abs(j - current); // current score.

                if (score > 25 || score < 0 || score > remaining) {  // jump can never go over 25 nor remaining
                    j++;
                    continue;
                }
                int jumpsLeft = pairs - i; // 4 - 1 = 3 jumps left within pairs. []1[]2[]3[]

                int maxSum;
                // --> max score we can achieve within jumpsLeft
                if (jumpsLeft == 0) {
                    maxSum = 0;
                } else {
                    maxSum = Math.max(j - 1, 26 - j) + 25 * (jumpsLeft - 1); // we can go upto the farther end (a or z) + remaining bounce of 25.
                }

                int remainingSum = remaining - score; // what we still need after taking j.

                if (remainingSum <= maxSum) {
                    letter = j; // smallest possible next letter found!
                    break;
                }
                j++;
            }

            sb.append((char) (('a') + letter - 1)); // append the value
            remaining -= Math.abs(letter - current); // decrement the remaining.
            current = letter; // now, the letter will start from 'o' (ex: aoazb for value 77)
        }
        System.out.println(sb);
    }
}
