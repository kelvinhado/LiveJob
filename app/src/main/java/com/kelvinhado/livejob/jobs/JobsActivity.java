package com.kelvinhado.livejob.jobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kelvinhado.livejob.R;
import com.kelvinhado.livejob.data.Injection;
import com.kelvinhado.livejob.utils.ActivityUtils;

public class JobsActivity extends AppCompatActivity {

    JobsFragment mFragment;

    JobsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        // use saved fragment instance if available
        if(savedInstanceState != null) {
            mFragment = (JobsFragment) getSupportFragmentManager().getFragment(savedInstanceState, "savedFragment");
        } else {
            mFragment = (JobsFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        }

        if (mFragment == null) {
            // Create the fragment
            mFragment = JobsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mFragment, R.id.content_frame);
        }

        // setup the presenter
        mPresenter = new JobsPresenter(mFragment, Injection.provideDataSource(this));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "savedFragment", mFragment);
    }
}
