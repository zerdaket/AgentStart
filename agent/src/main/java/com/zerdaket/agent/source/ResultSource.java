package com.zerdaket.agent.source;

import android.app.Activity;
import android.content.Intent;

import com.zerdaket.agent.result.ResultListener;

import androidx.annotation.NonNull;

/**
 * Created by zerdaket on 2019-11-26.
 */
public interface ResultSource extends Source {

    ResultSource setResultListener(ResultListener resultListener);

    void startForResult(@NonNull Class<? extends Activity> activityClass);

    void startForResult(@NonNull Class<? extends Activity> activityClass, Intent intent);

}
