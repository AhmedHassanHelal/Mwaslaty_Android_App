package com.example.seko.mwaslaty;

import com.example.seko.mwaslaty.android.model.Station;
import com.example.seko.mwaslaty.parsers.AvailableStationsParser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Helal on 07/05/2017.
 */
public class AvailableStationsParserUnitTest {
    @Test
    public void parsing_isCorrect() {
        AvailableStationsParser availableStationsParser = new AvailableStationsParser();
        String sampleJsonRespose = "[\n" +
                "  {\n" +
                "    \"ID\": \"1\",\n" +
                "    \"name\": \"10 رمضان\",\n" +
                "    \"longitude\": \"29.939939\",\n" +
                "    \"latitude\": \"31.20597\",\n" +
                "    \"metro\": \"0\",\n" +
                "    \"bus\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": \"271\",\n" +
                "    \"name\": \"حلوان\",\n" +
                "    \"longitude\": \"31.336853\",\n" +
                "    \"latitude\": \"29.848319\",\n" +
                "    \"metro\": \"1\",\n" +
                "    \"bus\": \"1\"\n" +
                "  }\n" +
                "]";
        List<Station> expected;
        expected = new ArrayList<Station>();
        Station station = new Station();
        station.setID(1);
        station.setName("10 رمضان");
        station.setLongitude(29.939939);
        station.setLatitude(31.20597);
        station.setMetro(0);
        station.setBus(1);
        expected.add(station);
        station = new Station();
        station.setID(271);
        station.setName("حلوان");
        station.setLongitude(31.336853);
        station.setLatitude(29.848319);
        station.setMetro(1);
        station.setBus(1);
        expected.add(station);
        List<Station> result = availableStationsParser.getAvailableStations(sampleJsonRespose);
        assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getID(), result.get(i).getID());
            assertEquals(expected.get(i).getName(), result.get(i).getName());
            assertEquals(expected.get(i).getLongitude(), result.get(i).getLongitude());
            assertEquals(expected.get(i).getLatitude(), result.get(i).getLatitude());
            assertEquals(expected.get(i).getMetro(), result.get(i).getMetro());
            assertEquals(expected.get(i).getBus(), result.get(i).getBus());
        }
    }

    @Test(expected = NullPointerException.class)
    public void parsing_wrongResponse() {
        AvailableStationsParser availableStationsParser = new AvailableStationsParser();
        String sampleJsonRespose = "[\n" +
                "  {\n" +
                "    \"ID\": \"1\",\n" +
                "    \"name\": \"10 رمضان\",\n" +
                "    \"longitude\": \"29.939939\",\n" +
                "    \"latitude\": \"31.20597\",\n" +
                "    \"metro\": \"0\",\n" +
                "    \"bus\": \"1\"\n" +
                "]";
        availableStationsParser.getAvailableStations(sampleJsonRespose).size();
    }
}
