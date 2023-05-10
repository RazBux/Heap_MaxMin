package App;

import jdk.jshell.Snippet;

import java.time.chrono.MinguoDate;

public class Heap_MaxMin {
    double heap[];

    public Heap_MaxMin(double[] heap){
        this.heap = heap;
    }

    public void HEAPIFY(int index){
        // Calculate the depth of the node using floor(log2(index + 1))
        int depth = (int) (Math.floor(Math.log(index + 1) / Math.log(2)));
        System.out.println(">> heapify index:" + index + " at depth:" + depth);

        //if the depth is even
        if (depth % 2 == 0) {
            int largest = Integer.MIN_VALUE;
            if (hasLeftSon(index)){
                double left = heap[2*index+1];
                if (left > heap[index])
                    largest = 2*index+1;
                else largest = index;
            }

            if (hasRightSon(index)){
                double right = heap[2*index+2];
                if (right > heap[largest])
                    largest = 2*index+2;
            }
            //exchange values if needed
            if (largest != index && largest != Integer.MIN_VALUE) {
                double temp = heap[index];
                heap[index] = heap[largest];
                heap[largest] = temp;
                System.out.println(heap[largest]+" <--> " + heap[index]);
                printHeap();
                HEAPIFY(largest);//continue to heapify
            }
            else System.out.println("--- noting to heapify ---");
        }//end of even depth

        else {//if the depth is odd
            int smallest = Integer.MIN_VALUE;
            if (hasLeftSon(index)){
                double left = heap[2*index+1];
                if (left < heap[index])
                    smallest = 2*index+1;
                else smallest = index;
            }

            if (hasRightSon(index)){
                double right = heap[2*index+2];
                if (right < heap[smallest])
                    smallest = 2*index+2;
            }

            //exchange values if needed
            if (smallest != index && smallest != Integer.MIN_VALUE) {
                double temp = heap[index];
                heap[index] = heap[smallest];
                heap[smallest] = temp;
                System.out.println(heap[smallest]+" <--> " + heap[index]);
                printHeap();
                HEAPIFY(smallest);//continue to heapify
            }
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

    private boolean hasLeftSon(int index){
        return (2*index+1 < this.heap.length);
    }
    private boolean hasRightSon(int index){
        return (2*index+2 < this.heap.length);
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

}
