package ch08.ex16;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Matcher getAddressMatcher(String address) {
        Pattern pattern = Pattern.compile("(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2}),\\s*(?<zipcode>(\\d{9}|\\d{5}))");
        return pattern.matcher(address);
    }

}
