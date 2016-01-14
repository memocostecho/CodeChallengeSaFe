package guillermorosales.com.codechallenge.retrofitService;

import java.util.List;

import guillermorosales.com.codechallenge.model.SFDistrictsModel;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by yasminegutierrez on 1/13/16.
 */
public class SFGovService {


    public static final String BASE_URL = "https://data.sfgov.org";

    public interface PetlyServiceAPI {

        @GET("/resource/ritf-b9ki.json")
        Observable<List<SFDistrictsModel>> fetchDistrictsInfo(@Query("$query")String query);

    }



}
