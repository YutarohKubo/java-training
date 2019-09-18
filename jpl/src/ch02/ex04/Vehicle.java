package ch02.ex04;

import java.util.Random;

public class Vehicle {
    private double currentSpeed;
    private double direction;
    private String owenerName;
    private static long VelicleId;
    private final int productId;

    public Vehicle () {
        productId = new Random().nextInt(1000000) + 1;
    }
}
