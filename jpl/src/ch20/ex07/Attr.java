package ch20.ex07;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class Attr {
    private final String name;
    private Object value = null;

    public Attr(String name) {
        this(name, null);
    }

    public Attr(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * DataInputStreamから、インスタンス変数にデータを格納する
     * @param in DataInputStream
     * @param valueType データの種類
     * @throws IOException 例外
     */
    public Attr(DataInputStream in, Type valueType) throws IOException{
        byte[] b = new byte[256];
        int ch;
        int pointer = 0;
        while (!isEOForNewLine((ch = in.read()))) {
            b[pointer] = (byte) ch;
            pointer++;
        }
        this.name = new String(b);
        Arrays.fill(b, (byte) 0);
        pointer = 0;
        while (!isEOForNewLine((ch = in.read()))) {
            b[pointer] = (byte) ch;
            pointer++;
        }
        switch (valueType) {
            case BYTE:
                this.value = Byte.parseByte(new String(b));
                break;

            case LONG:
                this.value = Long.parseLong(new String(b));
                break;

            case FLOAT:
                this.value = Float.parseFloat(new String(b));
                break;

            case SHORT:
                this.value = Short.parseShort(new String(b));
                break;

            case DOUBLE:
                this.value = Double.parseDouble(new String(b));
                break;

            case STRING:
                this.value = new String(b);
                break;

            case BOOLEAN:
                this.value = Boolean.parseBoolean(new String(b));
                break;

            case INTEGER:
                this.value = Integer.parseInt(new String(b));
                break;

            case CHARACTER:
                this.value = b[0];
                break;
        }
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object setValue(Object newValue) {
        Object oldValue = this.value;
        this.value = newValue;
        return oldValue;
    }

    public void writeData(DataOutput out) throws IOException {
        out.writeBytes(name);
        out.writeBytes(System.getProperty("line.separator"));
        out.writeBytes(value.toString());
        out.writeBytes(System.getProperty("line.separator"));
    }

    public boolean isEOForNewLine (int ch) {
        return ch == -1 || ch == System.getProperty("line.separator").getBytes()[0];
    }

    @Override
    public String toString() {
        return name + "='" + value + "'";
    }
}
