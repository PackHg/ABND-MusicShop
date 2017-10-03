package com.oz_heng.apps.android.musicshop;

import java.util.ArrayList;

/**
 * Data and methods relevant to an album.
 */
public class Album {

    // Title of the album
    private String mTitle = "";
    // List of songs
    private ArrayList<Song> mSongs = new ArrayList<>();

    /**
     * Create a new Album object.
     * @param title title of the album.
     * @param songs ArrayList of {@link Song}.
     */
    public Album(String title, ArrayList<Song> songs) {
        mTitle = title;
        for(int i = 0; i < songs.size(); i++) {
            mSongs.add(i, songs.get(i));
        }
    }

    /**
     * Get the title of the album.
     * @return the album's title.
     */
    public String getTitle() {
        return mTitle;
    }

    ArrayList<Song> getSongs() {
        return mSongs;
    }

    /**
     * Return the album's dummy price.
     * @return price.
     */
    double getPrice(){
        return mSongs.size() * 1.0;
    }
}
