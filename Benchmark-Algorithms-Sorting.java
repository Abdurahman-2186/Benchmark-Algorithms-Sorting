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
        
