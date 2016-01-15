package guillermorosales.com.codechallenge.ui.ViewModel;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public interface View {

    void showProgress();

    void hideProgress();

    void showSuccess();

    void throwErrorMessage(String message);
}