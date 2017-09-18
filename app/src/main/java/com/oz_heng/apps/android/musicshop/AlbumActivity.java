package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.ALBUM_NUMBER_ARG;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.albumArrayList;

public class AlbumActivity extends AppCompatActivity {

    private int mAlbumNumner = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        // Get the Album number passed through the Intent.
        Intent intent = getIntent();
        mAlbumNumner = intent.getIntExtra(ALBUM_NUMBER_ARG, mAlbumNumner);
        Album album = albumArrayList.get(mAlbumNumner);

        // set the Album number in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.album) + " " + (mAlbumNumner + 1));
        }

        // Set SongAdapter to the ListView.
        ListView songListView = (ListView) findViewById(R.id.album_list_view);
        SongAdapter songAdapter = new SongAdapter(this, album.getSongs());
        songListView.setAdapter(songAdapter);

        songListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AlbumActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).
                        show();
            }
        });
    }
}
