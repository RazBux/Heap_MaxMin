package App;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        double heap[] = {10, 202, 2, -2, 44};
        Heap_MaxMin h = new Heap_MaxMin(GenericMethods.getHeapFromFile());
        h.printHeap();
        h.BUILD_HEAP();
//        h.HEAPIFY(0);

        System.out.println("\nthe final Heap is:");
        h.printHeap();
    }



}
