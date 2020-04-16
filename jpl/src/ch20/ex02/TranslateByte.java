package ch20.ex02;

import java.io.*;

public class TranslateByte {

    static class TranslateFilterInputStream extends FilterInputStream {

        /**
         * Creates a new filtered reader.
         *
         * @param in a Reader object providing the underlying stream.
         * @throws NullPointerException if <code>in</code> is <code>null</code>
         */
        protected TranslateFilterInputStream(InputStream in) {
            super(in);
        }
    }

    static class TranslateFilterOutputStream extends FilterOutputStream {

        byte fromByte;
        byte toByte;

        protected TranslateFilterOutputStream(OutputStream out, byte fromByte, byte toByte) {
            super(out);
            this.fromByte = fromByte;
            this.toByte = toByte;
        }

        @Override
        public void write(int c) throws IOException {
            super.write(c == fromByte ? toByte : c);
        }
    }

    public static void main(String[] args) throws IOException {
        convertInputToOutput(System.in, System.out, args[0].charAt(0), args[1].charAt(0));
    }

    private static void convertInputToOutput (InputStream in, OutputStream out, char from, char to) throws IOException {
        int b;
        TranslateFilterInputStream filterInputStream = new TranslateFilterInputStream(in);
        TranslateFilterOutputStream filterOutputStream = new TranslateFilterOutputStream(out, (byte) from, (byte) to);
        while ((b = filterInputStream.read()) != -1) {
            filterOutputStream.write(b);
        }
    }

}
