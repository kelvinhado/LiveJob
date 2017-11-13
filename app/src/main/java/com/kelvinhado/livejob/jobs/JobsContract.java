package com.kelvinhado.livejob.jobs;

import com.kelvinhado.livejob.BasePresenter;
import com.kelvinhado.livejob.BaseView;
import com.kelvinhado.livejob.data.model.Job;

import java.util.List;

/**
 * Created by kelvin on 21/10/2017 .
 */

public interface JobsContract {

    interface View extends BaseView<Presenter> {

        void showLoadingIndicator(boolean show);

        void showLoadedJobsList(List<Job> applies);

        void showLoadingFailed();

    }

    interface Presenter extends BasePresenter {

        void requestLoadJobs(boolean forceRefresh);
    }
}
