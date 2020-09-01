package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class cancelarActivity extends AppCompatActivity {

    private EditText comentarioCancel;
    private Button btnCancelar ;
     int mensaje ;
     String res ;
    private DxApi dxApi;
    private String user , password , idUsuario2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar);

        mensaje = getIntent().getIntExtra("mensaje",0);
        idUsuario2 = getIntent().getStringExtra("idUsuario");

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        comentarioCancel = (EditText) findViewById(R.id.comentarioCancel);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String comen  = String.valueOf(comentarioCancel.getText());

                if(comen.isEmpty()){
                    Toast.makeText(getBaseContext(),"Nada escrito en motivo de cancelacion",Toast.LENGTH_SHORT).show();
                }else {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.4.87:80/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);

                    user = preferences.getString("user","");
                    password = preferences.getString("pass","");

                    String fechaHora = "";
                    int tipoOperacion= 0 ;
                    int idUsuario = 0;
                    int idTransportista= 0 ;
                    int idOperador = 0;
                    int idUnidad = 0;
                    int idRemolque = 0;
                    int idLinea = 0;
                    int estatusRemolque = 0;
                    String comentario = "";
                    String folio = "";


                    Post4 post4 = new Post4(user,password,fechaHora,tipoOperacion,idUsuario,idTransportista,idOperador,idUnidad,idRemolque,idLinea,estatusRemolque,comentario,folio,comen,mensaje);

                    Call<List<CEnvio>> callEnvio = dxApi.getEnvio(post4);

                    Toast.makeText(getBaseContext(),"Cancelado",Toast.LENGTH_SHORT).show();


                    callEnvio.enqueue(new Callback<List<CEnvio>>() {
                        @Override
                        public void onResponse(Call<List<CEnvio>> call, Response<List<CEnvio>> response) {

                            List<CEnvio> cEnvios = response.body();

                            res = cEnvios.get(0).getMensaje();

                            if (mensaje == 0){
                                Toast.makeText(cancelarActivity.this, "Error 202", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getBaseContext(),"Enviado Correctamente\nRedirigiendo...",Toast.LENGTH_SHORT).show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        // Actions to do after 10 seconds
                                        Intent i = new Intent(cancelarActivity.this, envioActivity.class);
                                        i.putExtra("idUsuario", idUsuario2);
                                        startActivity(i);
                                    }
                                }, 5000);
                            }



                        }

                        @Override
                        public void onFailure(Call<List<CEnvio>> call, Throwable t) {

                            Toast.makeText(getBaseContext(),"Eror404",Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
