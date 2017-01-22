package com.harsain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by harsain on 22/1/17.
 */
public enum UserInputEnum {
    INIT, FORWARD, TURN_LEFT, TURN_RIGHT, GPS_REPORT, QUIT;

    private final static Set<String> values = new HashSet<String>(UserInputEnum.values().length);

    static {
        for (UserInputEnum userInput : UserInputEnum.values()) {
            values.add(userInput.name());
        }
    }

    public static boolean contains (String value) {
        return values.contains(value);
    }
}
