package ch02.ex15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {

    @Test
    void vehicleChangeSpeed () {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.changeSpeed(100);
        assertEquals(100, vehicle1.getCurrentSpeed());
    }

    @Test
    void vehicleStop () {
        Vehicle vehicle = new Vehicle();
        vehicle.changeSpeed(100);
        vehicle.stop();
        assertEquals(0, vehicle.getCurrentSpeed());
    }

}