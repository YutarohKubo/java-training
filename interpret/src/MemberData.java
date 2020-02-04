import java.lang.reflect.Member;

public class MemberData {

    private MemberType memberType;
    private Member member;
    private Object value;

    public MemberData(MemberType memberType, Member member) {
        this.memberType = memberType;
        this.member = member;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public String toString() {
        return member.toString();
    }
}
