package ch20.ex05;

import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String cd = System.getProperty("user.dir");
        FileReader fileReader = new FileReader(cd + "\\src\\ch20\\ex05\\hogehoge");
        String searchedStr = "hoge";
        String str;
        StringBuilder builder = new StringBuilder();
        try (LineNumberReader in = new LineNumberReader(fileReader)){
            while ((str = in.readLine()) != null) {
                if (str.contains(searchedStr)) {
                    builder.append(in.getLineNumber());
                    builder.append(" : ");
                    builder.append(str);
                    builder.append(System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(builder.toString());
    }
}
