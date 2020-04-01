package com.soikea.commitment2050.service;

import com.soikea.commitment2050.model.City;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface CommitmentsPerCityService {

    /**
     * Gets the cities file from Liferay media library
     */
    InputStream getCitiesFile();

    /**
     * Returns contents of the cities file parsed as a List
     */
    List<City> getCitiesAsList();

    /**
     * Returns contents of the cities file parsed as a Map
     */
    Map<String, City> getCitiesAsMap();

    /**
     * Calculates how many commitments there are per city, city list provided.
     * Stores the information in a static model.
     */
    void calculateCommitmentsPerCity();
}
