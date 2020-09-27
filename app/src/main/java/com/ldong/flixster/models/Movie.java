package com.ldong.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath;
    String title;
    String overview;
    String backdropPath;

    //constructor to take in a json object, and react on the fields we care about
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    //why a static method here??
    public static List<Movie>  fromJSONArray(JSONArray movieJSONArray) throws JSONException {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < movieJSONArray.length(); i++) {
            movieList.add(new Movie(movieJSONArray.getJSONObject(i)));
        }
        return movieList;
    }

    public String getPosterPath() {
        //absolute path: %s here's what i want to replace with the item after ,
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

}
