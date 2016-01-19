package guillermorosales.com.codechallenge.presenters;

import java.util.List;

import guillermorosales.com.codechallenge.callbacks.FetchCategoriesCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsNumberCallBack;
import guillermorosales.com.codechallenge.interactors.MapFragmentInteractor;
import guillermorosales.com.codechallenge.interactors.MapFragmentInteractorImpl;
import guillermorosales.com.codechallenge.model.SFReportsModel;
import guillermorosales.com.codechallenge.ui.viewModel.MapViewModel;
import guillermorosales.com.codechallenge.ui.viewModel.ReportListViewModel;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public class MapFragmentPresenter implements ActivityFragmentPresenter, FetchReportsCallBack,
        FetchReportsNumberCallBack, FetchCategoriesCallBack {

    private MapViewModel mapView;
    private ReportListViewModel reportsListView;
    private MapFragmentInteractor interactor;

    public MapFragmentPresenter(MapViewModel mapView) {
        this.mapView = mapView;
        interactor = new MapFragmentInteractorImpl();
    }

    public MapFragmentPresenter(ReportListViewModel reportsListView) {
        this.reportsListView = reportsListView;
        interactor = new MapFragmentInteractorImpl();
    }

    public void fetchDistricts() {
        interactor.fetchReportNumbersByDistrict(mapView, this);
    }

    public void fetchReportsList(int page) {
        interactor.fetchReportsList(page, mapView, this);
    }

    public void fetchReports() {
        interactor.fetchReports(mapView, this);
    }

    public void fetchReportsByCategory(String category) {
        interactor.fetchReportsByCategory(category, mapView, this);
    }

    @Override
    public void start() {
        interactor.fetchCategories(mapView, this);
    }

    @Override
    public void onReportsListFetched(List<SFReportsModel> reports) {
        reportsListView.setReportsList(reports);
    }

    @Override
    public void onReportsFetched(List<SFReportsModel> reports) {
        mapView.setReports(reports);
    }

    @Override
    public void onReportsFetchedByCategory(List<SFReportsModel> reports) {
        mapView.setReportsByCategory(reports);
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
