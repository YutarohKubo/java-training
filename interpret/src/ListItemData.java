import java.lang.reflect.Member;

public class ListItemData {

    private int itemNumber;
    private Member member;

    public ListItemData(int itemNumber, Member member) {
        this.itemNumber = itemNumber;
        this.member = member;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public Member getMember() {
        return member;
    }
}
