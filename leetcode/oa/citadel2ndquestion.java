import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'getOptimalTeamSize' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY lowerSkill
     *  2. INTEGER_ARRAY higherSkill
     */

    public static int getOptimalTeamSize(List<Integer> lowerSkill, List<Integer> higherSkill) {
    // Write your code here
        // simple walkthrough: lowerskill[i] --> max # of lower skilled ppl, that 'i' is willing to take with. same for higherskill[i]. --> select max number of developers where every developer is satisfied with how how many are stronger & weaker. 
        
        // developer [i]'s skill level = their index (i + 1). 
        // 1. assign developers into correct position. weakest at index 0. (should have everyone to be stronger, and 0 weaker, 2nd weakest (idx 1) should only have 1 weaker, and rest to be stronger, etc)
        // 2. meaning, for a team of size (n), we should have a developer (idx) with 
        // --> formula: lowerskill[i] >= idx && higherskill[i] >= n - 1 - idx.
        
        // developer 'i' should be okay with having each 'pos' weaker teammates and 'n - 1 - 'pos'' stronger ones.
        int length = lowerSkill.size();
        int low = 0;
        int high = length; // binary search DSA O(nlogn) 
        int res = 0;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            int count = 0; // # of valid developers
            
            for (int i = 0; i < length; i++) {
                if (lowerSkill.get(i) >= count && higherSkill.get(i) >= (mid - count - 1)) {
                    count++;
                }
                if (count == mid) break; // team # satisfied. break out. 
            }
            
            if (count == mid) { // if satisfied, see if even larger # can be satisfied.
                res = mid;
                low = mid + 1;
            } else { // if not, try smaller one.
                high = mid - 1; 
            }
        }
        return res;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int lowerSkillCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> lowerSkill = IntStream.range(0, lowerSkillCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int higherSkillCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> higherSkill = IntStream.range(0, higherSkillCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.getOptimalTeamSize(lowerSkill, higherSkill);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
