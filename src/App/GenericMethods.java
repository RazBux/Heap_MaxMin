package App;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GenericMethods {
    /**
     * This function taking the heap from .txt, it doesn't sensitive to spaces, if there is just ","
     * between each value, the function will work fine.
     * in addition, it's take the heap only from the first line. so if there is another lines in the
     * file, it won't read them.
     * @return array of doubles.
     * @throws IOException
     */
    public static double[] getHeapFromFile() throws IOException {
        //taking the local user trying to fine the desktop path
        String fileSeparator = System.getProperty("file.separator"); //String "\"
        String userDir = System.getProperty("user.dir");
        int i = userDir.indexOf("Desktop");
        int end = userDir.indexOf(fileSeparator, i);
        String desktopPath;

        if(end == -1){
            desktopPath = userDir.substring(0, i +7);
        }
        else desktopPath = userDir.substring(0, end); //subtract the path of the local desktop

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(desktopPath));
        fc.setPreferredSize(new Dimension(1100, 700));
        fc.setDialogTitle("Pls choose file to BUILD-HEAP");

        int response = fc.showOpenDialog(null);
        String filePath;
        if(response ==JFileChooser.APPROVE_OPTION){
            filePath = fc.getSelectedFile().toString();
            System.out.println("You choose -> " + filePath);
        }
        else{
            System.out.println("no directory were choose");
            filePath = null;
        }
        int HEAP_FINISH_INDEX =0;
        double[] heap = new double[0];
        if(filePath.contains(".txt")){
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String lineSeparator = "";
            int lines = 0;

            while ((lineSeparator = br.readLine()) != null && lines<1) {
                String heapS[] = lineSeparator.split(", ");
                int index = 0;
                heap = new double[heapS.length + 2];

                for (String s : heapS) {
                    //validate each value in the split string with regular expression
                    if (s.trim().matches("^-?\\d+(\\.\\d+)?$")) {
                        heap[index] = Double.parseDouble(s.trim());
                        HEAP_FINISH_INDEX = index;
                        index++;
                    }
                }
                lines++;//read only the first line of the file
            }
            br.close();
        }
        //pass the heap to good size heap and return it
        double[] heapToReturn = new double[HEAP_FINISH_INDEX+1];
        for (int j =0; j<= HEAP_FINISH_INDEX; j++)
            heapToReturn[j] = heap[j];

        return heapToReturn;
    }

}
