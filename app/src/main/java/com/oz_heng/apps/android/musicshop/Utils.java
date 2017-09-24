package com.oz_heng.apps.android.musicshop;

import android.content.Context;
import android.widget.Toast;

/**
 * Helper methods
 */
public class Utils {

    private Utils() {
    }

    /**
     * Display a Toast message.
     * @param message message to display.
     */
    static public void displayToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
