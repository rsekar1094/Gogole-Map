package com.example.rajrajas.miniproject;

/**
 * Created by rajrajas on 5/6/2017.
 */

public class city_loc_item
{
    private double latitude,longitude;
    private String state_name;
    public city_loc_item(double latitude,double longitude,String state_name)
    {
        this.latitude=latitude;
        this.longitude=longitude;
        this.state_name=state_name;
    }

    double getLatitude()
    {
        return latitude;
    }
    double getLongitude()
    {
        return longitude;
    }

    String getState_name()
    {
        return state_name;
    }

}
