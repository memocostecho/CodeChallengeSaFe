package guillermorosales.com.codechallenge.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import guillermorosales.com.codechallenge.R;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public class UtilColorMarker {

    private static float rgbToHue(float r, float g, float b) {

        r = r / 255;
        g = g / 255;
        b = b / 255;
        float max = Math.max(r, g);
        max = Math.max(max, b);
        float min = Math.min(r, g);
        min = Math.min(min, b);
        float delta = max - min;
        float hue = 0;

        if (max == r) {
            System.out.println("r");
            hue = 60 * ((g - b) / delta);
        } else if (max == g) {
            System.out.println("g");
            hue = 60 * (((b - r) / delta) + 2);
        } else if (max == b) {
            System.out.println("b");
            hue = 60 * (((r - g) / delta) + 4);
        }
        return hue;
    }


    public static float getColorCode(Context context, int position) {

        switch (position) {
            case 0:
                return getHueColor(ContextCompat.getColor(context, R.color.danger0));
            case 1:
                return getHueColor(ContextCompat.getColor(context, R.color.danger0));
            case 2:
                return getHueColor(ContextCompat.getColor(context, R.color.danger0));
            case 3:
                return getHueColor(ContextCompat.getColor(context, R.color.danger1));
            case 4:
                return getHueColor(ContextCompat.getColor(context, R.color.danger2));
            case 5:
                return getHueColor(ContextCompat.getColor(context, R.color.danger3));
            case 6:
                return getHueColor(ContextCompat.getColor(context, R.color.danger4));
            case 7:
                return getHueColor(ContextCompat.getColor(context, R.color.danger5));
            case 8:
                return getHueColor(ContextCompat.getColor(context, R.color.danger6));
            case 9:
                return getHueColor(ContextCompat.getColor(context, R.color.danger7));
            default:
                return 0;
        }
    }

    private static float getHueColor(int color){
        return UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
    }

}
