package gdut.ff.sort;

/**
 * @Author bluesnail95
 * @Date 2020/10/11 19:25
 * @Description
 */
public class InsertSort {
    public static void main(String[] args) {
        int numbers[] = new int []{10, 6, 20, 40, 1, 17};
        //从小到大排序
        for (int i = 1; i < numbers.length; i++) {
            int current = numbers[i];
            int j = i - 1;
            while(j >= 0 && numbers[j] > current) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j+1] = current;
            PrintSort.print(numbers);
        }


//        for (int i = 1; i < numbers.length; i++) {
//            for (int j = i; j > 0; j--) {
//                if(numbers[j] > numbers[j - 1] ) {
//                    int temp = numbers[j];
//                    numbers[j] = numbers[j - 1];
//                    numbers[j -1] = temp;
//                }
//            }
//            System.out.println("numbers is " + numbers);
//        }



    }
}
