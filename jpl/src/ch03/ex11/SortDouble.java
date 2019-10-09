package ch03.ex11;

public abstract class SortDouble {

    private double[] values;
    private final SortMetrics curMetrics = new SortMetrics();

    public final SortMetrics sort(double[] data) {
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


    protected abstract void doSort();

}
