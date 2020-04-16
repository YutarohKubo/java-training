package ch20.ex07;

import java.io.*;

public class Main {

    static AttributedImpl attribute;

    static enum Operand {
        PLUS, MINUS, EQUAL,
    }

    public static void main(String[] args) throws IOException {
        attribute = new AttributedImpl();
        attribute.add(new Attr("apple", 3.0));
        attribute.add(new Attr("lemon", 5.0));
        attribute.add(new Attr("waffle", 8.0));
        String cd = System.getProperty("user.dir");
        FileOutputStream fileOutputStream = new FileOutputStream(cd + "\\src\\ch20\\ex07\\hogehoge");
        try (DataOutputStream out = new DataOutputStream(fileOutputStream)) {
            for (Attr attr : attribute) {
                attr.writeData(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream fileInputStream = new FileInputStream(cd + "\\src\\ch20\\ex07\\input_data");
        try (DataInputStream in = new DataInputStream(fileInputStream)) {
            Attr attr = new Attr(in, Type.DOUBLE);
            attribute.add(attr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        printAttrMap();
    }

    static boolean isEOLorEOF(int tokenizer) {
        return tokenizer == StreamTokenizer.TT_EOL || tokenizer == StreamTokenizer.TT_EOF;
    }

    static void printAttrMap() {
        for (Attr attr : attribute) {
            System.out.println(attr.getName() + " : " + attr.getValue());
        }
    }

}
