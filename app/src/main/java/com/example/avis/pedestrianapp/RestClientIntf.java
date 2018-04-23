package com.example.avis.pedestrianapp;

import java.util.List;

public interface RestClientIntf {
    public List<EcoStation> getEcoStations(String url);
}