package ch03.ex09;

public class GasTank extends EnergySource {
    @Override
    boolean empty() {
        return false;
    }
}
