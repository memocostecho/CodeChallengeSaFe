package guillermorosales.com.codechallenge.callbacks;

import java.util.List;

/**
 * Created by yasminegutierrez on 1/14/16.
 */
public interface FetchReportsNumberCallBack {

    void onReporsNumberFetched(List districts);
    void onReporsNumberFetchedFailed(String message);

}
