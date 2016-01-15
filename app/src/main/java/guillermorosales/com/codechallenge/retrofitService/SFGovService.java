package guillermorosales.com.codechallenge.retrofitService;

import java.util.List;

import guillermorosales.com.codechallenge.model.CategoriesModel;
import guillermorosales.com.codechallenge.model.ReportCountModel;
import guillermorosales.com.codechallenge.model.SFReportsModel;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public class SFGovService {

    public static final String BASE_URL = "https://data.sfgov.org";

    public interface PetlyServiceAPI {

        @GET("/resource/ritf-b9ki.json")
        Observable<List<SFReportsModel>> fetchReports(@Query("$query") String query);

        @GET("/resource/ritf-b9ki.json")
        Observable<List<ReportCountModel>> fetchIncidentsNumber(@Query("$query") String query);

        @GET("/resource/ritf-b9ki.json")
        Observable<List<CategoriesModel>> fetchIncidentCategories(@Query("$query") String query);

    }

}
