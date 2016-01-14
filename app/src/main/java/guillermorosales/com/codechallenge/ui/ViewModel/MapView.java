package guillermorosales.com.codechallenge.ui.ViewModel;

import java.util.List;

import guillermorosales.com.codechallenge.model.SFDistrictsModel;

/**
 * Created by yasminegutierrez on 1/13/16.
 */
public interface MapView extends View{

    void setDistricts(List<SFDistrictsModel> districts);

}
