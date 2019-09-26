package ch03.ex02;

public class Y extends X {
    protected int yMask = 0xff00;

    public Y () {
        System.out.println("---------Y()----------");
        printField();
        fullMask |= yMask;
        printField();
    }

    @Override
    public void printField() {
        super.printField();
        System.out.printf("yMask = %x\n", yMask);
    }

    public static void main(String[] args) {
        new Y();
    }
}
