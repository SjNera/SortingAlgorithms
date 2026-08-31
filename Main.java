import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dataTypeChoice = 0;
        int mainChoice;

        // Stores the user's original data
        Integer[] integerData = null;
        String[] stringData = null;

        // Choose data type
        do {
            dataTypeChoice = SortingInterface.showDataTypeMenu(scanner);
            scanner.nextLine();

            switch (dataTypeChoice) {
                case 1:
                    System.out.println("\nYou selected Integer.");
                    System.out.print("Enter integers separated by spaces: ");

                    // Read integer input
                    String integerInput = scanner.nextLine();
                    String[] integerValues = integerInput.trim().split("\\s+");

                    integerData = new Integer[integerValues.length];

                    // Convert String values to Integer
                    for (int i = 0; i < integerValues.length; i++) {
                        integerData[i] = Integer.parseInt(integerValues[i]);
                    }

                    SortingInterface.showMessage("Data saved!");
                    break;

                case 2:
                    System.out.println("\nYou selected String.");
                    System.out.print("Enter strings separated by spaces: ");

                    // Read String input
                    String stringInput = scanner.nextLine();
                    stringData = stringInput.trim().split("\\s+");

                    SortingInterface.showMessage("Data saved!");
                    break;

                case 3:
                    SortingInterface.showMessage("Exiting program.");
                    scanner.close();
                    return;

                default:
                    SortingInterface.showMessage("Invalid choice!");
            }
        } while (dataTypeChoice != 1 && dataTypeChoice != 2);

        // Main menu
        do {
            // Display current data and main menu
            if (dataTypeChoice == 1) {
                mainChoice = SortingInterface.showMainMenu(scanner, integerData);
            } else {
                mainChoice = SortingInterface.showMainMenu(scanner, stringData);
            }

            scanner.nextLine();

            switch (mainChoice) {
                // Choose sorting algorithm
                case 1:
                    int algorithmChoice;
                    boolean returnToMainMenu = false;

                    // Algorithm menu
                    do {
                        algorithmChoice = SortingInterface.showAlgorithmMenu(scanner);
                        scanner.nextLine();

                        switch (algorithmChoice) {
                            // Insertion Sort
                            case 1: {
                                int sortingOrder = SortingInterface.showSortingOrderMenu(scanner);
                                scanner.nextLine();

                                switch (sortingOrder) {
                                    // Ascending
                                    case 1:
                                        SortingInterface.showMessage("Ascending Order selected.");

                                        if (dataTypeChoice == 1) {
                                            Integer[] workingData = integerData.clone();
                                            AlgorithmExamples.insertionSort(workingData, true);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        } else {
                                            String[] workingData = stringData.clone();
                                            AlgorithmExamples.insertionSort(workingData, true);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        }

                                        int afterSortChoice = SortingInterface.showAfterSortMenu(scanner);
                                        scanner.nextLine();

                                        if (afterSortChoice == 1) {
                                            returnToMainMenu = true;
                                        } else if (afterSortChoice == 2) {
                                            SortingInterface.showMessage("Thank you for using the program!");
                                            scanner.close();
                                            return;
                                        } else {
                                            SortingInterface.showMessage("Invalid choice!");
                                            returnToMainMenu = true;
                                        }
                                        break;

                                    // Descending
                                    case 2:
                                        SortingInterface.showMessage("Descending Order selected.");

                                        if (dataTypeChoice == 1) {
                                            Integer[] workingData = integerData.clone();
                                            AlgorithmExamples.insertionSort(workingData, false);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        } else {
                                            String[] workingData = stringData.clone();
                                            AlgorithmExamples.insertionSort(workingData, false);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        }

                                        afterSortChoice = SortingInterface.showAfterSortMenu(scanner);
                                        scanner.nextLine();

                                        if (afterSortChoice == 1) {
                                            returnToMainMenu = true;
                                        } else if (afterSortChoice == 2) {
                                            SortingInterface.showMessage("Thank you for using the program!");
                                            scanner.close();
                                            return;
                                        } else {
                                            SortingInterface.showMessage("Invalid choice!");
                                            returnToMainMenu = true;
                                        }
                                        break;

                                    default:
                                        SortingInterface.showMessage("Invalid choice!");
                                }
                                break;
                            }

                            // Selection Sort
                            case 2:
                                SortingInterface.showMessage("Selection Sort will go here.");
                                break;

                            // Merge Sort
                            case 3: {
                                int sortingOrder = SortingInterface.showSortingOrderMenu(scanner);
                                scanner.nextLine();

                                switch (sortingOrder) {
                                    // Ascending
                                    case 1:
                                        SortingInterface.showMessage("Ascending Order selected.");

                                        if (dataTypeChoice == 1) {
                                            Integer[] workingData = integerData.clone();
                                            AlgorithmExamples.mergeSort(workingData, 0, workingData.length - 1, true);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        } else {
                                            String[] workingData = stringData.clone();
                                            AlgorithmExamples.mergeSort(workingData, 0, workingData.length - 1, true);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        }

                                        int afterSortChoice = SortingInterface.showAfterSortMenu(scanner);
                                        scanner.nextLine();

                                        if (afterSortChoice == 1) {
                                            returnToMainMenu = true;
                                        } else if (afterSortChoice == 2) {
                                            SortingInterface.showMessage("Thank you for using the program!");
                                            scanner.close();
                                            return;
                                        } else {
                                            SortingInterface.showMessage("Invalid choice!");
                                            returnToMainMenu = true;
                                        }
                                        break;

                                    // Descending
                                    case 2:
                                        SortingInterface.showMessage("Descending Order selected.");

                                        if (dataTypeChoice == 1) {
                                            Integer[] workingData = integerData.clone();
                                            AlgorithmExamples.mergeSort(workingData, 0, workingData.length - 1, false);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        } else {
                                            String[] workingData = stringData.clone();
                                            AlgorithmExamples.mergeSort(workingData, 0, workingData.length - 1, false);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        }

                                        afterSortChoice = SortingInterface.showAfterSortMenu(scanner);
                                        scanner.nextLine();

                                        if (afterSortChoice == 1) {
                                            returnToMainMenu = true;
                                        } else if (afterSortChoice == 2) {
                                            SortingInterface.showMessage("Thank you for using the program!");
                                            scanner.close();
                                            return;
                                        } else {
                                            SortingInterface.showMessage("Invalid choice!");
                                            returnToMainMenu = true;
                                        }
                                        break;

                                    default:
                                        SortingInterface.showMessage("Invalid choice!");
                                }
                                break;
                            }

                            // Quick Sort
                            case 4: {
                                int sortingOrder = SortingInterface.showSortingOrderMenu(scanner);
                                scanner.nextLine();

                                switch (sortingOrder) {
                                    // Ascending
                                    case 1:
                                        SortingInterface.showMessage("Ascending Order selected.");

                                        if (dataTypeChoice == 1) {
                                            Integer[] workingData = integerData.clone();
                                            AlgorithmExamples.quickSort(workingData, 0, workingData.length - 1, true);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        } else {
                                            String[] workingData = stringData.clone();
                                            AlgorithmExamples.quickSort(workingData, 0, workingData.length - 1, true);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        }

                                        int afterSortChoice = SortingInterface.showAfterSortMenu(scanner);
                                        scanner.nextLine();

                                        if (afterSortChoice == 1) {
                                            returnToMainMenu = true;
                                        } else if (afterSortChoice == 2) {
                                            SortingInterface.showMessage("Thank you for using the program!");
                                            scanner.close();
                                            return;
                                        } else {
                                            SortingInterface.showMessage("Invalid choice!");
                                            returnToMainMenu = true;
                                        }
                                        break;

                                    // Descending
                                    case 2:
                                        SortingInterface.showMessage("Descending Order selected.");

                                        if (dataTypeChoice == 1) {
                                            Integer[] workingData = integerData.clone();
                                            AlgorithmExamples.quickSort(workingData, 0, workingData.length - 1, false);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        } else {
                                            String[] workingData = stringData.clone();
                                            AlgorithmExamples.quickSort(workingData, 0, workingData.length - 1, false);
                                            System.out.print("Sorted Data: ");
                                            AlgorithmExamples.printArray(workingData);
                                        }

                                        afterSortChoice = SortingInterface.showAfterSortMenu(scanner);
                                        scanner.nextLine();

                                        if (afterSortChoice == 1) {
                                            returnToMainMenu = true;
                                        } else if (afterSortChoice == 2) {
                                            SortingInterface.showMessage("Thank you for using the program!");
                                            scanner.close();
                                            return;
                                        } else {
                                            SortingInterface.showMessage("Invalid choice!");
                                            returnToMainMenu = true;
                                        }
                                        break;

                                    default:
                                        SortingInterface.showMessage("Invalid choice!");
                                }
                                break;
                            }

                            // Back
                            case 5:
                                SortingInterface.showMessage("Going back to Main Menu.");
                                returnToMainMenu = true;
                                break;

                            default:
                                SortingInterface.showMessage("Invalid choice!");
                        }
                    } while (!returnToMainMenu);
                    break;

                // Change data
                case 2:
                    System.out.println("\nChanging data...");

                    do {
                        dataTypeChoice = SortingInterface.showDataTypeMenu(scanner);
                        scanner.nextLine();

                        switch (dataTypeChoice) {
                            case 1:
                                System.out.println("\nYou selected Integer.");
                                System.out.print("Enter integers separated by spaces: ");

                                // Read new integer input
                                String newIntegerInput = scanner.nextLine();
                                String[] newIntegerValues = newIntegerInput.trim().split("\\s+");

                                integerData = new Integer[newIntegerValues.length];

                                // Convert String values to Integer
                                for (int i = 0; i < newIntegerValues.length; i++) {
                                    integerData[i] = Integer.parseInt(newIntegerValues[i]);
                                }

                                SortingInterface.showMessage("Data updated!");
                                break;

                            case 2:
                                System.out.println("\nYou selected String.");
                                System.out.print("Enter strings separated by spaces: ");

                                // Read new String input
                                String newStringInput = scanner.nextLine();
                                stringData = newStringInput.trim().split("\\s+");

                                SortingInterface.showMessage("Data updated!");
                                break;

                            case 3:
                                // Return to Main Menu
                                break;

                            default:
                                SortingInterface.showMessage("Invalid choice!");
                        }
                    } while (dataTypeChoice != 1 &&
                            dataTypeChoice != 2 &&
                            dataTypeChoice != 3);
                    break;

                // Exit
                case 3:
                    SortingInterface.showMessage("Thank you for using the program!");
                    break;

                default:
                    SortingInterface.showMessage("Invalid choice!");
            }
        } while (mainChoice != 3);

        scanner.close();
    }
}