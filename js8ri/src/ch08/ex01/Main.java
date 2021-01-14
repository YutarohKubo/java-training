package ch08.ex01;

/**
 * 足し算引き算掛け算に関しては、符号付きで計算した結果を符号なしでの値に変換した場合に矛盾が生じないが、
 * 割り算に関しては、矛盾するので、専用の計算メソッドが必要となる
 */
public class Main {

    private static int POW_2_TO_30 = (int) Math.pow(2, 30);

    public static void main(String[] args) {
        int POW_2_TO_30_THREE_TIME = POW_2_TO_30 + POW_2_TO_30 + POW_2_TO_30;
        System.out.println("not unsigned (+) : " + POW_2_TO_30_THREE_TIME);
        System.out.println("unsigned (+) : " + Integer.toUnsignedLong(POW_2_TO_30_THREE_TIME));
        System.out.println("not unsigned (-) : " + (POW_2_TO_30_THREE_TIME - POW_2_TO_30));
        System.out.println("unsigned (-) : " + Integer.toUnsignedLong(POW_2_TO_30_THREE_TIME - POW_2_TO_30));
        System.out.println("not unsigned (/) : " + POW_2_TO_30_THREE_TIME / 3);
        System.out.println("unsigned (/) : " + Integer.divideUnsigned(POW_2_TO_30_THREE_TIME, 3));
    }

}
