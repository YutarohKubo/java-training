package ch06.ex02;

import java.util.Random;

public class Vehicle {
    private double currentSpeed;
    private double direction;
    private String ownerName;
    private static long vehicleId;
    private final int productId;
    private static int maxProductId;
    private static final int TURN_LEFT = 90;
    private static final int TURN_RIGHT = 270;

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
        direction += angle;
        direction %= 360;
    }

    public void turn(TurnDirection direction) {
        switch (direction) {
            case LEFT:
                this.direction += 90;
                break;

            case RIGHT:
                this.direction += 270;
                break;

            default:
                return;
        }
        this.direction %= 360;
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
