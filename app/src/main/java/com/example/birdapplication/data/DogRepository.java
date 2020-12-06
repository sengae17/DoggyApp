package com.example.birdapplication.data;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.birdapplication.data.database.DogDatabase;
import com.example.birdapplication.data.model.Dog;
import com.example.birdapplication.data.model.DogDao;
import com.example.birdapplication.network.DogNetworkDataSource;
import com.example.birdapplication.network.responses.ImageUrlResponse;

import java.util.List;

import retrofit2.Callback;

public class DogRepository {
    private DogDao dogDao;
    private DogNetworkDataSource dataSource;

    public DogRepository(Application application) {
        DogDatabase database = DogDatabase.getInstance(application);
        dogDao = database.dogDao();
        dataSource = new DogNetworkDataSource();

        dataSource.downloadedDogs.observeForever(dogs -> persistFetchedDogs(dogs));
    }

    //it will look if we have already list of dogs in the database
    //if not, it will download them from the API, and save them into database
    //and then return them from the database
    public LiveData<List<Dog>> getDogs() {
        new InitializeDogsDataAsyncTask(dogDao, dataSource).execute();
        return dogDao.getAllDogs();
    }

    public void getDogImage(int breedId, Callback<List<ImageUrlResponse>> responseCallback) {
        new GetDogImageAsyncTask(breedId, dataSource, responseCallback).execute();
    }

    private void persistFetchedDogs(List<Dog> fetchedDogs) {
        new InsertDogsAsyncTask(dogDao).execute(fetchedDogs);
    }

    private static class GetDogImageAsyncTask extends AsyncTask<Void, Void, Void> {
        private int breedId;
        private DogNetworkDataSource dataSource;
        private Callback<List<ImageUrlResponse>> responseCallback;

        private GetDogImageAsyncTask(int breedId, DogNetworkDataSource dataSource, Callback<List<ImageUrlResponse>> responseCallback ) {
            this.breedId = breedId;
            this.dataSource = dataSource;
            this.responseCallback = responseCallback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("DogRepository", "Executing GetDogImageAsyncTask");
            dataSource.fetchDogImage(breedId, responseCallback);
            return null;
        }
    }

    //A helper async class for initializing dog data, either from database or API.
    private static class InitializeDogsDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private DogDao dogDao;
        private DogNetworkDataSource dataSource;

        private InitializeDogsDataAsyncTask(DogDao dogDao, DogNetworkDataSource dataSource) {
            this.dogDao = dogDao;
            this.dataSource = dataSource;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (dogDao.getAllDogsNonLive() == null || dogDao.getAllDogsNonLive().isEmpty()) {
                Log.d("DogRepository", "Fetch is needed. Fetching data from the API.");
                dataSource.fetchAllDogs();
            } else {
                Log.d("DogRepository", "Fetch not needed. Getting data from the database.");
            }

            return null;
        }
    }


     //A helper async class for inserting dogs into the local database
    private static class InsertDogsAsyncTask extends AsyncTask<List<Dog>, Void, Void> {
        private DogDao dogDao;

        private InsertDogsAsyncTask(DogDao dogDao) {
            this.dogDao = dogDao;
        }

        @Override
        protected Void doInBackground(List<Dog>... lists) {
            dogDao.insert(lists[0]);
            return null;
        }
    }


     //A helper async function for updating a dog in the local database
    private static class UpdateDogAsyncTask extends AsyncTask<Dog, Void, Void> {
        private DogDao dogDao;

        private UpdateDogAsyncTask(DogDao dogDao) {
            this.dogDao = dogDao;
        }

        @Override
        protected Void doInBackground(Dog... dogs) {
            dogDao.updateImage(dogs[0].getId(), dogs[0].getPictureUrl());
            return null;
        }
    }
}
