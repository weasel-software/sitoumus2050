package com.soikea.commitment2050.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.soikea.commitment2050.model.City;
import com.soikea.commitment2050.model.Commitment;
import com.soikea.commitment2050.model.CommitmentsPerCityMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static com.soikea.commitment2050.service.Constants.*;

@Component(
    immediate = true,
    service = CommitmentsPerCityService.class
)
public class CommitmentsPerCityServiceImpl implements CommitmentsPerCityService {
    private static Logger logger = LoggerFactory.getLogger(CommitmentsPerCityServiceImpl.class.getName());
    private static Gson gson = new GsonBuilder().create();

    @Reference
    CommitmentService commitmentService;

    @Override
    public InputStream getCitiesFile(){
        FileEntry citiesListFileEntry = null;
        InputStream citiesFile = null;

        try {
            citiesListFileEntry = DLAppLocalServiceUtil.getFileEntry(CITIES_FILE_CATEGORY_ID);
        } catch (PortalException e) {
            logger.error("Failed to get file entry for cities from media library with: " + CITIES_FILE_CATEGORY_ID);
        }
        String citiesListVersion = citiesListFileEntry.getVersion();
        try {
            citiesFile = DLFileEntryLocalServiceUtil.getFileAsStream(CITIES_FILE_CATEGORY_ID, citiesListVersion);
        } catch (PortalException e) {
            logger.error("Failed to get cities file (" + CITIES_FILE_CATEGORY_ID + ") as stream.");
        }
        return citiesFile;
    }

    @Override
    public List<City> getCitiesAsList(){
        InputStream citiesFile = getCitiesFile();

        JsonReader reader = new JsonReader(new InputStreamReader(citiesFile));
        List<City> cities = new ArrayList<City>();
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                City city = gson.fromJson(reader, City.class);
                cities.add(city);
            }
            reader.endArray();
            reader.close();
        } catch (java.io.IOException e) {
            logger.error("Failed to convert cities file.");
        }

        return cities;
    }

    @Override
    public Map<String, City> getCitiesAsMap(){
        InputStream citiesFile = getCitiesFile();
        JsonReader reader = new JsonReader(new InputStreamReader(citiesFile));
        Map<String, City> cities = new HashMap<String, City>();

        try {
            reader.beginArray();
            while (reader.hasNext()) {
                City city = gson.fromJson(reader, City.class);
                city.commitmentAmount = 0;
                cities.put(city.name_fi_FI, city);
            }
            reader.endArray();
            reader.close();
        } catch (java.io.IOException e) {
            logger.error("Failed to convert cities file.");
        }
        return cities;
    }

    @Override
    public void calculateCommitmentsPerCity() {
        try {
            int commitmentsCount = 0;
            List<Commitment> commitments;
            commitmentsCount = JournalArticleLocalServiceUtil.getNotInTrashArticlesCount(COMMITMENTS_GROUP_ID, COMMITMENTS_FOLDER_ID);

            Map<String, City> commitmentsPerCity = getCitiesAsMap();

            for (int i = 0; i < commitmentsCount; i = i + 50) {
                try {
                    commitments = commitmentService.getRangeOfLatestApprovedCommitments(i, i + 50);
                    if (commitments != null) {
                        commitments.forEach((commitment) -> {
                            String commitmentAddress = commitment.getAddress();
                            if (commitmentsPerCity.containsKey(commitmentAddress)) {
                                City city = commitmentsPerCity.get(commitmentAddress);
                                int commitmentAmount = city.commitmentAmount;
                                int increasedCommitmentAmount = commitmentAmount + 1;
                                city.commitmentAmount = increasedCommitmentAmount;
                            }
                        });
                    }

                } catch (Exception e) {
                    logger.error("ERROR: " + e.getMessage());
                }
            }
            com.liferay.portal.kernel.json.JSONArray citiesArray = JSONFactoryUtil.createJSONArray();

            Iterator it = commitmentsPerCity.entrySet().iterator();

            while (it.hasNext()) {
                JSONObject city = JSONFactoryUtil.createJSONObject();
                Map.Entry pairs = (Map.Entry)it.next();
                if(((City) pairs.getValue()).commitmentAmount > 0){
                    city.put("city", pairs.getKey());
                    city.put("count", ((City) pairs.getValue()).commitmentAmount);
                    city.put("longitude", ((City) pairs.getValue()).longitude);
                    city.put("latitude", ((City) pairs.getValue()).latitude);
                    citiesArray.put(city);
                }
            }

            JSONObject map = JSONFactoryUtil.createJSONObject();
            map.put("data", citiesArray);

            CommitmentsPerCityMap.setCommitmentsPerCity(map);
            logger.debug("--------------------------------------------------------");
            logger.debug("Commitments per city successfully calculated and updated.");
            logger.debug("--------------------------------------------------------");

        } catch (Exception e) {
            logger.error("ERROR: Failed at calculateCommitmentsPerCity with message:", e.getMessage());
        }

    }
}
