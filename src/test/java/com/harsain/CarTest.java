package com.harsain;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by harsain on 22/1/17.
 */
public class CarTest extends TestCase {

    private Car car;
    OutputStream os;

    public void setUp() throws Exception {
        super.setUp();

        car = new Car();

        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    public void tearDown() throws Exception {
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    public void testInitShouldBeFirstCommand() {
        try {
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Car tempCar = new Car();
        try {
            tempCar.turnLeft();
            fail("Missing Exception");
        } catch (Exception exception) {
            assertEquals("Invalid first command. INIT should be the first command.", exception.getMessage());
        }
    }

    public void testAnyCommandShouldWorkAfterInit() {
        try {
            car.init(1,1, DirectionEnum.NORTH);
            car.forward();

            assertEquals("Report: 1, 2, NORTH \n", car.gpsReport());
        } catch (Exception e) {
            fail("Should not have thrown exception");
        }
    }

    public void testInit() throws Exception {
        car.init(1,1, DirectionEnum.EAST);

        assertEquals("Report: 1, 1, EAST \n", car.gpsReport());
    }

    public void testInitWithNorthDirection() throws Exception {
        car.init(1,1, DirectionEnum.NORTH);

        assertEquals("Report: 1, 1, NORTH \n", car.gpsReport());
    }

    public void testInitWithSouthDirection() throws Exception {
        car.init(1,1, DirectionEnum.SOUTH);

        assertEquals("Report: 1, 1, SOUTH \n", car.gpsReport());
    }

    public void testInitWithWestDirection() throws Exception {
        car.init(1,1, DirectionEnum.WEST);

        assertEquals("Report: 1, 1, WEST \n", car.gpsReport());
    }

    public void testForwardShouldMoveAUnitInTheDirectionOfTheCar() throws Exception {
        try {
            car.init(1,1, DirectionEnum.NORTH);
            car.forward();

            assertEquals("Report: 1, 2, NORTH \n", car.gpsReport());
        } catch (Exception e) {
            fail("Should not have thrown exception");
        }
    }

    public void testTurnLeftShouldReturnChangedDirection() throws Exception {
        try {
            car.init(1,1, DirectionEnum.NORTH);
            car.turnLeft();

            assertEquals("Report: 1, 1, WEST \n", car.gpsReport());
        } catch (Exception e) {
            fail("Should not have thrown exception");
        }
    }

    public void testTurnRightShouldReturnChangedDirection() throws Exception {
        try {
            car.init(1,1, DirectionEnum.NORTH);
            car.turnRight();

            assertEquals("Report: 1, 1, EAST \n", car.gpsReport());
        } catch (Exception e) {
            fail("Should not have thrown exception");
        }
    }

    public void testForwardShouldNotMoveIfOutOfIndex() {
        try {
            car.init(4,4, DirectionEnum.NORTH);
            car.forward();

            assertEquals("Report: 4, 4, NORTH \n", car.gpsReport());
        } catch (Exception e) {
            fail("Should not have thrown exception");
        }
    }

    public void testInvalidPositionShouldThrowException() throws Exception {
        try {
            car.init(4,100, DirectionEnum.NORTH);
            car.forward();
            car.gpsReport();

            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid position attributes. x & y should be between 0 - 4", e.getMessage());
        }
    }

    public void testForwardAfterMaxIndexShouldNotUpdateIndex() {
        try {
            car.init(1,1, DirectionEnum.NORTH);
            car.forward();
            assertEquals("Report: 1, 2, NORTH \n", car.gpsReport());

            car.forward();
            assertEquals("Report: 1, 3, NORTH \n", car.gpsReport());

            car.forward();
            assertEquals("Report: 1, 4, NORTH \n", car.gpsReport());

            car.forward();
            assertEquals("Report: 1, 4, NORTH \n", car.gpsReport());

        } catch (Exception e) {
            fail("Should not have thrown exception");
        }
    }

}