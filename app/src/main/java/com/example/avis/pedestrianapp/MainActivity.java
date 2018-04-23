package com.example.avis.pedestrianapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.*;

import java.util.ArrayList;
import java.util.List;

@EActivity
public class MainActivity extends AppCompatActivity {

    RestClient client = new RestClient();
    List<EcoStation> data = new ArrayList<>();
    @ViewById
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getStations();
    }

    @Background
    public void getStations() {
        List<EcoStation> ecoStations = client.getEcoStations("https://www.oulunliikenne.fi/public_traffic_api/eco_traffic/eco_counters.php");
        if(ecoStations.size() > 0) {
            for(EcoStation station : ecoStations) {
                data.add(station);
            }
            updateUI();
        }
    }

    @UiThread
    void updateUI() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
