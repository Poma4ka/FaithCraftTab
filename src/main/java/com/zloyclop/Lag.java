package com.zloyclop;

public class Lag implements Runnable {
    private static double tps = 20.1D;

    private static long oldTimestamp = System.currentTimeMillis();

    public static double getTPS()
    {
        return Math.round((tps * 10)) / 10D;
    }

    public void run()
    {
        tps = 1000.0D / (System.currentTimeMillis() - oldTimestamp) * 20.0D;

        oldTimestamp = System.currentTimeMillis();
    }
}