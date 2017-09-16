package com.oz_heng.apps.android.musicshop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Pack Heng on 16/09/17
 * pack@oz-heng.com
 */
public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(@NonNull Context context, @NonNull ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View songView = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if (songView == null) {
            songView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_song_item, parent, false);
        }

        Song song = getItem(position);

        TextView titleTextView = songView.findViewById(R.id.song_title);
        if (song != null) {
            titleTextView.setText(song.getTitle());
        }

        return songView;
    }
}
