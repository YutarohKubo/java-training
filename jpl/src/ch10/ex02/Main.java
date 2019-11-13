package ch10.ex02;

public class Main {

    public static void main(String[] args) {
        System.out.println("convertSpecialChar = " + convertSpecialChar("ab\nc\tde\b"));
    }

    private static String convertSpecialChar(String str) {
        String outputStr = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '\n':
                    outputStr += "\\n";
                    break;
                case '\t':
                    outputStr += "\\t";
                    break;
                case '\b':
                    outputStr += "\\b";
                    break;
                case '\r':
                    outputStr += "\\r";
                    break;
                case '\f':
                    outputStr += "\\f";
                    break;
                case '\\':
                    outputStr += "\\\\";
                    break;
                case '\'':
                    outputStr += "\\\'";
                    break;
                case '\"':
                    outputStr += "\\\"";
                    break;

                default:
                    outputStr += ch;
            }
        }
        return outputStr;
    }
}
