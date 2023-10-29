package cz.braza.educanet;

public class ArraySorts {
    public static void prettyPrint(int[] pole) {
        System.out.print("[");
        if (pole.length > 0)
            System.out.print(pole[0]);
        for (int i = 1; i < pole.length; i++)
            System.out.print(", " + pole[i]);
        System.out.print("]");
    }

    public static void bubbleSort(int[] pole) {
        boolean goAgain = true;
        while (goAgain) {
            goAgain = false;
            for (int i = 0; i < pole.length - 1; i++) {
                if (pole[i] > pole[i + 1]) {
                    swap(pole, i, i + 1);
                    goAgain = true;
                }
            }
        }
    }

    public static void selectionSort(int[] pole) {
        int sorted = 0;

        while (sorted < pole.length) {
            // 1. find position of the lowest value in the rest of the array:
            int lowest = sorted;
            for (int i = sorted; i < pole.length; i++)
                if (pole[lowest] > pole[i]) {
                    lowest = i;
                }
            // 2. swap with position <sorted>
            if (lowest != sorted)
                swap(pole, lowest, sorted);
            // 3. increase sorted by one
            sorted++;
        }
    }

    public static void quickSort(int[] pole) {
        quickSort(pole, 0, pole.length - 1);
    }

    /**
     * Recursive function to sort just a part of the array, given by lower and upper bounds index.
     * It works like this:
     * 0. If length is zero or one, this part is already sorted, return
     * 1. Picks a pivot (let's say last element)
     * 2. Swaps elements so that all smaller elements are now left of the pivot,
     *    while higher elements are right of the pivot
     * 3. now call quickSort() on left and right parts
     * @param pole
     * @param low
     * @param high
     */
    private static void quickSort(int[] pole, int low, int high) {
        if (low < high) {
            int pivot = qsPartition(pole, low, high);
            quickSort(pole, low, pivot - 1);
            quickSort(pole, pivot + 1, high);
        }
    }

    /**
     * Selects pivot (last value in the part of the array), reshuffles the array
     * so that all lower elements are to the left, higher to the right
     * and returns INDEX of pivot in the resulting partition
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static int qsPartition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private static void swap(int[] pole, int i, int j) {
        int dummy = pole[i];
        pole[i] = pole[j];
        pole[j] = dummy;
    }
    public static void main(String[] args) {
        int[] mojePole = {2, 5, 87, 14, 11, 0, 99, 42, 5, 7, 9, 64, 46, 24, 30, 28, 1, 9, 8, 4};
        //quickSort(mojePole);
        //bubbleSort(mojePole);
        selectionSort(mojePole);
        // array is sorted:
        prettyPrint(mojePole);

    }
}
