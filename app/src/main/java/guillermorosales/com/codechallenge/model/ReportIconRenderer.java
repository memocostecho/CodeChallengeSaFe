package guillermorosales.com.codechallenge.model;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by yasminegutierrez on 1/18/16.
 */
public class ReportIconRenderer extends DefaultClusterRenderer<ReportClusterItem> {

    public ReportIconRenderer(Context context, GoogleMap map,
                           ClusterManager<ReportClusterItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(ReportClusterItem item, MarkerOptions markerOptions) {

        markerOptions.icon(item.getIcon());
        markerOptions.snippet(item.getSnippet());
        markerOptions.title(item.getTitle());
        super.onBeforeClusterItemRendered(item, markerOptions);
    }
}
