package ch19.ex01;

/**
 * 単方向リストを形成するクラスです.<code>LinkedList</code>
 * リスト内の要素はvalue <code>Object</code> と、next <code>LinkedList</code> とで構成されます.
 *
 * @version 1.0
 * @author 久保裕太郎
 * @since 1.0
 */
public class LinkedList {

    /** リスト内に格納される要素の値 */
    private Object value;
    /** この要素の次の要素 */
    private LinkedList next;

    /** リストの先頭の要素 */
    private static LinkedList header = null;

    /**
     * リストを作成します.
     * 作成と同時にリストの先頭に要素を格納します.
     * @param value リストの先頭の値
     */
    public LinkedList(Object value) {
        this.value = value;
        if (header == null) {
            header = this;
        }
    }

    /**
     * リストにオブジェクトを格納します
     * java.util.Listのaddメソッドにつかい方がが似ています
     * @see <a href="https://docs.oracle.com/javase/jp/8/docs/api/java/util/List.html#add-E-">List#add</a>
     * @param data リストに格納するオブジェクト
     */
    public void add (Object data) {
        LinkedList list = new LinkedList(data);
        LinkedList ptr = header;

        while (ptr.next != null) {
            ptr = ptr.next;
        }
        ptr.next = list;
    }

    /**
     * リスト内の要素を取得します
     * java.util.Listのgetメソッドにつかい方がが似ています
     * @see <a href="https://docs.oracle.com/javase/jp/8/docs/api/java/util/List.html#get-int-">List#get</a>
     * @param index 取得したい要素か格納されているインデックス
     * @return 取得する要素
     */
    public Object get(int index) {
        LinkedList ptr = header;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.value;
    }

    /**
     * リスト内に格納されている要素の数を取得します
     * java.util.Listのsizeメソッドにつかい方がが似ています
     * @see <a href="https://docs.oracle.com/javase/jp/8/docs/api/java/util/List.html#size--">List#size</a>
     * @return 要素の数
     */
    public int size () {
        int size = 0;
        if (header != null) {
            size++;
        }
        LinkedList ptr = header;
        while (ptr.next != null) {
            ptr = ptr.next;
            size++;
        }
        return size;
    }

    @Override
    public String toString () {
        return value.toString();
    }

    /**
     * リスト内に格納されている要素一覧を表す文字列を取得します
     * @return 要素一覧の文字列
     */
    private String printList () {
        StringBuilder builder = new StringBuilder();
        LinkedList ptr = header;
        while (ptr.next != null) {
            builder.append(ptr + " -> ");
            ptr = ptr.next;
        }
        builder.append(ptr);
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList(new Vehicle("takahashi"));
        list.add(new Vehicle("satoh"));
        list.add(new Vehicle("suzuki"));
        list.add(new Vehicle("watanabe"));

        System.out.println(list.size());
    }
}
