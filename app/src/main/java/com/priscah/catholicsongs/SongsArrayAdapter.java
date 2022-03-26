package com.priscah.catholicsongs;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class SongsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mSongs;

    public SongsArrayAdapter(Context mContext, int resource, String[] mSongs) {
        super(mContext,resource);
        this.mContext = mContext;
        this.mSongs = mSongs;
    }

    @Override
    public Object getItem(int position) {
        String songs = mSongs[position];
        return String.format(songs);
    }

    @Override
    public int getCount() {
        return mSongs.length;
    }
}
