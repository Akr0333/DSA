import java.util.Arrays;

public class SortingDemo {
    static long comparisons;

    static void bubbleSort(int[] a) {
        comparisons = 0;
        for (int i = 0; i < a.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                comparisons++;
                if (a[j] > a[j + 1]) {
                    int temp = a[j]; a[j] = a[j + 1]; a[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    static void insertionSort(int[] a) {
        comparisons = 0;
        for (int i = 1; i < a.length; i++) {
            int key = a[i], j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (a[j] <= key) break;
                a[j + 1] = a[j--];
            }
            a[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11, 90, 34};

        int[] bubble = Arrays.copyOf(data, data.length);
        bubbleSort(bubble);
        System.out.println("Bubble Sort:    " + Arrays.toString(bubble));
        System.out.println("Comparisons: " + comparisons);

        int[] insertion = Arrays.copyOf(data, data.length);
        insertionSort(insertion);
        System.out.println("Insertion Sort: " + Arrays.toString(insertion));
        System.out.println("Comparisons: " + comparisons);
    }
}
