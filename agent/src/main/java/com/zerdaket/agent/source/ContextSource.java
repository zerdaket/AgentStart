package com.zerdaket.agent.source;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zerdaket on 2019-11-26.
 */
public class ContextSource implements Source {

    private Context mContext;

    public ContextSource(Context context) {
        mContext = context;
    }

    @Override
    public void start(@NonNull Class<? extends AppCompatActivity> activityClass) {
        start(activityClass, null);
    }

    @Override
    public void start(@NonNull Class<? extends AppCompatActivity> activityClass, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        mContext.startActivity(intent.setClass(mContext, activityClass));
    }

}
