package com.example.fernandoalfaro.holamundo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonCamera;
    private final int PHONE_CALL_CODE = 100;


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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersions(numero);
                    }
                }
            }

            private void OlderVersions(String numero) {
                Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));

                if (revisarPermisos(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentLlamada);
                } else {
                    Toast.makeText(Main2Activity.this, "Declinaste el permiso", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {

                    if (result == PackageManager.PERMISSION_GRANTED) {
                        String numero = editTextPhone.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)return;
                        startActivity(intent);
                    } else{
                        Toast.makeText(this, "Declino el acceso", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean revisarPermisos(String permission){
        int resultado = this.checkCallingOrSelfPermission(permission);
        return resultado == PackageManager.PERMISSION_GRANTED;
    }
}
