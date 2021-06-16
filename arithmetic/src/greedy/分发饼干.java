package greedy;

import java.util.Arrays;

/**
 * @description:
 * @author: sanduo
 * @date: 2021-02-05 18:00
 */
public class 分发饼干 {

    static int[] g = {1,2,3};
    static int[] s = {1,1};

    public static void main(String[] args) {
        System.out.println(findContentChildren(g, s));
    }

    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        for (int j = 0; count < g.length && j < s.length; j++) {

            if (s[j] >= g[count]) {
                count++;
            }

        }
        return count;
    }
}
