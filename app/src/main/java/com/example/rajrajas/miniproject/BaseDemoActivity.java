package com.example.rajrajas.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;

/**
 * Created by rajrajas on 5/1/2017.
 */

public abstract class BaseDemoActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<city_loc_item> items;
    private int i_temp = 0;
    private city_loc_item temp_item;
    private Timer timer;

    private boolean play_bool = true;
    private FloatingActionButton fab;
    SeekBar seekBar;

    protected int getLayoutId() {
        return R.layout.map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("Map");

        setUpMap();

        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekBar.setVisibility(View.GONE);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.city_json);
            items = new city_json_reader().read(inputStream);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        seekBar.setMax(items.size()-1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play_bool) {
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                    play_function();
                    play_bool = false;
                } else {
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    timer.cancel();
                    play_bool = true;
                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser)
            {
                progress = progresValue;
                seek_function(progresValue);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                seek_function(progress);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        startDemo();
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    /**
     * Run the demo-specific code.
     */
    protected abstract void startDemo();

    protected GoogleMap getMap() {
        return mMap;
    }

    private void play_function() {
        timer = new Timer();
        timer.schedule(new SayHello(), 0, 5000);

    }


    private class SayHello extends TimerTask {
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (i_temp < items.size()) {
                        temp_item = items.get(i_temp);
                        getMap().moveCamera(newLatLngZoom(new LatLng(temp_item.getLatitude(), temp_item.getLongitude()), 10));
                        i_temp = i_temp + 1;
                    } else {
                        timer.cancel();
                        fab.setImageResource(android.R.drawable.ic_media_play);
                    }
                }
            });

        }
    }

    private void seek_function(int temp_progress)
    {
        temp_item = items.get(temp_progress);
        getMap().moveCamera(newLatLngZoom(new LatLng(temp_item.getLatitude(), temp_item.getLongitude()), 10));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_play)
        {
            try {
                timer.cancel();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            fab.setVisibility(View.VISIBLE);
            seekBar.setVisibility(View.GONE);
            return true;
        }
        else if (id == R.id.action_seek)
        {
            try {
                timer.cancel();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            fab.setVisibility(View.GONE);
            seekBar.setVisibility(View.VISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

