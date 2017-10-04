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

import java.util.ArrayList;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.ALBUM_NUMBER_ARG;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.catalogue;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.wishlist;
import static com.oz_heng.apps.android.musicshop.Utils.displayToastMessage;
import static com.oz_heng.apps.android.musicshop.Utils.restoreWishlist;
import static com.oz_heng.apps.android.musicshop.Utils.saveWishlist;

public class WishlistActivity extends AppCompatActivity {
    private static final String LOG_TAG = WishlistActivity.class.getSimpleName();

    private static ArrayList<Album> mAlbumWishlist = new ArrayList<>();

    private AlbumAdaper mAlbumAdaper;

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
        restoreWishlist(this);

        Log.v(LOG_TAG, "onCreate() - wislist:" + wishlist.toString());

        /* Initialise mAlbumWishlist based of wishlist which contains the album numbers
           that have been added to the wishlist.
         */
        mAlbumWishlist.clear();
        for (int i = 0; i < wishlist.size(); i++) {
            mAlbumWishlist.add(catalogue.get(wishlist.get(i)));
        }

        // Set AlbumAdpater on gridiew with albumWishList as data source.
        GridView albumGridView = (GridView) findViewById(R.id.wishlist_gridview);
        mAlbumAdaper = new AlbumAdaper(this, mAlbumWishlist);
        albumGridView.setAdapter(mAlbumAdaper);

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
                Intent albumIntent = new Intent(WishlistActivity.this, AlbumActivity.class);
                albumIntent.putExtra(ALBUM_NUMBER_ARG, albumNumber);
                startActivity(albumIntent);
                return true;

            case R.id.album_item_cmenu_option_buy_album:
                Intent paymetIntent = new Intent(WishlistActivity.this, PaymentActivity.class);
                paymetIntent.putExtra(ALBUM_NUMBER_ARG, albumNumber);
                startActivity(paymetIntent);
                return true;

            case R.id.album_item_cmenu_option_remove_from_wishlist:
                mAlbumWishlist.remove(catalogue.get(wishlist.get(itemNumber)));
                wishlist.remove(itemNumber);
                mAlbumAdaper.notifyDataSetChanged();
                displayToastMessage(this, getString(R.string.album_removed_from_wishlist,
                        albumNumber + 1));
                saveWishlist(this);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
