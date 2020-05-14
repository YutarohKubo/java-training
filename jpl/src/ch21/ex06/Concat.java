package ch21.ex06;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Concat {

    public static void main(String[] args) throws IOException {
        Enumeration<InputStream> files = null;
        if (args.length == 0) {
            List<InputStream> inputs = new ArrayList<>();
            inputs.add(System.in);
            files = Collections.enumeration(inputs);
        } else {
            String cd = System.getProperty("user.dir");
            InputStream fileIn, bufIn;
            List<InputStream> inputs = new ArrayList<>(args.length);
            for (String arg : args) {
                fileIn = new FileInputStream(cd + "\\src\\ch21\\ex06\\" + arg);
                bufIn = new BufferedInputStream(fileIn);
                inputs.add(bufIn);
            }
            files = Collections.enumeration(inputs);
        }
        int ch = 0;
        for (;files.hasMoreElements();) {
            InputStream in = files.nextElement();
            while ((ch = in.read()) != -1) {
                System.out.println(ch);
            }
        }
    }

}
