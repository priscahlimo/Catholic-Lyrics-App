package com.priscah.catholicsongs.network;

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
