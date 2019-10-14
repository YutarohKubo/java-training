package ch04.ex02;

public class SortMetrics implements Cloneable{

    public long probeCnt, compareCnt, swapCnt;

    public void init() {
        probeCnt = swapCnt = compareCnt = 0;
    }

    @Override
    public String toString() {
        return probeCnt + " probes " + compareCnt + " compares " + swapCnt + " swaps";
    }

    @Override
    protected SortMetrics clone() {
        try {
            return (SortMetrics) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }
}
