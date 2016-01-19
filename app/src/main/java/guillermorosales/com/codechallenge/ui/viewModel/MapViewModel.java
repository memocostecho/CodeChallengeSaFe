package guillermorosales.com.codechallenge.ui.viewModel;

import java.util.List;

import guillermorosales.com.codechallenge.model.CategoriesModel;
import guillermorosales.com.codechallenge.model.ReportCountModel;
import guillermorosales.com.codechallenge.model.SFReportsModel;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public interface MapViewModel {

    void setReports(List<SFReportsModel> reports);

    void setReportsByCategory(List<SFReportsModel> reports);

    void setDistrictsData(List<ReportCountModel> districtsData);

    void setCategories(List<CategoriesModel> categories);

    void showReportOnMap(SFReportsModel report);

}
