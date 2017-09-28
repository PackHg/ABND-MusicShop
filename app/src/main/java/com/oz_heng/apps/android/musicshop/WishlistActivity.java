package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.ALBUM_NUMBER_ARG;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.KEY_WISHLIST;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.KEY_WISHLIST_SIZE;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.USER_DATA;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.catalogue;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.wishlist;
import static com.oz_heng.apps.android.musicshop.Utils.displayToastMessage;

public class WishlistActivity extends AppCompatActivity {
    static final String LOG_TAG = WishlistActivity.class.getSimpleName();

    static ArrayList<Album> albumWishlist = new ArrayList<>();

    GridView albumGridView;
    AlbumAdaper albumAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        // set the "Wishlist" title in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.wishlist));
        }

        // Restore wishlist data from SharedPreferences
        SharedPreferences sp = getSharedPreferences(USER_DATA, 0);
        if (sp != null) {
            int size = sp.getInt(KEY_WISHLIST_SIZE, 0);
            Log.v(LOG_TAG, "onCreate() - size: " + size);
            wishlist.clear();
            for(int i = 0; i < size; i++) {
                wishlist.add(i, sp.getInt(KEY_WISHLIST + i, -1));
                Log.v(LOG_TAG, "OnCreate() - wishlist(" + i + "): " + wishlist.get(i));
            }
        }

        Log.v(LOG_TAG, "onCreate() - wislist:" + wishlist.toString());

        /* Initialise albumWishlist based of wishlist which contains the album numbers
           that have been added to the wishlist.
         */
        albumWishlist.clear();
        for (int i = 0; i < wishlist.size(); i++) {
            albumWishlist.add(catalogue.get(wishlist.get(i)));
        }

        // Set AlbumAdpater on gridiew with albumWishList as data source.
        albumGridView = (GridView) findViewById(R.id.wishlist_gridview);
        albumAdaper = new AlbumAdaper(this, albumWishlist);
        albumGridView.setAdapter(albumAdaper);

        /* Set empty view on albumGridView, so that it only shows when the wishlist
           is empty.
         */
        View emptyView = findViewById(R.id.wishlist_empty);
        albumGridView.setEmptyView(emptyView);

        // Associate gridView with a contextual menu.
        registerForContextMenu(albumGridView);

        // Set "Catalogue" button to start CatalogueActivity
        Button catalogueButton = (Button) findViewById(R.id.wishlist_goto_catalogue);
        catalogueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WishlistActivity.this, CatalogueActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.wishlist_album_item_cmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        int itemNumber = (int) info.id;
        int albumNumber = wishlist.get(itemNumber);

        Log.v(LOG_TAG, "itemNumber: " + itemNumber);
        Log.v(LOG_TAG, "albumNumber: " + albumNumber);

        switch (item.getItemId()) {
            case R.id.album_item_cmenu_option_go_to_album:
                Intent intent = new Intent(WishlistActivity.this, AlbumActivity.class);
                intent.putExtra(ALBUM_NUMBER_ARG, albumNumber);
                startActivity(intent);
                return true;

            case R.id.album_item_cmenu_option_buy_album:
                Toast.makeText(this, "Album " + (albumNumber + 1) + " - Buy album", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.album_item_cmenu_option_remove_from_wishlist:
                albumWishlist.remove(catalogue.get(wishlist.get(itemNumber)));
                wishlist.remove(itemNumber);
                albumAdaper.notifyDataSetChanged();
                displayToastMessage(this, getString(R.string.album_removed_from_wishlist,
                        albumNumber + 1));
                saveWishlist();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        // Save wishlist data into SharedPreferences
//        SharedPreferences sp = getSharedPreferences(USER_DATA, 0);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putInt(KEY_WISHLIST_SIZE, wishlist.size());
//        Log.v(LOG_TAG, "onSTop() - wishlist.size(): " + wishlist.size());
//        for(int i = 0; i < wishlist.size(); i++) {
//            editor.putInt(KEY_WISHLIST + i, wishlist.get(i));
//            Log.v(LOG_TAG, "onSTop() - wishlist.get(" + i +"): " + wishlist.get(i));
//        }
//        editor.apply();
//        Log.v(LOG_TAG, "wishlist: " + wishlist.toString());
//    }

    /**
     * Save wishlist into SharedPreferences.
     */
    void saveWishlist() {
        SharedPreferences sp = getSharedPreferences(USER_DATA, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_WISHLIST_SIZE, wishlist.size());
        Log.v(LOG_TAG, "saveWishlist() - wishlist.size(): " + wishlist.size());
        for(int i = 0; i < wishlist.size(); i++) {
            editor.putInt(KEY_WISHLIST + i, wishlist.get(i));
            Log.v(LOG_TAG, "saveWishlist() - wishlist.get(" + i +"): " + wishlist.get(i));
        }
        editor.apply();
        Log.v(LOG_TAG, "saveWishlist() - wishlist: " + wishlist.toString());
    }

    /**
     * Restore wishlist from SharedPreferences
     */
    void restoreWishlist() {
        SharedPreferences sp = getSharedPreferences(USER_DATA, 0);
        if (sp != null) {
            int size = sp.getInt(KEY_WISHLIST_SIZE, 0);
            wishlist.clear();
            for(int i = 0; i < size; i++) {
                wishlist.add(i, sp.getInt(KEY_WISHLIST + i, -1));
            }
        }
    }
}
