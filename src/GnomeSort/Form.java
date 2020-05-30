package GnomeSort;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
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
    private JButton randomButton;
    private JButton runAllButton;
    private int[] arr;
    Player player;
    private int i = 0;

    private void restart() {
        try {
            arr = JTableUtils.readIntArrayFromJTable(table1);
            player = new Player(arr, new GnomeSort().sort(arr), table1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Form() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(table1, 80, true, true, false, true);
        table1.setRowHeight(25);

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    arr = JTableUtils.readIntArrayFromJTable(table1);
                    player = new Player(arr, new GnomeSort().sort(arr), table1);
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    SwingUtils.showInfoMessageBox("Массив состоит из одного числа");
                }
            }
        });

        runAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    player.runProcess();
                } catch (NullPointerException e) {
                    SwingUtils.showInfoMessageBox("Нажмите кнопку >Выполнить<, чтобы начать работу");
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    player.iteration(true);
                } catch (NullPointerException e) {
                    SwingUtils.showInfoMessageBox("Нажмите кнопку >Выполнить<, чтобы начать работу");
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    player.iteration(false);
                } catch (NullPointerException e) {
                    SwingUtils.showInfoMessageBox("Нажмите кнопку >Выполнить<, чтобы начать работу");
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
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
