package com.example.birdapplication.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.birdapplication.data.model.Dog;
import com.example.birdapplication.network.responses.BreedInformationResponse;
import com.example.birdapplication.network.responses.ImageUrlResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogNetworkDataSource {
    private DogsApi dogsApi;
    private MutableLiveData<List<Dog>> _downloadedDogs;
    public LiveData<List<Dog>> downloadedDogs;

    public DogNetworkDataSource() {
        dogsApi = ServiceGenerator.getDogsApi();
        _downloadedDogs = new MutableLiveData<>();
        downloadedDogs = getDownloadedDogs();
    }

    private MutableLiveData<List<Dog>> getDownloadedDogs() {
        return _downloadedDogs;
    }

    public void fetchAllDogs() {
        Call<List<BreedInformationResponse>> call = dogsApi.getDogs();
        call.enqueue(new Callback<List<BreedInformationResponse>>() {
            @Override
            public void onResponse(Call<List<BreedInformationResponse>> call, Response<List<BreedInformationResponse>> response) {
                if (!response.isSuccessful()) {
                    Log.e("DogNetworkDatasource", "An error occurred while fetching dogs from the API.");
                    Log.e("DogNetworkDatasource", "Code: " + response.code());
                }

                List<BreedInformationResponse> breeds = response.body();
                List<Dog> newDogs = new ArrayList<>();

                for (BreedInformationResponse breed : breeds) {
                    newDogs.add(new Dog(
                            breed.getId(),
                            breed.getName(),
                            breed.getBredFor(),
                            breed.getImageUrl(),
                            breed.getOrigin() == null ? "unknown" : breed.getOrigin(),
                            breed.getWeight(),
                            breed.getHeight(),
                            breed.getTemperament()
                    ));
                }

                Log.d("DogNetworkDatasource", "Fetched dogs from the API. Length: " + newDogs.size());
                _downloadedDogs.postValue(newDogs);
            }

            @Override
            public void onFailure(Call<List<BreedInformationResponse>> call, Throwable t) {
                Log.e("DogNetworkDatasource", "An error occurred while fetching dogs from the API.");
                Log.e("DogNetworkDatasource", "Error: " + t.getMessage());
            }
        });
    }

    public void fetchDogImage(int breedId, Callback<List<ImageUrlResponse>> responseCallback) {
        Call<List<ImageUrlResponse>> call = dogsApi.getDog(breedId);
        call.enqueue(responseCallback);
    }
}
