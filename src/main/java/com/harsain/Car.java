package com.harsain;

import com.harsain.Exception.InvalidDirectionException;
import com.harsain.Exception.InvalidFirstCommandException;
import com.harsain.Exception.InvalidPositionException;

/**
 * Created by harsain on 21/1/17.
 */
public class Car {

    /**
     * properties of the car
     */
    private int x = 0;
    private int y = 0;
    private DirectionEnum direction;

    /* variable used to check if the command is first command */
    private boolean isFirstCommand;

    /**
     * set the min and max indices for the grid
     */
    private final int minIndexLimit = 0;
    private final int maxIndexLimit = 4;

    /**
     * constructor
     */
    public Car() {
        this.isFirstCommand = true;
    }

    /**
     * gets the X index of the Car
     * @return
     */
    private int getX() {
        return x;
    }

    /**
     * sets X index of the Car
     * @param x
     */
    private void setX(int x) {
        this.x = x;
    }

    /**
     * gets Y index of the Car
     * @return
     */
    private int getY() {
        return y;
    }

    /**
     * sets Y index of the Car
     * @param y
     */
    private void setY(int y) {
        this.y = y;
    }

    /**
     * gets the Direction of the Car
     * @return
     */
    private DirectionEnum getDirection() {
        return direction;
    }

    /**
     * sets Direction for the Car
     * @param direction
     */
    private void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    /**
     * init command execution
     * it sets the X & Y indices and Direction of the car
     * @param x
     * @param y
     * @param direction
     * @throws InvalidPositionException
     * @throws InvalidDirectionException
     */
    public void init(int x, int y, DirectionEnum direction) throws InvalidPositionException, InvalidDirectionException {
        // check if the indices are valid
        if (isIndexValid(x, y)) {
            this.setX(x);
            this.setY(y);
            this.setDirection(direction);
            // if the command is executed the first time the variable is set to false,
            // so that other commands can be executed
            if (this.isFirstCommand) {
                this.isFirstCommand = false;
            }
        } else {    // else throw an exception
            throw new InvalidPositionException(String.format("Invalid position attributes. x & y should be between %d - %d", this.minIndexLimit, this.maxIndexLimit));
        }
    }

    /**
     * forward exceution
     * it moves the car
     * @throws InvalidFirstCommandException
     */
    public void forward() throws InvalidFirstCommandException {
        // check if it's the first command
        if (isFirstCommand) {
            throw new InvalidFirstCommandException("Invalid first command. INIT should be the first command.");
        }
        // based on the direction of the car update the position
        switch (this.getDirection()) {
            case NORTH:
                if (this.getY() < this.maxIndexLimit) {
                    this.setY(this.getY() + 1);
                }
                break;
            case SOUTH:
                if (this.getY() > this.minIndexLimit) {
                    this.setY(this.getY() - 1);
                }
                break;
            case EAST:
                if (this.getX() < maxIndexLimit) {
                    this.setX(this.getX() + 1);
                }
                break;
            case WEST:
                if (this.getX() > minIndexLimit) {
                    this.setX(this.getX() - 1);
                }
                break;
            default:
                break;
        }
    }

    /**
     * based on the current direction of the car,
     * this method will update the direction to direction -90
     * @throws InvalidFirstCommandException
     */
    public void turnLeft() throws InvalidFirstCommandException {
        // check if it's the first command
        if (isFirstCommand) {
            throw new InvalidFirstCommandException("Invalid first command. INIT should be the first command.");
        }
        this.turnDirection("LEFT");
    }

    /**
     * based on the current direction of the car,
     * this method will update the direction to direction + 90
     * @throws InvalidFirstCommandException
     */
    public void turnRight() throws InvalidFirstCommandException {
        // check if it's the first command
        if (isFirstCommand) {
            throw new InvalidFirstCommandException("Invalid first command. INIT should be the first command.");
        }
        this.turnDirection("RIGHT");
    }

    /**
     * this method will output the current position of the car
     * @throws InvalidFirstCommandException
     */
    public String gpsReport() throws InvalidFirstCommandException {
        // check if it's the first command
        if (isFirstCommand) {
            throw new InvalidFirstCommandException("Invalid first command. INIT should be the first command.");
        }
        return String.format("Report: %d, %d, %s \n", this.getX(), this.getY(), this.getDirection().toString());
    }

    /*
     * HELPERS
     */

    /**
     * update direction based on the turn
     * @param turn
     */
    private void turnDirection(String turn) {
        if (turn.equalsIgnoreCase("LEFT")) {
            if (this.direction.toString().equalsIgnoreCase("NORTH")) {
                this.setDirection(DirectionEnum.WEST);
            } else if (this.direction.toString().equalsIgnoreCase("WEST")) {
                this.setDirection(DirectionEnum.SOUTH);
            } else if (this.direction.toString().equalsIgnoreCase("SOUTH")) {
                this.setDirection(DirectionEnum.EAST);
            } else if (this.direction.toString().equalsIgnoreCase("EAST")) {
                this.setDirection(DirectionEnum.NORTH);
            }
        } else if(turn.equalsIgnoreCase("RIGHT")) {
            if (this.direction.toString().equalsIgnoreCase("NORTH")) {
                this.setDirection(DirectionEnum.EAST);
            } else if (this.direction.toString().equalsIgnoreCase("EAST")) {
                this.setDirection(DirectionEnum.SOUTH);
            } else if (this.direction.toString().equalsIgnoreCase("SOUTH")) {
                this.setDirection(DirectionEnum.WEST);
            } else if (this.direction.toString().equalsIgnoreCase("WEST")) {
                this.setDirection(DirectionEnum.NORTH);
            }
        }
    }

    /**
     * check if the Index is valid
     * by checking for the max and min indices
     * @param x
     * @param y
     * @return
     */
    private boolean isIndexValid(int x, int y) {
        return (x >= this.minIndexLimit && x <= maxIndexLimit) && (y >= this.minIndexLimit && y <= maxIndexLimit);
    }
}
