package guillermorosales.com.codechallenge.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import guillermorosales.com.codechallenge.R;
import guillermorosales.com.codechallenge.model.SFReportsModel;
import guillermorosales.com.codechallenge.ui.viewModel.MapView;
import guillermorosales.com.codechallenge.util.UtilString;

/**
 * Created by Guillermo Romero on 1/15/16.
 */
public class ReportsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SFReportsModel> reports= new ArrayList();

    private MapView mapView;

    public void setReports(List<SFReportsModel> reports) {
        this.reports = reports;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.reports_list_item, viewGroup, false);
        return new ReportViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        final ReportViewHolder holder = (ReportViewHolder) viewHolder;
        final SFReportsModel report = reports.get(position);
        holder.reportTitle.setText(UtilString.capitalizeFirstLetter(report.getCategory()
                .toLowerCase())+" - " + UtilString.capitalizeFirstLetter(report.getPddistrict()
                .toLowerCase()));
        holder.reportAddress.setText(report.getAddress());
        holder.reportTime.setText(report.getTime());
        holder.reportDate.setText(report.getDate().substring(0, report.getDate()
                .indexOf("T")));
        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.showReportOnMap(reports.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.report_address)
        TextView reportAddress;
        @Bind(R.id.report_title)
        TextView reportTitle;
        @Bind(R.id.report_time)
        TextView reportTime;
        @Bind(R.id.report_date)
        TextView reportDate;
        @Bind(R.id.report_list_item_container)
        LinearLayout itemContainer;

        public ReportViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
