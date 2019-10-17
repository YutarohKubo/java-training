package ch03.ex01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerVehicleTest {

    @Test
    void getSeatNumTest () {
        PassengerVehicle passengerVehicle1 = new PassengerVehicle(4, 2, "suzuki");
        assertEquals(4, passengerVehicle1.getSeatNum());
    }

    @Test
    void getPassengerNumTest () {
        PassengerVehicle passengerVehicle2 = new PassengerVehicle(8, 1, "okamoto");
        assertEquals(1, passengerVehicle2.getPassengerNum());
    }

}