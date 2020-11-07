package gdut.ff.sort;

/**
 * @Author bluesnail95
 * @Date 2020/10/25 9:11
 * @Description
 */
public class KMP {

    public static void main(String[] args) {
        String pattern = "btcdebtctt";
        String original = "abtcdebtcabbbbtcdebtcttbdddddabcdeeeee";

//        kmpTest01(original, pattern);
        kmpTest02(original, pattern);
    }

    /**
     * 暴力破解，直接遍历 时间复杂度O(i*j)
     * @param original 原始字符串
     * @param pattern 要匹配的字符串
     */
    public static void kmpTest01(String original, String pattern) {

        for (int i = 0; i < original.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < pattern.length(); j++) {
                if((i + j) > original.length() - 1 ||
                        !String.valueOf(original.charAt(i + j)).equalsIgnoreCase(String.valueOf(pattern.charAt(j)))) {
                    System.out.println("original.charAt(i + j) is " + original.charAt(i + j) + " String.valueOf(pattern.charAt(j) is " + String.valueOf(pattern.charAt(j)));
                    flag = false;
                    break;
                }
            }
            if(flag) {
                System.out.println("模式匹配成功，第一个索引是：" + i);
                return;
            }
        }
    }

    /**
     *
     * @param original 原始字符串
     * @param pattern 要匹配的字符串
     */
    public static void kmpTest02(String original, String pattern) {
        int[] next = getNext(pattern);
        PrintSort.print(next);
        char[] s_arr = original.toCharArray();
        char[] t_arr = pattern.toCharArray();
        int i = 0,j = 0;
        while(i < s_arr.length && j < t_arr.length) {
            if(j == -1 || s_arr[i] == t_arr[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if(j == t_arr.length){
            System.out.println("模式匹配成功，第一个索引是：" + (i - j));
        } else {
            System.out.println("模式无法匹配");
        }
    }



    public static int[] getNext(String pattern) {
        int next[] = new int[pattern.length()];
        next[0] = -1; next[1] = 0;
        int k = 0;
        for (int i = 2; i < pattern.length(); i++) {
            k = next[i - 1];
            while(k != -1) {
                if(pattern.charAt(i - 1) == pattern.charAt(k)) {
                    next[i] = k + 1;
                    break;
                } else {
                    k = next[k];
                }
                next[i] = 0;
            }
        }
        return next;
    }
}
