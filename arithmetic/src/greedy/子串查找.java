package greedy;

/**
 * @description:
 * @author: sanduo
 * @date: 2021-04-24 18:45
 */
public class 子串查找 {
    public static void main(String[] args){
        String s = "CDFGFABABAFABABAAAQWEDC";
        String t = "ABABAA";
        int[] next = kmpNext(t);
        int res = kmp(s, t,next);
        if (res!=-1){
            System.out.println("起始位置为："+res);
        }
        else System.out.println("主串中不包含字符串："+t);
//        printNext("ABCDABD");
//        printNext("ABABAA");
//        printNext("ABAABCAC");

    }

    public static int[] kmpNext(String t) {
        int[] next = new int[t.length()];
        int j = 0;
        next[0] = 0;
        for (int i= 1; i < t.length(); i++) {
            while (t.charAt(i) != t.charAt(j) && j > 0 ) {
                j = next[j -1];
            }
            if (t.charAt(i) == t.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    //
//    public static int[] kmpNext(String str){
//        int[] next = new int[str.length()];
//        next[0] = 0;
//        for(int j = 1,k = 0; j < str.length(); j++){
//            while(k > 0 && str.charAt(k) != str.charAt(j)){
//                k = next[k - 1];
//            }
//            if(str.charAt(j) == str.charAt(k)){
//                k++;
//            }
//            next[j] = k;
//        }
//        return next;
//    }
//
    public static int kmp(String s, String t, int[] next) {
        int j = 0;
        for (int i= 0; i < s.length(); i++) {
            while (s.charAt(i) != t.charAt(j) && j > 0 ) {
                j = next[j - 1];
            }
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            if (t.length() == j)
        }
        return -1;
    }
//    public static int kmp(String s, String t,int[] next){//主串  t 模式串
//        for(int i = 0, j = 0; i < s.length(); i++){
//
//            while(j > 0 && s.charAt(i) != t.charAt(j)){
//                j = next[j - 1];    //下一个匹配位为next数组的第j-1位
//            }
//            if(s.charAt(i) == t.charAt(j)){
//                j++;//主串通过i进行加1，模式串通过j加1
//            }
//            if(j == t.length()){
//                return i-j+1;//返回匹配位置
//            }
//        }
//        return -1;
//    }

//





}
