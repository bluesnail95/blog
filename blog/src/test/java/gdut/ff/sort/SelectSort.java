package gdut.ff.sort;

/**
 * @Author bluesnail95
 * @Date 2020/10/11 20:51
 * @Description
 */
public class SelectSort {

    public static void main(String[] args) {
        int numbers[] = new int []{10, 6, 20, 40, 1, 17, 4};
        //
        for (int i = 0; i < numbers.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if(numbers[min] > numbers[j]) {
                    min = j;
                }
            }
            if(min != i) {
                int temp = numbers[min];
                numbers[min] = numbers[i];
                numbers[i] = temp;
            }
            PrintSort.print(numbers);
        }
    }


}
