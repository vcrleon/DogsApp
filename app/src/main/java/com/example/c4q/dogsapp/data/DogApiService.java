package com.example.c4q.dogsapp.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by c4q on 6/6/18.
 */

public interface DogApiService {

    public static final String BASE_URL = "https://dog.ceo/api/";

    @GET("breed/{name}/images")
    Call<DogApiResponse> getDogImages(@Path("name") String breed);

}
