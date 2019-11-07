package ch07.ex01;

public class HelloWorldWithUnicode {

    public static void main(String[] args) {

        System.out.println(examineEncode16("Hello, World") + " = " + "\u0048\u0065\u006c\u006c\u006f\u002c\u0020\u0057\u006f\u0072\u006c\u0064");
    }

    private static String examineEncode16(String str) {
        byte[] strbytes = str.getBytes();
        StringBuilder builder = new StringBuilder();
        for (byte b : strbytes) {
            builder.append("\\u00");
            builder.append(Integer.toHexString(b));
        }
        return builder.toString();
    }

}
