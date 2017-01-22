package com.harsain;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by harsain on 22/1/17.
 */
public class TurnLeftCommandTest extends TestCase {

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

    public void testExecuteIfTurnLeftIsFirstCommand() throws Exception {
        try {
            TurnLeftCommand turnLeft = new TurnLeftCommand(car);
            turnLeft.execute(null);

            fail("Should have thrown an Exception");
        } catch (Exception exception) {
            assertEquals("Invalid first command. INIT should be the first command.", exception.getMessage());
        }
    }

    public void testExecuteIfTurnLeftFromNorthCommand() {
        try {
            TurnLeftCommand turnLeft = new TurnLeftCommand(car);

            car.init(1,1, DirectionEnum.NORTH);

            turnLeft.execute(null);

            assertEquals("Report: 1, 1, WEST \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not thrown an Exception");
        }
    }

    public void testExecuteIfTurnLeftFromWestCommand() {
        try {
            TurnLeftCommand turnLeft = new TurnLeftCommand(car);

            car.init(1,1, DirectionEnum.WEST);

            turnLeft.execute(null);

            assertEquals("Report: 1, 1, SOUTH \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not thrown an Exception");
        }
    }

    public void testExecuteIfTurnLeftFromSouthCommand() {
        try {
            TurnLeftCommand turnLeft = new TurnLeftCommand(car);

            car.init(1,1, DirectionEnum.SOUTH);

            turnLeft.execute(null);

            assertEquals("Report: 1, 1, EAST \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not thrown an Exception");
        }
    }

    public void testExecuteIfTurnLeftFromEastCommand() {
        try {
            TurnLeftCommand turnLeft = new TurnLeftCommand(car);

            car.init(1,1, DirectionEnum.EAST);

            turnLeft.execute(null);

            assertEquals("Report: 1, 1, NORTH \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not thrown an Exception");
        }
    }
}