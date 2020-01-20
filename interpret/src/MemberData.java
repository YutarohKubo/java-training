import java.lang.reflect.Member;

public class MemberData {

    public enum  Type {
        CONSTRUCTOR,
        FIELD,
        METHOD,
        CLASS,
    }

    private Type memberType;
    private Member member;

    public MemberData(Type memberType, Member member) {
        this.memberType = memberType;
        this.member = member;
    }

    public Type getMemberType() {
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
