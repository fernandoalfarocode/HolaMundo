package com.example.fernandoalfaro.holamundo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imageButtonCamera = (ImageButton) findViewById(R.id.imageButtonCamara);
        imageButtonWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imageButtonPhone = (ImageButton) findViewById(R.id.imageButtonPhone);

        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = editTextPhone.getText().toString();
                if (numero != null) {
                    Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("Tel: " + numero));
                    if (ActivityCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intentLlamada);
                }
            }

        });
    }

    private boolean revisarPermisos(String permission){
        int resultado = this.checkCallingOrSelfPermission(permission);
        return resultado == PackageManager.PERMISSION_GRANTED;
    }
}
