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
            StringBuilder builder = new StringBuilder("NULL");
            for (int i = 0; i < 52 - "NULL".length(); i++) {
                builder.append(" ");
            }
            return builder.toString();
        }
        if (arrayItem.toString().length() > 50) {
            return arrayItem.toString().substring(0, 50) + "...";
        } else {
            StringBuilder resultStr = new StringBuilder(arrayItem.toString());
            for (int i = 0; i < 52 - arrayItem.toString().length(); i++) {
                resultStr.append(" ");
            }
            return resultStr.toString();
        }
    }
}
