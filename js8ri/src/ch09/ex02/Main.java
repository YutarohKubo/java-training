package ch09.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    static final String CD = System.getProperty("user.dir");

    public static void main(String[] args) {
        Scanner in = null;
        PrintWriter out = null;
        IOException e = null;
        try {
            in = new Scanner(Paths.get(String.format("%s\\src\\ch09\\ex02\\document1.txt", CD)));
            out = new PrintWriter(String.format("%s\\src\\ch09\\ex02\\out.txt", CD));
            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            e = e1;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                if (e != null) {
                    e2.addSuppressed(e);
                }
                e2.printStackTrace();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e2) {
                if (e != null) {
                    e2.addSuppressed(e);
                }
                e2.printStackTrace();
            }
        }
    }

}
