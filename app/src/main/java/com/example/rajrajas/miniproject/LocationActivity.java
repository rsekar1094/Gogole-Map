package com.example.rajrajas.miniproject;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Location;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;

/**
 * Created by rajrajas on 5/6/2017.
 */

public class LocationActivity extends BaseDemoActivity
{
    //private ClusterManager<MyItem> mClusterManager;

    Location location;
    City city;
    @Override
    protected void startDemo()
    {


        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            {
                add_map_markers();
            }
        } catch (IOException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        catch (JSONException e)
        {
            Toast.makeText(this, "JSON", Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void add_map_markers() throws IOException,JSONException
    {

        InputStream inputStream = getResources().openRawResource(R.raw.city_json);

        List<city_loc_item> items = new city_json_reader().read(inputStream);

        for (city_loc_item item : items)
        {

            MarkerOptions marker = new MarkerOptions().position(new LatLng(item.getLatitude(),item.getLongitude())).title(item.getState_name());

            getMap().addMarker(marker);
        }

        getMap().moveCamera(newLatLngZoom(new LatLng(items.get(0).getLatitude(), items.get(0).getLongitude()), 10));

    }

}