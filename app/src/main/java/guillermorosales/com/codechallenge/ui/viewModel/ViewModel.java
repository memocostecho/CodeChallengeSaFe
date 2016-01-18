package guillermorosales.com.codechallenge.ui.viewModel;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public interface ViewModel {

    void showProgress();

    void hideProgress();

    void showSuccess(String message);

    void throwErrorMessage(String message);

}
