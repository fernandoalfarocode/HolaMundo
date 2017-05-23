package com.example.fernandoalfaro.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button btncompartir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.vertexto);

            Bundle bundle = getIntent().getExtras();
            String textual = bundle.getString("1");
            Toast.makeText(this, textual, Toast.LENGTH_SHORT).show();
            textView.setText(textual);

        btncompartir = (Button) findViewById(R.id.button3);
        btncompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        }
    }
