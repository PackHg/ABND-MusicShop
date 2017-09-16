package com.oz_heng.apps.android.musicshop;

/**
 * Data and methods relevant to a song.
 */
public class Song {

    // Title of the song
    private String mTitle = "";

    public Song(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }
}
