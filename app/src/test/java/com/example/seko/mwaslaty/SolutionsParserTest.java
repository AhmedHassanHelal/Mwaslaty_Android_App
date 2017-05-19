package com.example.seko.mwaslaty;

import com.example.seko.mwaslaty.android.model.Checkpoint;
import com.example.seko.mwaslaty.android.model.Solution;
import com.example.seko.mwaslaty.parsers.SolutionsParser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Helal on 10/05/2017.
 */
public class SolutionsParserTest {
    @Test
    public void parsing_isCorrect() {
        SolutionsParser solutionsParser = new SolutionsParser();
        String sampleJsonRespose = "{\n" +
                "\"Checkpoint\":\n" +
                "[\n" +
                "\t{\n" +
                "\t\t\"StationID\":\"13\",\n" +
                "\t\t\"BusName\":\"321\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"StationID\":\"15\",\n" +
                "\t\t\"BusName\": \"111\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"StationID\":\"14\",\n" +
                "\t\t\"BusName\":\"213\"\n" +
                "\t}\n" +
                "],\n" +
                "\"Cost\" :\"6\"\n" +
                "}";
        Solution resultSolution = solutionsParser.getSolution(sampleJsonRespose);
        Solution expectedSolution = new Solution();
        List<Checkpoint> checkpoints = new ArrayList<Checkpoint>();
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setStationID("13");
        checkpoint.setBusID("321");
        checkpoint = new Checkpoint();
        checkpoint.setStationID("15");
        checkpoint.setBusID("111");
        checkpoint = new Checkpoint();
        checkpoint.setStationID("14");
        checkpoint.setBusID("213");
        expectedSolution.setCheckpoints(checkpoints);
        expectedSolution.setCost("6");
        assertEquals(expectedSolution.getCost(), resultSolution.getCost());
        for (int i = 0; i < expectedSolution.getCheckpoints().size(); i++) {
            assertEquals(expectedSolution.getCheckpoints().get(i).getBusID(), resultSolution.getCheckpoints().get(i).getBusID());
            assertEquals(expectedSolution.getCheckpoints().get(i).getStationID(), resultSolution.getCheckpoints().get(i).getStationID());
        }
    }

    @Test(expected = NullPointerException.class)
    public void parsing_wrongResponse() {
        SolutionsParser availableStationsParser = new SolutionsParser();
        String sampleJsonRespose = "Any garbage writing";
        availableStationsParser.getSolution(sampleJsonRespose).getCost();
    }
}
