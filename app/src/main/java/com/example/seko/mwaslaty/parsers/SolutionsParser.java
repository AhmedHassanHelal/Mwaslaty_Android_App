package com.example.seko.mwaslaty.parsers;

import com.example.seko.mwaslaty.android.model.Solution;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by Helal on 10/05/2017.
 */
public class SolutionsParser {
    public Solution getSolution(String mJsonResponse) {
        Solution ret = null;
        Gson gson = new GsonBuilder().create();
        try {
            ret = gson.fromJson(mJsonResponse, Solution.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
