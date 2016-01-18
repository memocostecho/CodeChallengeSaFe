package guillermorosales.com.codechallenge.presenters;

import android.content.Context;

import java.util.List;

import guillermorosales.com.codechallenge.R;
import guillermorosales.com.codechallenge.callbacks.FetchCategoriesCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsNumberCallBack;
import guillermorosales.com.codechallenge.interactors.MapFragmentInteractor;
import guillermorosales.com.codechallenge.interactors.MapFragmentInteractorImpl;
import guillermorosales.com.codechallenge.model.SFReportsModel;
import guillermorosales.com.codechallenge.ui.viewModel.MapView;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public class MapFragmentPresenter implements Presenter, FetchReportsCallBack, FetchReportsNumberCallBack, FetchCategoriesCallBack {

    private MapView mapView;
    private MapFragmentInteractor interactor;
    private Context context;

    public MapFragmentPresenter(MapView mapView,Context context) {
        this.mapView = mapView;
        this.context = context;
        interactor = new MapFragmentInteractorImpl();
    }

    public void fetchDistricts() {
        mapView.showProgress();
        interactor.fetchReportNumbersByDistrict(mapView, this);
    }

    public void fetchReports(int page) {
        interactor.fetchReports(page, mapView, this);
    }

    public void fetchReportsByCategory(String category) {
        mapView.showProgress();
        interactor.fetchReportsByCategory(category, mapView, this);
    }

    @Override
    public void start() {
        interactor.fetchCategories(mapView, this);
    }


    @Override
    public void onReportsFetched(List<SFReportsModel> reports) {
        mapView.setReports(reports);
        mapView.hideProgress();
        mapView.showSuccess(context.getResources().getString(R.string.message_success_get_reports,
                reports.size()));
    }

    @Override
    public void onReportsFetchedByCategory(List<SFReportsModel> reports) {
        mapView.setReportsByCategory(reports);
        mapView.hideProgress();
        mapView.showSuccess(context.getResources().getString(R.string.message_success_get_reports_by_category, reports.size(), reports.get(0).getCategory()
                .toLowerCase()));
    }

    @Override
    public void onReportsFetchFailed(String message) {
        mapView.throwErrorMessage(message);
    }

    @Override
    public void onReportsNumberFetched(List districts) {
        mapView.setDistrictsData(districts);
    }

    @Override
    public void onReportsNumberFetchedFailed(String message) {
        mapView.throwErrorMessage(message);
    }

    @Override
    public void onCategoriesFetched(List categories) {
        mapView.setCategories(categories);
    }

    @Override
    public void onCategoriesFetchFailed(String message) {
        mapView.throwErrorMessage(message);
    }
}
