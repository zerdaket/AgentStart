package com.zerdaket.agentstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zerdaket.agent.AgentStart;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button firstBtn = findViewById(R.id.btn_first);
        Button secondBtn = findViewById(R.id.btn_second);
        resultTv = findViewById(R.id.tv_result);

        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class)
                        .putExtra("From", "From: MainActivity FirstButton");
                AgentStart.with(MainActivity.this)
                        .setResultListener(new AgentStart.ResultListener() {
                            @Override
                            public void onResult(int resultCode, Intent data) {
                                if (resultCode != RESULT_OK) return;
                                resultTv.setText(data.getStringExtra("Result"));
                            }
                        })
                        .startForResult(intent);
            }
        });

        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class)
                        .putExtra("From", "From: MainActivity SecondButton");
                AgentStart.with(MainActivity.this)
                        .setResultListener(new AgentStart.ResultListener() {
                            @Override
                            public void onResult(int resultCode, Intent data) {
                                if (resultCode != RESULT_OK) return;
                                resultTv.setText(data.getStringExtra("Result"));
                            }
                        })
                        .startForResult(intent);
            }
        });

        if (savedInstanceState == null) {
            MainFragment fragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment, "MainFragment")
                    .commit();
        }
    }

}
