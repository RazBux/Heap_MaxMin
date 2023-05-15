package App;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.management.MonitorInfo;
import java.text.ParseException;
import javax.swing.*;

import static java.awt.BorderLayout.CENTER;


public class GUI extends JFrame {
    static Heap_MaxMin heap;

    public static void main(String[] args) throws IOException {
        new GUI();
    }

    private JPanel contentPane;
    private JToolBar toolBar;
    private JPanel buttonPanel;
    private CardLayout buttonLayout;


    public GUI() throws IOException {
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocation(700, 350);
        super.setResizable(true);
        super.setTitle("Heap Max Min");
        super.setLayout(null);
        super.setVisible(true);
        super.setResizable(false);

        setPreferredSize(new Dimension(350, 350));

        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        buttonLayout = new CardLayout();
        buttonPanel = new JPanel(buttonLayout);
        buttonPanel.setBackground(Color.WHITE);


        /**Heap panel START*/
        JPanel Heap_Panel = new JPanel();

        // Create the "Build heap" button
        JButton build_heap_Button = new JButton("Build Heap");

        // Create the heapify button
        JButton heapify_Button = new JButton("Heapify");
        heapify_Button.setEnabled(false);

        // Create "extract_max" button
        JButton heap_extract_max_Button = new JButton("Heap extract max");
        heap_extract_max_Button.setEnabled(false);

        // Create "extract_min" button
        JButton heap_extract_min_Button = new JButton("Heap extract min");
        heap_extract_min_Button.setEnabled(false);

        // Create "choose dates" button
        JButton heap_insert_Button = new JButton("Heap insert");
        heap_insert_Button.setEnabled(false);

        // Create "choose dates" button
        JButton heap_delete_Button = new JButton("Heap delete");
        heap_delete_Button.setEnabled(false);


        Heap_Panel.setLayout(new FlowLayout(FlowLayout.LEFT)); // set layout to vertical

        // Create a panel for the button "build_heap_Button"
        JPanel row1Panel = new JPanel();
        row1Panel.setLayout(new FlowLayout());
        row1Panel.add(build_heap_Button);

        // Create a panel for the button "heapify_Button"
        JPanel row2Panel = new JPanel();
        row2Panel.setLayout(new FlowLayout());
        row2Panel.add(heapify_Button);

        // Create a panel for the "extract_max_Button"
        JPanel row3Panel = new JPanel();
        row3Panel.setLayout(new FlowLayout());
        row3Panel.add(heap_extract_max_Button);

        // Create a panel for the "extract_min_Button"
        JPanel row4Panel = new JPanel();
        row4Panel.setLayout(new FlowLayout());
        row4Panel.add(heap_extract_min_Button);

        // Create a panel for the "heap_insert_Button"
        JPanel row5Panel = new JPanel();
        row5Panel.setLayout(new FlowLayout());
        row5Panel.add(heap_insert_Button);

        // Create a panel for the "heap_delete_Button"
        JPanel row6Panel = new JPanel();
        row6Panel.setLayout(new FlowLayout());
        row6Panel.add(heap_delete_Button);


        //settings the radio button
        JRadioButton radio_type = new JRadioButton("Type");
        JRadioButton radio_file = new JRadioButton("File");
        radio_type.setEnabled(false);
        radio_file.setEnabled(false);
        //radio panel
        JPanel radioPanel = new JPanel();
        radioPanel.add(radio_file);
        radioPanel.add(radio_type);
        radioPanel.setMaximumSize(new Dimension(200, 35));

        // Add the row panels to RTC_Panel
        Heap_Panel.add(row1Panel);
        Heap_Panel.add(radioPanel);
        Heap_Panel.add(row2Panel);
        Heap_Panel.add(row3Panel);
        Heap_Panel.add(row4Panel);
        Heap_Panel.add(row5Panel);
        Heap_Panel.add(row6Panel);

        Heap_Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        /**Heap panel END*/



        /////////////////////////////button method part///////////////////////////////
        build_heap_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radio_file.setEnabled(true);
                radio_type.setEnabled(true);
                build_heap_Button.setEnabled(false);
            }
        });

        radio_file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radio_file.setEnabled(false);
                radio_type.setEnabled(false);
                try {
                    heap = new Heap_MaxMin(GenericMethods.getHeapFromFile());
                    heap.BUILD_HEAP();

                    heap_delete_Button.setEnabled(true);
                    heap_extract_max_Button.setEnabled(true);
                    heap_extract_min_Button.setEnabled(true);
                    heap_insert_Button.setEnabled(true);
                    heapify_Button.setEnabled(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        radio_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radio_file.setEnabled(false);
                radio_type.setEnabled(false);
                String message = JOptionPane.showInputDialog(Heap_Panel, "Enter your Heap:");
                if (message != null ) {
//                    JOptionPane.showMessageDialog(Heap_Panel, "You entered: " + message);
                    String heapS[] = message.split(",");
//                    for (String s : heapS)
//                        System.out.println(s);
                    int index = 0;
                    double[] heapInput = new double[heapS.length];
                    for (String s : heapS) {
                        //validate each value in the split string with regular expression
                        if (s.trim().matches("^-?\\d+(\\.\\d+)?$")) {
                            heapInput[index] = Double.parseDouble(s.trim());
                            index++;
                        }
                    }
                    heap = new Heap_MaxMin(heapInput);
                    heap.BUILD_HEAP();
                }
                heap_delete_Button.setEnabled(true);
                heap_extract_max_Button.setEnabled(true);
                heap_extract_min_Button.setEnabled(true);
                heap_insert_Button.setEnabled(true);
                heapify_Button.setEnabled(true);

            }
        });


        heap_delete_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        heap_insert_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String insertValue = String.valueOf(Double.parseDouble(
                        JOptionPane.showInputDialog(Heap_Panel, "Enter your value for insert:")));
                if (insertValue.trim().matches("^-?\\d+(\\.\\d+)?$")) {
                    heap.HEAP_INSERT(Double.parseDouble(insertValue));
                }
            }
        });

        heap_extract_min_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heap.HEAP_EXTRACT_MIN();
            }
        });

        heap_extract_max_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heap.HEAP_EXTRACT_MAX();
            }
        });

        heapify_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });





        ///////////////////////Main Panel///////////////////////
        buttonPanel.add(Heap_Panel, "set1");


        //Create button for the Tool-bar
        JButton button1 = new JButton("Heap");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonLayout.show(buttonPanel, "set1");
            }
        });


        //adding all the components to the main app window and pack them
        toolBar = new JToolBar();
        toolBar.add(button1);
        toolBar.setEnabled(false);

        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(buttonPanel, CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }




}



