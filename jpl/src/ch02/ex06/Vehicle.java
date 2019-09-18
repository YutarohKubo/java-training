package ch02.ex06;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class Vehicle {
    private double currentSpeed;
    private double direction;
    private String ownerName;
    private static long VelicleId;
    private final int productId;

    public Vehicle(String ownerName) {
        this.ownerName = ownerName;
        productId = new Random().nextInt(1000000) + 1;
    }

    @Override
    public String toString() {
        return ownerName;
    }

    public void printField () {
        System.out.println("speed = " + currentSpeed);
        System.out.println("direction = " + direction);
        System.out.println("ownerName = " + ownerName);
        System.out.println("productId = " + productId);
    }
}
