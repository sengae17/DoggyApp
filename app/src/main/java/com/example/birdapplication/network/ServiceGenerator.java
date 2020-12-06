package com.example.birdapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//returns an object for making API calls
public class ServiceGenerator {
    private static String baseUrl = "https://api.thedogapi.com/v1/";
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();
    private static DogsApi dogsApi = retrofit.create(DogsApi.class);
    public static DogsApi getDogsApi() {
        return dogsApi;
    }
}
