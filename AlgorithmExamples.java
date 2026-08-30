class DataType<T> {
        private T[] array;

        public DataType(T[] array) {
            this.array = array;
        }

        public T[] get() {
            return array;
        }

        public void set(T[] array) {
            this.array = array;
        }



}


public class AlgorithmExamples {
    public static void main(String[] args) {
    // Call the generic class here    
    }
    

    public static <T> void bubbleSort(T[] array) {

    }
    
    public static <T> void insertionSort(T[] array) {

    }

    public static <T> void mergeSort(T[] array) {

    }

    public static <T> void quickSort(T[] array) {

    }


    // Helper methods for sorting algorithms can be added here
    public void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

}