package com.example.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText usu, pass;
    private Button ingre;
    private String login;
    private String password;
    private String mensaje;
    private String tran;
    private SoapPrimitive resultString;
    private int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };

    private DxApi dxApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        usu = (EditText) findViewById(R.id.usuario);
        pass = (EditText)findViewById(R.id.contraseña);
        ingre = (Button)findViewById(R.id.btnIngresar);




        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
            return;
        }

        ingre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                login = usu.getText().toString();
                password = pass.getText().toString();


                //Filtros del login
                if (login.length()==0 && password.length()==0){
                    Toast.makeText(MainActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }
                else if (login.length()==0 ){
                    Toast.makeText(MainActivity.this, "Debes Ingresar un Usuario", Toast.LENGTH_SHORT).show();

                }
                else if (password.length()==0){
                    Toast.makeText(MainActivity.this, "Debes Ingresar una Contraseña ", Toast.LENGTH_SHORT).show();

                }
                else if (login.length() !=0 && password.length() !=0 ) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://dxxpress.net/API/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    Post post = new Post(login,password);

                    Call<ResponseBody> call = dxApi.createPost(post);

                  call.enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                          if(!response.isSuccessful()){
                              Toast.makeText(MainActivity.this, "onResponse1" + response.code(), Toast.LENGTH_LONG).show();
                              return;
                          }

                          //Toast.makeText(MainActivity.this, "onResponse2 : " + response.code() + "\nString : " +  response.toString() , Toast.LENGTH_SHORT).show();
                            ResponseBody postResponse = response.body();

                            String E = "";
                          try {
                              E += postResponse.string();
                              if (E.contains("1")){
                                  Intent i = new Intent(MainActivity.this, envioActivity.class);
                                  startActivity(i);
                              }
                              else {
                                  Toast.makeText(MainActivity.this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                              }
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      }

                      @Override
                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                          Toast.makeText(MainActivity.this, "Error 404", Toast.LENGTH_LONG).show();
                      }
                  });

                }

            }
        });


    }

    @Override
    protected void onStart() {

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        super.onStart();
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onBackPressed() {

    }



}