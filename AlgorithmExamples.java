import java.util.Random;

class DataType<T> {
    private T[] array;

    public DataType(T[] array) {
        this.array = array;
    }

    public T[] get() {
        return array;
    }
}

public class AlgorithmExamples {

    // ==========================================
    // ANSI COLORS
    // ==========================================
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String DIM = "\u001B[2m";
    private static final String YELLOW = "\u001B[33m";   // insertion — element being placed
    private static final String RED = "\u001B[31m";      // selection — swap
    private static final String BLUE = "\u001B[34m";     // merge — merged range
    private static final String MAGENTA = "\u001B[35m";  // quicksort — pivot
    private static final String GREEN = "\u001B[32m";    // sorted / final result
    private static final String CYAN = "\u001B[36m";     // headers

    // ==========================================
    // ANIMATION HELPERS
    // ==========================================

    // Prints a labelled array snapshot, highlighting only the given indices
    // in highlightColor so the reader's eye goes straight to what changed.
    private static void pauseAndPrint(String label, String labelColor, Object[] array, int[] highlight, String highlightColor) {
        System.out.print(labelColor + String.format("%-32s", label) + RESET + " : ");
        for (int idx = 0; idx < array.length; idx++) {
            boolean isHighlighted = false;
            for (int h : highlight) {
                if (h == idx) { isHighlighted = true; break; }
            }
            if (isHighlighted) {
                System.out.print(highlightColor + BOLD + array[idx] + RESET + " ");
            } else {
                System.out.print(array[idx] + " ");
            }
        }
        System.out.println();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void printLabeledArray(String label, String labelColor, Object[] array, String arrayColor) {
        System.out.print(labelColor + String.format("%-32s", label) + RESET + " : ");
        for (Object o : array) {
            System.out.print(arrayColor + o + RESET + " ");
        }
        System.out.println();
    }

    private static int[] rangeIndices(int left, int right) {
        int[] indices = new int[right - left + 1];
        for (int k = left; k <= right; k++) indices[k - left] = k;
        return indices;
    }

    // ==========================================
    // SORTING ALGORITHMS
    // ==========================================

    public static <T extends Comparable<T>> void insertionSort(T[] array, boolean ascending) {
        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;

            if (ascending) {
                while (j >= 0 && array[j].compareTo(key) > 0) {
                    array[j + 1] = array[j];
                    j--;
                }
            } else {
                while (j >= 0 && array[j].compareTo(key) < 0) {
                    array[j + 1] = array[j];
                    j--;
                }
            }
            array[j + 1] = key;
            pauseAndPrint("Inserting element " + key, YELLOW, array, new int[]{j + 1}, YELLOW);
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] array, boolean ascending) {
        for (int i = 0; i < array.length - 1; i++) {
            int selectedIndex = i;

            if (ascending) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j].compareTo(array[selectedIndex]) < 0) {
                        selectedIndex = j;
                    }
                }
            } else {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j].compareTo(array[selectedIndex]) > 0) {
                        selectedIndex = j;
                    }
                }
            }

            T temp = array[selectedIndex];
            array[selectedIndex] = array[i];
            array[i] = temp;

            pauseAndPrint("Swapping to index " + i, RED, array, new int[]{i, selectedIndex}, RED);
        }
    }

    public static <T extends Comparable <T>> void merge(T[] array, int left, int middle, int right, boolean ascending) {
        int node1 = middle - left + 1;
        int node2 = right - middle;

        @SuppressWarnings("unchecked")
        T[] leftArray = (T[]) new Comparable[node1];
        @SuppressWarnings("unchecked")
        T[] rightArray = (T[]) new Comparable[node2];

        for (int i = 0; i < node1; ++i) leftArray[i] = array[left + i];
        for (int j = 0; j < node2; ++j) rightArray[j] = array[middle + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < node1 && j < node2) {
            boolean condition = ascending ? leftArray[i].compareTo(rightArray[j]) <= 0 : leftArray[i].compareTo(rightArray[j]) >= 0;

            if (condition) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < node1) {
            array[k] = leftArray[i];
            i++; k++;
        }

        while (j < node2) {
            array[k] = rightArray[j];
            j++; k++;
        }

        pauseAndPrint("Merged indices [" + left + " to " + right + "]", BLUE, array, rangeIndices(left, right), BLUE);
    }

    public static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right, boolean ascending) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle, ascending);
            mergeSort(array, middle + 1, right, ascending);
            merge(array, left, middle, right, ascending);
        }
    }

    private static <T> void randomPivot(T[] array, int left, int right) {
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);

        T temp1 = array[pivotIndex];
        array[pivotIndex] = array[right];
        array[right] = temp1;
    }

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right, boolean ascending) {
        randomPivot(array, left, right);
        int pivotIndex = right;
        int i = (left - 1);

        for (int j = left; j < right; j++) {
            boolean condition = ascending ? array[j].compareTo(array[pivotIndex]) <= 0 : array[j].compareTo(array[pivotIndex]) >= 0;
            if (condition){
                i++;
                T temp2 = array[i];
                array[i] = array[j];
                array[j] = temp2;
            }
        }

        T temp = array[i + 1];
        array[i + 1] = array[pivotIndex];
        array[pivotIndex] = temp;

        pauseAndPrint("Partitioned around pivot '" + array[i + 1] + "'", MAGENTA, array, new int[]{i + 1}, MAGENTA);

        return i + 1;
    }

    public static <T extends Comparable<T>> void quickSort(T[] array, int left, int right, boolean ascending) {
        if (left < right) {
            int pivotIndex = partition(array, left, right, ascending);
            quickSort(array, left, pivotIndex - 1, ascending);
            quickSort(array, pivotIndex + 1, right, ascending);
        }
    }

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // ==========================================
    // MERGE SORT — DIVIDE/MERGE TREE (console view)
    // Same recursion as mergeSort()/merge() above, just traced
    // as an indented, colorized, animated tree instead of a flat list.
    // ==========================================

    private static <T extends Comparable<T>> void mergeQuiet(T[] array, int left, int middle, int right, boolean ascending) {
        int node1 = middle - left + 1;
        int node2 = right - middle;

        @SuppressWarnings("unchecked")
        T[] leftArray = (T[]) new Comparable[node1];
        @SuppressWarnings("unchecked")
        T[] rightArray = (T[]) new Comparable[node2];

        for (int i = 0; i < node1; ++i) leftArray[i] = array[left + i];
        for (int j = 0; j < node2; ++j) rightArray[j] = array[middle + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < node1 && j < node2) {
            boolean condition = ascending ? leftArray[i].compareTo(rightArray[j]) <= 0 : leftArray[i].compareTo(rightArray[j]) >= 0;
            if (condition) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < node1) { array[k] = leftArray[i]; i++; k++; }
        while (j < node2) { array[k] = rightArray[j]; j++; k++; }
    }

    private static <T> String rangeToString(T[] array, int left, int right) {
        StringBuilder sb = new StringBuilder();
        for (int k = left; k <= right; k++) {
            sb.append(array[k]);
            if (k < right) sb.append(" ");
        }
        return sb.toString();
    }

    private static void printAnimated(String line) {
        System.out.println(line);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static <T extends Comparable<T>> void mergeSortTree(T[] array, int left, int right, boolean ascending, String prefix, boolean isTail) {
        String connector = isTail ? "\u2514\u2500 " : "\u251C\u2500 ";
        String dimPrefix = DIM + prefix + connector + RESET;

        if (left == right) {
            printAnimated(dimPrefix + "[" + array[left] + "]");
            return;
        }

        printAnimated(dimPrefix + BLUE + "Divide " + RESET + "[" + left + ".." + right + "]: " + rangeToString(array, left, right));

        String childPrefix = prefix + (isTail ? "   " : "\u2502  ");
        int middle = (left + right) / 2;
        mergeSortTree(array, left, middle, ascending, childPrefix, false);
        mergeSortTree(array, middle + 1, right, ascending, childPrefix, true);

        mergeQuiet(array, left, middle, right, ascending);
        printAnimated(DIM + childPrefix + RESET + MAGENTA + "Merge  " + RESET + "[" + left + ".." + right + "]: " + rangeToString(array, left, right));
    }

    public static <T extends Comparable<T>> void mergeSortTree(T[] array, boolean ascending) {
        int left = 0, right = array.length - 1;
        printAnimated(BLUE + "Divide " + RESET + "[" + left + ".." + right + "]: " + rangeToString(array, left, right));

        int middle = (left + right) / 2;
        mergeSortTree(array, left, middle, ascending, "", false);
        mergeSortTree(array, middle + 1, right, ascending, "", true);

        mergeQuiet(array, left, middle, right, ascending);
        printAnimated(GREEN + BOLD + "Merge sort result: " + rangeToString(array, left, right) + RESET);
    }

    // ==========================================
    // COMMAND-LINE DEMO
    // ==========================================
    private static Integer[] freshSample() {
        return new Integer[] { 42, 17, 8, 56, 23, 4, 91, 30 };
    }

    private static void banner(String title) {
        String rule = "\u2500".repeat(46);
        System.out.println();
        System.out.println(CYAN + BOLD + rule + RESET);
        System.out.println(CYAN + BOLD + title + RESET);
        System.out.println(CYAN + BOLD + rule + RESET);
    }

    public static void main(String[] args) {
        boolean ascending = true; // change to false to sort descending

        if (args.length > 0 && args[0].equalsIgnoreCase("desc")) {
            ascending = false;
        }

        System.out.println(BOLD + "Order: " + RESET + (ascending ? "ascending" : "descending"));
        System.out.println(DIM + "Legend: " + RESET
            + YELLOW + "insert" + RESET + DIM + " · " + RESET
            + RED + "swap" + RESET + DIM + " · " + RESET
            + BLUE + "merge" + RESET + DIM + " · " + RESET
            + MAGENTA + "pivot" + RESET + DIM + " · " + RESET
            + GREEN + "sorted" + RESET);

        Integer[] a1 = freshSample();
        banner("Insertion sort");
        printLabeledArray("Starting array", DIM, a1, RESET);
        insertionSort(a1, ascending);
        printLabeledArray("Result", GREEN + BOLD, a1, GREEN);

        Integer[] a2 = freshSample();
        banner("Selection sort");
        printLabeledArray("Starting array", DIM, a2, RESET);
        selectionSort(a2, ascending);
        printLabeledArray("Result", GREEN + BOLD, a2, GREEN);

        Integer[] a3 = freshSample();
        banner("Merge sort");
        printLabeledArray("Starting array", DIM, a3, RESET);
        mergeSort(a3, 0, a3.length - 1, ascending);
        printLabeledArray("Result", GREEN + BOLD, a3, GREEN);

        Integer[] a3tree = freshSample();
        banner("Merge sort — divide/merge tree");
        mergeSortTree(a3tree, ascending);

        Integer[] a4 = freshSample();
        banner("Quick sort");
        printLabeledArray("Starting array", DIM, a4, RESET);
        quickSort(a4, 0, a4.length - 1, ascending);
        printLabeledArray("Result", GREEN + BOLD, a4, GREEN);
    }
}