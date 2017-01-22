package com.harsain;

import com.harsain.Exception.InvalidDirectionException;
import com.harsain.Exception.InvalidFirstCommandException;
import com.harsain.Exception.InvalidPositionException;

/**
 * Created by harsain on 21/1/17.
 */
public class GPSReportCommand extends Command {
    public GPSReportCommand(Car car) {
        super(car);
    }

    public void execute(String arguments) throws InvalidPositionException, InvalidDirectionException, InvalidFirstCommandException {
        String position = _car.gpsReport();
        System.out.print(position);
    }
}
