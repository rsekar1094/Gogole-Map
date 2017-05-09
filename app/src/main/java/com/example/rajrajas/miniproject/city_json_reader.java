package com.example.rajrajas.miniproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rajrajas on 5/6/2017.
 */

public class city_json_reader
{
    private static final String REGEX_INPUT_BOUNDARY_BEGINNING = "\\A";

    public List<city_loc_item> read(InputStream inputStream) throws JSONException
    {
        List<city_loc_item> items = new ArrayList<city_loc_item>();
        String json = new Scanner(inputStream).useDelimiter(REGEX_INPUT_BOUNDARY_BEGINNING).next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);

            double lat = Double.parseDouble(object.getString("latitude"));
            double lon = Double.parseDouble(object.getString("longitude"));
            String state_name=object.getString("state");

            items.add(new city_loc_item(lat, lon,state_name));
        }
        return items;
    }
}
