package App;

public class Heap_MaxMin {
    double heap[];

    public Heap_MaxMin(double[] heap){
        this.heap = heap;
    }

    /**
     * heap assume that the subTree below are ordered, and just make sure the value
     * we give to the method will be at the right index when it's finished
     * @param index we want to heapify
     */
    public void HEAPIFY(int index){
        // Calculate the depth of the node using floor(log2(index + 1))
        int depth = (int) (Math.floor(Math.log(index + 1) / Math.log(2)));
        System.out.println(">> heapify index:" + index + " at depth:" + depth);

        //if the depth is even
        if (depth % 2 == 0) {

            int largest = Integer.MIN_VALUE;
            if (hasLeftSon(index)){
                 double left = getLeftSonValue(index);
                if (left > heap[index])
                    largest = 2*index+1;
                else largest = index;
            }

            if (hasRightSon(index)){
                double right = getRightSonValue(index);
                if (right > heap[largest])
                    largest = 2*index+2;
            }

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

            //if needed keep to heapify
            if (keepHeapify)
                HEAPIFY(largest);//continue to heapify

            else System.out.println("--- noting to heapify ---");
        }//end of even depth

        /**if the depth is ODD*/
        else {
            int smallest = Integer.MIN_VALUE;
            if (hasLeftSon(index)){
                double left = getLeftSonValue(index);
                if (left < heap[index])
                    smallest = 2*index+1;
                else smallest = index;
            }

            if (hasRightSon(index)){
                double right = getRightSonValue(index);
                if (right < heap[smallest])
                    smallest = 2*index+2;
            }

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

            //if needed keep to heapify
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
    }

    public double HEAP_EXTRACT_MAX(){
        return heap[0];
    }

    private void HEAP_EXTRACT_MIN(double[] A){

    }

    private void HEAP_INSERT(double[] A, int key){

    }

    private void HEAP_DELETE(double[] A, int i){

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

    /**
     * this method return the largest grandson of the given index
     * if there isn't any index fit for that, return -1
     */
    public int checkGrandSons(int index, boolean max){
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

//
//            //printing the heap we got from the file
//            System.out.print("HEAP: ");
//            System.out.print("[");
//            for (int i1 = 0; i1 < heap.length; i1++) {
//                if (i1 != heap.length - 1)
//                    System.out.print("{"+i1+","+heap[i1] + "}, ");
//                else System.out.println("{"+i1+","+heap[i1] + "}]\n ");
//            }
//        }
//        else System.out.println("Pls provide a valid heap");
    }

}
