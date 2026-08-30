import java.util.Scanner;

public class SortingInterface {
    // Displays the data type menu
    public static int showDataTypeMenu(Scanner scanner) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("        SORTING ALGORITHM DEMO");
        System.out.println("========================================");
        System.out.println("[1] Integer");
        System.out.println("[2] String");
        System.out.println("[3] Exit");
        System.out.print("\nChoose data type: ");
        return scanner.nextInt();
    }

    // Displays the main menu and current data
    public static <T> int showMainMenu(
            Scanner scanner, T[] data) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("              MAIN MENU");
        System.out.println("========================================");
        System.out.println("Current Data:");

        AlgorithmExamples.printArray(data);

        System.out.println();
        System.out.println("[1] Choose Sorting Algorithm");
        System.out.println("[2] Change Data");
        System.out.println("[3] Exit");
        System.out.print("\nEnter choice: ");

        return scanner.nextInt();
    }


    // Displays the sorting algorithm menu
    public static int showAlgorithmMenu(Scanner scanner) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("         SORTING ALGORITHM MENU");
        System.out.println("========================================");
        System.out.println("[1] Insertion Sort");
        System.out.println("[2] Selection Sort");
        System.out.println("[3] Merge Sort");
        System.out.println("[4] Quick Sort");
        System.out.println("[5] Back");
        System.out.print("\nEnter choice: ");

        return scanner.nextInt();
    }

    // Displays the sorting order menu
    public static int showSortingOrderMenu(Scanner scanner) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("          SORTING ORDER MENU");
        System.out.println("========================================");
        System.out.println("[1] Ascending");
        System.out.println("[2] Descending");
        System.out.print("\nChoose sorting order: ");
        return scanner.nextInt();
    }

    // Displays the menu after sorting
    public static int showAfterSortMenu(Scanner scanner) {
        System.out.println();
        System.out.println("[1] Return to Main Menu");
        System.out.println("[2] Exit");
        System.out.print("\nEnter choice: ");
        return scanner.nextInt();
    }

    // Displays a message
    public static void showMessage(String message) {
        System.out.println("\n" + message);
    }
}