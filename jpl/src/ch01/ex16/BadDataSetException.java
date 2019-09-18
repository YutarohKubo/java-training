package ch01.ex16;

import java.io.IOException;

public class BadDataSetException extends Exception {
    private String setName;
    private IOException e;

    public BadDataSetException (String setName, IOException e) {
        this.setName = setName;
        this.e = e;
    }

    @Override
    public String getMessage() {
        return "setName is " + setName + ", IOExceptionMessage is " + e.getMessage();
    }
}
