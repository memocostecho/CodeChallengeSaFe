package guillermorosales.com.codechallenge.interactors;

import guillermorosales.com.codechallenge.callbacks.FetchCategoriesCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsNumberCallBack;
import guillermorosales.com.codechallenge.ui.viewModel.MapViewModel;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public interface MapFragmentInteractor {

    void fetchReportsList(int page, MapViewModel mapView, FetchReportsCallBack callBack);

    void fetchReports(MapViewModel mapView, FetchReportsCallBack callBack);

    void fetchReportNumbersByDistrict(MapViewModel mapView, FetchReportsNumberCallBack callBack);

    void fetchCategories(MapViewModel mapView, final FetchCategoriesCallBack callBack);

    void fetchReportsByCategory(String category, final MapViewModel mapView, final FetchReportsCallBack
            callBack);

}
