package com.zerdaket.agent.source;

import android.content.Intent;

import com.zerdaket.agent.result.ResultListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zerdaket on 2019-11-26.
 */
public interface ResultSource extends Source {

    ResultSource setResultListener(ResultListener resultListener);

    void startForResult(@NonNull Class<? extends AppCompatActivity> activityClass);

    void startForResult(@NonNull Class<? extends AppCompatActivity> activityClass, Intent intent);

}
