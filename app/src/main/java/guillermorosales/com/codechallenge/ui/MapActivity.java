package guillermorosales.com.codechallenge.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import guillermorosales.com.codechallenge.R;
import guillermorosales.com.codechallenge.model.CategoriesModel;
import guillermorosales.com.codechallenge.model.ReportCountModel;
import guillermorosales.com.codechallenge.model.SFReportsModel;
import guillermorosales.com.codechallenge.presenters.MapFragmentPresenter;
import guillermorosales.com.codechallenge.ui.ViewModel.MapView;
import guillermorosales.com.codechallenge.util.UIUtil;
import guillermorosales.com.codechallenge.util.UtilColorMarker;

public class MapActivity extends AppCompatActivity implements MapView,OnMapReadyCallback {


    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.coordinatorLayout)
    protected CoordinatorLayout coordinatorLayout;
    private GoogleMap map;
    private  MapFragmentPresenter presenter;
    private int page = 0;
    private ProgressDialog mDialog;
    private LinkedHashMap incidentsCount = new LinkedHashMap();
    private boolean showDistrictsToggle=true;
    private boolean showReportsToggle=true;
    private List<SFReportsModel> reports;
    private List<SFReportsModel> reportsByCategory;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializeLoadingIndicator();
        SupportMapFragment  mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presenter = new  MapFragmentPresenter(this);
        presenter.start();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu= menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getTitle().equals("ALL")) {
            paintMap();
        }
        else {
            presenter.fetchReportsByCategory(item.getTitle().toString());
        }
        return true;
    }


    @OnClick(R.id.reports_map_toggle)
    public void toogleReports(ImageView view){

        if(showReportsToggle) {
            view.setColorFilter(Color.GRAY);
        }
        else{

            view.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.report_color));
        }

        showReportsToggle = !showReportsToggle;
        paintMap();

    }

    @OnClick(R.id.districts_map_toggle)
    public void toogleDistricts(ImageView view){

        map.clear();

        if(showDistrictsToggle) {
            view.setColorFilter(Color.GRAY);
        }
        else{

            view.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.danger0));
        }

        showDistrictsToggle = !showDistrictsToggle;
        paintMap();

    }

    public void initializeLoadingIndicator(){
        mDialog= new ProgressDialog(MapActivity.this);
        mDialog.setMessage(getResources().getString(R.string.loading_map_message));
        mDialog.setCancelable(false);
    }

    @Override
    public void setReports(List<SFReportsModel> reports) {
        this.reports = reports;
        paintMap();
    }

    @Override
    public void setReportsByCategory(List<SFReportsModel> reportsByCategory) {
        this.reportsByCategory = reportsByCategory;
        paintMap();
    }

    public void paintMap(){

        map.clear();
        LinkedHashMap incidentsCountAux = (LinkedHashMap)incidentsCount.clone();

        for (SFReportsModel report : reports) {
            if(incidentsCountAux.get(report.getPddistrict())!=-1){
                int position = new ArrayList<String>(incidentsCountAux.keySet()).indexOf(report.getPddistrict());
                if(showDistrictsToggle)
                {
                    paintDistrictMarkerOnMap(position,report,Integer.parseInt((String)incidentsCountAux.get(report.getPddistrict())));
                }

                incidentsCountAux.put(report.getPddistrict(), -1);
            }

            if (showReportsToggle && reportsByCategory==null) {
                paintReportOnMap(report);
            }
        }
        if(reportsByCategory!=null){

            for(SFReportsModel report: reportsByCategory){
                paintReportOnMap(report);
            }

            reportsByCategory = null;

        }
    }

    public void paintReportOnMap(SFReportsModel report){
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(Float.parseFloat(report.getLocation().getLatitude()), Float.parseFloat(report.getLocation().getLongitude()))).icon(BitmapDescriptorFactory.fromResource(R.drawable.inc))
                    .title(report.getCategory()).snippet(report.getDate().substring(0, report.getDate().indexOf("T")) + " at " + report.getTime()));
    }


    public void paintDistrictMarkerOnMap(int position,SFReportsModel report,int reportsNum){
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(UtilColorMarker.getColorCode(this,position)))
                .position(new LatLng(Float.parseFloat(report.getY()), Float.parseFloat(report.getX())))
                .title(report.getPddistrict()).snippet(reportsNum+" incidents last year"));

    }

    @Override
    public void setDistrictsData(List<ReportCountModel> districtsData) {

        for (ReportCountModel district : districtsData) {
            incidentsCount.put(district.getPddistrict(),district.getCount());
        }


        presenter.fetchReports(page);
    }

    @Override
    public void setCategories(List<CategoriesModel> categories) {

        menu.add("ALL");
        for (CategoriesModel category: categories){
            menu.add(category.getCategory());
        }

    }

    @Override
    public void showProgress() {
        mDialog.show();
    }

    @Override
    public void hideProgress() {
        mDialog.hide();
    }

    @Override
    public void showSuccess() {

        UIUtil.showSnackMessage(coordinatorLayout,getResources().getString(R.string.message_success));

    }

    @Override
    public void throwErrorMessage(String message) {
        UIUtil.showSnackMessage(coordinatorLayout,message);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        presenter.fetchDistricts();
    }


}
