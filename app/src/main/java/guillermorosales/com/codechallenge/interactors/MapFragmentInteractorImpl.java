package guillermorosales.com.codechallenge.interactors;

import java.util.List;
import guillermorosales.com.codechallenge.callbacks.FetchDistrictsCallBack;
import guillermorosales.com.codechallenge.model.SFDistrictsModel;
import guillermorosales.com.codechallenge.retrofitService.SFGovService;
import guillermorosales.com.codechallenge.ui.ViewModel.MapView;
import guillermorosales.com.codechallenge.util.UtilDate;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yasminegutierrez on 1/13/16.
 */
public class MapFragmentInteractorImpl implements MapFragmentInteractor {

    static int LIMIT = 50;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SFGovService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

    SFGovService.PetlyServiceAPI service = retrofit.create(SFGovService.PetlyServiceAPI.class);




    @Override
    public void fetchDistricts(int page, final MapView mapView, final FetchDistrictsCallBack callBack) {

        service.fetchDistrictsInfo("select * where date <"+ UtilDate.getSupDateQuery()+" AND date > "+UtilDate.getInfDateQuery()+" LIMIT "+LIMIT+" OFFSET "+page*LIMIT).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

                callBack.onDistrictsFetchFailed(throwable.getMessage());

            }
        }).subscribe(new Action1<List<SFDistrictsModel>>() {
            @Override
            public void call(List<SFDistrictsModel> sfDistrictsModels) {


                callBack.onDistrictsFetched(sfDistrictsModels);


            }


        });

    }
}
