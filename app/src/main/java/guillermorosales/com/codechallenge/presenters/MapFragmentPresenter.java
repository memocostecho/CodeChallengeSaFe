package guillermorosales.com.codechallenge.presenters;

import android.content.Intent;

import java.util.List;

import guillermorosales.com.codechallenge.callbacks.FetchDistrictsCallBack;
import guillermorosales.com.codechallenge.interactors.MapFragmentInteractor;
import guillermorosales.com.codechallenge.interactors.MapFragmentInteractorImpl;
import guillermorosales.com.codechallenge.ui.ViewModel.MapView;

/**
 * Created by yasminegutierrez on 1/13/16.
 */
public class MapFragmentPresenter implements Presenter,FetchDistrictsCallBack {


    MapView mapView;
    MapFragmentInteractor interactor;


    public MapFragmentPresenter(MapView mapView) {
        this.mapView = mapView;
        interactor = new MapFragmentInteractorImpl();
    }


    public void fetchDistricts(int page){
        mapView.showProgress();
        interactor.fetchDistricts(page,mapView,this);
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {


    }

    @Override
    public void pause() {

    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onDistrictsFetched(List districts) {
        mapView.setDistricts(districts);
        mapView.hideProgress();
        mapView.showSuccess();
    }

    @Override
    public void onDistrictsFetchFailed(String message) {
        mapView.throwErrorMessage(message);
    }
}
