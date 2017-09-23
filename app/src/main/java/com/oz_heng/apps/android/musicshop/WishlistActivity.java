package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.catalogue;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.wishlist;

public class WishlistActivity extends AppCompatActivity {

    ArrayList<Album> albumWishlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        // set the "Wishlist" title in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.wishlist));
        }

        /* Initialise albumWishlist based of wishlist which contains the album numbers
           that have been added to the wishlist.
         */
        for (int i = 0; i < wishlist.size(); i++) {
            albumWishlist.add(catalogue.get(wishlist.get(i)));
        }

        // Set AlbumAdpater on gridiew with albumWishList as data source.
        GridView gridView = (GridView) findViewById(R.id.wishlist_gridview);
        AlbumAdaper albumAdaper = new AlbumAdaper(this, albumWishlist);
        gridView.setAdapter(albumAdaper);

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
}
