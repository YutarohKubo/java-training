package ch03.ex09;

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

}
