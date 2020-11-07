package gdut.ff.sort;

/**
 * @Author bluesnail95
 * @Date 2020/10/11 23:22
 * @Description
 */
public class MergeSort {

    public static void main(String[] args) {
        int numbers[] = new int []{10, 6, 20, 40, 1, 17, 4};
        mergeSort(numbers, 0, numbers.length - 1);
    }

    public static int[] mergeSort(int arr[], int low, int high) {
        if(low >= high) {
            return arr;
        }
        int mid = (low + high) /2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
        return arr;

    }

    public static void merge(int arr[], int low, int mid, int high) {
        System.out.println("low is " + low + " high is " + high + " mid is " + mid);
        int k = 0;
        int i = low;
        int j = mid + 1;
        int newArr[] = new int[high - low + 1];
        while(i <= mid && j <= high) {
            if(arr[i] < arr[j]) {
                newArr[k++] = arr[i++];
            } else {
                newArr[k++] = arr[j++];
            }

        }

        while(i <= mid) {
            newArr[k++] = arr[i++];
        }

        while(j <= high) {
            newArr[k++] = arr[j++];
        }

        for (int z = 0; z < newArr.length; z++) {
            arr[z + low] = newArr[z];
        }

        PrintSort.print(arr);
    }
}
