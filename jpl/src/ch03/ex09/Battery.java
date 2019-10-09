package ch03.ex09;

public class Battery extends EnergySource {
    @Override
    boolean empty() {
        return false;
    }
}
