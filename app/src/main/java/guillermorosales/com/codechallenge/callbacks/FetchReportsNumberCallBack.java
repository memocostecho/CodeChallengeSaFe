package guillermorosales.com.codechallenge.callbacks;

import java.util.List;

/**
 * Created by Guillermo Romero on 1/14/16.
 */
public interface FetchReportsNumberCallBack {

    void onReportsNumberFetched(List districts);

    void onReportsNumberFetchedFailed(String message);

}
