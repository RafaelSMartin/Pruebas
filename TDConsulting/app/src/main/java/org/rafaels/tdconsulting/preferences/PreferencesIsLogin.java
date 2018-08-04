package org.rafaels.tdconsulting.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;

public class PreferencesIsLogin extends PreferenceFragment{
    private static final String PREFS_IS_LOGIN = "preferencesIsLogin";

    public static boolean savePreferencesIsLoggin(Context context, String key, Boolean value){
        SharedPreferences settings = context.getSharedPreferences(PREFS_IS_LOGIN,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getPreferencesIsLogin(Context context, String key){
        SharedPreferences settings = context.getSharedPreferences(PREFS_IS_LOGIN,
                Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }

}
