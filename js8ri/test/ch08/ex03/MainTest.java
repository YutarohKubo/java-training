package ch08.ex03;

import org.junit.jupiter.api.Test;
import static ch08.ex03.Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void test_gcd1_1() {
        assertEquals(3, gcd1(36, 15));
    }

    @Test
    public void test_gcd1_2() {
        assertEquals(3, gcd1(-36, 15));
    }

    @Test
    public void test_gcd1_3() {
        assertEquals(3, gcd1(36, -15));
    }

    @Test
    public void test_gcd1_4() {
        assertEquals(3, gcd1(-36, -15));
    }

    @Test
    public void test_gcd2_1() {
        assertEquals(3, gcd2(36, 15));
    }

    @Test
    public void test_gcd2_2() {
        assertEquals(3, gcd2(-36, 15));
    }

    @Test
    public void test_gcd2_3() {
        assertEquals(3, gcd2(36, -15));
    }

    @Test
    public void test_gcd2_4() {
        assertEquals(3, gcd2(-36, -15));
    }

    @Test
    public void test_gcd3_1() {
        assertEquals(3, gcd3(36, 15));
    }

    @Test
    public void test_gcd3_2() {
        assertEquals(3, gcd3(-36, 15));
    }

    @Test
    public void test_gcd3_3() {
        assertEquals(3, gcd3(36, -15));
    }

    @Test
    public void test_gcd3_4() {
        assertEquals(3, gcd3(-36, -15));
    }

}
