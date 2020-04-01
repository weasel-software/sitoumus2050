package com.soikea.commitment2050.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Helper class to present statistics values
 *
 */
public class StatisticsValueAndDate implements Serializable {
    private static final long serialVersionUID = 7500675260510654105L;
    private Double value;
    private String date;

    public StatisticsValueAndDate() {
    }

    public StatisticsValueAndDate(Double value, String date) {
        this.value = value;
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
}
