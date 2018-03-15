package com.agrovamp.agrovamp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Nishat Sayyed on 13-03-2018.
 */

public class PreferenceManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String PREF_NAME = "pref_name";
    public static final String KEY_LANG = "lang_key";
    public static final String IS_STORED = "is_stored";
    public static final String IS_QR_STORED = "is_qr_stored";
    public static final String KEY_QR = "KEY_QR";
    public static final String IS_FIRST_TIME = "is_first_time";

    public PreferenceManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setFirstTime(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME, true);
        editor.commit();
    }

    public void checkFirstTime() {
        if (this.isFirstTime()) {
            Intent intent = new Intent(context, MobileNumberActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    public void storeQRCode(String qrCode) {
        editor.putBoolean(IS_QR_STORED, true);
        editor.putString(KEY_QR, qrCode);
        editor.commit();
    }

    public void storeLanguageCode(String langCode) {
        editor.putBoolean(IS_STORED, true);
        editor.putString(KEY_LANG, langCode);
        editor.commit();
    }

    public String getQRCode() {
        String qrCode = preferences.getString(KEY_QR, "12345");
        return qrCode;
    }

    public String getLanguageCode() {
        Log.d(MainActivity.TAG, "getLanguageCode() called");
        String languageCode = preferences.getString(KEY_LANG, "en");
        Log.d(MainActivity.TAG, "Language code; " + languageCode);
        return languageCode;
    }

    public boolean isQRStored() {
        return preferences.getBoolean(IS_QR_STORED, false);
    }

    public boolean isStored() {
        return preferences.getBoolean(IS_STORED, false);
    }

    public boolean isFirstTime() {
        return preferences.getBoolean(IS_FIRST_TIME, false);
    }
}
