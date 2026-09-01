import java.util.HashMap;
import java.util.Map;
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
    // ANIMATION / VISUALIZATION CONFIG
    // ==========================================

    // Flip to false to fall back to the original plain-text step log
    // (useful if a console/IDE doesn't render ANSI colors properly).
    private static final boolean ANIMATE_WITH_BOXES = true;

    // How long (ms) each animation frame stays on screen before the next one.
    private static final int ANIMATION_DELAY_MS = 800;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    private static final String ANSI_BRIGHT_RED = "\u001B[91m";
    private static final String ANSI_BRIGHT_MAGENTA = "\u001B[95m";
    private static final String ANSI_BRIGHT_CYAN = "\u001B[96m";

    // The "role" a box plays in the current animation frame, and the color
    // that goes with that role.
    private enum BoxState {
        DEFAULT(ANSI_RESET, "Untouched"),
        SORTED(ANSI_BRIGHT_GREEN, "Sorted"),
        ACTIVE(ANSI_BRIGHT_RED, "Moving"),
        PIVOT(ANSI_BRIGHT_MAGENTA, "Pivot"),
        RANGE(ANSI_BRIGHT_CYAN, "Active Range");

        final String color;
        final String label;

        BoxState(String color, String label) {
            this.color = color;
            this.label = label;
        }
    }

    // ==========================================
    // ANIMATION HELPER
    // ==========================================
    private static void pauseAndPrint(String message, Object[] array, Map<Integer, BoxState> highlights) {
        if (ANIMATE_WITH_BOXES) {
            printBoxes(message, array, highlights);
        } else {
            System.out.printf("%-35s : ", message);
            for (Object element : array) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        try {
            // Pauses the program to create an animation effect
            Thread.sleep(ANIMATION_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Draws every element as a colored box and redraws the terminal in place
    // each frame, so it plays like a real animation instead of a scrolling log.
    private static void printBoxes(String message, Object[] array, Map<Integer, BoxState> highlights) {
        int maxLen = 1;
        for (Object element : array) {
            maxLen = Math.max(maxLen, String.valueOf(element).length());
        }
        int boxWidth = maxLen + 2;
        int cellWidth = boxWidth + 2;

        StringBuilder top = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        StringBuilder bot = new StringBuilder();
        StringBuilder idxLine = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            BoxState state = highlights.getOrDefault(i, BoxState.DEFAULT);
            String color = state.color;
            String value = String.valueOf(array[i]);

            int pad = boxWidth - value.length();
            int padLeft = pad / 2;
            int padRight = pad - padLeft;

            top.append(color).append('+').append("-".repeat(boxWidth)).append('+').append(ANSI_RESET).append(' ');
            mid.append(color).append('|').append(" ".repeat(padLeft)).append(value).append(" ".repeat(padRight)).append('|').append(ANSI_RESET).append(' ');
            bot.append(color).append('+').append("-".repeat(boxWidth)).append('+').append(ANSI_RESET).append(' ');

            String idxStr = String.valueOf(i);
            int idxPad = cellWidth - idxStr.length();
            int idxPadLeft = idxPad / 2;
            int idxPadRight = idxPad - idxPadLeft;
            idxLine.append(" ".repeat(idxPadLeft)).append(idxStr).append(" ".repeat(idxPadRight)).append(' ');
        }

        clearScreen();
        System.out.println(ANSI_BOLD + "  SORTING ANIMATION" + ANSI_RESET);
        System.out.println("  " + "=".repeat(50));
        System.out.println();
        System.out.println(top);
        System.out.println(mid);
        System.out.println(bot);
        System.out.println(idxLine);
        System.out.println();
        System.out.println("  " + message);
        System.out.println();
        System.out.println("  Legend: "
                + BoxState.SORTED.color + "Sorted" + ANSI_RESET + "   "
                + BoxState.ACTIVE.color + "Moving" + ANSI_RESET + "   "
                + BoxState.PIVOT.color + "Pivot" + ANSI_RESET + "   "
                + BoxState.RANGE.color + "Active Range" + ANSI_RESET);
    }

    private static void clearScreen() {
        System.out.print("\u001B[H\u001B[2J");
        System.out.flush();
    }

    // Renders one final, all-green frame once an algorithm finishes sorting.
    private static void printSortedFrame(Object[] array) {
        Map<Integer, BoxState> highlights = new HashMap<>();
        for (int k = 0; k < array.length; k++) {
            highlights.put(k, BoxState.SORTED);
        }
        pauseAndPrint("Sorted!", array, highlights);
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

            Map<Integer, BoxState> highlights = new HashMap<>();
            for (int k = 0; k <= i; k++) {
                highlights.put(k, BoxState.SORTED);
            }
            highlights.put(j + 1, BoxState.ACTIVE);
            pauseAndPrint("Inserting element " + key, array, highlights);
        }
        printSortedFrame(array);
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

            Map<Integer, BoxState> highlights = new HashMap<>();
            for (int k = 0; k <= i; k++) {
                highlights.put(k, BoxState.SORTED);
            }
            highlights.put(i, BoxState.ACTIVE);
            pauseAndPrint("Swapping to index " + i, array, highlights);
        }
        printSortedFrame(array);
    }

    public static <T extends Comparable<T>> void merge(T[] array, int left, int middle, int right, boolean ascending) {
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
            i++;
            k++;
        }
        while (j < node2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }

        boolean isFullArray = (left == 0 && right == array.length - 1);
        Map<Integer, BoxState> highlights = new HashMap<>();
        for (int idx = 0; idx < array.length; idx++) {
            if (idx >= left && idx <= right) {
                highlights.put(idx, isFullArray ? BoxState.SORTED : BoxState.RANGE);
            } else {
                highlights.put(idx, BoxState.DEFAULT);
            }
        }
        String label = isFullArray ? "Sorted!" : "Merged indices [" + left + " to " + right + "]";
        pauseAndPrint(label, array, highlights);
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
            if (condition) {
                i++;
                T temp2 = array[i];
                array[i] = array[j];
                array[j] = temp2;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[pivotIndex];
        array[pivotIndex] = temp;

        int pivotFinalIndex = i + 1;
        Map<Integer, BoxState> highlights = new HashMap<>();
        for (int idx = 0; idx < array.length; idx++) {
            if (idx == pivotFinalIndex) {
                highlights.put(idx, BoxState.PIVOT);
            } else if (idx >= left && idx <= right) {
                highlights.put(idx, BoxState.RANGE);
            } else {
                highlights.put(idx, BoxState.DEFAULT);
            }
        }
        pauseAndPrint("Partitioned around pivot '" + array[pivotFinalIndex] + "'", array, highlights);
        return pivotFinalIndex;
    }

    public static <T extends Comparable<T>> void quickSort(T[] array, int left, int right, boolean ascending) {
        if (left < right) {
            int pivotIndex = partition(array, left, right, ascending);
            quickSort(array, left, pivotIndex - 1, ascending);
            quickSort(array, pivotIndex + 1, right, ascending);
        }
        if (left == 0 && right == array.length - 1) {
            printSortedFrame(array);
        }
    }

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}