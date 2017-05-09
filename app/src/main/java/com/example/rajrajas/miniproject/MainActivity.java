package com.example.rajrajas.miniproject;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



import org.json.JSONException;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

import java.io.IOException;
import java.io.InputStream;

import java.net.InetAddress;
import java.util.List;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;


/**
 * Created by rajrajas on 5/1/2017.
 */

public class MainActivity extends BaseDemoActivity {
    //private ClusterManager<MyItem> mClusterManager;

    Location location;
    City city;
    @Override
    protected void startDemo() {
        getMap().moveCamera(newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        //mClusterManager = new ClusterManager<MyItem>(this, getMap());

        //getMap().setOnCameraIdleListener(mClusterManager);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                add_map_markers();
            }
        } catch (IOException e)
        {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
        catch (JSONException e)
        {
            Toast.makeText(this, "JSON", Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void add_map_markers() throws IOException,JSONException
    {
       // InputStream file_in=getResources().openRawResource(R.raw.GeoLiteCity);

        //File file =new File("src/main/res/raw/geolitecity.dat");


        try (WebServiceClient client = new WebServiceClient.Builder(122413, "gESoYum6GqrD")
                .build())
        {

            CountryResponse response = client.country();
            //InetAddress ipAddress = InetAddress.getByName("128.101.101.101");

            // Do the lookup
            //CityResponse response = client.city(ipAddress);


            Toast.makeText(getApplicationContext(),response.getCountry().toString(),Toast.LENGTH_LONG).show();

           // Country country = response.getCountry();
         /*   System.out.println(country.getIsoCode());            // 'US'
            System.out.println(country.getName());               // 'United States'
            System.out.println(country.getNames().get("zh-CN")); // '美国'

            Subdivision subdivision = response.getMostSpecificSubdivision();
            System.out.println(subdivision.getName());       // 'Minnesota'
            System.out.println(subdivision.getIsoCode());    // 'MN'
*/
             //city = response.getCity();


            //Postal postal = response.getPostal();


            // location = response.getLocation();
        } catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);


        List<MyItem> items = new MyItemReader().read(inputStream);

        for (MyItem item : items)
        {

            //MarkerOptions marker = new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())).title(city.getName());

            //getMap().addMarker(marker);
        }

    }

}