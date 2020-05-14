package ch21.ex07;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StackTest {

    @Test
    public void testPushPop() {
        String[] expectedArray = {"a", "b", "d"};
        MyStack<String> stack = new MyStack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.pop();
        stack.push("d");

        assertArrayEquals(expectedArray, stack.toArray());
    }

    @Test
    public void testException() {
        MyStack<String> stack = new MyStack<>();
        assertThrows(EmptyStackException.class, stack::peek);
    }

}
