package ch20.ex08;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

    public static void main(String[] args) {
        String cd = System.getProperty("user.dir");
        String line = "";
        StringBuilder builder = new StringBuilder();
        try (RandomAccessFile randomAccessFileRead = new RandomAccessFile(cd + "\\src\\ch20\\ex08\\input_data", "r");
             RandomAccessFile randomAccessFileWrite = new RandomAccessFile(cd + "\\src\\ch20\\ex08\\output_data", "rw")) {
            int dataNum = 0;
            while ((line = randomAccessFileRead.readLine()) != null) {
                if (line.substring(0, 2).equals("%%")) {
                    builder.append(dataNum + 1);
                    builder.append(":");
                    builder.append(line.substring(2));
                    dataNum++;
                } else {
                    builder.append(line);
                }
                builder.append(System.getProperty("line.separator"));
            }
            randomAccessFileWrite.writeBytes(builder.toString());
            showRandomEntry(randomAccessFileWrite, dataNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showRandomEntry (RandomAccessFile writtenDataFile, int dataNum) throws IOException {
        writtenDataFile.seek(0);
        int entryNum = (int) (Math.random() * dataNum) + 1;
        String line = "";
        StringBuilder builder = new StringBuilder();
        while ((line = writtenDataFile.readLine()) != null) {
            if (line.substring(0, 2).equals(entryNum + ":")) {
                builder.append(line.substring(2));
                while (!isNullOrNextEntry(line = writtenDataFile.readLine(), entryNum)) {
                    builder.append("\n");
                    builder.append(line);
                }
                break;
            }
        }
        System.out.println(builder.toString());
    }

    private static boolean isNullOrNextEntry (String line, int entryNum) {
        return line == null || line.substring(0, 2).equals((entryNum + 1) + ":");
    }

}
