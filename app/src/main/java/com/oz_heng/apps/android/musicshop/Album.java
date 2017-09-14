package com.oz_heng.apps.android.musicshop;

import java.util.ArrayList;

/**
 * Data and methods relevant to an album.
 */
public class Album {

    // Title of the album
    private String mTitle;
    // List of songs
    private ArrayList<String> mListOfSongs;

    /**
     * Create a new Album object.
     * @param title title of the album.
     */
    public Album(String title) {
        mTitle = title;
    }

    /**
     * Get the title of the album.
     * @return the album's title.
     */
    public String getTitle() {
        return mTitle;
    }
}
