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

import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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

                    SegundoPlano tarea = new SegundoPlano();
                    tarea.execute();
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

    ///////////////////////////////////////////////////////////////

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



    private class SegundoPlano extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {

                //Respuesta de la llamada , es un json
                tran = resultString.toString();


                //Si el usuario no existe el servicio regresara el string {"CUsuario":[{}] }
                if (tran.length() <= 15) {
                    Toast.makeText(MainActivity.this, "Cuenta Equivocada", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("user", login);
                    editor.putString("pass", password);
                    editor.commit();

                    //Libreria gson se utliza para traducir de json a string y viceversa
                    Gson gson = new Gson();

                    //Retira la palabra usuario y los corchetes para el uso de la libreria gson
                    String reusu = tran.replace("{\"Usuario\":[{", "{");
                    String reusu2 = reusu.replace("}] }", "}");

                    //Se usa la libreria para traducir el json , el string obtenido se almacena en la libreria CUsuario
                    CUsuario Usuario1 = gson.fromJson(reusu2, CUsuario.class);

                    String nombreOperador = Usuario1.getNombreOperador();
                    String idEmpresa = Usuario1.getIdEmpresa();
                    String idUsuario = Usuario1.getIdUsuario();
                    String nombreUsuario = Usuario1.getNombreUsuario();
                    String idUnidad = Usuario1.getIdUnidad();
                    String claveUnidad = Usuario1.getClaveUnidad();
                    String idOperador = Usuario1.getIdOperador();
                    String idFlota = Usuario1.getIdFlota();
                    String idViaje = Usuario1.getIdViaje();

                    //Se llama a la siguiente actividad y se envian variables
                    Intent i = new Intent(MainActivity.this, envioActivity.class);
                    i.putExtra("nombreOperador", nombreOperador);
                    i.putExtra("nombreUsuario", nombreUsuario);
                    i.putExtra("idUnidad", idUnidad);
                    i.putExtra("idUsuario", idUsuario);
                    i.putExtra("idOperador", idOperador);
                    i.putExtra("idViaje", idViaje);
                    startActivity(i);

                }
            }catch (Exception ex){
                Toast.makeText(MainActivity.this, "Error 404", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String SOAP_ACTION = "http://dxxpress.net/wsInspeccion/Version_20171221_1212";
            String METHOD_NAME = "Login";
            String NAMESPACE  = "http://dxxpress.net/wsInspeccion/";
            String URL = "http://dxxpress.net/wsInspeccion/interfaceOperadores3.asmx";


            try{

                SoapObject Request = new SoapObject(NAMESPACE,METHOD_NAME);
                Request.addProperty("login", login);
                Request.addProperty("password", password);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);

                HttpTransportSE transport= new HttpTransportSE(URL);
                transport.call(SOAP_ACTION, soapEnvelope);


                resultString = (SoapPrimitive) soapEnvelope.getResponse();


                mensaje= "OK";

            }catch (Exception ex){

                mensaje = "ERROR: " +ex.getMessage();
            }
            return null;
        }
    }


}