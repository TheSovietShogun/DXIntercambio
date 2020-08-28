package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.serialization.SoapPrimitive;

import java.util.List;

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
        pass = (EditText) findViewById(R.id.contraseña);
        ingre = (Button) findViewById(R.id.btnIngresar);


        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
            return;
        }
try{
        ingre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                login = usu.getText().toString();
                password = pass.getText().toString();


                //Filtros del login
                if (login.length() == 0 && password.length() == 0) {
                    Toast.makeText(MainActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                } else if (login.length() == 0) {
                    Toast.makeText(MainActivity.this, "Debes Ingresar un Usuario", Toast.LENGTH_SHORT).show();

                } else if (password.length() == 0) {
                    Toast.makeText(MainActivity.this, "Debes Ingresar una Contraseña ", Toast.LENGTH_SHORT).show();

                } else if (login.length() != 0 && password.length() != 0) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.4.87:80/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    Post post = new Post(login, password);

                    Call<List<CUsuario>> call = dxApi.createPost(post);
                    call.enqueue(new Callback<List<CUsuario>>() {
                        @Override
                        public void onResponse(Call<List<CUsuario>> call, Response<List<CUsuario>> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "onResponse1" + response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            List<CUsuario> cUsuarios = response.body();

                            String idUsuario = cUsuarios.get(0).getIdUsuario();
                            String res = cUsuarios.get(0).getRespuesta();

                            if (res.contains("1")) {

                                SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("user", login);
                                editor.putString("pass", password);
                                editor.commit();

                                Intent i = new Intent(MainActivity.this, envioActivity.class);
                                i.putExtra("idUsuario", idUsuario);
                                startActivity(i);
                            } else {
                                Toast.makeText(MainActivity.this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CUsuario>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Erro 404", Toast.LENGTH_LONG).show();

                        }
                    });

                }

            }
        });

    }catch(Exception e){
    Toast.makeText(MainActivity.this, "Erro : "+  e , Toast.LENGTH_LONG).show();
}
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