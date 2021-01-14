package ch08.ex16;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static ch08.ex16.Main.getAddressMatcher;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void addressMatchingTest1() {
        Matcher matcher = getAddressMatcher("america, AM, 00495");
        assertTrue(matcher.find());
        assertEquals("america", matcher.group("city"));
        assertEquals("AM", matcher.group("state"));
        assertEquals("00495", matcher.group("zipcode"));
    }

    @Test
    public void addressMatchingTest2() {
        Matcher matcher = getAddressMatcher("igirisu, IG, 049385832");
        assertTrue(matcher.find());
        assertEquals("igirisu", matcher.group("city"));
        assertEquals("IG", matcher.group("state"));
        assertEquals("049385832", matcher.group("zipcode"));
    }

    @Test
    public void addressMatchingTest3() {
        Matcher matcher = getAddressMatcher("japan, JA, 049385");
        assertTrue(matcher.find());
        assertEquals("japan", matcher.group("city"));
        assertEquals("JA", matcher.group("state"));
        assertEquals("04938", matcher.group("zipcode"));
    }

    @Test
    public void addressMatchingTest4() {
        Matcher matcher = getAddressMatcher("i love singapore, SI, 538290592481 i play baseball.");
        assertTrue(matcher.find());
        assertEquals("i love singapore", matcher.group("city"));
        assertEquals("SI", matcher.group("state"));
        assertEquals("538290592", matcher.group("zipcode"));
    }

}
