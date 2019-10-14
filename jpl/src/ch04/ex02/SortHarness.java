package ch04.ex02;

abstract public class SortHarness implements Sortable {

    private Comparable[] values;
    private final SortMetrics curMetrics = new SortMetrics();

    public final SortMetrics sort(Comparable[] data) {
        values = data;
        curMetrics.init();
        doSort();
        return getMetrics();
    }

    public final SortMetrics getMetrics() {
        return curMetrics.clone();
    }

    protected final int getDataLength() {
        return values.length;
    }

    protected final Comparable probe(int i) {
        curMetrics.probeCnt++;
        return values[i];
    }

    protected final int compare(int i, int j) {
        curMetrics.compareCnt++;
        Comparable d1 = values[i];
        Comparable d2 = values[j];
        return d1.compareTo(d2);
    }

    protected final void swap(int i, int j) {
        curMetrics.swapCnt++;
        Comparable tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    protected abstract void doSort();
}
