package com.priscah.catholicsongs.util;

import android.support.annotation.NonNull;

import com.priscah.catholicsongs.adapter.songsListAdapter;
import com.priscah.catholicsongs.models.Songs;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
