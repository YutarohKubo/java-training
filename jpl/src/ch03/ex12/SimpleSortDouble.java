package ch03.ex12;

public class SimpleSortDouble extends SortDouble {

    @Override
    protected void doSort() {
        for (int i = 0; i < getDataLength(); i++) {
            for (int j = i + 1; j < getDataLength(); j++) {
                if (compare(i, j) > 0) {
                    swap(i, j);
                }
            }
        }
    }

}
