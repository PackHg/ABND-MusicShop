package com.oz_heng.apps.android.musicshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.KEY_WISHLIST;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.KEY_WISHLIST_SIZE;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.USER_DATA;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.wishlist;

/**
 * Helper methods
 */
class Utils {

    private Utils() {
    }

    /**
     * Display a Toast message.
     * @param context The Activity context.
     * @param message Message to display.
     */
    static void displayToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Save wishlist's last added item and size into SharedPreferences.
     */
    static void saveWishlistLastItem(Context context) {
        SharedPreferences sp =  context.getSharedPreferences(USER_DATA, 0);
        SharedPreferences.Editor editor = sp.edit();
        int size = wishlist.size();
        if (size > 0) {
            editor.putInt(KEY_WISHLIST + (size - 1), wishlist.get(size - 1));
            editor.putInt(KEY_WISHLIST_SIZE, size);
            editor.apply();
        }
    }

    /**
     * Save wishlist into SharedPreferences.
     */
     static void saveWishlist(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_DATA, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_WISHLIST_SIZE, wishlist.size());
        for(int i = 0; i < wishlist.size(); i++) {
            editor.putInt(KEY_WISHLIST + i, wishlist.get(i));
        }
        editor.apply();
    }

    /**
     * Restore wishlist from SharedPreferences.
     */
    static void restoreWishlist(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_DATA, 0);
        if (sp != null) {
            int size = sp.getInt(KEY_WISHLIST_SIZE, 0);
            wishlist.clear();
            for (int i = 0; i < size; i++) {
                wishlist.add(i, sp.getInt(KEY_WISHLIST + i, -1));
            }
        }
    }
}
