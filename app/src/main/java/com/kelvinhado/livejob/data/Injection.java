package com.kelvinhado.livejob.data;

import android.content.Context;

import com.kelvinhado.livejob.data.source.JobDataSource;
import com.kelvinhado.livejob.data.source.local.JobLocalDataSource;

/**
 * Created by kelvin on 21/10/2017 .
 */

public class Injection {

    public static JobDataSource provideDataSource(Context context) {
        return JobLocalDataSource.getInstance(context);
    }
}
