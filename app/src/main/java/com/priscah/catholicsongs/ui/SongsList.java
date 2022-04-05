package com.priscah.catholicsongs.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.priscah.catholicsongs.R;
import com.priscah.catholicsongs.SongsArrayAdapter;

public class SongsList extends AppCompatActivity {
    private String[] songs = new String[] {"Mimina Neema", "Uninyunyizie Maji",
            "Nimeonja Pendo lako", "Sadaka Yangu", "Shukrani", "Simama Imara",
            "Iyelele", "Sala yangu Ipae", "Moyo wangu", "Lazaro"};

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);
        mListView = (ListView) findViewById(R.id.listSongs);

        SongsArrayAdapter adapter = new SongsArrayAdapter(this, android.R.layout.simple_list_item_1, songs);
        mListView.setAdapter(adapter);
    }

}