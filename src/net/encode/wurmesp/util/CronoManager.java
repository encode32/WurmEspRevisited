package net.encode.wurmesp.util;

import java.util.HashMap;

public class CronoManager {
    private long timelapse;
    private long time;
    private long remaining;
    private long future;
    private long last;

    public CronoManager(long timelapse) {
        this.timelapse = timelapse;
        this.time = System.currentTimeMillis();
        this.future = this.time + this.timelapse;
        this.last = this.remaining = (this.future - System.currentTimeMillis()) / 1000L;
    }

    public void restart(long timelapse) {
        this.timelapse = timelapse;
        this.time = System.currentTimeMillis();
        this.future = this.time + this.timelapse;
        this.last = this.remaining = (this.future - System.currentTimeMillis()) / 1000L;
    }

    public HashMap<CronoDataType, Long> getTime() {
        this.last = this.remaining = (this.future - System.currentTimeMillis()) / 1000L;
        long days = (long)Math.floor(this.remaining / 86400L);
        long hours = (long)Math.floor(this.remaining % 86400L / 3600L);
        long minutes = (long)Math.floor(this.remaining % 86400L % 3600L / 60L);
        long seconds = (long)Math.floor(this.remaining % 86400L % 3600L % 60L);
        HashMap<CronoDataType, Long> returnedTime = new HashMap<CronoDataType, Long>();
        returnedTime.put(CronoDataType.DAYS, days);
        returnedTime.put(CronoDataType.HOURS, hours);
        returnedTime.put(CronoDataType.MINUTES, minutes);
        returnedTime.put(CronoDataType.SECONDS, seconds);
        return returnedTime;
    }

    public int getDays() {
        this.last = this.remaining = (this.future - System.currentTimeMillis()) / 1000L;
        long days = (long)Math.floor(this.remaining / 86400L);
        return (int)days;
    }

    public int getHours() {
        this.last = this.remaining = (this.future - System.currentTimeMillis()) / 1000L;
        long hours = (long)Math.floor(this.remaining % 86400L / 3600L);
        return (int)hours;
    }

    public int getMinutes() {
        this.last = this.remaining = (this.future - System.currentTimeMillis()) / 1000L;
        long minutes = (long)Math.floor(this.remaining % 86400L % 3600L / 60L);
        return (int)minutes;
    }

    public int getSeconds() {
        this.last = this.remaining = (this.future - System.currentTimeMillis()) / 1000L;
        long seconds = (long)Math.floor(this.remaining % 86400L % 3600L % 60L);
        return (int)seconds;
    }

    public boolean hasNext() {
        this.remaining = (this.future - System.currentTimeMillis()) / 1000L;
        return this.remaining < this.last;
    }

    public boolean hasEnded() {
        return System.currentTimeMillis() > this.future;
    }

    public static enum CronoDataType {
        DAYS,
        HOURS,
        MINUTES,
        SECONDS;

    }
}

