package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button add_button, sub_button, div_button, mult_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        add_button = (Button) findViewById(R.id.add_button);
        sub_button = (Button) findViewById(R.id.sub_button);
        div_button = (Button) findViewById(R.id.div_button);
        mult_button = (Button) findViewById(R.id.mult_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair<Long, Long> vals = GetVal();
                GoToResult(vals.first + vals.second);
            }
        });
        sub_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair<Long, Long> vals = GetVal();
                GoToResult(vals.first - vals.second);
            }
        });
        mult_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair<Long, Long> vals = GetVal();
                GoToResult(vals.first * vals.second);
            }
        });
        div_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair<Long, Long> vals = GetVal();
                GoToResult(vals.first / vals.second);
            }
        });
    }

    Pair<Long, Long> GetVal() {
        long val1 = Long.parseLong(num1.getText().toString());
        long val2 = Long.parseLong(num2.getText().toString());
        return Pair.create(val1, val2);
    }

    void GoToResult(long ans) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("RESULT", ans);
        startActivity(intent);
    }


}
