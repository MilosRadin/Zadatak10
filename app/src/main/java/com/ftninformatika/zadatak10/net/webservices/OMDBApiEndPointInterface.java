package com.ftninformatika.zadatak10.net.webservices;

import com.ftninformatika.zadatak10.model.Movie;
import com.ftninformatika.zadatak10.model.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public class OMDBApiEndPointInterface {


    public interface OMDBApiEndpointInterface {
        @GET("/")
        Call<Result> getMoviesByTitle(@QueryMap Map<String, String> options);

        @GET("/")
        Call<Movie> getMoviesDetails(@QueryMap Map<String, String> options);
    }
}
