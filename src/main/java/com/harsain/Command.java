package com.harsain;

/**
 * Created by harsain on 21/1/17.
 */
public abstract class Command {
    protected Car _car;

    public Command(Car car) {
        this._car = car;
    }

    public abstract void execute(String arguments) throws Exception;
}
