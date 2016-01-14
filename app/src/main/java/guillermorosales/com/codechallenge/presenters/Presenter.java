package guillermorosales.com.codechallenge.presenters;

import android.content.Intent;

/**
 * Abstract presenter to work as a base presenter for other presenters
 * Handles life cycle of activity/presenter
 * <p/>
 * Created by leonelmendez on 23/08/15.
 */
public interface Presenter {

    /**
     * Called when presenter is starting
     */
    void start();

    /**
     * Called when presenter is resumed
     */
    void resume();

    /**
     * Called when presenter is paused
     */
    void pause();

    void activityResult(int requestCode, int resultCode, Intent data);


    }
