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

    public static <T> void mergeSort(T[] array) {

    }

    public static <T> void quickSort(T[] array) {

    }


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
}