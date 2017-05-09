package com.example.rajrajas.miniproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rajrajas on 5/1/2017.
 */

public class MyItemReader {

    /*
     * This matches only once in whole input,
     * so Scanner.next returns whole InputStream as a String.
     * http://stackoverflow.com/a/5445161/2183804
     */
    private static final String REGEX_INPUT_BOUNDARY_BEGINNING = "\\A";

    public List<MyItem> read(InputStream inputStream) throws JSONException
    {
        List<MyItem> items = new ArrayList<MyItem>();
        String json = new Scanner(inputStream).useDelimiter(REGEX_INPUT_BOUNDARY_BEGINNING).next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);

            String ip_address = object.getString("ip_Address");
            String time_stamp = object.getString("time_stamp");

            items.add(new MyItem(ip_address, time_stamp));
        }
        return items;
    }

}

