package ch02.ex05;

import java.util.Random;

public class Vehicle {
    private double currentSpeed;
    private double direction;
    private String ownerName;
    private static long VelicleId;
    private final int productId;

    public Vehicle () {
        productId = new Random().nextInt(1000000) + 1;
    }

    public void printField () {
        System.out.println("speed = " + currentSpeed);
        System.out.println("direction = " + direction);
        System.out.println("ownerName = " + ownerName);
        System.out.println("productId = " + productId);
    }

    public static void main(String[] args) {
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        Vehicle vehicle3 = new Vehicle();
        Vehicle vehicle4 = new Vehicle();
        Vehicle vehicle5 = new Vehicle();

        vehicle1.printField();
        vehicle2.printField();
        vehicle3.printField();
        vehicle4.printField();
        vehicle5.printField();
    }
}
