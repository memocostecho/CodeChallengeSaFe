package guillermorosales.com.codechallenge.callbacks;

import java.util.List;

import guillermorosales.com.codechallenge.model.SFReportsModel;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public interface FetchReportsCallBack {

    void onReportsFetched(List<SFReportsModel> districts);

    void onReportsFetchedByCategory(List<SFReportsModel> districts);

    void onReportsFetchFailed(String message);

}
