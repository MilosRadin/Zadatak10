package com.ftninformatika.zadatak10.net.webservices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTService {

    public static final String BASE_URL="https//www.omdbapi.com";
    public static final String API_KEY="fad8d90";

    public static Retrofit getRetrofitInstance(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static OMDBApiEndPointInterface.OMDBApiEndpointInterface apiInterface(){
        OMDBApiEndPointInterface.OMDBApiEndpointInterface apiService = getRetrofitInstance().create(OMDBApiEndPointInterface.OMDBApiEndpointInterface.class);

        return apiService;
    }
}
