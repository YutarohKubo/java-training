package ch21.ex01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private List<String> sortedLineList = new ArrayList<>();

    public void sortListFromFile () {
        String cd = System.getProperty("user.dir");
        File fileHello = new File(cd + "\\src\\ch21\\ex01\\hello");
        try (BufferedReader in = new BufferedReader(new FileReader(fileHello)))  {
            String line;
            while ((line = in.readLine()) != null)  {
                sortedLineList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(sortedLineList);
    }

    public List<String> getSortedLineList() {
        return sortedLineList;
    }
}
