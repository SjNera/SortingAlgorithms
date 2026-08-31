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
<<<<<<< HEAD
    public static void main(String[] args) {
    // Call the generic class here
    // Example usage of the DataType class
        Integer[] intArray = {5, 2, 9, 1, 5, 6};
        DataType<Integer> intData = new DataType<>(intArray);
        System.out.println("Integer Array: ");
        printArray(intData.get());

        String[] strArray = {"apple", "orange", "banana", "grape"};
        DataType<String> strData = new DataType<>(strArray);
        System.out.println("String Array: ");
        printArray(strData.get());    
    }
    
=======
>>>>>>> origin/Rhian

    public static <T> void bubbleSort(T[] array) {

    }

    public static <T extends Comparable<T>> void insertionSort(T[] array, boolean ascending) {
        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;

            // ascending: larger elements will move to the right
            if (ascending) {

                while (j >= 0 && array[j].compareTo(key) > 0) {
                    array[j + 1] = array[j];
                    j--;
                }

            } else {
                // descending: smaller elements will move to the left
                while (j >= 0 && array[j].compareTo(key) < 0) {
                    array[j + 1] = array[j];
                    j--;
                }
            }

            array[j + 1] = key;
        }
    }

    /**
     * Merge two subarrays of array[].
     * First subarray is array[left..middle]
     * Second subarray is array[middle+1..right]
    */ 
    public static <T extends Comparable <T>> void merge(T[] array, int left,int middle, int right ) {
        int node1 = middle - left + 1;
        int node2 = right - middle;

        // Create temporary arrays
        T[] leftArray = (T[]) new Object[node1];
        T[] rightArray = (T[]) new Object[node2];

        for (int i = 0; i < node1; ++i) {
            leftArray[i] = array[left + i];
            System.out.println("Merging: " + leftArray[i]);
        }
        for (int j = 0; j < node2; ++j) {
            rightArray[j] = array[middle + 1 + j];
            System.out.println("Merging: " + rightArray[j]);
        }

        int i = 0, j = 0;
        int k = left;

        while (i < node1 && j < node2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;

                System.out.println("Merging: " + array[k]);
            } else {
                array[k] = rightArray[j];
                j++;
                System.out.println("Merging: " + array[k]);
            }}

        while (i < node1) {
            array[k] = leftArray[i];
            i++;
            k++;
            System.out.println("Merging: " + array[k]);
        }

        while (j < node2) {
            array[k] = rightArray[j];
            j++;
            k++;
            System.out.println("Merging: " + array[k]);
        }
        }

    public static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Recursively sort first and second halves
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Merge the sorted halves
            merge(array, left, middle, right);
        }
    }


    /**
     * Random QuickSort algorithm implementation for generic types.
     * For this, the type T must implement the Comparable interface to allow comparison between elements.
     * 
     * @param <T>
     * @param array
     * 
     */

    // This function is for calculating random pivot index for quicksort 

    private static <T> void randomPivot(T[] array, int left, int right) {
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);
        
        T temp1 = array[pivotIndex];
        array[pivotIndex] = array[right];
        array[right] = temp1;        
    } 

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        randomPivot(array,left,right);  
        int pivotIndex = right;

        int i = (left - 1);

        for (int j = left; j < right; j++) {
            if (array[j].compareTo(array[pivotIndex]) <= 0){
                i++;

                T temp2 = array[i];
                array[i] = array[j];
                array[j] = temp2;

            }  
        }

        T temp = array[i + 1];
        array[i + 1] = array[pivotIndex];
        array[pivotIndex] = temp;


        return i + 1;
        
    }
 
    public static <T extends Comparable<T>> void quickSort(T[] array) {
        
    }

    

<<<<<<< HEAD
    // Helper methods for sorting algorithms can be added here
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }



=======
    // displays any type of array
    public static <T> void printArray(T[] array) {

        System.out.print("[");

        for (int i = 0; i < array.length; i++) {

            System.out.print(array[i]);

            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
>>>>>>> origin/Rhian
}