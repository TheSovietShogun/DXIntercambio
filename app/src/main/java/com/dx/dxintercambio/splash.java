package com.dx.dxintercambio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.ksoap2.serialization.SoapPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class splash extends Activity {

    private String usuario , contraseña , idUsuario , login , password ,mensaje ;
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
                            .baseUrl("http://192.168.4.87:80/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    Post post = new Post(login,password);

                    Call<List<CUsuario>> call = dxApi.createPost(post);

                    call.enqueue(new Callback<List<CUsuario>>() {
                        @Override
                        public void onResponse(Call<List<CUsuario>> call, Response<List<CUsuario>> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(splash.this, "Error 500" + response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            List<CUsuario> cUsuarios = response.body();

                            String idUsuario = cUsuarios.get(0).getIdUsuario();
                            String res = cUsuarios.get(0).getRespuesta();

                            if (res.contains("1")){
                                Intent i = new Intent(splash.this, envioActivity.class);
                                i.putExtra("idUsuario", idUsuario);
                                startActivity(i);
                            } else {
                                Toast.makeText(splash.this, "Usuario Incorrecto" , Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<CUsuario>> call, Throwable t) {
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
