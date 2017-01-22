package com.harsain;

import junit.framework.TestCase;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by harsain on 22/1/17.
 */
public class InitCommandTest extends TestCase {

    private Car car;
    OutputStream os;

    public void setUp() throws Exception {
        super.setUp();

        car = new Car();
    }

    public void tearDown() throws Exception {
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    public void testExecuteWithInvalidXYArgs() throws Exception {
        InitCommand command = new InitCommand(car);
        String arguments = "a,a,EAST";
        try {
            command.execute(arguments);

            fail("Should throw an Exception");
        } catch (Exception exception) {
            assertEquals("Invalid x,y values. PLACE takes 3 parameters in format INT,INT,STRING no spaces. eg. 1,2,EAST", exception.getMessage());
        }
    }

    public void testExecuteWithInvalidXArg() throws Exception {
        InitCommand command = new InitCommand(car);
        String arguments = "a,1,EAST";
        try {
            command.execute(arguments);

            fail("Should throw an Exception");
        } catch (Exception exception) {
            assertEquals("Invalid x,y values. PLACE takes 3 parameters in format INT,INT,STRING no spaces. eg. 1,2,EAST", exception.getMessage());
        }
    }

    public void testExecuteWithInvalidYArg() throws Exception {
        InitCommand command = new InitCommand(car);
        String arguments = "1,a,EAST";
        try {
            command.execute(arguments);

            fail("Should throw an Exception");
        } catch (Exception exception) {
            assertEquals("Invalid x,y values. PLACE takes 3 parameters in format INT,INT,STRING no spaces. eg. 1,2,EAST", exception.getMessage());
        }
    }

    public void testExecuteWithInvalidDirectionArg() throws Exception {
        InitCommand command = new InitCommand(car);
        String arguments = "1,1,INVALID";
        try {
            command.execute(arguments);

            fail("Should throw an Exception");
        } catch (Exception exception) {
            assertEquals("Directions should be between [NORTH, SOUTH, EAST, WEST]", exception.getMessage());
        }
    }

    public void testExecuteWithNoDirectionArg() throws Exception {
        InitCommand command = new InitCommand(car);
        String arguments = "1,1";
        try {
            command.execute(arguments);

            fail("Should throw an Exception");
        } catch (Exception exception) {
            assertEquals("Invalid x,y values. PLACE takes 3 parameters in format INT,INT,STRING no spaces. eg. 1,2,EAST", exception.getMessage());
        }
    }

    public void testExecuteWithValidArg() throws Exception {
        InitCommand command = new InitCommand(car);
        String arguments = "1,1,NORTH";
        try {
            command.execute(arguments);

            assertEquals("Report: 1, 1, NORTH \n", car.gpsReport());

        } catch (Exception exception) {
            fail("Should not throw an Exception");
        }
    }
}