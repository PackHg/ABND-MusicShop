package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.ALBUM_NUMBER_ARG;

public class ReadMoreActivity extends AppCompatActivity {

    private int mAlbumNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more);

        // Get the Album number passed through the Intent.
        Intent intent = getIntent();
        mAlbumNumber = intent.getIntExtra(ALBUM_NUMBER_ARG, mAlbumNumber);

        // set the Album number in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            String title = getString(R.string.album_detail, mAlbumNumber + 1);
            actionBar.setTitle(title);
        }

        Button okButton = (Button) findViewById(R.id.read_more_button_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadMoreActivity.this, AlbumActivity.class);
                intent.putExtra(ALBUM_NUMBER_ARG, mAlbumNumber);
                startActivity(intent);
            }
        });
    }
}
