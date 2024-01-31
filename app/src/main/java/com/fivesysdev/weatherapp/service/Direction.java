package com.fivesysdev.weatherapp.service;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

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
        Direction min = Direction.N;
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
