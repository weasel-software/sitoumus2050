package com.soikea.commitment2050.service;

import java.util.*;

/**
 * Helper class to map main goals to agenda2030 goals.
 * Should be used only when migrating
 */
public class Goals2050Agenda2030Map {
    private static final Goals2050Agenda2030Map instance = new Goals2050Agenda2030Map();
    private static Map<String, List<Long>> map;

    public static Goals2050Agenda2030Map getInstance(){
        return instance;
    }

    private Goals2050Agenda2030Map() {
        map = new HashMap<String, List<Long>>();

        List<Long> ag1 = new ArrayList<Long>(
                Arrays.asList(
                    31822l,
                    31828l,
                    31819l,
                    31820l,
                    31821l,
                    31823l,
                    31826l,
                    31834l,
                    31835l));

        List<Long> ag2 = new ArrayList<Long>(
                Arrays.asList(
                    31834l,
                    31822l,
                    31828l,
                    31835l));
        List<Long> ag3 = new ArrayList<Long>(
                Arrays.asList(
                    31826l,
                    31822l,
                    31827l,
                    31830l,
                    31834l,
                    31835l));
        List<Long> ag4 = new ArrayList<Long>(
                Arrays.asList(
                    31821l,
                    31829l,
                    31828l,
                    31831l,
                    31834l,
                    31835l));
        List<Long> ag5 = new ArrayList<Long>(
                Arrays.asList(
                    31825l,
                    31831l,
                    31820l,
                    31827l,
                    31833l,
                    31835l));
        List<Long> ag6 = new ArrayList<Long>(
                Arrays.asList(
                    31826l,
                    31827l,
                    31820l,
                    31824l,
                    31825l,
                    31830l,
                    31833l,
                    31835l));
        List<Long> ag7 = new ArrayList<Long>(
                Arrays.asList(
                    31830l,
                    31822l,
                    31826l,
                    31827l,
                    31835l));
        List<Long> ag8 = new ArrayList<Long>(
                Arrays.asList(
                    31833l,
                    31834l,
                    31821l,
                    31824l,
                    31830l,
                    31832l,
                    31835l));

//        map.put("Yhdenvertaiset mahdollisuudet hyvinvointiin", ag1);
//        map.put("Vaikuttavien ihmisten yhteiskunta", ag2);
//        map.put("Työtä kestävästi", ag3);
//        map.put("Kestävät yhdyskunnat ja paikallisyhteisöt", ag4);
//        map.put("Hiilineutraali yhteiskunta", ag5);
//        map.put("Resursiviisas talous", ag6);
//        map.put("Luonnon kantokykyä kunnioittavat elämäntavat", ag7);
//        map.put("Luontoa kunnioittava päätöksenteko", ag8);

        map.put("Yhdenvertaisuus", ag1);
        map.put("Vaikuttavien kansalaisten yhteiskunta", ag2);
        map.put("Työtä kestävästi", ag3);
        map.put("Kestävät yhdyskunnat", ag4);
        map.put("Hiilineutraali yhteiskunta", ag5);
        map.put("Resurssiviisas talous", ag6);
        map.put("Kestävät elämäntavat", ag7);
        map.put("Luontoa kunnioittava päätöksenteko", ag8);


    }

    /**
     * Get agenda2030 categoryids
     * @param goal goal name
     * @return list on ids
     */
    public static long[] getAgenda2030Categories(String goal) {
        if (map.containsKey(goal)) {
            long[] result = map.get(goal).stream().mapToLong(l -> l).toArray();
            return result;
        }
        return new long[0];
    }
}
