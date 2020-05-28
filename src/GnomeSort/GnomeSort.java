package GnomeSort;

import java.util.ArrayList;
import java.util.List;

public class GnomeSort {

    public List<SortState> sort(int[] array) {
        int i = 1;
        int j = 2;
        int[] arr = new int[array.length];
        List<SortState> stats = new ArrayList<>();
        System.arraycopy(array, 0, arr, 0, arr.length);
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
