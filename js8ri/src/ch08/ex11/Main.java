package ch08.ex11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {

    static Charset charset = StandardCharsets.UTF_8;

    public static void main(String[] args) {
        System.out.println(getWebPage("https://www.google.com", "aaa", "bbb"));
    }

    static String getWebPage(String urlStr, String userName, String password) {
        String userNameAndPass = userName + ":" + password;
        byte[] b = Base64.getEncoder().encode(userNameAndPass.getBytes(charset));
        String userInfoEncoded = new String(b, charset);
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + userInfoEncoded);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (connection == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), charset))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

}
