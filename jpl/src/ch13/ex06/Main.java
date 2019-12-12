package ch13.ex06;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(integerParseComma(1234567898, 4));
    }

    private static String integerParseComma (int parsedInt, int numDigitComma) {
        String str = Integer.toString(parsedInt);
        List<String> outputList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            outputList.add(str.substring(str.length() - i - 1, str.length() - i));
            if (i % numDigitComma == numDigitComma - 1) {
                outputList.add(",");
            }
        }
        Collections.reverse(outputList);
        StringBuilder outputStr = new StringBuilder();
        for (String s : outputList) {
            outputStr.append(s);
        }
        return outputStr.toString();
    }

}
