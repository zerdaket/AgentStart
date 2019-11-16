package com.zerdaket.agentstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zerdaket.agent.AgentStart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zerdaket on 2019-11-12.
 */
public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button firstBtn = view.findViewById(R.id.btn_fragment_first);
        Button secondBtn = view.findViewById(R.id.btn_fragment_second);
        final TextView resultTv = view.findViewById(R.id.tv_fragment_result);

        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().putExtra("From", "From: MainFragment FirstButton");
                AgentStart.with(MainFragment.this)
                        .setResultListener(new AgentStart.ResultListener() {
                            @Override
                            public void onResult(int resultCode, Intent data) {
                                if (resultCode != RESULT_OK) return;
                                resultTv.setText(data.getStringExtra("Result"));
                            }
                        })
                        .startForResult(FirstActivity.class, intent);
            }
        });

        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().putExtra("From", "From: MainFragment SecondButton");
                AgentStart.with(MainFragment.this)
                        .setResultListener(new AgentStart.ResultListener() {
                            @Override
                            public void onResult(int resultCode, Intent data) {
                                if (resultCode != RESULT_OK) return;
                                resultTv.setText(data.getStringExtra("Result"));
                            }
                        })
                        .startForResult(SecondActivity.class, intent);
            }
        });

        return view;
    }

}
