package com.kelvinhado.livejob.jobs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kelvinhado.livejob.R;
import com.kelvinhado.livejob.data.model.Job;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kelvin on 21/10/2017 .
 */

public class JobsFragment extends Fragment implements JobsContract.View, SwipeRefreshLayout.OnRefreshListener, JobsAdapter.ListItemClickListener {

    private JobsContract.Presenter mPresenter;

    private List<Job> mJobsList;

    @BindView(R.id.rv_jobs)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    JobsAdapter mAdapter;

    public static JobsFragment newInstance() {
        return new JobsFragment();
    }

    public JobsFragment() {
        // required empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJobsList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.jobs_fragment, container, false);
        ButterKnife.bind(this, mRootView);

        mAdapter = new JobsAdapter(mJobsList, this, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mPresenter.start();
        return mRootView;
    }

    @Override
    public void setPresenter(JobsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void showLoadedJobsList(List<Job> jobs) {
        Toast.makeText(getActivity(), "has loaded", Toast.LENGTH_SHORT).show();
        mJobsList = jobs;
        mAdapter.swap(mJobsList);
    }

    @Override
    public void showLoadingFailed() {
        Toast.makeText(getActivity(), R.string.error_loading_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        mPresenter.requestLoadJobs(true);
    }

    @Override
    public void onListItemClicked(String applicationId) {
        Toast.makeText(getActivity(), "Item clicked", Toast.LENGTH_SHORT).show();
    }
}
