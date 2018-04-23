package com.example.avis.pedestrianapp;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestClient implements RestClientIntf {

    @Override
    public List<EcoStation> getEcoStations(String url) {
        List<EcoStation> ecoStations = new ArrayList<>();

        try {
            URL endpoint = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
            connection.setRequestProperty("User-Agent", "getStations-rest-app-1");
            connection.setRequestProperty("Accept", "application/json");
            if(connection.getResponseCode() == 200) {
                InputStream responseBody = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(responseBody, "UTF-8");

                JsonReader jsonReader = new JsonReader(reader);
                jsonReader.beginObject();

                while(jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                    if(name.equals("ecostation")) {
                        jsonReader.beginArray();
                        while(jsonReader.hasNext()) {
                            ecoStations.add(translateStation(jsonReader));
                        }
                        jsonReader.endArray();
                    }
                }
                jsonReader.endObject();
            } else {
                String redirect = connection.getHeaderField("Location");
                connection.disconnect();
                System.out.println(redirect);
                getEcoStations(redirect);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ecoStations;
    }

    private EcoStation translateStation(JsonReader reader) throws IOException {
        EcoStation ecoStation = new EcoStation();

        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            if(name.equals("name")) {
                ecoStation.setName(reader.nextString());
            } else if(name.equals("id")) {
                ecoStation.setId(reader.nextInt());
            } else if (name.equals("direction_name")) {
                ecoStation.setDirectionName(reader.nextString());
            } else if (name.equals("type")) {
                ecoStation.setType(reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        //System.out.println(ecoStation.toString());
        return ecoStation;
    }
}
