package com.zerdaket.agent.result;

import android.content.Intent;

/**
 * Created by zerdaket on 2019-11-26.
 */
public interface ResultListener {
    void onResult(int resultCode, Intent data);
}
