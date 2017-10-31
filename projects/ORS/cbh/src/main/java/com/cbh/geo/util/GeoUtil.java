package com.cbh.geo.util;

import com.cbh.mongo.location.GeoLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Tommy on 2017/8/9.
 */
public class GeoUtil {
    public GeoLocation googleLocation(String address) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/geocode/json?address="+ URLEncoder.encode(address,"UTF8")+"&key=AIzaSyCF0oh0dPvdTbgCs-Nrlc0J8cKqCC72cA4")
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0")
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .build();

        Response response = client.newCall(request).execute();
        ObjectMapper objectMapper=new ObjectMapper();
        GeoLocation geoLocation=objectMapper.readValue(response.body().string(),GeoLocation.class);
        return geoLocation;
    }
}
