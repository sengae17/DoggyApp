package com.example.birdapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.birdapplication.data.model.Dog;
import com.example.birdapplication.network.responses.ImageUrlResponse;
import com.example.birdapplication.viewModels.DogViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogDetailsActivity extends AppCompatActivity {

    private static final String TAG = "DogDetailsActivity";
    protected DogViewModel mViewModel;
    private Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bird_detail);

        mViewModel = new ViewModelProvider(this).get(DogViewModel.class);

        //receving the dog object from intent
        Intent i = getIntent();
        this.dog = i.getParcelableExtra("dog");

        //assigning
        ProgressBar progressBar = findViewById(R.id.pb_loadingImage);
        ImageView iv_dogImage = findViewById(R.id.iv_dogPicture);
        TextView dogName = findViewById(R.id.tv_dogName);
        TextView dogBredFor = findViewById(R.id.tv_dogBredFor);
        TextView dogHeight = findViewById(R.id.tv_dogHeight);
        TextView dogWeight = findViewById(R.id.tv_dogWeight);
        TextView dogTemperament = findViewById(R.id.tv_dogTemperament);
        TextView dogOrigin = findViewById(R.id.tv_dogOrigin);

        //little spinning circle
        progressBar.setVisibility(View.VISIBLE);
        iv_dogImage.setVisibility(View.GONE);

        if (dog != null) {
            dogName.setText(dog.getName());
            dogBredFor.setText(dog.getBredFor());
            dogHeight.setText(dog.getHeight());
            dogWeight.setText(dog.getWeight());
            dogTemperament.setText(dog.getTemperament());
            dogOrigin.setText(dog.getOrigin());
            setDogImage(dog.getId());
        }
    }

    public void getNewImage(View view) {
        ProgressBar progressBar = findViewById(R.id.pb_loadingImage);
        ImageView iv_dogImage = findViewById(R.id.iv_dogPicture);
        progressBar.setVisibility(View.VISIBLE);
        iv_dogImage.setVisibility(View.GONE);

        setDogImage(dog.getId());
    }

    //load an image from URl
    private void setDogImage(int breedId) {
        Log.d("DogDetailsActivity", "Fetching an image for a dog " + breedId);
        mViewModel.getDogImageWithCallback(breedId, new Callback<List<ImageUrlResponse>>() {
            @Override
            public void onResponse(Call<List<ImageUrlResponse>> call, Response<List<ImageUrlResponse>> response) {
                if (!response.isSuccessful()) {
                    Log.e("DogDetailsActivity", "An error occurred while fetching a dog image from the API.");
                    Log.e("DogDetailsActivity", "Code: " + response.code());
                }

                String imageUrl = response.body().get(0).getImageUrl();

                ProgressBar progressBar = findViewById(R.id.pb_loadingImage);
                ImageView iv_dogImage = findViewById(R.id.iv_dogPicture);

                //helper code that loads the image from URL
                Glide.with(getApplication()).load(imageUrl).into(iv_dogImage);

                //progressbar is not visible anymore
                progressBar.setVisibility(View.GONE);
                iv_dogImage.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<List<ImageUrlResponse>> call, Throwable t) {
                Log.e("DogRepository", "An error occurred while fetching a dog image from the API.");
                Log.e("DogRepository", "Error: " + t.getMessage());
            }
        });
    }
}
