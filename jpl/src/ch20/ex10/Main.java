package ch20.ex10;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static Map<String, Integer> wordMap = new HashMap();

    public static void main(String[] args) {
        String cd = System.getProperty("user.dir");
        inspectTokenInFile(cd + "\\src\\ch20\\ex10\\hello1");
        System.out.println(wordMap);
    }

    static void inspectTokenInFile (String pathName) {
        try (FileReader fileReader = new FileReader(pathName)){
            StreamTokenizer tokenizer = new StreamTokenizer(fileReader);
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                String token = tokenizer.sval;
                int inspectedWordNum = wordMap.get(token) == null ? 1 : wordMap.get(token) + 1;
                wordMap.put(token, inspectedWordNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
