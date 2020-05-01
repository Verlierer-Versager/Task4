package GnomeSort;

import java.util.ArrayList;
import java.util.List;

public class GnomeSort {

    public List<SortState> sort(int[] arr) {
        int i = 1;
        int j = 2;
        List<SortState> stats = new ArrayList<>();
        while (i < arr.length) {
            if (arr[i - 1] < arr[i]) {
                i = j;
                j++;
            } else {
                int temp = arr[i - 1];
                arr[i - 1] = arr[i];
                arr[i] = temp;
                stats.add(new SortState(i - 1, i));
                i--;
                if (i == 0) {
                    i = j;
                    j++;
                }
            }
        }
        return stats;
    }

}
