package guillermorosales.com.codechallenge.callbacks;

import java.util.List;

import guillermorosales.com.codechallenge.model.SFReportsModel;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public interface FetchReportsCallBack {

    void onReportsListFetched(List<SFReportsModel> reports);

    void onReportsFetched(List<SFReportsModel> reports);

    void onReportsFetchedByCategory(List<SFReportsModel> districts);

    void onReportsFetchFailed(String message);

}
