package guillermorosales.com.codechallenge.callbacks;

import java.util.List;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public interface FetchReportsCallBack {

    void onReportsFetched(List districts);
    void onReportsFetchedByCategory(List districts);
    void onReportsFetchFailed(String message);

}
