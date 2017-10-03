package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.ALBUM_NUMBER_ARG;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.catalogue;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.wishlist;
import static com.oz_heng.apps.android.musicshop.Utils.displayToastMessage;
import static com.oz_heng.apps.android.musicshop.Utils.restoreWishlist;
import static com.oz_heng.apps.android.musicshop.Utils.saveWishlist;
import static com.oz_heng.apps.android.musicshop.Utils.saveWishlistLastItem;

public class AlbumActivity extends AppCompatActivity {
    private static final String LOG_TAG = AlbumActivity.class.getSimpleName();

    private int mAlbumNumber = 0;
    private boolean mIsInWishlist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        // Get the Album number passed through the Intent.
        Intent intent = getIntent();
        mAlbumNumber = intent.getIntExtra(ALBUM_NUMBER_ARG, mAlbumNumber);
        Album album = catalogue.get(mAlbumNumber);

        // set the Album number in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.album) + " " + (mAlbumNumber + 1));
        }

        // Restore wishlist data from SharedPreferences.
        restoreWishlist(this);
        Log.v(LOG_TAG, "onCreate() - wislist:" + wishlist.toString());

        /* Depending on the Album is or isn't in the wishlist, set the corresponding text on the button
           R.id.album_add_or_remove_whishlist and set the corresponding action on this button (add or
           remove from wishlist.
         */
        final Button wishlistButton = (Button) findViewById(R.id.album_add_or_remove_whishlist);
        mIsInWishlist = wishlist.contains(mAlbumNumber);
        if (mIsInWishlist) {
            wishlistButton.setText(getString(R.string.remove_from_wishlist));
        } else {
            wishlistButton.setText(getString(R.string.add_to_wishlist));
        }
        wishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wishlist.contains(mAlbumNumber)) {
                    /* If the Album is in the wishlist, remove it from the latter and update the text
                       on the button.
                     */
                    wishlist.remove(Integer.valueOf(mAlbumNumber));
                    saveWishlist(AlbumActivity.this);
                    displayToastMessage(AlbumActivity.this, getString(R.string.album_removed_from_wishlist,
                            mAlbumNumber + 1));
                    wishlistButton.setText(R.string.add_to_wishlist);
                } else {
                    /* If the Album isn't in the wishlist, add it to the latter and update the text
                       on the button.
                     */
                    wishlist.add(mAlbumNumber);
                    saveWishlistLastItem(AlbumActivity.this);
                    displayToastMessage(AlbumActivity.this, getString(R.string.album_added_to_wishlist,
                            mAlbumNumber + 1));
                    wishlistButton.setText(getString(R.string.remove_from_wishlist));
                }
                Log.v(LOG_TAG, "wishlist: " + wishlist.toString());
            }
        });

        // Set SongAdapter to the ListView. This ListView is a mockup.
        ListView songListView = (ListView) findViewById(R.id.album_list_view);
        SongAdapter songAdapter = new SongAdapter(this, album.getSongs());
        songListView.setAdapter(songAdapter);

        songListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String song = "Song " + (mAlbumNumber + 1) + "-" +
                        String.format("%02d%n", position + 1);
                displayToastMessage(AlbumActivity.this, getString(R.string.listview_is_mockup));
            }
        });

        // Set the button R.id.album_catalogue to start CatalogueActivity.
        Button catalogueButton = (Button) findViewById(R.id.album_catalogue);
        catalogueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlbumActivity.this, CatalogueActivity.class);
                startActivity(intent);
            }
        });

        /* Disply the album price on the button R.id.album_buy, and set this button
           to start the PaymentActivity.
         */
        Button buyButton = (Button) findViewById(R.id.album_buy);
        double price = album.getPrice();
        buyButton.setText(String.format("$%.2f%n", price).trim());
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlbumActivity.this, PaymentActivity.class);
                intent.putExtra(ALBUM_NUMBER_ARG, mAlbumNumber);
                startActivity(intent);
            }
        });

        // Set the button R.id.album_readmore to start the ReadMoreActivity.
        Button readMoreButton = (Button) findViewById(R.id.album_readmore);
        readMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlbumActivity.this, ReadMoreActivity.class);
                intent.putExtra(ALBUM_NUMBER_ARG, mAlbumNumber);
                startActivity(intent);
            }
        });

        Button goToWishlistButton = (Button) findViewById(R.id.album_go_to_whishlist);
        goToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlbumActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });

    }
}
