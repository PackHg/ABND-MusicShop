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
 * {@link AlbumAdaper} is an {@link ArrayAdapter} that can provide the layout for each
 * list based on a data source, which is a list of {@link Album} objects.
 */
public class AlbumAdaper extends ArrayAdapter<Album> {

    public AlbumAdaper(@NonNull Context context, @NonNull ArrayList<Album> albums) {
        super(context, 0, albums);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View albumView = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if (albumView == null) {
            albumView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_album_item, parent,false);
        }

        Album album = getItem(position);

        TextView titleTextView = (TextView)  albumView.findViewById(R.id.album_title);
        titleTextView.setText(album.getTitle());

        return albumView;
    }
}
