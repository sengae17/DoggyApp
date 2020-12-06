package com.example.birdapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.birdapplication.data.model.Dog;
import com.example.birdapplication.network.responses.ImageUrlResponse;
import com.example.birdapplication.data.DogRepository;

import java.util.List;

import retrofit2.Callback;

public class DogViewModel extends AndroidViewModel {
    private DogRepository repository;

    public DogViewModel(@NonNull Application application) {
        super(application);
        repository = new DogRepository(this.getApplication());
    }

    public LiveData<List<Dog>> getDogs() {
        return repository.getDogs();
    }

    public void getDogImageWithCallback(int breedId, Callback<List<ImageUrlResponse>> callback) {
        repository.getDogImage(breedId, callback);
    }
}
