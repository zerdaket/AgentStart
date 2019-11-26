package com.zerdaket.agent.source;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

/**
 * Created by zerdaket on 2019-11-26.
 */
public interface Source {

    void start(@NonNull Class<? extends Activity> activityClass);

    void start(@NonNull Class<? extends Activity> activityClass, Intent intent);

}
