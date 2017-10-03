package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.ALBUM_NUMBER_ARG;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.catalogue;

public class ReadMoreActivity extends AppCompatActivity {

    private int mAlbumNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more);

        // Get the Album number passed through the Intent.
        Intent intent = getIntent();
        mAlbumNumber = intent.getIntExtra(ALBUM_NUMBER_ARG, mAlbumNumber);
        Album album = catalogue.get(mAlbumNumber);

        // set the title in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            String title = getString(R.string.about_the_album) + " " + (mAlbumNumber + 1);
            actionBar.setTitle(title);
        }

    }
}
