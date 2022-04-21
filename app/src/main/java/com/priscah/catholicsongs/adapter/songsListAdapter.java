package com.priscah.catholicsongs.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.priscah.catholicsongs.R;
import com.priscah.catholicsongs.models.Songs;
import com.priscah.catholicsongs.util.ItemTouchHelperAdapter;
import com.priscah.catholicsongs.util.ItemTouchHelperViewHolder;
import com.priscah.catholicsongs.util.OnStartDragListener;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class songsListAdapter  extends RecyclerView.Adapter<songsListAdapter.SongsViewHolder> implements ItemTouchHelperAdapter {
    private final List<Songs> mSongs;
    private final Context mContext;
    private final OnStartDragListener  mStartDragListener;

    public songsListAdapter(List<Songs> mSongs, Context mContext, OnStartDragListener startDragListener) {
        this.mSongs = mSongs;
        this.mContext = mContext;
        this.mStartDragListener = startDragListener;
    }

    @Override
    public songsListAdapter.SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item_drag, parent, false);
        SongsViewHolder viewHolder = new SongsViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(songsListAdapter.SongsViewHolder holder, int position) {
       holder.bindSongs(mSongs.get(position));
        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    @Override
    public void onItemDismiss(int position) {
        mSongs.remove(position);
        notifyItemRemoved(position);
    }



    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mSongs, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mSongs, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }


    //Below is an inner/nested class that will extend the recyclerview.viewHolder class
    public static class SongsViewHolder extends RecyclerView.ViewHolder  implements ItemTouchHelperViewHolder {

        @BindView(R.id.songTitle)

        TextView mSongTextView;
        @BindView(R.id.songsChoir)
        TextView mChoirTextView;
        @BindView(R.id.songsAlbum) TextView mAlbumTextView;
        @BindView(R.id.songsLyrics)
        TextView mLyricsTextView;

        @BindView(R.id.dragIcon)
        ImageView handleView;

        private final Context mContext;


        public SongsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        //we are binding to our recycler views
        public void bindSongs(Songs songs) {
            handleView = (ImageView) itemView.findViewById(R.id.dragIcon);
            mSongTextView.setText( "Song: "+ " " + songs.getsong());
            mChoirTextView.setText("Choir: "+ " " + songs.getChoir());
            mAlbumTextView.setText("Album: "+ " " +songs.getAlbum());
            mLyricsTextView.setText(songs.getLyrics());


        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
