package ch03.ex01;

import java.util.Random;

public class Vehicle {
    private double currentSpeed;
    private double direction;
    private String ownerName;
    private static long vehicleId;
    private final int productId;
    private static int maxProductId;

    public Vehicle() {
        this(null);
    }

    public Vehicle(String ownerName) {
        vehicleId++;
        productId = new Random().nextInt(1000000) + 1;
        if (maxProductId < productId) {
            maxProductId = productId;
        }
        this.ownerName = ownerName;
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

    public void turn(double angle) {

    }

    public void turn (int angle) {

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

    /*public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        Vehicle vehicle1 = new Vehicle(args[0]);
        System.out.println(vehicle1);
    }*/

}
