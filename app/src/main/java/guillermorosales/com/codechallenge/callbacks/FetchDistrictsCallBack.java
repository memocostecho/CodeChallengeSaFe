package guillermorosales.com.codechallenge.callbacks;

import java.util.List;

/**
 * Created by yasminegutierrez on 1/13/16.
 */
public interface FetchDistrictsCallBack {

    void onDistrictsFetched(List districts);
    void onDistrictsFetchFailed(String message);

}
