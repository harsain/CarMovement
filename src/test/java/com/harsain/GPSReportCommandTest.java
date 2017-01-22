package com.harsain;

import com.harsain.Exception.InvalidDirectionException;
import com.harsain.Exception.InvalidFirstCommandException;
import com.harsain.Exception.InvalidPositionException;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by harsain on 22/1/17.
 */
public class GPSReportCommandTest extends TestCase {

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

    public void testExecuteIfGPSReportIsFirstCommand() throws Exception {
        GPSReportCommand gpsReport = new GPSReportCommand(car);
        try {
            gpsReport.execute(null);

            fail("Should throw an Exception");
        } catch (Exception exception) {
            assertEquals("Invalid first command. INIT should be the first command.", exception.getMessage());
        }
    }

    public void testExecuteWithValidArguments() throws InvalidDirectionException, InvalidPositionException, InvalidFirstCommandException {
        try {
            car.init(1, 1, DirectionEnum.NORTH);
            GPSReportCommand gpsReportCommand = new GPSReportCommand(car);

            gpsReportCommand.execute(null);

            assertEquals("Report: 1, 1, NORTH \n", os.toString());

        } catch (Exception exception) {
            fail("Should not throw an Exception");
        }
    }
}