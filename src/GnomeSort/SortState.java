package GnomeSort;

public class SortState {

    private int[] arr;
    private int i;
    private int j;

    public SortState(int[] arr, int i, int j) {
        this.arr = new int[arr.length];
        System.arraycopy(arr, 0, this.arr, 0, arr.length);
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int[] getArr() {
        return arr;
    }
}
