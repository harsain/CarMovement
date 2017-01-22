package com.harsain;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by harsain on 22/1/17.
 */
public class TurnRightCommandTest extends TestCase {

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

    public void testExecuteIfTurnRightIsFirstCommand() throws Exception {
        try {
            TurnRightCommand turnRight = new TurnRightCommand(car);
            turnRight.execute(null);

            fail("Should have thrown an Exception");
        } catch (Exception exception) {
            assertEquals("Invalid first command. INIT should be the first command.", exception.getMessage());
        }
    }

    public void testExecuteIfTurnRightFromNorth() {
        try {
            TurnRightCommand turnRight = new TurnRightCommand(car);

            car.init(1,1, DirectionEnum.NORTH);

            turnRight.execute(null);

            assertEquals("Report: 1, 1, EAST \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not have thrown an Exception");
        }
    }

    public void testExecuteIfTurnRightFromEast() {
        try {
            TurnRightCommand turnRight = new TurnRightCommand(car);

            car.init(1,1, DirectionEnum.EAST);

            turnRight.execute(null);

            assertEquals("Report: 1, 1, SOUTH \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not have thrown an Exception");
        }
    }

    public void testExecuteIfTurnRightFromSouth() {
        try {
            TurnRightCommand turnRight = new TurnRightCommand(car);

            car.init(1,1, DirectionEnum.SOUTH);

            turnRight.execute(null);

            assertEquals("Report: 1, 1, WEST \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not have thrown an Exception");
        }
    }

    public void testExecuteIfTurnRightFromWest() {
        try {
            TurnRightCommand turnRight = new TurnRightCommand(car);

            car.init(1,1, DirectionEnum.WEST);

            turnRight.execute(null);

            assertEquals("Report: 1, 1, NORTH \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not have thrown an Exception");
        }
    }
}