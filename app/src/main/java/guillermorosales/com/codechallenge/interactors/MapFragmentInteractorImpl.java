package guillermorosales.com.codechallenge.interactors;

import java.util.List;

import guillermorosales.com.codechallenge.callbacks.FetchCategoriesCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsCallBack;
import guillermorosales.com.codechallenge.callbacks.FetchReportsNumberCallBack;
import guillermorosales.com.codechallenge.model.CategoriesModel;
import guillermorosales.com.codechallenge.model.ReportCountModel;
import guillermorosales.com.codechallenge.model.SFReportsModel;
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
 * Created by Guillermo Romero on 1/13/16.
 */
public class MapFragmentInteractorImpl implements MapFragmentInteractor {

    private static int LIMIT = 50;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SFGovService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    private SFGovService.SFGovServiceAPI service = retrofit.create(SFGovService.SFGovServiceAPI.class);

    @Override
    public void fetchReports(int page, final MapView mapView, final FetchReportsCallBack callBack) {
        service.fetchReports("select * where date <" + UtilDate.getCurrentDayString() + " AND " +
                "date > " + UtilDate.getLastMonthDateString() + " LIMIT " + LIMIT + " OFFSET " +
                page * LIMIT).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Action1<List<SFReportsModel>>() {
            @Override
            public void call(List<SFReportsModel> sfDistrictsModels) {
                callBack.onReportsFetched(sfDistrictsModels);
            }
        });
    }

    @Override
    public void fetchReportsByCategory(String category, final MapView mapView, final FetchReportsCallBack callBack) {
        service.fetchReports("select * where date <" + UtilDate.getCurrentDayString() + " AND date > " + UtilDate.getLastYearDateString() + " AND category = '" + category + "' LIMIT " + LIMIT).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<SFReportsModel>>() {
            @Override
            public void call(List<SFReportsModel> sfDistrictsModels) {
                callBack.onReportsFetchedByCategory(sfDistrictsModels);
            }
        });
    }


    @Override
    public void fetchReportNumbersByDistrict(final MapView mapView, final FetchReportsNumberCallBack callBack) {
        service.fetchIncidentsNumber("select pddistrict,count(*) where date>" + UtilDate.getLastYearDateString() + " GROUP BY pddistrict order by count").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<ReportCountModel>>() {
            @Override
            public void call(List<ReportCountModel> sfDistrictsModels) {
                callBack.onReportsNumberFetched(sfDistrictsModels);
            }


        });
    }

    @Override
    public void fetchCategories(MapView mapView, final FetchCategoriesCallBack callBack) {
        service.fetchIncidentCategories("select category  where date> '2015-12-01'   group by category LIMIT 50").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<CategoriesModel>>() {
            @Override
            public void call(List<CategoriesModel> sfDistrictsModels) {
                callBack.onCategoriesFetched(sfDistrictsModels);
            }


        });
    }

}
