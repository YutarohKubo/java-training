package ch20.ex06;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

public class Main {

    static AttributedImpl attribute;

    static enum Operand {
        PLUS, MINUS, EQUAL,
    }

    public static void main(String[] args) {
        attribute = new AttributedImpl();
        String cd = System.getProperty("user.dir");
        try (FileReader fileReader = new FileReader(cd + "\\src\\ch20\\ex06\\program1")) {
            StreamTokenizer tokenizer = new StreamTokenizer(fileReader);
            String variableName = "";
            Operand selectedOperand = null;
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                tokenizer.pushBack();
                while (!isEOLorEOF(tokenizer.nextToken())) {
                    if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                        if (selectedOperand == null) {
                            throw new IllegalStateException();
                        }
                        switch (selectedOperand) {
                            case PLUS:
                                Attr attrPlus = attribute.find(variableName);
                                attrPlus.setValue((double) attrPlus.getValue() + tokenizer.nval);
                                break;

                            case MINUS:
                                Attr attrMinus = attribute.find(variableName);
                                attrMinus.setValue((double) attrMinus.getValue() - tokenizer.nval);
                                break;

                            case EQUAL:
                                Attr attrEqual = attribute.find(variableName);
                                attrEqual.setValue(tokenizer.nval);
                                break;
                        }
                    } else if (tokenizer.ttype == '+') {
                        selectedOperand = Operand.PLUS;
                    } else if (tokenizer.ttype == '-') {
                        selectedOperand = Operand.MINUS;
                    } else if (tokenizer.ttype == '=') {
                        selectedOperand = Operand.EQUAL;
                    } else if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                        variableName = tokenizer.sval;
                        System.out.println(variableName);
                        if (!attribute.containsKey(variableName)) {
                            attribute.add(new Attr(variableName, 0.0));
                        }
                    }
                }
            }
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
