// java version!

import java.util.*;

public class thanos {

    public static int correctUniverse(int n, int[] worlds) {
        int total = 0;
        int local = 0;

        for (int i = worlds.length - 2; i >=0; i--) {
            if (worlds[i] >= worlds[i + 1]) { // ex: 5 2 3 --> 5 is triggered. 5 - 2 + 1 = required kill. (4)
                local += worlds[i] - worlds[i + 1] + 1;
                worlds[i] -= local; // 5 is now 1. (1 2 3, satisfied)

                if (worlds[i] < 0) {
                    return 1; // edge case.
                }

                total += local;
            }
        }
        return total;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // read number of worlds
        int[] worlds = new int[n];

        for (int i = 0; i < n; i++) {
            worlds[i] = sc.nextInt();
        }

        int result = correctUniverse(n, worlds);
        System.out.println(result);
        sc.close();
    }
}
