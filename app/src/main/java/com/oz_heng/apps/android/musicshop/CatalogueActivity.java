package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * {@link CatalogueActivity} displays a list of albums.
 */
public class CatalogueActivity extends AppCompatActivity {

    // Key for passing the album number as argument through an Intent.
    static final String ALBUM_NUMBER_ARG = "album_number";

    // Albums' data
    static final ArrayList<Album> albumArrayList = new ArrayList<>(
            Arrays.asList(
                    new Album("Album 1"),
                    new Album("Album 2"),
                    new Album("Album 3"),
                    new Album("Album 4"),
                    new Album("Album 5"),
                    new Album("Album 6"),
                    new Album("Album 7"),
                    new Album("Album 8"),
                    new Album("Album 9"),
                    new Album("Album 10")
            )
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);

        GridView albumGridView = (GridView) findViewById(R.id.catalogue_gridview);

        // Set AlbumAdapter to the GridView
        AlbumAdaper albumAdaper = new AlbumAdaper(this, albumArrayList);
        albumGridView.setAdapter(albumAdaper);

        albumGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CatalogueActivity.this, "Album " + (i+1), Toast.LENGTH_SHORT)
                        .show();

                // Start the AlbumActivity with the album number.
                Intent intent = new Intent(CatalogueActivity.this, AlbumActivity.class);
                intent.putExtra(ALBUM_NUMBER_ARG, i);
                startActivity(intent);
            }
        });
    }
}
