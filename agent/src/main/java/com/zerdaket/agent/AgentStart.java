package com.zerdaket.agent;

import android.content.Intent;
import android.os.Bundle;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by zerdaket on 2019-11-13.
 */
public class AgentStart {

    public interface ResultListener {
        void onResult(int resultCode, Intent data);
    }

    public static Option with(AppCompatActivity activity) {
        return new Option(activity);
    }

    public static Option with(Fragment fragment) {
        return new Option(fragment);
    }

    public static class Option {

        private AppCompatActivity mActivity;
        private Fragment mFragment;
        private String mTag;
        private ResultListener mResultListener;

        Option(AppCompatActivity activity) {
            mActivity = activity;
            mTag = activity.toString();
        }

        Option(Fragment fragment) {
            mFragment = fragment;
            mTag = fragment.toString();
        }

        public Option setResultListener(ResultListener resultListener) {
            mResultListener = resultListener;
            return this;
        }

        public void start(@NonNull Class<? extends AppCompatActivity> activityClass) {
            start(activityClass, null);
        }

        public void start(@NonNull Class<? extends AppCompatActivity> activityClass, Intent intent) {
            if (intent == null) {
                intent = new Intent();
            }
            if (mActivity == null) {
                mFragment.startActivity(intent.setClass(mFragment.getContext(), activityClass));
            } else {
                mActivity.startActivity(intent.setClass(mActivity, activityClass));
            }
        }

        public void startForResult(@NonNull Class<? extends AppCompatActivity> activityClass) {
            startForResult(activityClass, null);
        }

        public void startForResult(@NonNull Class<? extends AppCompatActivity> activityClass, Intent intent) {
            if (intent == null) {
                intent = new Intent();
            }
            intent.setClass(mActivity == null ? mFragment.getContext() : mActivity, activityClass);

            int requestCode = new Random().nextInt(0x0000FFFF);
            FragmentManager fragmentManager = getFragmentManager();
            ResultFragment resultFragment = findResultFragment(fragmentManager);
            if (resultFragment != null) {
                resultFragment
                        .addRequest(requestCode, intent, mResultListener)
                        .start();
                return;
            }
            resultFragment = ResultFragment.newInstance().addRequest(requestCode, intent, mResultListener);
            fragmentManager.beginTransaction().add(resultFragment, mTag).commitAllowingStateLoss();
        }

        private FragmentManager getFragmentManager() {
            if (mActivity == null) {
                return mFragment.getChildFragmentManager();
            } else {
                return mActivity.getSupportFragmentManager();
            }
        }

        private ResultFragment findResultFragment(FragmentManager fragmentManager) {
            if (fragmentManager.isDestroyed()) {
                throw new IllegalStateException("Can't access ResultFragment from onDestroy");
            }

            Fragment fragmentByTag = fragmentManager.findFragmentByTag(mTag);
            if (fragmentByTag != null && !(fragmentByTag instanceof ResultFragment)) {
                throw new IllegalStateException("Unexpected "
                        + "fragment instance was returned by " + mTag);
            }
            return (ResultFragment) fragmentByTag;
        }

    }

    public static class ResultFragment extends Fragment {

        private int mCurrentRequestCode;
        private Intent mIntent;
        private ResultListener mResultListener;

        static ResultFragment newInstance() {
            return new ResultFragment();
        }

        ResultFragment addRequest(int requestCode, Intent intent, ResultListener listener) {
            mCurrentRequestCode = requestCode;
            mIntent = intent;
            mResultListener = listener;
            return this;
        }

        void start() {
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

}
