package guillermorosales.com.codechallenge.util;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

/**
 * Created by yasminegutierrez on 1/15/16.
 */
public class UIUtil {

    public static void showSnackMessage(CoordinatorLayout coordinatorLayout,String message){


        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
