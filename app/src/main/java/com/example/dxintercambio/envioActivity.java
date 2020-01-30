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

public class envioActivity extends AppCompatActivity {

    private EditText registradoPor ,operador , unidad , noRemolque , comentarios;
    private Spinner tipoOperacion ,transportista, linea, estatus;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_envio);


        usuario = getIntent().getStringExtra("idUsuario");

        tipoOperacion = (Spinner) findViewById(R.id.spinner2);
        registradoPor = (EditText)findViewById(R.id.editText8);
        transportista = (Spinner) findViewById(R.id.spinner);
        operador = (EditText)findViewById(R.id.editText9);
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

       try {

        tipoOpeArr = new String[]{"Llegada", "Salida"};
        tranportistasArr = new String[]{"DX"};
        lineaArr = new String[]{"Otro"};
        estatusArr = new String[]{"Cargado"};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoOpeArr);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            tipoOperacion.setAdapter(adapter);

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tranportistasArr);
            adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            transportista.setAdapter(adapter1);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lineaArr);
            adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            linea.setAdapter(adapter2);

            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estatusArr);
            adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            estatus.setAdapter(adapter3);



        }catch (Exception e){
            Toast.makeText(envioActivity.this, "Error 500AD", Toast.LENGTH_LONG).show();
        }





    }



    @Override
    public void onBackPressed() {

    }






}