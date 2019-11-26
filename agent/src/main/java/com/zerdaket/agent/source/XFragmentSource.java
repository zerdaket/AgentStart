package com.zerdaket.agent.source;

import android.app.Activity;
import android.content.Intent;

import com.zerdaket.agent.result.ResultFragment;
import com.zerdaket.agent.result.ResultListener;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by zerdaket on 2019-11-26.
 */
public class XFragmentSource implements ResultSource {

    private Fragment mFragment;
    private ResultListener mResultListener;
    private String mTag;

    public XFragmentSource(Fragment fragment) {
        mFragment = fragment;
        mTag = fragment.toString();
    }

    @Override
    public ResultSource setResultListener(ResultListener resultListener) {
        mResultListener = resultListener;
        return this;
    }

    @Override
    public void start(@NonNull Class<? extends Activity> activityClass) {
        start(activityClass, null);
    }

    @Override
    public void start(@NonNull Class<? extends Activity> activityClass, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        mFragment.startActivity(intent.setClass(mFragment.getContext(), activityClass));
    }

    @Override
    public void startForResult(@NonNull Class<? extends Activity> activityClass) {
        startForResult(activityClass, null);
    }

    @Override
    public void startForResult(@NonNull Class<? extends Activity> activityClass, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(mFragment.getContext(), activityClass);

        int requestCode = new Random().nextInt(0x0000FFFF);
        FragmentManager fragmentManager = mFragment.getChildFragmentManager();
        ResultFragment resultFragment = ResultFragment.findResultFragment(fragmentManager, mTag);
        if (resultFragment != null) {
            resultFragment
                    .addRequest(requestCode, intent, mResultListener)
                    .start();
            return;
        }
        resultFragment = ResultFragment.newInstance().addRequest(requestCode, intent, mResultListener);
        fragmentManager.beginTransaction().add(resultFragment, mTag).commitAllowingStateLoss();
    }
}
