package com.priscah.catholicsongs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.priscah.catholicsongs.R;
import com.priscah.catholicsongs.models.Songs;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class songsListAdapter  extends RecyclerView.Adapter<songsListAdapter.SongsViewHolder>{
    private List<Songs> mSongs;
    private Context mContext;

    public songsListAdapter(List<Songs> sales, Context context){
        mContext = context;
        mSongs = sales;
    }

    @Override
    public songsListAdapter.SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_lyrics, parent, false);
        SongsViewHolder viewHolder = new SongsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(songsListAdapter.SongsViewHolder holder, int position) {
       holder.bindSongs(mSongs.get(position));
    }
    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    //Below is an inner/nested class that will extend the recyclerview.viewHolder class
    public class SongsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.songTitle)
        TextView mSongTextView;
        @BindView(R.id.songsChoir)
        TextView mChoirTextView;
        @BindView(R.id.songsAlbum) TextView mAlbumTextView;
        @BindView(R.id.songsLyrics) TextView mLyricsTextView;

        private Context mContext;

        public SongsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        //we are binding to our recycler views
        public void bindSongs(Songs songs) {
            mSongTextView.setText(songs.getsong());
            mChoirTextView.setText(songs.getChoir());
            mAlbumTextView.setText(songs.getAlbum());
            mLyricsTextView.setText(songs.getLyrics());

        }
    }
}
