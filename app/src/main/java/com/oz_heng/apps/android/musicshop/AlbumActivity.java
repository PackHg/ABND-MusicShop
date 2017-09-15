package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.ALBUM_NUMBER_ARG;

public class AlbumActivity extends AppCompatActivity {

    private int mAlbumNumner = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        // Get the Album number passed through the Intent.
        Intent intent = getIntent();
        mAlbumNumner = intent.getIntExtra(ALBUM_NUMBER_ARG, mAlbumNumner);

        TextView textView = (TextView) findViewById(R.id.album_reference);
        textView.setText("Album " + (mAlbumNumner + 1));
    }
}
