package ch09.ex02;

public class Main {

    public static int countBitsOn (int bits){
        int num = 0;
        int mask = 1;

        if (bits < 0) {
            //32bit目は符号ビットであり、マイナスを表す1が立っていて、maskも32ビット目に立っているとき、
            //1が立っているにも関わらす、(bits & mask) > 0はfalseになるため、例外的にnumをインクリメントする
            num++;
        }

        while (mask != 0) {
            if ((bits & mask) > 0) {
                num++;
                System.out.println(Integer.toBinaryString(mask) + ":" + Integer.toBinaryString(mask).length());
            }
            mask = mask << 1;
        }

        return num;
    }

    public static void main(String[] args) {
        int bits = -9;
        System.out.println("count of bits in " + bits + " is " + countBitsOn(bits));
    }

}
