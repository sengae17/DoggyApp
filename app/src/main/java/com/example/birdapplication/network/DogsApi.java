package com.example.birdapplication.network;

import com.example.birdapplication.network.responses.BreedInformationResponse;
import com.example.birdapplication.network.responses.ImageUrlResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DogsApi {
    //searches for breads
    @Headers({"x-api-key: e003e6a6-e326-48e4-9ce4-9ba6c9458fa1"})
    @GET("breeds")
    Call<List<BreedInformationResponse>> getDogs();

    //searches for pict
    @Headers({"x-api-key: e003e6a6-e326-48e4-9ce4-9ba6c9458fa1"})
    @GET("images/search")
    Call<List<ImageUrlResponse>> getDog(@Query("breed_id") int breedId);
}
