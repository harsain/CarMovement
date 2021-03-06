package com.harsain;

import com.harsain.Exception.InvalidDirectionException;
import com.harsain.Exception.InvalidFirstCommandException;
import com.harsain.Exception.InvalidPositionException;

/**
 * Created by harsain on 21/1/17.
 */
public class TurnLeftCommand extends Command {
    public TurnLeftCommand(Car car) {
        super(car);
    }

    public void execute(String arguments) throws InvalidPositionException, InvalidDirectionException, InvalidFirstCommandException {
        _car.turnLeft();
    }
}
