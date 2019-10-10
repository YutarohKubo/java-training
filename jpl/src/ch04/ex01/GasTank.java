package ch04.ex01;

public class GasTank implements EnergySource {
    @Override
    public boolean empty() {
        return false;
    }
}
