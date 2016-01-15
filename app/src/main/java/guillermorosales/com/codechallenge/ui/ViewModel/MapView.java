package guillermorosales.com.codechallenge.ui.ViewModel;

import java.util.List;

import guillermorosales.com.codechallenge.model.CategoriesModel;
import guillermorosales.com.codechallenge.model.ReportCountModel;
import guillermorosales.com.codechallenge.model.SFReportsModel;

/**
 * Created by yasminegutierrez on 1/13/16.
 */
public interface MapView extends View{

    void setReports(List<SFReportsModel> reports);
    void setReportsByCategory(List<SFReportsModel> reports);
    void setDistrictsData(List<ReportCountModel> districtsData);
    void setCategories(List<CategoriesModel> categories);


}
