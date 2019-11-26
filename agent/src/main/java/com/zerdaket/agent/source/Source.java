package com.zerdaket.agent.source;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zerdaket on 2019-11-26.
 */
public interface Source {

    void start(@NonNull Class<? extends AppCompatActivity> activityClass);

    void start(@NonNull Class<? extends AppCompatActivity> activityClass, Intent intent);

}
