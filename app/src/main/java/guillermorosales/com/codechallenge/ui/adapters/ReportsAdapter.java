package guillermorosales.com.codechallenge.ui.adapters;

import android.content.Context;
import android.location.Address;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import guillermorosales.com.codechallenge.R;
import guillermorosales.com.codechallenge.model.SFReportsModel;
import guillermorosales.com.codechallenge.ui.viewModel.MapViewModel;
import guillermorosales.com.codechallenge.util.ReverseGeocodeObservable;
import guillermorosales.com.codechallenge.util.UtilString;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Guillermo Romero on 1/15/16.
 */
public class ReportsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int REPORT_ITEM = 1;
    private final int REPORT_LOADING = 2;
    private List<SFReportsModel> reports = new ArrayList();
    private MapViewModel mapView;
    private boolean showLoading = true;
    private Context context;

    public ReportsAdapter(MapViewModel mapView, Context context) {
        this.mapView = mapView;
        this.context = context;
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
    }

    public void setReports(List<SFReportsModel> reports) {
        this.reports = reports;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView;
        if (viewType == REPORT_ITEM) {

            itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.reports_list_item, viewGroup, false);
            return new ReportViewHolder(itemView);

        } else {

            itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.recyler_bottom_loading, viewGroup, false);
            return new LoadingViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        if (position <= reports.size() - 1) {

            final ReportViewHolder holder = (ReportViewHolder) viewHolder;
            final SFReportsModel report = reports.get(position);
            holder.reportTitle.setText(UtilString.capitalizeFirstLetter(report.getCategory()
                    .toLowerCase()) + " - " + UtilString.capitalizeFirstLetter(report.getPddistrict()
                    .toLowerCase()));
            holder.reportAddress.setText(report.getAddress());
            holder.reportTime.setText(report.getTime());
            holder.reportDate.setText(report.getDate().substring(0, report.getDate()
                    .indexOf("T")));

            ReverseGeocodeObservable.createObservable(context, Float
                    .parseFloat
                            (reports.get(reports.indexOf(report))
                                    .getLocation().getLatitude()), Float.parseFloat(reports.get(
                    reports.indexOf(report)).getLocation().getLongitude())
                    , 1).subscribeOn
                    (Schedulers.newThread()).subscribe(
                    new Action1<List<Address>>() {
                        @Override
                        public void call(List<Address> addresses) {
                            reports.get(reports.indexOf(report)).setAddress(addresses.get(0)
                                    .toString());
                            notifyDataSetChanged();
                        }


                    });
            holder.itemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapView.showReportOnMap(reports.get(position));
                }
            });


        } else {
            if (!showLoading) {
                final LoadingViewHolder holder = (LoadingViewHolder) viewHolder;
                holder.progressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return reports.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == reports.size()) {
            return REPORT_LOADING;
        } else {
            return REPORT_ITEM;
        }
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

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.bottom_progress)
        ProgressBar progressBar;

        public LoadingViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
