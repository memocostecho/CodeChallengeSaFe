package guillermorosales.com.codechallenge.callbacks;

import java.util.List;

/**
 * Created by Guillermo Romero on 1/14/16.
 */
public interface FetchCategoriesCallBack {

    void onCategoriesFetched(List categories);
    void onCategoriesFetchFailed(String message);
}
