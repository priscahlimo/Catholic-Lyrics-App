package com.priscah.catholicsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    private Button mFindSongsButton;
    @BindView(R.id.button) Button mFindSongsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mFindSongsButton = (Button) findViewById(R.id.button);
        mFindSongsButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v== mFindSongsButton){
        Intent intent = new Intent(MainActivity.this, SongsList.class);
        startActivity(intent);}
    }

}

