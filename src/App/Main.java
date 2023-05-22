//package App;
//
//import javax.swing.*;
//import java.io.*;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) throws IOException {
//        System.out.println("Hello and welcome to the HeapMinMax\n");
//        System.out.println("Pls insert the values of the heap you want\n" +
//                "to build as a \"MaxMin Heap\", insert like 10,2,4,-33,2....\n" +
//                "if there is comma it will ignore them");
//        System.out.println("\nYour Heap: ");
//        Scanner scanner = new Scanner(System.in);
//        String message = scanner.nextLine();
//        if (message != null) {
//            String heapS[] = message.split(",");
//            int index = 0;
//            double[] heapInput = new double[heapS.length];
//            for (String s : heapS) {
//                //validate each value in the split string with regular expression
//                if (s.trim().matches("^-?\\d+(\\.\\d+)?$")) {
//                    heapInput[index] = Double.parseDouble(s.trim());
//                    index++;
//                }
//            }
//            heap = new Heap_MaxMin(heapInput);
//            heap.BUILD_HEAP();
//        }
//
//        int number = -1;
//        Scanner scan = new Scanner(System.in);
//        while (number != 0) {
//            number = printMenu();
//            switch (number) {
//                //Insert
//                case 1: {
//                    System.out.println("Pls choose value to Insert: ");
//                    double insertValue = scan.nextDouble();
//                    heap.HEAP_INSERT(insertValue);
//                    break;
//                }
//                //Delete
//                case 2: {
//                    System.out.println("Pls choose index to Delete: ");
//                    int indexToDelete = scan.nextInt();
//                    if (indexToDelete >= 0 && indexToDelete < heap.heap.length) {
//                        heap.HEAP_DELETE(indexToDelete);
//                    } else System.out.println("Invalid Value, pleas provide valid one");
//                    break;
//                }
//                //Max
//                case 3: {
//                    heap.HEAP_EXTRACT_MAX();
//                    break;
//                }
//                //Min
//                case 4: {
//                    heap.HEAP_EXTRACT_MIN();
//                    break;
//                }
//                //Print heap
//                case 5: {
//                    System.out.println("Print Heap");
//                    heap.printHeap();
//                    break;
//                }
//            }
//        }
//        System.out.println("you choose to Exit");
//    }
//
//
//    public static int printMenu(){
//        System.out.println();
//        System.out.println("0. Exit");
//        System.out.println("1. Insert value");
//        System.out.println("2. Delete index");
//        System.out.println("3. Extract max");
//        System.out.println("4. Extract min");
//        System.out.println("5. Print heap");
//
//
//        System.out.println("Pls enter the number you want to use: ");
//        Scanner scanner = new Scanner(System.in);
//        int number = scanner.nextInt();
//        return number;
//    }
//
//}
