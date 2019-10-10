package ch04.ex01;

public class Battery implements EnergySource {
    @Override
    public boolean empty() {
        return false;
    }
}
