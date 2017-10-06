package com.oz_heng.apps.android.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.ALBUM_NUMBER_ARG;

public class ReadMoreActivity extends AppCompatActivity {

    private int mAlbumNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more);

        // To allow Up navigation in the action bar.
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Get the Album number passed through the Intent.
        Intent intent = getIntent();
        mAlbumNumber = intent.getIntExtra(ALBUM_NUMBER_ARG, mAlbumNumber);

        // Set the Album number in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            String title = getString(R.string.album_detail, mAlbumNumber + 1);
            actionBar.setTitle(title);
        }

        // Set the OK button to go back to the album.
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent albumIntent = new Intent(this, AlbumActivity.class);
                albumIntent.putExtra(ALBUM_NUMBER_ARG, mAlbumNumber);
                startActivity(albumIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
