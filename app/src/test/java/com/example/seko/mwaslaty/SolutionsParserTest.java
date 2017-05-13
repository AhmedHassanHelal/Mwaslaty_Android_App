package com.example.seko.mwaslaty;

import com.example.seko.mwaslaty.android.model.Checkpoint;
import com.example.seko.mwaslaty.android.model.Solution;
import com.example.seko.mwaslaty.parsers.SolutionsParser;

import org.junit.Test;

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
        Checkpoint[] checkpoints = new Checkpoint[3];
        checkpoints[0] = new Checkpoint();
        checkpoints[0].setStationID("13");
        checkpoints[0].setBusName("321");
        checkpoints[1] = new Checkpoint();
        checkpoints[1].setStationID("15");
        checkpoints[1].setBusName("111");
        checkpoints[2] = new Checkpoint();
        checkpoints[2].setStationID("14");
        checkpoints[2].setBusName("213");
        expectedSolution.setChekpoint(checkpoints);
        expectedSolution.setCost("6");
        assertEquals(expectedSolution.getCost(), resultSolution.getCost());
        for (int i = 0; i < expectedSolution.getChekpoint().size(); i++) {
            assertEquals(expectedSolution.getChekpoint().get(i).getBusName(), resultSolution.getChekpoint().get(i).getBusName());
            assertEquals(expectedSolution.getChekpoint().get(i).getStationID(), resultSolution.getChekpoint().get(i).getStationID());
        }
    }

    @Test(expected = NullPointerException.class)
    public void parsing_wrongResponse() {
        SolutionsParser availableStationsParser = new SolutionsParser();
        String sampleJsonRespose = "Any garbage writing";
        availableStationsParser.getSolution(sampleJsonRespose).getCost();
    }
}
