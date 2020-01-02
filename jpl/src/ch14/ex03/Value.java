package ch14.ex03;

public class Value {
    public int value = 0;

    synchronized public void incrementValue () {
        value++;
        System.out.println("value = " + value);
    }
}
