package com.priscah.catholicsongs.network;

import static com.priscah.catholicsongs.constants.YELP_BASE_URL;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SongsClient {
    private static Retrofit retrofit = null;

    public static final String BASE_URL = "https://run.mocky.io/v3/39fe3289-3730-4b1a-a052-bd8bca19216f/";
    public static SongsAPI getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(SongsAPI.class);
    }
}
