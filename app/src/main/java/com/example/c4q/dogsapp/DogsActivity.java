package com.example.c4q.dogsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.c4q.dogsapp.data.DogApiResponse;
import com.example.c4q.dogsapp.data.DogApiService;
import com.example.c4q.dogsapp.presentation.DogsRVAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        String breed = "poodle";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DogApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DogApiService dogApiService = retrofit.create(DogApiService.class);
        Call<DogApiResponse> call = dogApiService.getDogImages(breed);
        call.enqueue(new Callback<DogApiResponse>() {
            @Override
            public void onResponse(Call<DogApiResponse> call, Response<DogApiResponse> response) {
               List<String> dogImageUrls = response.body().getMessage(); //a list of the urls
                DogsRVAdapter adapter = new DogsRVAdapter(dogImageUrls); //create a new adapter and make it a member variable if you need to refresh a list and then just set a new list
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<DogApiResponse> call, Throwable t) {
                Toast.makeText(DogsActivity.this, "uh oh", Toast.LENGTH_SHORT).show();

            }
        }); //enqueue is asynchronous (runs on the background thread -- execute(); is synchronous runs in the main thread
    }


}
