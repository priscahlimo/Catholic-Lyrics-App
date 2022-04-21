package com.priscah.catholicsongs.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.priscah.catholicsongs.R;
import com.priscah.catholicsongs.SongsArrayAdapter;
import com.priscah.catholicsongs.adapter.songsListAdapter;
import com.priscah.catholicsongs.constants;
import com.priscah.catholicsongs.models.Songs;
import com.priscah.catholicsongs.network.SongsAPI;
import com.priscah.catholicsongs.network.SongsClient;
import com.priscah.catholicsongs.util.ItemTouchHelperAdapter;
import com.priscah.catholicsongs.util.OnStartDragListener;
import com.priscah.catholicsongs.util.SimpleItemTouchHelperCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsList extends AppCompatActivity implements OnStartDragListener{

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;


    private songsListAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;


    List<Songs> songs;
    RecyclerView recyclerView;
   OnStartDragListener  startDragListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);
        ButterKnife.bind(this);


        SongsAPI client = SongsClient.getClient();

        Call<List<Songs>> call = client.getSong();

        call.enqueue(new Callback<List<Songs>>() {
            @Override
            public void onResponse(Call<List<Songs>> call, Response<List<Songs>> response) {
                if(response.isSuccessful()){
                    songs = response.body();
                    mAdapter = new songsListAdapter(songs,SongsList.this,startDragListener);

                    ItemTouchHelper.Callback callback =
                            new SimpleItemTouchHelperCallback(mAdapter);
                    ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
                    touchHelper.attachToRecyclerView(recyclerView);

                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SongsList.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);


                    showSongs();
                    hideProgressBar();
                }else{

                    showUnsuccessfulMessage();

                }


            }

            @Override
            public void onFailure(Call<List<Songs>> call, Throwable t) {

                showFailureMessage();
            }
        });

    }


//    search widget
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);


        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String song) {

                SongsAPI client = SongsClient.getClient();
                Call<List<Songs>> call = client.getSong();
                call.enqueue(new Callback<List<Songs>>() {
                    @Override
                    public void onResponse(Call<List<Songs>> call, Response<List<Songs>> response) {
                        if(response.isSuccessful()){
                            songs = response.body();
                            mAdapter = new songsListAdapter(songs,SongsList.this,startDragListener);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SongsList.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                            showSongs();
                            hideProgressBar();
                        }else{

                            showUnsuccessfulMessage();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Songs>> call, Throwable t) {

                        showFailureMessage();
                    }

                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String location) {
                return false;
            }
        });
        return true;
    }

//end of search functionality

    private void showSongs() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    //on start drag method implementation
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}