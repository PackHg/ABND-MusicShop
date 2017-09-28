package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static com.oz_heng.apps.android.musicshop.Utils.displayToastMessage;

/**
 * {@link CatalogueActivity} displays a list of albums.
 */
public class CatalogueActivity extends AppCompatActivity {

    private static final String LOG_TAG = CatalogueActivity.class.getSimpleName();

    // Key for passing the album number as argument through an Intent.
    static final String ALBUM_NUMBER_ARG = "album_number";

    // Albums' dummy data
    static final ArrayList<Album> catalogue = new ArrayList<>(
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

    // Wishlist of album numbers
    static ArrayList<Integer> wishlist = new ArrayList<>();

    // Keys used for saving and restoring wishlist into and from SharedPreferences
    final static String USER_DATA = "com.oz_heng.apps.android.musicshop.userData";
    static final String KEY_WISHLIST = "wishlist";
    static final String KEY_WISHLIST_SIZE= "wishlist_size";

    private Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);

        // set the title in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.catalogue));
        }

        // Restore wishlist data from SharedPreferences
        utils.restoreWishlist(this);

        Log.v(LOG_TAG, "onCreate() - wislist:" + wishlist.toString());

        // Set AlbumAdapter to the GridView
        GridView albumGridView = (GridView) findViewById(R.id.catalogue_gridview);
        AlbumAdaper albumAdaper = new AlbumAdaper(this, catalogue);
        albumGridView.setAdapter(albumAdaper);

        // Associate albumGridView with a contextual menu.
        registerForContextMenu(albumGridView);

        // Set "Wishlist" button to start WishlistActivity
        Button wishlistButton = (Button) findViewById(R.id.catalogue_goto_wishlist);
        wishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogueActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.catalogue_album_item_cmenu, menu);
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
                if (wishlist.contains(albumNumber)) {
                    displayToastMessage(this, getString(R.string.album_already_in_wishlist,
                            albumNumber + 1));
                } else {
                    wishlist.add(albumNumber);
                    displayToastMessage(this, getString(R.string.album_added_to_wishlist,
                            albumNumber + 1));
                    utils.saveWishlistLastItem(this);
                }
                Log.v(LOG_TAG, "wishlist: " + wishlist.toString());
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
  }
