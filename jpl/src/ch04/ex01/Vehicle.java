package ch04.ex01;

import java.util.Random;

public class Vehicle {
    private double currentSpeed;
    private double direction;
    private String ownerName;
    private static long vehicleId;
    private final int productId;
    private static int maxProductId;
    private EnergySource energySource;

    public Vehicle() {
        this(null, new GasTank());
    }

    public Vehicle(String ownerName) {
        this(ownerName, new GasTank());
    }

    public Vehicle(String ownerName, EnergySource energySource) {
        vehicleId++;
        productId = new Random().nextInt(1000000) + 1;
        if (maxProductId < productId) {
            maxProductId = productId;
        }
        this.ownerName = ownerName;
        this.energySource = energySource;
    }

    public static int getMaxProductId () {
        return maxProductId;
    }

    public final void changeSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public final void stop () {
        currentSpeed = 0;
    }

    public void turn(double angle) {
        direction += angle;
        direction %= 360;
    }

    public void turn (int angle) {
        direction += angle;
        direction %= 360;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getDirection() {
        return direction;
    }

    public void setEnergySource(EnergySource energySource) {
        this.energySource = energySource;
    }

    public void start () {
        if (energySource.empty()) {
            return;
        }

        if (energySource instanceof GasTank) {
            System.out.println("GasTank is not empty yet");
        }

        if (energySource instanceof Battery) {
            System.out.println("Battery is not empty yet");
        }
    }

    @Override
    public String toString() {
        return ownerName;
    }

    /*public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        Vehicle vehicle1 = new Vehicle(args[0]);
        System.out.println(vehicle1);
    }*/

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("nagahara", new Battery());
        vehicle.start();

    }

}
