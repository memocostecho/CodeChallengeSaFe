package guillermorosales.com.codechallenge.ui.fragments;

import android.location.Address;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import guillermorosales.com.codechallenge.R;
import guillermorosales.com.codechallenge.model.CategoriesModel;
import guillermorosales.com.codechallenge.model.ReportCountModel;
import guillermorosales.com.codechallenge.model.SFReportsModel;
import guillermorosales.com.codechallenge.presenters.MapFragmentPresenter;
import guillermorosales.com.codechallenge.ui.MapActivity;
import guillermorosales.com.codechallenge.ui.adapters.ReportsAdapter;
import guillermorosales.com.codechallenge.ui.listeners.EndlessRecyclerOnScrollListener;
import guillermorosales.com.codechallenge.ui.viewModel.MapViewModel;
import guillermorosales.com.codechallenge.ui.viewModel.ReportListViewModel;
import guillermorosales.com.codechallenge.util.ReverseGeocodeObservable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Guillermo Romero on 1/16/16.
 */
public class ReportsListedFragment extends Fragment implements ReportListViewModel,SwipeRefreshLayout
        .OnRefreshListener {

    @Bind(R.id.reports_recycler)
    protected RecyclerView reportsRecycler;
    @Bind(R.id.reports_pull_to_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager linearManager;
    private ReportsAdapter adapter;
    private MapFragmentPresenter presenter;
    private int page = 1;
    private List<SFReportsModel> reports;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MapFragmentPresenter(this,getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reports_list, container, false);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        linearManager = new LinearLayoutManager(getActivity());
        reportsRecycler.setLayoutManager(linearManager);
        reportsRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener
                (linearManager) {
            @Override
            public void onLoadMore(int current_page) {
                    presenter.fetchReportsList(page);
                    page++;
            }
        });
        adapter = new ReportsAdapter();
        adapter.setMapView((MapActivity) getActivity());
        reportsRecycler.setAdapter(adapter);
        reports = new ArrayList<>((ArrayList)this.getArguments().getSerializable
                ("reports"));
        adapter.setReports(reports);

       for (final SFReportsModel report : reports){
            ReverseGeocodeObservable.createObservable(getActivity().getApplicationContext(), Float
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
                            adapter.notifyDataSetChanged();
                        }


                    });
        }
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void setReportsList(List<SFReportsModel> reportsList) {
        if(reportsList.isEmpty()){
            adapter.setShowLoading(false);
            adapter.notifyDataSetChanged();
        }else{
            reports.addAll(reportsList);
            adapter.setReports(reports);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        presenter.fetchReportsList(0);
    }
}


