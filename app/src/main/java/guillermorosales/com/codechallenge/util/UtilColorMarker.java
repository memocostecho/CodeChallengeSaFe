package guillermorosales.com.codechallenge.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import guillermorosales.com.codechallenge.R;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public class UtilColorMarker {

    public static float rgbToHue(float r, float g, float b) {

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
        int color = 0;
        float returnColor = 0;

        switch (position) {
            case 0:
                color = ContextCompat.getColor(context, R.color.danger0);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            case 1:
                color = ContextCompat.getColor(context, R.color.danger0);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            case 2:
                color = ContextCompat.getColor(context, R.color.danger0);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            case 3:
                color = ContextCompat.getColor(context, R.color.danger1);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            case 4:
                color = ContextCompat.getColor(context, R.color.danger2);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            case 5:
                color = ContextCompat.getColor(context, R.color.danger3);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            case 6:
                color = ContextCompat.getColor(context, R.color.danger4);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            case 7:
                color = ContextCompat.getColor(context, R.color.danger5);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            case 8:
                color = ContextCompat.getColor(context, R.color.danger6);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            case 9:
                color = ContextCompat.getColor(context, R.color.danger7);
                returnColor = UtilColorMarker.rgbToHue(Color.red(color), Color.green(color), Color.blue(color));
                break;
            default:
                break;
        }
        return returnColor;
    }

}
