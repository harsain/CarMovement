package com.harsain;

import com.harsain.Exception.InvalidDirectionException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by harsain on 21/1/17.
 */
public class InitCommand extends Command {

    // properties required for the car object
    private int x;
    private int y;
    private DirectionEnum direction;

    /**
     * constructor
     * @param car
     */
    public InitCommand(Car car) {
        super(car);
    }

    /**
     * sets the X index
     * @param x
     */
    private void setX(int x) {
        this.x = x;
    }

    /**
     * sets Y index
     * @param y
     */
    private void setY(int y) {
        this.y = y;
    }

    /**
     * sets Direction
     * @param direction
     */
    private void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    /**
     * execute the command
     * this method validates and calls the correct method on the car object
     * @param arguments
     * @throws Exception
     */
    public void execute(String arguments) throws Exception {
        // validate & set parameters
        this.validateAndSetParameters(arguments);
        // call the init method on the car object
        _car.init(x, y, direction);
    }

    /**
     * this method validates and sets the parameters
     * @param arguments
     * @throws Exception
     */
    private void validateAndSetParameters(String arguments) throws Exception {
        // split the arguments
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(arguments.split(",")));
        // check the arguments should be exactly 3; which are x, y & direction
        if (args.size() == 3) {
            try {
                Integer x = Integer.parseInt(args.get(0).trim());
                Integer y = Integer.parseInt(args.get(1).trim());
                this.setX(x);
                this.setY(y);
                try {
                    DirectionEnum direction = DirectionEnum.valueOf(args.get(2).trim().toUpperCase());
                    this.setDirection(direction);
                } catch (Exception exception ) {
                    throw new InvalidDirectionException(String.format("Directions should be between %s", Arrays.asList(DirectionEnum.values())));
                }
            } catch (NumberFormatException numEx) {
                throw new Exception("Invalid x,y values. PLACE takes 3 parameters in format INT,INT,STRING no spaces. eg. 1,2,EAST");
            } catch (IllegalArgumentException illegalArgEx) {
                throw new Exception("Invalid direction input. PLACE takes 3 parameters in format INT,INT,STRING no spaces. eg. 1,2,EAST");
            }
        } else
            throw new Exception("Invalid x,y values. PLACE takes 3 parameters in format INT,INT,STRING no spaces. eg. 1,2,EAST");
    }
}
