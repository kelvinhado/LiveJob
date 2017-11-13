package com.kelvinhado.livejob.jobs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kelvinhado.livejob.R;
import com.kelvinhado.livejob.data.model.Job;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobViewHolder> {

    private List<Job> mDataSet;

    private ListItemClickListener mListener;

    private Context mContext;

    public JobsAdapter(List<Job> jobsList, ListItemClickListener listener, Context context) {
        mDataSet = jobsList;
        mListener = listener;
        mContext = context;
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.jobs_item_list, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        Job apply = mDataSet.get(position);
        holder.itemView.setTag(apply.getId());
        holder.bind(apply);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void swap(List<Job> list) {
        if (mDataSet != null) {
            mDataSet.clear();
            mDataSet.addAll(list);
        } else {
            mDataSet = list;
        }
        notifyDataSetChanged();
    }

    public interface ListItemClickListener {
        void onListItemClicked(String jobId);
    }

    class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_item_company_name)
        TextView companyName;
        @BindView(R.id.tv_item_company_type)
        TextView companyType;
        @BindView(R.id.tv_item_salary)
        TextView salary;
        @BindView(R.id.tv_item_salary_gain)
        TextView salaryGain;
        @BindView(R.id.tv_item_job_title)
        TextView jobTitle;
        @BindView(R.id.tv_item_last_uptdate)
        TextView lastUpdate;
        @BindView(R.id.pb_item_progress)
        ProgressBar progress;

        public JobViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Job job) {
            companyName.setText(job.getCompany().getName());
            companyType.setText(job.getCompany().getType());
            jobTitle.setText(job.getTitle());
            progress.setProgress(job.getProcessPercentage());
            salary.setText(formatSalary(job));
            salaryGain.setText(formatGain(job));
            lastUpdate.setText(job.getLastUpdateDate().toString());
        }

        @Override
        public void onClick(View view) {
            mListener.onListItemClicked(mDataSet.get(getAdapterPosition()).getId());
        }
    }

    private String formatSalary(Job apply) {
        NumberFormat formatter = new DecimalFormat("#00.0");
        String salary = formatter.format(computeSalary(apply.getMinSalary(), apply.getMaxSalary()));
        return mContext.getApplicationContext().getString(R.string.default_salary, salary);
    }

    private String formatGain(Job apply) {
        NumberFormat formatter = new DecimalFormat("#00.0");
        double diff = computeSalary(apply.getMinSalary(), apply.getMaxSalary()) - apply.getCurrentSalary();
        double result = (diff / apply.getCurrentSalary()) * 100;
        String salaryGain = formatter.format(formatter.format(result));
        return mContext.getApplicationContext().getString(R.string.default_salary_gain, salaryGain);
    }

    private double computeSalary(double min, double max) {
        if (max == 0.0) return min;
        return (min + max) / 2;
    }

}