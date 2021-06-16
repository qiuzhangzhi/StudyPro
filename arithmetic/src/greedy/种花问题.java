//package greedy;
//
///**
// * @description:
// * @author: sanduo
// * @date: 2021-02-05 18:31
// */
//public class 种花问题 {
//    static int[] flowerbed={};
//    static int n;
//    public static void main(String args[]) {
//        System.out.println(canPlaceFlowers(flowerbed, n));
//    }
//    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
//        for (int j = 0; j < flowerbed.length - 1; j++) {
//            if (flowerbed[j] == 1) {
//
//            }
//            if (flowerbed[j-1] == 0 && flowerbed[j] == 0 && flowerbed[j+1] == 0) {
//                n--;
//                j += 2;
//            } else if (flowerbed[j-1] == 1){
//                j +=1;
//            } else if (flowerbed[j] == 1) {
//                j += 2;
//            }
//
//        }
//
//    }
//}
