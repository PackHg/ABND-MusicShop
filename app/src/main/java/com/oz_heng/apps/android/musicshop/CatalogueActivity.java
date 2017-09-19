package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
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

    // Albums' dummy data
    static final ArrayList<Album> albumArrayList = new ArrayList<>(
            Arrays.asList(
                    new Album("Album 1", new ArrayList<>(Arrays.asList(new Song("Song 1-01"),
                            new Song("Song 1-02"), new Song("Song 1-03"), new Song("Song 1-04"),
                            new Song("Song 1-05"), new Song("Song 1-06"), new Song("Song 1-07"),
                            new Song("Song 1-08"), new Song("Song 1-09"), new Song("Song 1-10")))),
                    new Album("Album 2", new ArrayList<>(Arrays.asList(new Song("Song 2-01"),
                            new Song("Song 2-02"), new Song("Song 2-03"), new Song("Song 2-05")))),
                    new Album("Album 3", new ArrayList<>(Arrays.asList(new Song("Song 3-01"),
                            new Song("Song 3-02"), new Song("Song 3-03"), new Song("Song 3-04"),
                            new Song("Song 3-05"), new Song("Song 3-06"), new Song("Song 3-07"),
                            new Song("Song 3-08"), new Song("Song 3-09"), new Song("Song 3-10"),
                            new Song("Song 3-11"), new Song("Song 3-12"), new Song("Song 3-13"),
                            new Song("Song 3-14")))),
                    new Album("Album 4", new ArrayList<>(Arrays.asList(new Song("Song 4-01"),
                            new Song("Song 4-02"), new Song("Song 4-03"), new Song("Song 4-04"),
                            new Song("Song 4-05"), new Song("Song 4-06"), new Song("Song 4-07"),
                            new Song("Song 4-08"), new Song("Song 4-09"), new Song("Song 4-10")))),
                    new Album("Album 5", new ArrayList<>(Arrays.asList(new Song("Song 5-01"),
                            new Song("Song 5-02"), new Song("Song 5-03")))),
                    new Album("Album 6", new ArrayList<>(Arrays.asList(new Song("Song 6-01"),
                            new Song("Song 6-02"), new Song("Song 6-03"), new Song("Song 6-04"),
                            new Song("Song 6-05"), new Song("Song 6-06"), new Song("Song 6-07"),
                            new Song("Song 6-08"), new Song("Song 6-09"), new Song("Song 6-10")))),
                    new Album("Album 7", new ArrayList<>(Arrays.asList(new Song("Song 7-01"),
                            new Song("Song 7-02"), new Song("Song 7-03"), new Song("Song 7-04"),
                            new Song("Song 7-05"), new Song("Song 7-06"), new Song("Song 7-07"),
                            new Song("Song 7-08"), new Song("Song 7-09"), new Song("Song 7-10"),
                            new Song("Song 7-11"), new Song("Song 7-12"), new Song("Song 7-13"),
                            new Song("Song 7-14"), new Song("Song 7-15"), new Song("Song 7-16"),
                            new Song("Song 7-17"), new Song("Song 7-18"), new Song("Song 7-19"),
                            new Song("Song 7-20")))),
                    new Album("Album 8", new ArrayList<>(Arrays.asList(new Song("Song 8-01"),
                            new Song("Song 8-02")))),
                    new Album("Album 9", new ArrayList<>(Arrays.asList(new Song("Song 9-01"),
                            new Song("Song 9-02"), new Song("Song 9-03"), new Song("Song 9-04"),
                            new Song("Song 9-05"), new Song("Song 9-06"), new Song("Song 9-07"),
                            new Song("Song 9-08"), new Song("Song 9-09"), new Song("Song 9-10")))),
                    new Album("Album 10", new ArrayList<>(Arrays.asList(new Song("Song 10-01"),
                            new Song("Song 10-02"), new Song("Song 10-03"), new Song("Song 10-04"))))
            ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);

        // set the title in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.catalogue));
        }

        // Set AlbumAdapter to the GridView
        GridView albumGridView = (GridView) findViewById(R.id.catalogue_gridview);
        AlbumAdaper albumAdaper = new AlbumAdaper(this, albumArrayList);
        albumGridView.setAdapter(albumAdaper);

        // Associate albumGridView with a contextual menu.
        registerForContextMenu(albumGridView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_album_item, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        int albumNumber = (int) info.id;

        switch (item.getItemId()) {
            case R.id.album_item_cmenu_option_go_to_album:
                Intent intent = new Intent(CatalogueActivity.this, AlbumActivity.class);
                intent.putExtra(ALBUM_NUMBER_ARG, albumNumber);
                startActivity(intent);
                return true;
            case R.id.album_item_cmenu_option_buy_album:
                Toast.makeText(this, "Album " + (albumNumber +1) + " - Buy album", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.album_item_cmenu_option_add_to_wishlist:
                Toast.makeText(this, "Album " + (albumNumber +1) + " - Add to wishlist", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
