package com.soikea.commitment2050.model;

public enum ReportingInterval {
    CUSTOM(-1),
    MONTH_1(1),
    MONTH_3(3),
    MONTH_6(6),
    MONTH_12(12);

    private long interval;

    private ReportingInterval(long interval) {
        this.interval = interval;
    }

    public long getInterval() {
        return interval;
    }


}
