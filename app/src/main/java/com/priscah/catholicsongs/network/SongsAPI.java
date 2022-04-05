package com.priscah.catholicsongs.network;

import com.priscah.catholicsongs.models.Songs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongsAPI {
    @GET("songs/search")
    Call<Songs> getSong(
            @Query("song") String song,
            @Query("term") String term
    );
}
