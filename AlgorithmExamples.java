import java.util.Arrays;
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
    public static void main(String[] args) {
        // Examples usage

        String[] stringArray = {"banana", "apple", "cherry", "date"};
        System.out.println();
        DataType<String> stringData = new DataType<>(stringArray);

        System.out.println("Original String Array:" + Arrays.toString(stringData.get()));
        System.out.println();
        
        Integer[] intArray = {5, 2, 9, 1, 5, 6};
        DataType<Integer> intData = new DataType<>(intArray);

        System.out.println("Original Integer Array:" + Arrays.toString(intData.get()));
        System.out.println();


        quickSort(stringArray, 0, stringArray.length - 1, true);
        System.out.println("Sorted String Array:" + Arrays.toString(stringArray));
        System.out.println();

        quickSort(intArray, 0, intArray.length - 1, true);
        System.out.println("Sorted Integer Array:" + Arrays.toString(intArray));
        System.out.println();

        mergeSort(stringArray, 0, stringArray.length - 1, false);
        System.out.println("Merged String Array:" + Arrays.toString(stringArray));
        System.out.println();

        mergeSort(intArray, 0, intArray.length - 1, true);
        System.out.println("Merged Integer Array:" + Arrays.toString(intArray));    
        System.out.println();
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


        public static <T extends Comparable<T>> void selectionSort(T[] array, boolean ascending) {
        for (int i = 0; i < array.length - 1; i++) {
            int selectedIndex = i;

            // ascending: find the smallest element in the remaining array
            if (ascending) {

                for (int j = i + 1; j < array.length; j++) {
                    if (array[j].compareTo(array[selectedIndex]) < 0) {
                        selectedIndex = j;
                    }
                }

            } else {
                // descending: find the largest element in the remaining array
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j].compareTo(array[selectedIndex]) > 0) {
                        selectedIndex = j;
                    }
                }
            }

            T temp = array[selectedIndex];
            array[selectedIndex] = array[i];
            array[i] = temp;
        }
    }
    
    /**
     * Merge two subarrays of array[].
     * First subarray is array[left..middle]
     * Second subarray is array[middle+1..right]
    */ 
    public static <T extends Comparable <T>> void merge(T[] array, int left,int middle, int right, boolean ascending ) {
        int node1 = middle - left + 1;
        int node2 = right - middle;

        // Create temporary arrays
        @SuppressWarnings("unchecked")
        T[] leftArray = (T[]) new Comparable[node1];

        @SuppressWarnings("unchecked")
        T[] rightArray = (T[]) new Comparable[node2];

        for (int i = 0; i < node1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < node2; ++j) {
            rightArray[j] = array[middle + 1 + j];

        }

        int i = 0, j = 0;
        int k = left;

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
        }

    public static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right, boolean ascending) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Recursively sort first and second halves
            mergeSort(array, left, middle, ascending);
            mergeSort(array, middle + 1, right, ascending);

            // Merge the sorted halves
            merge(array, left, middle, right, ascending);
        }
    }


    /**x
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

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right, boolean ascending) {
        randomPivot(array,left,right);  
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


        return i + 1;
        
    }
 
    public static <T extends Comparable<T>> void quickSort(T[] array, int left, int right, boolean ascending) {
        if ( left < right) {
            int pivotIndex = partition(array,left,right,ascending);
            
            quickSort(array, left, pivotIndex - 1, ascending);
            quickSort(array, pivotIndex + 1, right, ascending);

        }   
    }   

    

    // Helper methods for sorting algorithms can be added here
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }



}