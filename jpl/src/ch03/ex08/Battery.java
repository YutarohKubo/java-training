package ch03.ex08;

public class Battery extends EnergySource {
    @Override
    boolean empty() {
        return false;
    }
}
