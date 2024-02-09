package com.fivesysdev.weatherapp.util;

public enum Direction {
    NORTH(0), NORTH_EAST(45), EAST(90), SOUTH_EAST(135), SOUTH(180), SOUTH_WEST(225), WEST(270), NORTH_WEST(315);

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private int degrees;



    private static int normalizeDegrees(int degrees) {
        degrees = degrees%360;
        if (degrees < 0) degrees = 360 + degrees;
        return degrees;
    }

    public static Direction closestToDegrees(int degrees) {
        degrees = normalizeDegrees(degrees);
        Direction min = Direction.NORTH;
        for (Direction direction: Direction.values()) {
            if (degrees > direction.degrees){
                min = direction;
                continue;
            }
            if (degrees - min.degrees > direction.degrees - degrees) {
                return direction;
            } else {
                return min;
            }
        }
        return min;
    }


}
