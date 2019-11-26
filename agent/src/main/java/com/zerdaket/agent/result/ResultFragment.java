package com.zerdaket.agent.result;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by zerdaket on 2019-11-26.
 */
public class ResultFragment extends Fragment {

    private int mCurrentRequestCode;
    private Intent mIntent;
    private ResultListener mResultListener;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    public static ResultFragment findResultFragment(FragmentManager fragmentManager, String tag) {
        if (fragmentManager.isDestroyed()) {
            throw new IllegalStateException("Can't access ResultFragment from onDestroy");
        }

        Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);
        if (fragmentByTag != null && !(fragmentByTag instanceof ResultFragment)) {
            throw new IllegalStateException("Unexpected "
                    + "fragment instance was returned by " + tag);
        }
        return (ResultFragment) fragmentByTag;
    }

    public ResultFragment addRequest(int requestCode, Intent intent, ResultListener listener) {
        mCurrentRequestCode = requestCode;
        mIntent = intent;
        mResultListener = listener;
        return this;
    }

    public void start() {
        startActivityForResult(mIntent, mCurrentRequestCode);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != mCurrentRequestCode || mResultListener == null) return;
        mResultListener.onResult(resultCode, data);
    }
}
