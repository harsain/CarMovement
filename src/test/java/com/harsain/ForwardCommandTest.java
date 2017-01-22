package com.harsain;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by harsain on 22/1/17.
 */
public class ForwardCommandTest extends TestCase {

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

    public void testExecuteIfForwardIsFirstCommand() throws Exception {
        try {
            ForwardCommand forwardCommand = new ForwardCommand(car);
            forwardCommand.execute(null);

            fail("Should have thrown an Exception");
        } catch (Exception exception) {
            assertEquals("Invalid first command. INIT should be the first command.", exception.getMessage());
        }
    }

    public void testExecuteToMoveCarForward() {
        try {
            ForwardCommand forwardCommand = new ForwardCommand(car);

            car.init(1, 1, DirectionEnum.NORTH);

            forwardCommand.execute(null);

            assertEquals("Report: 1, 2, NORTH \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not throw an Exception");
        }
    }

    public void testExecuteToMoveCarForwardFromMaxIndex() {
        try {
            ForwardCommand forwardCommand = new ForwardCommand(car);

            car.init(1, 4, DirectionEnum.NORTH);

            forwardCommand.execute(null);

            assertEquals("Report: 1, 4, NORTH \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not throw an Exception");
        }
    }

    public void testExecuteToMoveCarForwardFromMinIndex() {
        try {
            ForwardCommand forwardCommand = new ForwardCommand(car);

            car.init(0, 1, DirectionEnum.WEST);

            forwardCommand.execute(null);

            assertEquals("Report: 0, 1, WEST \n", car.gpsReport());
        } catch (Exception exception) {
            fail("Should not throw an Exception");
        }
    }
}