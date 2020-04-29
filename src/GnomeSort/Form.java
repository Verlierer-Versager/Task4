package GnomeSort;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame {
    private JPanel panelMain;
    private JTable table1;
    private JButton executeButton;
    private JButton backButton;
    private JButton nextButton;
    private JTable table2;
    private JLabel ivalue1;
    private JLabel jvalue1;
    private JLabel ivalue2;
    private JLabel jvalue2;
    private JButton randomButton;
    private List<SortState> stats = new ArrayList<>();
    private int[] arr;
    private GnomeSort gnomeSort = new GnomeSort();
    private String ivalue = new String("Значение i: ");
    private String jvalue = new String("Значение j: ");
    private int i = 0;

    private void changeStats() {
        if (i < stats.size() - 1) {
            ivalue1.setText(ivalue + stats.get(i).getI());
            jvalue1.setText(jvalue + stats.get(i).getJ());
            ivalue2.setText(ivalue + stats.get(i + 1).getI());
            jvalue2.setText(jvalue + stats.get(i + 1).getJ());
            JTableUtils.writeArrayToJTable(table1, stats.get(i).getArr());
            JTableUtils.writeArrayToJTable(table2, stats.get(i + 1).getArr());
        } else {
            SwingUtils.showInfoMessageBox("Массив отсортирован");
        }
    }

    private void restart() {
        try {
            arr = JTableUtils.readIntArrayFromJTable(table1);
            stats = new ArrayList<>();
            stats = gnomeSort.sort(arr);
            i = 0;
            changeStats();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Form() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(table1, 40, true, true, true, true);
        JTableUtils.initJTableForArray(table2, 40, true, true, true, true);
        table1.setRowHeight(25);
        table2.setRowHeight(25);


        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (stats.size() == 0) {
                    try {
                        int[] arr = JTableUtils.readIntArrayFromJTable(table1);
                        stats = gnomeSort.sort(arr);
                        JTableUtils.writeArrayToJTable(table2, stats.get(1).getArr());
                        changeStats();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (IndexOutOfBoundsException e) {
                        SwingUtils.showInfoMessageBox("Массив состоит из одного числа");
                    }
                } else {
                    restart();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                i++;
                changeStats();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (i != 0) {
                    i--;
                    changeStats();
                }
                if (i >= stats.size() - 1) {
                    i = stats.size() - 2;
                    changeStats();
                }
            }
        });

        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    arr = ArrayUtils.createRandomIntArray(10, 0, 100);
                    JTableUtils.writeArrayToJTable(table1, arr);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
