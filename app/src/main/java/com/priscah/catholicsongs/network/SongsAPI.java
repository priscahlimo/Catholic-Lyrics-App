package com.priscah.catholicsongs.network;

import com.priscah.catholicsongs.models.Songs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongsAPI {
    @GET("songs")
    Call<List<Songs>> getSong(
            @Query("location") String location,
            @Query("term") String term
    );
}
