package com.soikea.commitment2050.model;

import com.liferay.portal.kernel.json.JSONObject;

/**
 * Map containing the number of commitments per that city
 */
public class CommitmentsPerCityMap {

    private static JSONObject commitmentsPerCity;

    public static JSONObject getCommitmentsPerCity() {
        return commitmentsPerCity;
    }

    public static void setCommitmentsPerCity(JSONObject mapJson) {
        commitmentsPerCity = mapJson;
    }
}
