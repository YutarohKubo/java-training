package ch03.ex03;

public class X {
    protected int xMask = 0x00ff;
    protected int fullMask;

    public X () {
        System.out.println("----------X()----------");
        printField();
        fullMask = mask(xMask);
        printField();
    }

    public int mask (int orig) {
        return (orig & fullMask);
    }

    public void printField () {
        System.out.printf("xMask = %x\n", xMask);
        System.out.printf("fullMask = %x\n", fullMask);
    }

}
