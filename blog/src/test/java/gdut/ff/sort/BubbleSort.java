package gdut.ff.sort;

/**
 * @Author bluesnail95
 * @Date 2020/9/5 11:32
 * @Description
 */
public class BubbleSort {

    public static void main(String[] args) {
        int numbers[] = new int []{10, 6, 20, 40, 1, 17};
        for (int i = 1; i < numbers.length; i++) {
            boolean flag = true;
            for (int j = 0; j < numbers.length - 1; j++) {
                if(numbers[j] < numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    flag = false;
                }
            }
            PrintSort.print(numbers);
            if(flag) {
                break;
            }
        }

//        Integer i1 = new Integer(100);
//        Integer i2 = new Integer(100);
//        System.out.println(i1 == i2);//false
//
//        Integer i3 = new Integer(20);
//        int i4 = 20;
//        System.out.println(i3 == i4);//true
//
//        Integer i5 = new Integer(200);
//        Integer i6 = new Integer(200);
//        System.out.println(i5 == i6);//false
//
//        Integer i7 = 30;
//        Integer i8 = new Integer(30);
//        System.out.println(i7 == i8);//false



    }


}
