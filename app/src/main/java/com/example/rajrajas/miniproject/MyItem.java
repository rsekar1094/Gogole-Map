package com.example.rajrajas.miniproject;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by rajrajas on 5/1/2017.
 */

public class MyItem
{

    private String ip_address,time_Stamp;

     MyItem(String ip_address, String  time_Stamp)
    {
       this.ip_address=ip_address;
        this.time_Stamp=time_Stamp;
    }


    public String get_ip_address()
    {
        return ip_address;
    }
    public String get_timestamp()
    {
        return time_Stamp;
    }
}
