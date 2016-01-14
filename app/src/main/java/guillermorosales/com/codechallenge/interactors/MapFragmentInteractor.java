package guillermorosales.com.codechallenge.interactors;

import guillermorosales.com.codechallenge.callbacks.FetchDistrictsCallBack;
import guillermorosales.com.codechallenge.ui.ViewModel.MapView;

/**
 * Created by yasminegutierrez on 1/13/16.
 */
public interface MapFragmentInteractor {

    void fetchDistricts(int page,MapView mapView,FetchDistrictsCallBack callBack);
}
