package guillermorosales.com.codechallenge.model;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Guillermo Romero on 1/18/16.
 */
public class ReportClusterItem implements ClusterItem {

    private final LatLng mPosition;
    private final String snippet;
    private final String title;
    private final BitmapDescriptor icon;

    public ReportClusterItem(double lat, double lng,String title,String snippet,BitmapDescriptor
            icon) {
        mPosition = new LatLng(lat, lng);
        this.title = title;
        this.snippet = snippet;
        this.icon = icon;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getTitle() {
        return title;
    }

    public BitmapDescriptor getIcon() {
        return icon;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
