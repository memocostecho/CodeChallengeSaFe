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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.Serializable;
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
import guillermorosales.com.codechallenge.ui.fragments.ReportsListedFragment;
import guillermorosales.com.codechallenge.ui.viewModel.MapViewModel;
import guillermorosales.com.codechallenge.util.UIUtil;
import guillermorosales.com.codechallenge.util.UtilColorMarker;
import guillermorosales.com.codechallenge.util.UtilString;

public class MapActivity extends AppCompatActivity implements MapViewModel, OnMapReadyCallback {

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.coordinatorLayout)
    protected CoordinatorLayout coordinatorLayout;
    private GoogleMap map;
    private MapFragmentPresenter presenter;
    private ProgressDialog mDialog;
    private LinkedHashMap incidentsCount = new LinkedHashMap();
    private boolean showDistrictsToggle = true;
    private boolean showReportsToggle = true;
    private boolean showListFragmentToggle = false;
    private List<SFReportsModel> reports;
    private List<SFReportsModel> reportsByCategory;
    private Menu menu;
    private ReportsListedFragment reportsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializeLoadingIndicator();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        reportsFragment = new ReportsListedFragment();
        presenter = new MapFragmentPresenter(this,getApplicationContext());
        presenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        return true;
    }

    @Override
    public void onBackPressed() {
        if(showListFragmentToggle){
            toogleListFragment(null);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals(getResources().getString(R.string.all_filter_text))) {
            paintMap();
        } else {
            presenter.fetchReportsByCategory(item.getTitle().toString());
        }
        return true;
    }


    @OnClick(R.id.reports_map_toggle)
    public void toogleReports(ImageView view) {
        if (showReportsToggle) {
            view.setColorFilter(Color.GRAY);
        } else {

            view.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.report_color));
        }
        showReportsToggle = !showReportsToggle;
        paintMap();
    }

    @OnClick(R.id.districts_map_toggle)
    public void toogleDistricts(ImageView view) {
        map.clear();
        if (showDistrictsToggle) {
            view.setColorFilter(Color.GRAY);
        } else {
            view.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.danger0));
        }
        showDistrictsToggle = !showDistrictsToggle;
        paintMap();
    }


    @OnClick(R.id.view_list)
    public void toogleListFragment(ImageView view) {

        if(!showListFragmentToggle){
            Bundle bundle = new Bundle();
            bundle.putSerializable("reports", (Serializable) reports);
            reportsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_in,
                    R.anim.fragment_out)
                    .add(R.id.reports_list_container, reportsFragment).commit();
        }else{

            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_in,
                    R.anim.fragment_out)
                    .remove(reportsFragment).commit();

        }
        showListFragmentToggle = !showListFragmentToggle;

    }


    public void initializeLoadingIndicator() {
        mDialog = new ProgressDialog(MapActivity.this);
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

    private void paintMap() {
        map.clear();
        LinkedHashMap incidentsCountAux = (LinkedHashMap) incidentsCount.clone();
        for (SFReportsModel report : reports) {
            if (incidentsCountAux.get(report.getPddistrict()) != null) {
                int position = new ArrayList<String>(incidentsCountAux.keySet()).indexOf(
                        report.getPddistrict());
                if (showDistrictsToggle) {
                    paintDistrictMarkerOnMap(position, report, Integer.parseInt
                            ((String) incidentsCountAux.get(report.getPddistrict())));
                }

                incidentsCountAux.put(report.getPddistrict(),null);
            }
            if (showReportsToggle && reportsByCategory == null) {
                paintReportOnMap(report);
            }
        }
        if (reportsByCategory != null) {
            for (SFReportsModel report : reportsByCategory) {
                paintReportOnMap(report);
            }
            reportsByCategory = null;
        }
    }

    private void paintReportOnMap(SFReportsModel report) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(Float.parseFloat(report.getLocation().getLatitude()), Float
                        .parseFloat(report.getLocation().getLongitude()))).icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.inc))
                .title(report.getCategory()).snippet(report.getDate().substring(0, report.getDate()
                        .indexOf("T")) + " at " + report.getTime()));
    }


    private void paintDistrictMarkerOnMap(int position, SFReportsModel report, int reportsNum) {
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(UtilColorMarker.getColorCode(this, position)))
                .position(new LatLng(Float.parseFloat(report.getY()), Float.parseFloat(report.getX())))
                .title(report.getPddistrict()).snippet(reportsNum + " incidents last year"));

    }

    @Override
    public void setDistrictsData(List<ReportCountModel> districtsData) {
        for (ReportCountModel district : districtsData) {
            incidentsCount.put(district.getPddistrict(), district.getCount());
        }
        presenter.fetchReports();
    }

    @Override
    public void setCategories(List<CategoriesModel> categories) {
        menu.add(getResources().getString(R.string.all_filter_text));
        for (CategoriesModel category : categories) {
            menu.add(UtilString.capitalizeFirstLetter(category.getCategory().toLowerCase()));
        }
    }

    @Override
    public void showReportOnMap(SFReportsModel report) {

        if(!reports.contains(report)){
            paintReportOnMap(report);
        }

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(Float.parseFloat(
                        report.getLocation().getLatitude()),
                        Float.parseFloat(
                                report.getLocation().getLongitude())));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(20);
        map.moveCamera(center);
        map.animateCamera(zoom);
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
    public void showSuccess(String message) {
        new UIUtil().showSnackMessage(coordinatorLayout, message);
    }

    @Override
    public void throwErrorMessage(String message) {
        new UIUtil().showSnackMessage(coordinatorLayout, message);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        presenter.fetchDistricts();
    }

}
