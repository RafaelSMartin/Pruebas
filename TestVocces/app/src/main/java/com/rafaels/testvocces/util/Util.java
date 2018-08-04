package com.rafaels.testvocces.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Rafael S Martin on 27/02/2018.
 */

public class Util {

    public static String ucFirst(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

//    public static String getStringFormatted(String datestring) {
//        String format = "dd/MM/yyyy";
//        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
//        return sdf.format((datestring.replaceAll("-", "/")));
//    }





}
