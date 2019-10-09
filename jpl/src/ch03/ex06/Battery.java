package ch03.ex06;

public class Battery extends EnergySource {
    @Override
    boolean empty() {
        return false;
    }
}
