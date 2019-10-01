package ch03.ex09;

public class Garage implements Cloneable {

    private Vehicle[] vehicles;

    public Garage (Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Vehicle vehicle : vehicles) {
            builder.append(vehicle);
            builder.append(",");
        }
        return builder.toString();
    }

    @Override
    protected Garage clone() {
        try {
            Garage garage = (Garage) super.clone();
            garage.vehicles = vehicles.clone();
            return garage;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }

    public static void main(String[] args) {
        PassengerVehicle passengerVehicle1 = new PassengerVehicle(4, 2, "suzuki");
        PassengerVehicle passengerVehicle2 = passengerVehicle1.clone();
        passengerVehicle2.setOwnerName("shibayama");
        PassengerVehicle passengerVehicle3 = passengerVehicle1.clone();
        passengerVehicle3.setOwnerName("kuroda");

        Garage garage = new Garage(new Vehicle[]{passengerVehicle1, passengerVehicle2, passengerVehicle3});
        Garage cloneGarage = garage.clone();
        cloneGarage.getVehicles()[1].setOwnerName("murata");

        System.out.println("garage: " + garage);
        System.out.println("cloneGarage: " + cloneGarage);
    }
}
