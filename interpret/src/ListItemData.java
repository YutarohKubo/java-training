import java.lang.reflect.Member;

public class ListItemData {

    private int itemNumber;
    private Object arrayItem;

    private DataHolder dataHolder;

    public class DataHolder {
        public Object generatedObject;
    }

    public DataHolder getDataHolder() {
        return dataHolder;
    }

    public ListItemData(int itemNumber, Object arrayItem) {
        this.itemNumber = itemNumber;
        this.arrayItem = arrayItem;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public Object getArrayItem() {
        return arrayItem;
    }

    public void setupDataHolder () {
        dataHolder = new DataHolder();
    }

    @Override
    public String toString() {
        if (arrayItem == null) {
            return "NULL";
        }
        return arrayItem.toString();
    }
}
