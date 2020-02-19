package com.example.dxintercambio;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class envioActivity extends AppCompatActivity {

    private EditText registradoPor  , unidad , noRemolque , comentarios;
    private Spinner tipoOperacion ,transportista, linea, estatus ;
    private Button enviar;
    private String tran;
    private SoapPrimitive resultString;
    private GridView gridView;
    private String [] tipoOpeArr ;
    private String [] tranportistasArr ;
    private String [] lineaArr ;
    private String [] estatusArr ;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    private String usuario ;
    private DxApi dxApi;
    private String user , password ;
    private AutoCompleteTextView operador ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_envio);


        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);

        user = preferences.getString("user","");
        password = preferences.getString("pass","");


        usuario = getIntent().getStringExtra("idUsuario");

        tipoOperacion = (Spinner) findViewById(R.id.spinner2);
        registradoPor = (EditText)findViewById(R.id.editText8);
        transportista = (Spinner) findViewById(R.id.spinner);
        operador = (AutoCompleteTextView)findViewById(R.id.spinner69);
        unidad = (EditText)findViewById(R.id.editText7);
        noRemolque = (EditText)findViewById(R.id.editText11);
        linea = (Spinner) findViewById(R.id.spinner10);
        estatus = (Spinner) findViewById(R.id.spinner3);
        comentarios = (EditText)findViewById(R.id.editText12);
        enviar = (Button) findViewById(R.id.button);

        registradoPor.setText(usuario);
        registradoPor.setEnabled(false);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(envioActivity.this, imgActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dxxpress.net/API/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dxApi = retrofit.create(DxApi.class);

        Post post = new Post(user,password);


        Call<List<CLinea>> call = dxApi.getLinea(post);

        call.enqueue(new Callback<List<CLinea>>() {
            @Override
            public void onResponse(Call<List<CLinea>> call, Response<List<CLinea>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(envioActivity.this, "Error 404L", Toast.LENGTH_LONG).show();
                }

                List<CLinea> cLineas = response.body();
                ArrayAdapter<CLinea> adapter = new ArrayAdapter<CLinea>(envioActivity.this ,android.R.layout.simple_spinner_item, cLineas);
                adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                linea.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CLinea>> call, Throwable t) {
                Toast.makeText(envioActivity.this, "Error 404L", Toast.LENGTH_LONG).show();
            }
        });



        Call<List<COperador>> callOpe = dxApi.getOperador(post);

        callOpe.enqueue(new Callback<List<COperador>>() {
            @Override
            public void onResponse(Call<List<COperador>> call, Response<List<COperador>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(envioActivity.this, "Error 404O", Toast.LENGTH_LONG).show();
                }

                List<COperador> cOperadors = response.body();
                ArrayAdapter<COperador> adapter2 = new ArrayAdapter<COperador>(envioActivity.this ,android.R.layout.simple_spinner_item, cOperadors);
                adapter2.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                operador.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<List<COperador>> call, Throwable t) {
                Toast.makeText(envioActivity.this, "Error 404O", Toast.LENGTH_LONG).show();
            }
        });


        Call<List<CFlota>> callFlota = dxApi.getFlota(post);

        callFlota.enqueue(new Callback<List<CFlota>>() {
            @Override
            public void onResponse(Call<List<CFlota>> call, Response<List<CFlota>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(envioActivity.this, "Error 404F", Toast.LENGTH_LONG).show();
                }

                List<CFlota> cFlotas = response.body();
                ArrayAdapter<CFlota> adapter3 = new ArrayAdapter<CFlota>(envioActivity.this ,android.R.layout.simple_spinner_item, cFlotas);
                adapter3.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                transportista.setAdapter(adapter3);
            }

            @Override
            public void onFailure(Call<List<CFlota>> call, Throwable t) {
                Toast.makeText(envioActivity.this, "Error 404F", Toast.LENGTH_LONG).show();
            }
        });

    }



    @Override
    public void onBackPressed() {

    }
}