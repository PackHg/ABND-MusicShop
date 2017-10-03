package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.ALBUM_NUMBER_ARG;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.catalogue;

public class PaymentActivity extends AppCompatActivity {

    private int mAlbumNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Get the Album number passed through the Intent.
        Intent intent = getIntent();
        mAlbumNumber = intent.getIntExtra(ALBUM_NUMBER_ARG, mAlbumNumber);
        Album album = catalogue.get(mAlbumNumber);

        // set the title in the action bar.
        double price = album.getPrice();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            String title = getString(R.string.payment) + ": " + getString(R.string.album) + " " +
                    (mAlbumNumber + 1) + " (" + String.format("$%.2f%n", price).trim() + ")";
            actionBar.setTitle(title);
        }
    }
}