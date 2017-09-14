package com.oz_heng.apps.android.musicshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * {@link CatalogueActivity} displays a list of albums.
 */
public class CatalogueActivity extends AppCompatActivity {

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

        AlbumAdaper albumAdaper = new AlbumAdaper(this, albumArrayList);

        GridView albumListView = (GridView) findViewById(R.id.catalogue_gridview);
        albumListView.setAdapter(albumAdaper);
    }
}
