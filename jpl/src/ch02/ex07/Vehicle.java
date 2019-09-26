package ch02.ex07;

import java.util.Random;

public class Vehicle {
    private double currentSpeed;
    private double direction;
    private String ownerName;
    private static long vehicleId;
    private final int productId;
    
    public Vehicle () {
        this(null);
    }

    public Vehicle(String ownerName) {
        vehicleId++;
        productId = new Random().nextInt(1000000) + 1;
        this.ownerName = ownerName;
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
    public static void main(String[] args) {
        Vehicle vehicle1 = new Vehicle("satoh");
        Vehicle vehicle2 = new Vehicle("suzuki");
        Vehicle vehicle3 = new Vehicle("watanabe");
        Vehicle vehicle4 = new Vehicle("takahashi");
        Vehicle vehicle5 = new Vehicle();

        vehicle1.printField();
        vehicle2.printField();
        vehicle3.printField();
        vehicle4.printField();
        vehicle5.printField();
    }
    
}
