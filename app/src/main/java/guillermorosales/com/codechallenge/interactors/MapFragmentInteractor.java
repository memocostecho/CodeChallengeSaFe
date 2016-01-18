package guillermorosales.com.codechallenge.interactors;

import guillermorosales.com.codechallenge.callbacks.FetchCategoriesCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsNumberCallBack;
import guillermorosales.com.codechallenge.ui.viewModel.MapView;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public interface MapFragmentInteractor {

    void fetchReportsList(int page, MapView mapView, FetchReportsCallBack callBack);

    void fetchReports(MapView mapView, FetchReportsCallBack callBack);

    void fetchReportNumbersByDistrict(MapView mapView, FetchReportsNumberCallBack callBack);

    void fetchCategories(MapView mapView, final FetchCategoriesCallBack callBack);

    void fetchReportsByCategory(String category, final MapView mapView, final FetchReportsCallBack
            callBack);

}
