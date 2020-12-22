package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class firmasActivity extends AppCompatActivity {


    SignatureView signatureView;
    SignatureView signatureView2;
    ImageView imgClear;
    ImageView imgClear2;
    Button btnEnvio ;
    Bitmap bitmap1;
    Bitmap bitmap2;
    DxApi dxApi;
    String folio ,id;
    int mensaje;
    private String user , password ;

    Bitmap emptyBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firmas);

        signatureView =  (SignatureView) findViewById(R.id.signature_view);
        imgClear = (ImageView) findViewById(R.id.imageView32);


        signatureView2 =  (SignatureView) findViewById(R.id.signature_view2);
        imgClear2 = (ImageView) findViewById(R.id.imageView31);

        btnEnvio = (Button) findViewById(R.id.button2);

        folio = getIntent().getStringExtra("folio");
        mensaje = getIntent().getIntExtra("mensaje",0);
        id = String.valueOf(mensaje);


        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);

        user = preferences.getString("user","");
        password = preferences.getString("pass","");


        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signatureView2.clearCanvas();
            }
        });

        imgClear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signatureView.clearCanvas();
            }
        });

        btnEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bitmap1 = signatureView2.getSignatureBitmap();
                bitmap2 = signatureView.getSignatureBitmap();


                if (signatureView.isBitmapEmpty() || signatureView2.isBitmapEmpty()) {
                    Toast.makeText(firmasActivity.this, "Falta firma", Toast.LENGTH_LONG).show();
                }else  {


                    String nombreFirma = "firmaOperador-"+folio;
                    String nombreFirma2 = "firmaInter-"+folio;



                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
                    String encodedImage = Base64.encodeToString(bytes.toByteArray(), Base64.NO_WRAP);

                    ByteArrayOutputStream bytes2 = new ByteArrayOutputStream();
                    bitmap2.compress(Bitmap.CompressFormat.JPEG, 70, bytes2);
                    String encodedImage2 = Base64.encodeToString(bytes2.toByteArray(), Base64.NO_WRAP);


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.5.50/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    Post7 post7 = new Post7(user,password,nombreFirma,encodedImage,folio,nombreFirma2,encodedImage2);
                    Call<String> callImg2 = dxApi.getImg2(post7);

                    callImg2.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(firmasActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                            }

                            String cEnvios = String.valueOf(response);

                            if(cEnvios.contains("200")){
                                Toast.makeText(getBaseContext(),"Enviado",Toast.LENGTH_SHORT).show();

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://192.168.5.50/api/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                dxApi = retrofit.create(DxApi.class);

                                Post8 post8 = new Post8(user,password,id);
                                Call<List<CEnvio>> callTerminado = dxApi.getTerminado(post8);

                                callTerminado.enqueue(new Callback<List<CEnvio>>() {
                                    @Override
                                    public void onResponse(Call<List<CEnvio>> call, Response<List<CEnvio>> response) {
                                        if (!response.isSuccessful()) {
                                            Toast.makeText(firmasActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                                        }else {

                                            List<CEnvio> cEnvios = response.body();

                                            String mensaje = cEnvios.get(0).getMensaje();

                                            if (mensaje.contains("Enviado con exito")){

                                                Intent i = new Intent(firmasActivity.this, splash.class);
                                                startActivity(i);

                                            }else{
                                                Toast.makeText(firmasActivity.this, "Error al Enviar", Toast.LENGTH_LONG).show();
                                            }

                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<List<CEnvio>> call, Throwable t) {
                                        Toast.makeText(firmasActivity.this, "Error 404U", Toast.LENGTH_LONG).show();
                                    }
                                });

                            }else {
                                Toast.makeText(getBaseContext(),"Error Al Enviar",Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(firmasActivity.this, "Error 404U", Toast.LENGTH_LONG).show();
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
