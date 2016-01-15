package guillermorosales.com.codechallenge.presenters;

import java.util.List;

import guillermorosales.com.codechallenge.callbacks.FetchCategoriesCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsNumberCallBack;
import guillermorosales.com.codechallenge.interactors.MapFragmentInteractor;
import guillermorosales.com.codechallenge.interactors.MapFragmentInteractorImpl;
import guillermorosales.com.codechallenge.ui.ViewModel.MapView;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public class MapFragmentPresenter implements Presenter, FetchReportsCallBack, FetchReportsNumberCallBack, FetchCategoriesCallBack {


    private MapView mapView;
    private MapFragmentInteractor interactor;


    public MapFragmentPresenter(MapView mapView) {
        this.mapView = mapView;
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

        interactor.fetchReportsByCategory(category, mapView, this);

    }

    @Override
    public void start() {
        interactor.fetchCategories(mapView, this);
    }


    @Override
    public void onReportsFetched(List reports) {
        mapView.setReports(reports);
        mapView.hideProgress();
        mapView.showSuccess();
    }

    @Override
    public void onReportsFetchedByCategory(List reports) {
        mapView.setReportsByCategory(reports);

    }

    @Override
    public void onReportsFetchFailed(String message) {
        mapView.throwErrorMessage(message);
    }

    @Override
    public void onReporsNumberFetched(List districts) {
        mapView.setDistrictsData(districts);
    }

    @Override
    public void onReporsNumberFetchedFailed(String message) {
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
