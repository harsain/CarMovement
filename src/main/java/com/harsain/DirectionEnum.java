package com.harsain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by harsain on 21/1/17.
 */
public enum DirectionEnum {
    NORTH, SOUTH, EAST, WEST;

    public final static Set<String> values = new HashSet<String>(DirectionEnum.values().length);

    static {
        for (DirectionEnum dir : DirectionEnum.values() ) {
            values.add(dir.name());
        }
    }

    public static boolean contains(String value) {
        return values.contains(value);
    }
}
