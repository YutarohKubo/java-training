package ch03.ex08;

public class PassengerVehicle extends Vehicle implements Cloneable {

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

    @Override
    protected PassengerVehicle clone(){
        return (PassengerVehicle) super.clone();
    }

    public static void main(String[] args) {
        PassengerVehicle passengerVehicle1 = new PassengerVehicle(4, 2, "suzuki");
        PassengerVehicle passengerVehicle2 = passengerVehicle1.clone();
        passengerVehicle2.setOwnerName("shibayama");
        PassengerVehicle passengerVehicle3 = passengerVehicle1.clone();
        passengerVehicle3.setOwnerName("kuroda");

        System.out.println("vehicle1 = " + passengerVehicle1);
        System.out.println("vehicle2 = " + passengerVehicle2);
        System.out.println("vehicle3 = " + passengerVehicle3);
    }

}
