package test.ch02.ex15;

import ch02.ex15.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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