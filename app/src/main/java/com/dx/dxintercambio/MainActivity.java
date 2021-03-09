package com.dx.dxintercambio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

    private EditText usu, pass , Hcontra;
    private Button ingre;
    private String login;
    private String password;
    private String mensaje;
    private String tran;
    private SoapPrimitive resultString;
    private int PERMISSION_ALL = 1;
    private String ip ;
    String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };


    private DxApi dxApi;
    private AlertDialog alert;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.hide){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();

            View view = inflater.inflate(R.layout.custom_alert,null);

            builder.setView(view)
                    .setTitle("Configuracion de Sistemas")
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            String contraCulera = Hcontra.getText().toString();

                            if (contraCulera.contains("74859990")){

                                Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(MainActivity.this, sistemas.class);
                                i.putExtra("mode", "0");
                                startActivity(i);

                            }else if (contraCulera.contains("mañananoaiclases42069")){

                                Toast.makeText(MainActivity.this, "Monkey Flip", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(MainActivity.this, sistemas.class);
                                i.putExtra("mode", "420");
                                startActivity(i);

                            } else {
                                Toast.makeText(MainActivity.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                            }
                    }
                });

            AlertDialog alert = builder.create();
            alert.show();

            Hcontra = view.findViewById(R.id.hcontra);


        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);
        ip = preferences.getString("Aip","");

        usu = (EditText) findViewById(R.id.usuario);
        pass = (EditText) findViewById(R.id.contraseña);
        ingre = (Button) findViewById(R.id.btnIngresar);


        //Toast.makeText(MainActivity.this, "OnCreate", Toast.LENGTH_SHORT).show();

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


                //Filtros del login the game
                if (login.length() == 0 && password.length() == 0) {
                    Toast.makeText(MainActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                } else if (login.length() == 0) {
                    Toast.makeText(MainActivity.this, "Debes Ingresar un Usuario", Toast.LENGTH_SHORT).show();
                } else if (password.length() == 0) {
                    Toast.makeText(MainActivity.this, "Debes Ingresar una Contraseña ", Toast.LENGTH_SHORT).show();
                } else if (login.length() != 0 && password.length() != 0) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://"+ip+"/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    Post post = new Post(login, password);

                    Call<List<CUsuario>> call = dxApi.createPost(post);
                    call.enqueue(new Callback<List<CUsuario>>() {
                        @Override
                        public void onResponse(Call<List<CUsuario>> call, Response<List<CUsuario>> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Error 500" , Toast.LENGTH_SHORT).show();
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

                            String prueba = t.getMessage();

                            Toast.makeText(MainActivity.this, "Error 404  " + prueba, Toast.LENGTH_LONG).show();

                        }
                    });

                }

            }
        });

    }catch(Exception e){
    Toast.makeText(MainActivity.this, "Error : "+  e , Toast.LENGTH_LONG).show();
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