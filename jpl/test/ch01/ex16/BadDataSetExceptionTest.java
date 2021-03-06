package ch01.ex16;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadDataSetExceptionTest {

    @Test
    void thrownBadDataSetException () {
        try {
            fail("data!");
        } catch (BadDataSetException e) {
            assertEquals(e.getMessage(), "setName is " + "data!" + ", IOExceptionMessage is " + "exception!");
        }
    }

    void fail (String setName) throws BadDataSetException {
        try {
            throwIOException();
        } catch (IOException e) {
            throw new BadDataSetException(setName, e);
        }
    }

    void throwIOException () throws IOException {
        throw new IOException("exception!");
    }

}