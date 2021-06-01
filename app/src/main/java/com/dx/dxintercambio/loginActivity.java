package com.dx.dxintercambio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class loginActivity extends AppCompatActivity {

    private EditText usu, pass ;
    private Button ingre;
    private String login,password;
    public static final int REQUEST_PERMISSION = 420 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usu = (EditText) findViewById(R.id.usuario);
        pass = (EditText) findViewById(R.id.contraseña);
        ingre = (Button) findViewById(R.id.btnIngresar);


        try{
        ingre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login = usu.getText().toString();
                password = pass.getText().toString();

                //the game
                if (login.length() == 0 && password.length() == 0) {
                    Toast.makeText(loginActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                } else if (login.length() == 0) {
                    Toast.makeText(loginActivity.this, "Debes Ingresar un Usuario", Toast.LENGTH_SHORT).show();
                } else if (password.length() == 0) {
                    Toast.makeText(loginActivity.this, "Debes Ingresar una Contraseña ", Toast.LENGTH_SHORT).show();
                } else if (login.length() != 0 && password.length() != 0) {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(loginActivity.this);
                    List<CUsuarioRel> loginDB = dataBaseHelper.login(login,password);

                    if (loginDB == null || loginDB.isEmpty()) {

                        Toast.makeText(loginActivity.this, "Usuario Incorrecto o Base de Datos Local No Sincronizada" , Toast.LENGTH_LONG).show();

                    }else{

                        String idUsuario = loginDB.get(0).getId();

                        Intent i = new Intent(loginActivity.this, etapa1_Activity.class);
                        i.putExtra("idUsuario", idUsuario);
                        startActivity(i);

                    }
                }
                }
                });

            }catch(Exception e){
            Toast.makeText(loginActivity.this, "Error : "+  e , Toast.LENGTH_LONG).show();
                }
    }

    @Override
    protected void onStart() {

        permission();

        super.onStart();
    }

    @AfterPermissionGranted(REQUEST_PERMISSION)
    public void permission() {
        String[] perms ;

        if (android.os.Build.VERSION.SDK_INT >= 29){
            perms = new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.VIBRATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_NETWORK_STATE
            };
        } else{
            perms = new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.VIBRATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
        }

        if(EasyPermissions.hasPermissions(this, perms)) {
            Toast.makeText(this, "Permisos Aceptados", Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(this, "App DX Intercambios no cuenta con permisos", REQUEST_PERMISSION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);

    }






}