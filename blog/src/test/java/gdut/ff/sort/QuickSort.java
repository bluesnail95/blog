package gdut.ff.sort;

/**
 * @Author bluesnail95
 * @Date 2020/10/11 21:04
 * @Description
 */
public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        System.out.println("begin>>> low is " + low + " high is " + high);
        System.out.print("begin>>>");
        PrintSort.print(arr);
        if(low>= high) {
            return;
        }
        int begin = arr[low];
        int left = low;
        int right = high;

        while(left < right) {

            while(arr[right] >= begin && left < right) {
                right--;
            }

            while(arr[left] <= begin && left < right) {
                left++;
            }


            if(left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        arr[low] = arr[left];
        arr[left] = begin;
        PrintSort.print(arr);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }

    public static void main(String[] args) {
        int numbers[] = new int []{10, 6, 20, 40, 1, 17, 4};
        quickSort(numbers, 0 , numbers.length - 1);
    }

}
