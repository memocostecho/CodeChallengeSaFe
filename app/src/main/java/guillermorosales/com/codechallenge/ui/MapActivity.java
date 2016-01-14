package guillermorosales.com.codechallenge.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import guillermorosales.com.codechallenge.R;
import guillermorosales.com.codechallenge.model.SFDistrictsModel;
import guillermorosales.com.codechallenge.presenters.MapFragmentPresenter;
import guillermorosales.com.codechallenge.ui.ViewModel.MapView;
import guillermorosales.com.codechallenge.util.UtilColorMarker;
import guillermorosales.com.codechallenge.util.UtilMap;

public class MapActivity extends AppCompatActivity implements MapView,OnMapReadyCallback {


    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.coordinatorLayout)
    protected CoordinatorLayout coordinatorLayout;
    private GoogleMap map;
    private  MapFragmentPresenter presenter;
    private int page = 0;
    private TreeMap reportingMap = new TreeMap();
    ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mDialog= new ProgressDialog(MapActivity.this);
        mDialog.setMessage(getResources().getString(R.string.loading_map_message));
        mDialog.setCancelable(false);
        presenter = new  MapFragmentPresenter(this);
        SupportMapFragment  mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_load_more) {
            page++;
            presenter.fetchDistricts(page);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDistricts(List<SFDistrictsModel> districts) {

        for (SFDistrictsModel district : districts){


            if(reportingMap.get(district.getPddistrict())==null){
                reportingMap.put(district.getPddistrict(),new Float(1));

            }else{

                reportingMap.put(district.getPddistrict(),(Float)reportingMap.get(district.getPddistrict())+1);
            }



        }

        Map orderedMap = UtilMap.sortByValue(reportingMap);
        int index=0;

        while(index<reportingMap.size()){

            reportingMap.put(orderedMap.keySet().toArray()[index], UtilColorMarker.getColorCode(getApplicationContext(),index));
            index++;

        }


        for (SFDistrictsModel district : districts) {


            map.addMarker(new MarkerOptions()
                    .position(new LatLng(Float.parseFloat(district.getLocation().getLatitude()), Float.parseFloat(district.getLocation().getLongitude())))
                    .title(district.getCategory() + " at " + district.getTime()).icon(BitmapDescriptorFactory.defaultMarker((Float)reportingMap.get(district.getPddistrict()))));



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
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, getResources().getString(R.string.message_success), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void throwErrorMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        presenter.fetchDistricts(page);
    }

}
