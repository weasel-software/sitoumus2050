package com.soikea.commitment2050.model;

import java.util.ArrayList;
import java.util.List;

public enum MeterChartType {
    LINE,
    COLUMN,
    PIE,
    BAR,
    SCATTER,
    DONUT,
    TABLE,
    GAUGE,
    TIMELINE,
    MAP;

    private static final List<String> commitmentChartNames = new ArrayList<String>();
    private static final List<String> allChartNames = new ArrayList<String>();

    static {
        commitmentChartNames.add(LINE.toString());
        commitmentChartNames.add(COLUMN.toString());
        commitmentChartNames.add(PIE.toString());

        for ( MeterChartType m : MeterChartType.values()) {
            allChartNames.add(m.toString());
        }
    }

    public static List<String> getCommitmentChartNames() {
        return commitmentChartNames;
    }

    public static List<String> getAllChartNames() {
        return allChartNames;
    }
}
