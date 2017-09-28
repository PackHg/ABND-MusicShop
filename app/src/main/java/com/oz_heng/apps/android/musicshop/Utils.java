package com.oz_heng.apps.android.musicshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import static com.oz_heng.apps.android.musicshop.CatalogueActivity.KEY_WISHLIST;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.KEY_WISHLIST_SIZE;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.USER_DATA;
import static com.oz_heng.apps.android.musicshop.CatalogueActivity.wishlist;

/**
 * Helper methods
 */
class Utils {
    private final static String LOG_TAG = Utils.class.getSimpleName();

    Utils() {
    }

    /**
     * Display a Toast message.
     * @param message message to display.
     */
    static void displayToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Save wishlist's last added item and size into SharedPreferences.
     */
    void saveWishlistLastItem(Context context) {
        SharedPreferences sp =  context.getSharedPreferences(USER_DATA, 0);
        SharedPreferences.Editor editor = sp.edit();
        int size = wishlist.size();
        if (size > 0) {
            Log.v(LOG_TAG, "saveWishlistLastItem() - size: " + size);
            editor.putInt(KEY_WISHLIST + (size - 1), wishlist.get(size - 1));
            editor.putInt(KEY_WISHLIST_SIZE, size);
            editor.apply();
            Log.v(LOG_TAG, "saveWishlistLastItem() - wishlist.get("+ (size -1) +"): " +
                    wishlist.get(size -1));
        }
    }

    /**
     * Save wishlist into SharedPreferences.
     */
    void saveWishlist(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_DATA, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_WISHLIST_SIZE, wishlist.size());
        Log.v(LOG_TAG, "saveWishlist() - wishlist.size(): " + wishlist.size());
        for(int i = 0; i < wishlist.size(); i++) {
            editor.putInt(KEY_WISHLIST + i, wishlist.get(i));
            Log.v(LOG_TAG, "saveWishlist() - wishlist.get(" + i +"): " + wishlist.get(i));
        }
        editor.apply();
        Log.v(LOG_TAG, "saveWishlist() - wishlist: " + wishlist.toString());
    }

    /**
     * Restore wishlist from SharedPrefernces
     */
    void restoreWishlist(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_DATA, 0);
        if (sp != null) {
            int size = sp.getInt(KEY_WISHLIST_SIZE, 0);
            Log.v(LOG_TAG, "restoreWishlist() - size: " + size);
            wishlist.clear();
            for (int i = 0; i < size; i++) {
                wishlist.add(i, sp.getInt(KEY_WISHLIST + i, -1));
                Log.v(LOG_TAG, "restoreWishlist() - wishlist(" + i + "): " + wishlist.get(i));
            }
        }
    }
}
