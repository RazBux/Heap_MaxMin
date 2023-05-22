package App;

import java.io.IOException;
import java.util.Scanner;

public class Heap_MaxMin {
    double[] heap;

    public Heap_MaxMin(double[] heap){
        this.heap = heap;
    }

    /**
     * Heap assume that the subTree below is ordered, and just make sure the value
     * we give to the method will be at the right index when it's finished to run
     * @param index we want to heapify
     */
    public void HEAPIFY(int index){
        //validate the index
        if (index < 0 || index > heap.length-1)
            return;

        // Calculate the depth of the node using floor(log2(index + 1))
        int depth = getDepth(index);
        System.out.println(">> heapify index:" + index + " at depth:" + depth);

        //if the depth is even
        if (depth % 2 == 0) {
            int largest = findLargestChild(index);
            boolean keepHeapify = false;//indicate if we need to keep heapify or not

            //exchange values if needed
            if (largest != index && largest != Integer.MIN_VALUE) {
                swap(index,largest);
                keepHeapify = true;
            }
            //checking the grandsons
            int largestGrandSon = checkGrandSons(index,true);
            if (largestGrandSon != -1 && heap[largestGrandSon] > heap[index]) {
                largest = largestGrandSon;//update the largest for the next heapify
                swap(index, largestGrandSon);
                keepHeapify = true;
            }

            //if needed keep heapifying
            if (keepHeapify)
                HEAPIFY(largest);//continue to heapify

            else System.out.println("--- noting to heapify ---");
        }//end of even depth

        //if the depth is odd
        else {
            int smallest = findSmallestChild(index);
            boolean keepHeapify = false;//indicate if we need to keep heapify or not

            //exchange values if needed
            if (smallest != index && smallest != Integer.MIN_VALUE) {
                swap(smallest,index);
                keepHeapify = true;
            }

            //checking the grandsons
            int smallestGrandSon = checkGrandSons(index,false);
            if (smallestGrandSon != -1 && heap[smallestGrandSon] < heap[index]) {
                smallest = smallestGrandSon;//update the largest for the next heapify
                swap(index, smallestGrandSon);
                keepHeapify = true;
            }

            //if needed keep heapifying
            if (keepHeapify)
                HEAPIFY(smallest);//continue to heapify

            else System.out.println("--- noting to heapify ---");

        }//end of depth odd
    }//end of Heapify


    /**
     * build heap
     */
    public void BUILD_HEAP(){
        System.out.println(">> Build Heap Start");
        for (int i = heap.length/2; i >= 0  ; i--) {
            HEAPIFY(i);
        }
        printHeap();
    }

    public double HEAP_EXTRACT_MAX(){
        System.out.println("Max Value: " + heap[0]);
        return heap[0];
    }

    public double HEAP_EXTRACT_MIN(){
        double minVlaue = heap[0];
        if (heap.length >= 3)
            minVlaue = heap[1] > heap[2] ? heap[2] : heap[1];
        else if (heap.length == 2 )
            minVlaue = heap[1];
        System.out.println("Min Value: " + minVlaue);
        return minVlaue;
    }

    public void HEAP_INSERT(double key){
        System.out.println(">>> Insert " + key);
        double heapForBuild[] = new double[heap.length+1];
        for (int i =0; i < heap.length; i++) {
            heapForBuild[i] = heap[i];
        }
        heapForBuild[heapForBuild.length-1] = key;
        heap = heapForBuild;

        //heapify up
        int index = heap.length-1;
        while (index > 0) {
            //heapify up
            index = getParent(index);
            HEAPIFY(index);
        }
        System.out.print("New heap: ");
        printHeap();
    }

    /**
     * delete the given index from the heap and then order the heap to match
     * the roles of min-max heap
     * @param indexToDelete
     */
    public void HEAP_DELETE(int indexToDelete){
        System.out.println(">>> delete " + indexToDelete);
        //validate the index to delete
        if (indexToDelete < heap.length && indexToDelete >=0) {

            // Replace the value with the last element in the heap
            heap[indexToDelete] = heap[heap.length - 1];
            double heapForBuild[] = new double[heap.length - 1];
            for (int i = 0; i < heap.length - 1; i++)
                heapForBuild[i] = heap[i];
            heap = heapForBuild;
            System.out.print("Heap after delete: ");
            printHeap();
            HEAPIFY(indexToDelete);
            System.out.print("Fixed heap: ");
            printHeap();
        }
        else System.out.println("Pls provide a valid index between 0 to " + heap.length);
    }

    /**Left-Son method*/
    private boolean hasLeftSon(int index){
        return (2*index+1 < this.heap.length);
    }
    private double getLeftSonValue(int index){
        return heap[2*index+1];
    }
    private int getLeftSonIndex(int index){
        return 2*index+1;
    }

    /**Right-Son method*/
    private boolean hasRightSon(int index){
        return (2*index+2 < this.heap.length);
    }
    private double getRightSonValue(int index){
        return heap[2*index+2];
    }
    private int getRightSonIndex(int index){
        return 2*index+2;
    }

    private int getParent(int index){
        int parent = (index - 1) / 2;
        if (parent >= 0 && parent < heap.length)
            return parent;
        return -1;
    }

    /**provide the depth of the given index in the heap*/
    private int getDepth(int index){
        if (index >= heap.length || index < 0)
            return -1;
        return (int) (Math.floor(Math.log(index + 1) / Math.log(2)));
    }

    /**
     * This method return the largest grandson of the given index
     * if there isn't any index fit for that, return -1
     */
    private int checkGrandSons(int index, boolean max){
        if (index >= heap.length || index < 0) {
            System.out.println("invalid index pls enter valid one");
            return -1;
        }
        int Left_LeftSon = getLeftSonIndex(getLeftSonIndex(index));
        int Right_LeftSon = getRightSonIndex(getLeftSonIndex(index));
        int Left_RightSon = getLeftSonIndex(getRightSonIndex(index));
        int Right_RightSon = getRightSonIndex(getRightSonIndex(index));
        //add all the grandsons to array
        int[] allGrandSons = {Left_LeftSon,Right_LeftSon,Left_RightSon,Right_RightSon};

        if (max) {
            int maxValueIndex = index;
            for (int i : allGrandSons) {
                if (i < heap.length && heap[i] > heap[maxValueIndex]) {
                    maxValueIndex = i;
                }
            }
            if (maxValueIndex != index)
                return maxValueIndex;
            else
                return -1;

        }
        else {
            int minValueIndex = index;
            for (int i : allGrandSons) {
                if (i < heap.length && heap[i] < heap[minValueIndex])
                    minValueIndex = i;
            }
            if (minValueIndex != index)
                return minValueIndex;
            else
                return -1;
        }
    }

    private void swap(int index1, int index2){
        double temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
        System.out.println(heap[index1]+" <--> " + heap[index2]);
        printHeap();
    }

    private int findSmallestChild(int index) {
        int smallest = Integer.MIN_VALUE;
        if (hasLeftSon(index)) {
            double left = getLeftSonValue(index);
            if (left < heap[index])
                smallest = getLeftSonIndex(index);
            else
                smallest = index;
        }
        if (hasRightSon(index)) {
            double right = getRightSonValue(index);
            if (right < heap[smallest])
                smallest = getRightSonIndex(index);
        }
        return smallest;
    }

    private int findLargestChild(int index) {
        int largest = Integer.MIN_VALUE;
        if (hasLeftSon(index)) {
            double left = getLeftSonValue(index);
            if (left > heap[index])
                largest = getLeftSonIndex(index);
            else
                largest = index;
        }
        if (hasRightSon(index)) {
            double right = getRightSonValue(index);
            if (right > heap[largest])
                largest = getRightSonIndex(index);
        }
        return largest;
    }

    /**
     * method for printing the heap --> will look like this: [10.0, 2.2, 5.55....]
     */
    public void printHeap(){
        if (heap != null && heap.length > 0) {
            //printing the heap we got from the file
            System.out.print("HEAP: ");
            System.out.print("[");
            for (int i1 = 0; i1 < heap.length; i1++) {
                if (i1 != heap.length - 1)
                    System.out.print(heap[i1] + ", ");
                else System.out.println(heap[i1] + "]\n");
            }
        }
        else System.out.println("Pls provide a valid heap");
    }

    //check the aliasing
    private static void sortHeap(Heap_MaxMin heap){
        double heapF[] = new double[heap.heap.length];
        //copy all the object to avoid aliasing
        for (int i =0; i< heapF.length; i++)
            heapF[i] = heap.heap[i];

        Heap_MaxMin h = new Heap_MaxMin(heapF);

        double[] sortHeap = new double[heapF.length];
        int indexToFill = heapF.length-1;
        for (int i = 0; i<sortHeap.length; i++){
            sortHeap[indexToFill] = h.heap[0];
            indexToFill--;

            h.HEAP_DELETE(0);
        }

        //print the sorted heap
        System.out.print("SORTED HEAP: ");
        System.out.print("[");
        for (int i1 = 0; i1 < sortHeap.length; i1++) {
            if (i1 != sortHeap.length - 1)
                System.out.print(sortHeap[i1] + ", ");
            else System.out.println(sortHeap[i1] + "]");
        }
        System.out.println("FYI the original heap remain the same\n");
    }
//10,54,8,5550,-80,10,0,1,2,3,4,5,6


    //Main
    public static void main(String[] args) throws IOException {
        Heap_MaxMin heapMaxMin = null;
        System.out.println("Hello and welcome to the HeapMinMax\n");
        System.out.println("Pls insert the values of the heap you want\n" +
                "to build as a \"MaxMin Heap\", insert like 10,2,4,-33,2....\n" +
                "if there is comma it will ignore them");
        System.out.println("\nYour Heap: ");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        if (message != null) {
            String heapS[] = message.split(",");
            int index = 0;
            double[] heapInput = new double[heapS.length];
            for (String s : heapS) {
                //validate each value in the split string with regular expression
                if (s.trim().matches("^-?\\d+(\\.\\d+)?$")) {
                    heapInput[index] = Double.parseDouble(s.trim());
                    index++;
                }
            }
            heapMaxMin = new Heap_MaxMin(heapInput);
            heapMaxMin.BUILD_HEAP();
        }

        int number = -1;
        Scanner scan = new Scanner(System.in);
        while (number != 0) {
            number = printMenu();
            switch (number) {
                //Exit
                case 0:
                    break;
                //Insert
                case 1: {
                    System.out.println("Pls choose value to Insert: ");
                    double insertValue = scan.nextDouble();
                    heapMaxMin.HEAP_INSERT(insertValue);
                    break;
                }
                //Delete
                case 2: {
                    System.out.println("Pls choose index to Delete: ");
                    int indexToDelete = scan.nextInt();
                    if (indexToDelete >= 0 && indexToDelete < heapMaxMin.heap.length) {
                        heapMaxMin.HEAP_DELETE(indexToDelete);
                    } else System.out.println("Invalid Value, pleas provide valid one");
                    break;
                }
                //Max
                case 3: {
                    heapMaxMin.HEAP_EXTRACT_MAX();
                    break;
                }
                //Min
                case 4: {
                    heapMaxMin.HEAP_EXTRACT_MIN();
                    break;
                }
                //Print heap
                case 5: {
                    System.out.println("Print Heap");
                    heapMaxMin.printHeap();
                    break;
                }
                //Print heap
                case 6: {
                    sortHeap(heapMaxMin);
                    break;
                }
                default: {
                    System.out.println(">>> invalid option pls choose valid one...");
                }
            }
        }
        System.out.println("you choose to Exit");
    }


    public static int printMenu(){
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("1. Insert value");
        System.out.println("2. Delete index");
        System.out.println("3. Extract max");
        System.out.println("4. Extract min");
        System.out.println("5. Print heap");
        System.out.println("6. Sort heap");


        System.out.println("Pls enter the number you want to use: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return number;
    }
}
