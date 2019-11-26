package com.zerdaket.agent;

import android.content.Context;

import com.zerdaket.agent.source.ActivitySource;
import com.zerdaket.agent.source.ContextSource;
import com.zerdaket.agent.source.ResultSource;
import com.zerdaket.agent.source.Source;
import com.zerdaket.agent.source.XFragmentSource;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by zerdaket on 2019-11-13.
 */
public class AgentStart {

    public static Source with(Context context) {
        return new ContextSource(context);
    }

    public static ResultSource with(FragmentActivity activity) {
        return new ActivitySource(activity);
    }

    public static ResultSource with(Fragment fragment) {
        return new XFragmentSource(fragment);
    }

}
