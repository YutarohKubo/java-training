package ch02.ex16;

import java.util.Random;

public class Vehicle {
    private double currentSpeed;
    private double direction;
    private String ownerName;
    private static long vehicleId;
    private final int productId;
    private static int maxProductId;

    public Vehicle() {
        vehicleId++;
        productId = new Random().nextInt(1000000) + 1;
        if (maxProductId < productId) {
            maxProductId = productId;
        }
    }

    public Vehicle(String ownerName) {
        this();
        this.ownerName = ownerName + "'s car!";
    }

    public static int getMaxProductId () {
        return maxProductId;
    }

    public void changeSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void stop () {
        currentSpeed = 0;
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

    @Override
    public String toString() {
        return ownerName;
    }

}
