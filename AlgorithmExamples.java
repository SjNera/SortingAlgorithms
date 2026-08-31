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
    // ANIMATION HELPER
    // ==========================================
    private static void pauseAndPrint(String message, Object[] array) {
        System.out.printf("%-35s : ", message);
        for (Object element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
        try {
            // Pauses the program for 800 milliseconds to create an animation effect
            Thread.sleep(800); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
            pauseAndPrint("Inserting element " + key, array);
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
            
            pauseAndPrint("Swapping to index " + i, array);
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
        
        // ANIMATION PRINT
        pauseAndPrint("Merged indices [" + left + " to " + right + "]", array);
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

        // ANIMATION PRINT
        pauseAndPrint("Partitioned around pivot '" + array[i + 1] + "'", array);

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
}