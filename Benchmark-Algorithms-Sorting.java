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

