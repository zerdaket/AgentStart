package com.zerdaket.agentstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zerdaket on 2019-11-12.
 */
public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textView = findViewById(R.id.tv_from);

        if (getIntent() != null) {
            textView.setText(getIntent().getStringExtra("From"));
        }

        Button button = findViewById(R.id.btn_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = getIntent().getStringExtra("From") + "\n" + "Result: FirstActivity";
                Intent intent = new Intent().putExtra("Result", result);
                setResult(RESULT_OK, intent);
                onBackPressed();
            }
        });
    }
}
