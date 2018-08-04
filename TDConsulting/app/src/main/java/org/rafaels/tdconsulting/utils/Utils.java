package org.rafaels.tdconsulting.utils;

import android.app.Activity;
import android.content.Intent;

public class Utils {

    public static void launchActivity(Activity activity, Class clase){
        Intent i = new Intent(activity, clase);
        activity.startActivity(i);
    }

}
