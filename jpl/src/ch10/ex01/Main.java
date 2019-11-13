package ch10.ex01;

public class Main {

    public static void main(String[] args) {
        System.out.println("convertSpecialChar = " + convertSpecialChar("ab\nc\tde\b"));
    }

    private static String convertSpecialChar (String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '\n') {
                builder.append("\\n");
            } else if (ch == '\t') {
                builder.append("\\t");
            } else if (ch == '\b') {
                builder.append("\\b");
            } else if (ch == '\r') {
                builder.append("\\r");
            } else if (ch == '\f') {
                builder.append("\\f");
            } else if (ch == '\\') {
                builder.append("\\\\");
            } else if (ch == '\'') {
                builder.append("\\\'");
            } else if (ch == '\"') {
                builder.append("\\\"");
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }
}
