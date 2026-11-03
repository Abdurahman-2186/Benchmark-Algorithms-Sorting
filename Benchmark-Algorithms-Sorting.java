import java.util.Arrays;
import java.util.Random;
 
/**
 * Sorting-Algorithms-Benchmark
 * Measures the running time (in microseconds) of 5 sorting algorithms
 * on 4 kinds of input (random, reversed, partially sorted, fully sorted), N = 100,000.
 */
public class SortingBenchmark {
     static final int N = 100_000;
    static final String[] ALGOS = {"Selection Sort", "Insertion Sort", "Bubble Sort", "Merge Sort", "Quick Sort"};
    static final String[] CASES = {"Random", "Sorted Reversely", "Sorted Partially", "Sorted Fully"};
 
    // ===================== Test-data generators =====================
    static int[] randomArray(Random rnd) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) a[i] = rnd.nextInt(1_000_000);
        return a;
    }

 
    static int[] reversedArray(Random rnd) {
        int[] a = randomArray(rnd);
        Arrays.sort(a);
        for (int i = 0, j = N - 1; i < j; i++, j--) { int t = a[i]; a[i] = a[j]; a[j] = t; }
        return a;
    }
 
    // ~80% of elements in place (sorted), ~20% replaced by random values scattered around
    static int[] partiallySortedArray(Random rnd) {
        int[] a = randomArray(rnd);
        Arrays.sort(a);
        int scattered = N / 5;
        for (int k = 0; k < scattered; k++) a[rnd.nextInt(N)] = rnd.nextInt(1_000_000);
        return a;
    }
 
    static int[] sortedArray(Random rnd) {
        int[] a = randomArray(rnd);
        Arrays.sort(a);
        return a;
    }
 
    // ===================== Sorting algorithms =====================
    // 1) Selection Sort: O(n^2) always, O(1) space, n-1 swaps at most
    static void selectionSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < n; j++)
                if (a[j] < a[smallest]) smallest = j;
            int t = a[i]; a[i] = a[smallest]; a[smallest] = t;
        }
    }
 
    // 2) Insertion Sort: best O(n), worst O(n^2)
    static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) { a[j + 1] = a[j]; j--; }
            a[j + 1] = key;
        }
    }
 
    // 3) Bubble Sort (optimized with a "swapped" flag): best O(n), worst O(n^2)
    static void bubbleSort(int[] a) {
        int n = a.length;
        boolean swapped = true;
        for (int i = 0; swapped && i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                    swapped = true;
                }
            }
        }
    }
 
    // 4) Merge Sort: O(n log n) always, O(n) extra space
    static void mergeSort(int[] a, int left, int right) {
        if (left >= right) return;
        int middle = left + (right - left) / 2;
        mergeSort(a, left, middle);
        mergeSort(a, middle + 1, right);
        merge(a, left, middle, right);
    }
 
    static void merge(int[] a, int left, int middle, int right) {
        int n1 = middle - left + 1, n2 = right - middle;
        int[] L = new int[n1], R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = a[left + i];
        for (int j = 0; j < n2; j++) R[j] = a[middle + 1 + j];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) a[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        while (i < n1) a[k++] = L[i++];
        while (j < n2) a[k++] = R[j++];
    }
 
    // 5) Quick Sort (first element as pivot, exactly as in the lecture)
    //    average O(n log n), worst O(n^2) (already sorted / reversed input)
    static void quickSort(int[] a, int left, int right) {
        if (left >= right) return;
        int middle = partition(a, left, right);
        quickSort(a, left, middle - 1);
        quickSort(a, middle + 1, right);
    }
     static int partition(int[] a, int left, int right) {
        int x = a[left];
        int i = left;
        for (int j = left + 1; j <= right; j++) {
            if (a[j] < x) {
                i++;
                int t = a[i]; a[i] = a[j]; a[j] = t;
            }
        }
        int t = a[i]; a[i] = a[left]; a[left] = t;
        return i;
    }
 
    // ===================== Timing =====================
    static long timeSort(int algo, int[] original) {
        int[] testArray = Arrays.copyOf(original, original.length); // copy is NOT timed
        long start = System.nanoTime();
        switch (algo) {
            case 0: selectionSort(testArray); break;
            case 1: insertionSort(testArray); break;
            case 2: bubbleSort(testArray); break;
            case 3: mergeSort(testArray, 0, testArray.length - 1); break;
            case 4: quickSort(testArray, 0, testArray.length - 1); break;
        }
        long end = System.nanoTime();
        if (!isSorted(testArray)) throw new IllegalStateException(ALGOS[algo] + " failed to sort!");
        return (end - start) / 1000; // microseconds
    }
 
