package com.example.dxintercambio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

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

public class splash extends Activity {

    private String usuario , contraseña , tran , login , password ,mensaje ;
    private SoapPrimitive resultString;
    private DxApi dxApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);

                usuario = preferences.getString("user","");
                contraseña = preferences.getString("pass","");


                if (usuario != "" && contraseña != "" ){

                    login = usuario;
                    password = contraseña;

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
                                Toast.makeText(splash.this, "onResponse1" + response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }

                            ResponseBody postResponse = response.body();

                            String E = "";
                            try {
                                E += postResponse.string();
                                if (E.contains("1")){
                                    Intent i = new Intent(splash.this, envioActivity.class);
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(splash.this, "Usuario Incorrecto" , Toast.LENGTH_SHORT).show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(splash.this, "Error al intentar conectar\nReedirigiendo...." , Toast.LENGTH_LONG).show();
                                Intent i = new Intent(splash.this, MainActivity.class);
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                           // Toast.makeText(splash.this, "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(splash.this, "Error 404" , Toast.LENGTH_LONG).show();
                            Intent i = new Intent(splash.this, MainActivity.class);
                            startActivity(i);
                        }
                    });

                    }else if (usuario == "" && contraseña == "" ){


                    Intent i = new Intent(splash.this, MainActivity.class);
                    startActivity(i);

                } else {

                    Intent i = new Intent(splash.this, MainActivity.class);
                    startActivity(i);
                }

            }
        },2000);

        }catch (Exception e ){

            Intent i = new Intent(splash.this, MainActivity.class);
            startActivity(i);
        }
    }

}
