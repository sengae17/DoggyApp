package com.example.birdapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdapplication.data.model.Dog;
import com.example.birdapplication.viewModels.DogViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected List<Dog> mDogs;
    protected DogViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(DogViewModel.class);
        // Observes if there's any changes in the list of dogs. The code below will excetute if there's change.
        // It will recretate my recycleview with new data
        mViewModel.getDogs().observe(this, dogs -> {
            mDogs = dogs;
            mRecyclerView = findViewById(R.id.recyclerView);
            mAdapter = new CustomAdapter(getApplicationContext(), mDogs);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mAdapter);
            //Creates intent and goes into my dog detailed activity
            mAdapter.setOnItemClickListener(position -> {
                Intent intent = new Intent(getApplicationContext(), DogDetailsActivity.class);
                intent.putExtra("dog", mDogs.get(position));
                startActivity(intent);
            });
        });
    }
}
