package ch02.ex17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void turnDoubleValue() {
        Vehicle vehicle = new Vehicle("takeshi");
        vehicle.turn(180.0);
        vehicle.turn(270.5);
        assertEquals(vehicle.getDirection(), 90.5);
    }

    @Test
    void turnTurnDirectionValue() {
        Vehicle vehicle = new Vehicle("takashi");
        vehicle.turn(90);
        vehicle.turn(90);
        vehicle.turn(270);
        assertEquals(vehicle.getDirection(), 90);
    }
}