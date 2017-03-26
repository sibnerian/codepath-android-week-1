package com.example.ian_sibner.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    private static final String posterPathPrefix = "https://image.tmdb.org/t/p/w342";
    private static final String backdropPathPrefix = "https://image.tmdb.org/t/p/w780";

    public String getPosterPath() {
        return posterPathPrefix + posterPath;
    }

    public String getBackdropPath() {
        return backdropPathPrefix + backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    String backdropPath;
    String posterPath;
    String originalTitle;
    String overview;

    public Movie(JSONObject movieObject) throws JSONException {
        this.backdropPath = movieObject.getString("backdrop_path");
        this.posterPath = movieObject.getString("poster_path");
        this.originalTitle = movieObject.getString("original_title");
        this.overview = movieObject.getString("overview");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray arr) throws JSONException {
        ArrayList<Movie> results = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            results.add(new Movie(arr.getJSONObject(i)));
        }
        return results;
    }
}
