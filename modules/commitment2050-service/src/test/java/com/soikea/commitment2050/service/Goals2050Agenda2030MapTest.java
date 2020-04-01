package com.soikea.commitment2050.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Goals2050Agenda2030MapTest {

    @Test
    public void testMap() {
        Goals2050Agenda2030Map map = Goals2050Agenda2030Map.getInstance();
        long[] result = map.getAgenda2030Categories("");
        assertThat(result.length).isEqualTo(0);
        result = map.getAgenda2030Categories(null);
        assertThat(result.length).isEqualTo(0);
        result = map.getAgenda2030Categories("Yhdenvertaisuus");
        assertThat(result.length).isEqualTo(9);
    }
}
