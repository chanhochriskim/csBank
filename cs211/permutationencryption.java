import java.io.*;
import java.util.*;

public class permutationencryption {

    public static String encrypt(int n, int[] nums, char[] msg) {
        // getting the proper loop count.
        int count = msg.length;
        if ((msg.length % n) != 0) {
            count = ((msg.length / n) + 1) * n; // ex: msg length 42. n = 5. missing 3 extra space. count = (8+1) * 5 = 45
        }

        // add empty values inside msg by we miss, so that it doesn't throw error -->
        char[] padded = new char[count];
        for (int i = 0; i < msg.length; i++) {
            padded[i] = msg[i];
        }

        for (int i = msg.length; i < count; i++) {
            padded[i] = ' ';  // fill remaining with spaces
        }

        char[] newMsg = new char[count];
        int j = 0;
        int mount = 0;

        for (int i = 0; i < count; i++) {
            if (j == n) {
                j = 0;
                mount += n;
            }

            newMsg[i] = padded[mount + (nums[j % n] - 1)];
            j++;
        }

        return new String(newMsg);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {

            String[] strArray = line.split(" "); // str array will have 6 values (in case: 5 1 3 6 2 4 6).
            int n = Integer.parseInt(strArray[0]);
            int[] nums = new int[n];
            if (n == 0) break;

            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(strArray[i + 1]); // but we only save permutation.
            }

            String str = br.readLine(); // pure msg.
            char[] msg = str.toCharArray();

            String encrypted = encrypt(n, nums, msg);

            System.out.println("'" + encrypted + "'");
        }

    }
}
