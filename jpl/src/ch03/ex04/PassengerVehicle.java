package ch03.ex04;

public class PassengerVehicle extends Vehicle {

    private int seatNum;
    private int passengerNum;

    public PassengerVehicle (int seatNum, int passengerNum) {
        this(seatNum, passengerNum, null);
    }

    public PassengerVehicle (int seatNum, int passengerNum, String ownerName) {
        super(ownerName);
        this.seatNum = seatNum;
        this.passengerNum = passengerNum;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public int getPassengerNum() {
        return passengerNum;
    }

    public static void main(String[] args) {
        PassengerVehicle passengerVehicle1 = new PassengerVehicle(4, 2, "suzuki");
        PassengerVehicle passengerVehicle2 = new PassengerVehicle(8, 1, "okamoto");
        PassengerVehicle passengerVehicle3 = new PassengerVehicle(5, 5, "okamura");

        System.out.println("vehicle1 = " + passengerVehicle1);
        System.out.println("vehicle2 = " + passengerVehicle2);
        System.out.println("vehicle3 = " + passengerVehicle3);
    }

}
