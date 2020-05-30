package GnomeSort;

import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Player {
    int index;
    private int i = 0;
    private int[] arr;
    private List<SortState> states = new ArrayList<>();
    JTable table;

    public Player(int[] arr, List<SortState> states, JTable table) {
        this.arr = arr;
        this.states = states;
        this.table = table;
    }

    public int[] getArr() {
        return arr;
    }

    public List<SortState> getStates() {
        return states;
    }

    private void select(int j, boolean toSelect) {
        if (toSelect) {
            String str = "8" + Math.abs(arr[j]) + "8";
            int a = Integer.parseInt(str);
            if (arr[j] < 0) {
                arr[j] = -a;
            } else {
                arr[j] = a;
            }
        } else {
            String str = Integer.toString(Math.abs(arr[j]));
            char[] ch = new char[str.length() - 2];
            str.getChars(1, str.length() - 1, ch, 0);
            int a = Integer.parseInt(new String(ch));
            if (arr[j] < 0) {
                arr[j] = -a;
            } else {
                arr[j] = a;
            }
        }
    }

    public void iteration(boolean toNextStep) throws Exception {
        if (toNextStep) {
            if (i != states.size()) {
                index = i + 1;
                timer.start();
            } else {
                throw new Exception("has  already sorted");
            }
        } else {
            if (i > 0) {
                index = i;
                i--;
                timer.start();
                i--;
            } else {
                throw new Exception("there is no step to return");
            }
        }
        timer.start();
    }

    public void runProcess() {
        i = 0;
        index = states.size();
        timer.start();
    }

    Timer timer = new Timer(3000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (i < index) {
                int a = states.get(i).getI();
                int b = states.get(i).getJ();
                select(a, true);
                select(b, true);
                JTableUtils.writeArrayToJTable(table, arr);
                int temp = arr[a];
                arr[a] = arr[b];
                arr[b] = temp;
                select(a, false);
                select(b, false);
                i++;
            } else {
                JTableUtils.writeArrayToJTable(table, arr);
                timer.stop();
            }
        }
    });
    Timer timer1 = new Timer(2000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            JTableUtils.writeArrayToJTable(table, arr);
        }
    });

}
