package comeatest.rafaels.org.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.TextView;

import comeatest.rafaels.org.R;


public class Utils {

    public static void setTypefaceAvenirLight(Context context, TextView textView) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.avenir_light);
        textView.setTypeface(typeface);
    }

    public static void setTypefaceAvenirMedium(Context context, TextView textView) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.avenir_medium);
        textView.setTypeface(typeface);
    }

    public static void setTypefaceAvenirHeavy(Context context, TextView textView) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.avenir_heavy);
        textView.setTypeface(typeface);
    }

    public static void setTypefaceAvenirBlack(Context context, TextView textView) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.avenir_black);
        textView.setTypeface(typeface);
    }


}
