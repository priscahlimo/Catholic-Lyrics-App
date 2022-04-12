package com.priscah.catholicsongs.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.priscah.catholicsongs.R;
import com.priscah.catholicsongs.constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.button) Button mFindSongsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mFindSongsButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v== mFindSongsButton){
        Intent intent = new Intent(MainActivity.this, SongsList.class);
        startActivity(intent);}


    }

}

