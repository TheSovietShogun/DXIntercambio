package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class incidenciaActivity extends AppCompatActivity {

    private String A_sello1 ;
    private String A_sello2 ;
    private String A_llanta1 ;
    private String A_llanta2 ;
    private String A_llanta3 ;
    private String A_llanta4 ;
    private String A_llanta5 ;
    private String A_llanta6 ;
    private String A_llanta7 ;
    private String A_llanta8 ;
    private String A_llantajumbo ;
    private String A_selloExtra ;
    private String A_sello3;

    private String P_sello1 ;
    private String P_sello2 ;
    private String P_llanta1 ;
    private String P_llanta2;
    private String P_llanta3 ;
    private String P_llanta4;
    private String P_llanta5 ;
    private String P_llanta6 ;
    private String P_llanta7 ;
    private String P_llanta8 ;
    private String P_llantajumbo ;
    private String P_selloExtra ;
    private String P_sello3 ;
    private String incidencia;

    private TextView TWAsello1 ;
    private TextView TWAsello2 ;
    private TextView TWAllanta1 ;
    private TextView TWAllanta2 ;
    private TextView TWAllanta3 ;
    private TextView TWAllanta4 ;
    private TextView TWAllanta5 ;
    private TextView TWAllanta6 ;
    private TextView TWAllanta7 ;
    private TextView TWAllanta8 ;
    private TextView TWAllantasjumbo ;
    private TextView TWAselloExtra ;
    private TextView TWAsello3 ;
    private TextView TWAfecha ;
    private TextView TWAusuario ;
    private TextView TWApatio ;
    private TextView TWAtransportista ;
    private TextView TWAunidad ;
    private TextView TWAoperador ;
    private TextView TWAtipoMovimiento ;
    private TextView TWAtipoOperacion ;
    private TextView TWAestatusRemolque ;
    private TextView TWAcomentario ;


    private TextView TWPsello1 ;
    private TextView TWPsello2 ;
    private TextView TWPllanta1 ;
    private TextView TWPllanta2 ;
    private TextView TWPllanta3 ;
    private TextView TWPllanta4 ;
    private TextView TWPllanta5 ;
    private TextView TWPllanta6 ;
    private TextView TWPllanta7 ;
    private TextView TWPllanta8 ;
    private TextView TWPllantasjumbo ;
    private TextView TWPselloExtra ;
    private TextView TWPsello3 ;
    private TextView TWPfecha ;
    private TextView TWPusuario ;
    private TextView TWPpatio ;
    private TextView TWPtransportista ;
    private TextView TWPunidad ;
    private TextView TWPoperador ;
    private TextView TWPtipoMovimiento ;
    private TextView TWPtipoOperacion ;
    private TextView TWPestatusRemolque ;
    private TextView TWPcomentario ;

    private Button btnCancelar ;
    private Button btnConfirmar ;

    private EditText comen ;

    private String user ;
    private String comentario ;
    private String password ;
    private DxApi dxApi;
    private String folio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia);

        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);



        user = preferences.getString("user","");
        password = preferences.getString("pass","");


        String A_sello1= getIntent().getStringExtra("A_sello1");
        String A_sello2 = getIntent().getStringExtra("A_sello2");
        String A_llanta1 = getIntent().getStringExtra("A_llanta1");
        String A_llanta2 = getIntent().getStringExtra("A_llanta2");
        String A_llanta3 = getIntent().getStringExtra("A_llanta3");
        String A_llanta4 = getIntent().getStringExtra("A_llanta4");
        String A_llanta5 = getIntent().getStringExtra("A_llanta5");
        String A_llanta6 = getIntent().getStringExtra("A_llanta6");
        String A_llanta7 = getIntent().getStringExtra("A_llanta7");
        String A_llanta8 = getIntent().getStringExtra("A_llanta8");
        String A_llantajumbo = getIntent().getStringExtra("A_llantajumbo");
        String A_selloExtra = getIntent().getStringExtra("A_selloExtra");
        String A_sello3= getIntent().getStringExtra("A_sello3");
         String A_tipoOperacion = getIntent().getStringExtra("A_tipoOperacion");
         String A_estatusRemolque = getIntent().getStringExtra("A_estatusRemolque");
         String A_tipoMovimiento = getIntent().getStringExtra("A_tipoMovimiento");
         String A_comentario = getIntent().getStringExtra("A_comentario");
         String A_patio = getIntent().getStringExtra("A_patio");
         String A_fechaServidor = getIntent().getStringExtra("A_fechaServidor");
         String A_operador = getIntent().getStringExtra("A_operador");
         String A_unidad = getIntent().getStringExtra("A_unidad");
         String A_transportista = getIntent().getStringExtra("A_transportista");
         String A_linea = getIntent().getStringExtra("A_linea");
         String A_remolque = getIntent().getStringExtra("A_remolque");
         String A_usuario = getIntent().getStringExtra("A_usuario");


        String P_sello1 = getIntent().getStringExtra("P_sello1");
        String P_sello2 = getIntent().getStringExtra("P_sello2");
        String P_llanta1 = getIntent().getStringExtra("P_llanta1");
        String P_llanta2= getIntent().getStringExtra("P_llanta2");
        String P_llanta3= getIntent().getStringExtra("P_llanta3");
        String P_llanta4= getIntent().getStringExtra("P_llanta4");
        String P_llanta5 = getIntent().getStringExtra("P_llanta5");
        String P_llanta6 = getIntent().getStringExtra("P_llanta6");
        String P_llanta7 = getIntent().getStringExtra("P_llanta7");
        String P_llanta8 = getIntent().getStringExtra("P_llanta8");
        String P_llantajumbo = getIntent().getStringExtra("P_llantajumbo");
        String P_selloExtra = getIntent().getStringExtra("P_selloExtra");
        String P_sello3 = getIntent().getStringExtra("P_sello3");
        String P_tipoOperacion = getIntent().getStringExtra("P_tipoOperacion");
        String P_estatusRemolque = getIntent().getStringExtra("P_estatusRemolque");
        String P_tipoMovimiento = getIntent().getStringExtra("P_tipoMovimiento");
        String P_comentario = getIntent().getStringExtra("P_comentario");
        String P_patio = getIntent().getStringExtra("P_patio");
        String P_fechaServidor = getIntent().getStringExtra("P_fechaServidor");
        String P_operador = getIntent().getStringExtra("P_operador");
        String P_unidad = getIntent().getStringExtra("P_unidad");
        String P_transportista = getIntent().getStringExtra("P_transportista");
        String P_linea = getIntent().getStringExtra("P_linea");
        String P_remolque = getIntent().getStringExtra("P_remolque");
        String P_usuario = getIntent().getStringExtra("P_usuario");

        folio = getIntent().getStringExtra("folio");


        int idIntercambio = getIntent().getIntExtra("idIntercambio",0);


        if(P_llantajumbo.contains("1")){
            P_llantajumbo = "Si";
        }else if (P_llantajumbo.contains("0")){
            P_llantajumbo = "No";
        }

        if(A_llantajumbo.contains("1")){
            A_llantajumbo = "Si";
        }else if (A_llantajumbo.contains("0")){
            A_llantajumbo = "No";
        }


        if(P_selloExtra.contains("1")){
            P_selloExtra = "Si";
        }else if (P_selloExtra.contains("0")){
            P_selloExtra = "No";
        }

        if(A_selloExtra.contains("1")){
            A_selloExtra = "Si";
        }else if (A_selloExtra.contains("0")){
            A_selloExtra = "No";
        }

        TWAsello1 = (TextView) findViewById(R.id.sello1_Actual);
        TWAsello2 = (TextView) findViewById(R.id.sello2_Actual);
        TWAllanta1 = (TextView) findViewById(R.id.llanta1_Actual);
        TWAllanta2 = (TextView) findViewById(R.id.llanta2_Actual);
        TWAllanta3 = (TextView) findViewById(R.id.llanta3_Actual);
        TWAllanta4 = (TextView) findViewById(R.id.llanta4_Actual);
        TWAllanta5 = (TextView) findViewById(R.id.llanta5_Actual);
        TWAllanta6 = (TextView) findViewById(R.id.llanta6_Actual);
        TWAllanta7 = (TextView) findViewById(R.id.llanta7_Actual);
        TWAllanta8 = (TextView) findViewById(R.id.llanta8_Actual);
        TWAllantasjumbo= (TextView) findViewById(R.id.llantasJumbo_Actual);
        TWAselloExtra= (TextView) findViewById(R.id.selloExtra_Actual);
        TWAsello3 = (TextView) findViewById(R.id.selloExtra3_Actual);
        TWAfecha  = (TextView) findViewById(R.id.fecha_Actual);
        TWAusuario  = (TextView) findViewById(R.id.usuario_Actual);
        TWApatio  = (TextView) findViewById(R.id.patio_Actual);
        TWAtransportista  = (TextView) findViewById(R.id.transportista_Actual);
        TWAunidad  = (TextView) findViewById(R.id.unidad_Actual);
        TWAoperador  = (TextView) findViewById(R.id.operador_Actual);
        TWAtipoMovimiento  = (TextView) findViewById(R.id.tipoMovimiento_Actual);
        TWAtipoOperacion  = (TextView) findViewById(R.id.tipoOperacion_Actual);
        TWAestatusRemolque  = (TextView) findViewById(R.id.estatusRemolque_Actual);
        TWAcomentario  = (TextView) findViewById(R.id.comentario_Actual);

        btnConfirmar = (Button) findViewById(R.id.button);
        btnCancelar = (Button) findViewById(R.id.button3);

        TWPsello1 = (TextView) findViewById(R.id.sello1_Pasado);
        TWPsello2 = (TextView) findViewById(R.id.sello2_Pasado);
        TWPllanta1 = (TextView) findViewById(R.id.llanta1_Pasado);
        TWPllanta2 = (TextView) findViewById(R.id.llanta2_Pasado);
         TWPllanta3 = (TextView) findViewById(R.id.llanta3_Pasado);
         TWPllanta4 = (TextView) findViewById(R.id.llanta4_Pasado);
        TWPllanta5 = (TextView) findViewById(R.id.llanta5_Pasado);
        TWPllanta6 = (TextView) findViewById(R.id.llanta6_Pasado);
        TWPllanta7 = (TextView) findViewById(R.id.llanta7_Pasado);
        TWPllanta8= (TextView) findViewById(R.id.llanta8_Pasado) ;
        TWPllantasjumbo= (TextView) findViewById(R.id.llantasJumbo_Pasado) ;
        TWPselloExtra = (TextView) findViewById(R.id.selloExtra_Pasado);
         TWPsello3 = (TextView) findViewById(R.id.selloExtra3_Pasado);
        TWPfecha  = (TextView) findViewById(R.id.fecha_Pasado);
        TWPusuario  = (TextView) findViewById(R.id.usuario_Pasado);
        TWPpatio  = (TextView) findViewById(R.id.patio_Pasado);
        TWPtransportista  = (TextView) findViewById(R.id.transportista_Pasado);
        TWPunidad  = (TextView) findViewById(R.id.unidad_Pasado);
        TWPoperador  = (TextView) findViewById(R.id.operador_Pasado);
        TWPtipoMovimiento  = (TextView) findViewById(R.id.tipoMovimiento_Pasado);
        TWPtipoOperacion  = (TextView) findViewById(R.id.tipoOperacion_Pasado);
        TWPestatusRemolque  = (TextView) findViewById(R.id.estatusRemolque_Pasado);
        TWPcomentario  = (TextView) findViewById(R.id.comentario_Pasado);

        comen = (EditText) findViewById(R.id.editText3);

        TWAsello1.setText(A_sello1);
        TWAsello2.setText(A_sello2);
        TWAllanta1.setText(A_llanta1);
        TWAllanta2.setText(A_llanta2);
        TWAllanta3.setText(A_llanta3);
        TWAllanta4.setText(A_llanta4);
        TWAllanta5.setText(A_llanta5);
        TWAllanta6.setText(A_llanta6);
        TWAllanta7.setText(A_llanta7);
        TWAllanta8.setText(A_llanta8);
        TWAllantasjumbo.setText(A_llantajumbo);
        TWAselloExtra.setText( A_selloExtra);
        TWAsello3.setText(A_sello3);
        TWAfecha.setText(A_fechaServidor);
        TWAusuario.setText(A_usuario);
        TWApatio.setText(A_patio);
        TWAtransportista.setText(A_transportista);
        TWAunidad.setText(A_unidad);
        TWAoperador.setText(A_operador);
        TWAtipoMovimiento.setText(A_tipoMovimiento);
        TWAtipoOperacion.setText(A_tipoOperacion);
        TWAestatusRemolque.setText(A_estatusRemolque);
        TWAcomentario.setText(A_comentario);


        TWPsello1.setText(P_sello1);
        TWPsello2.setText(P_sello2);
        TWPllanta1.setText(P_llanta1);
        TWPllanta2.setText(P_llanta2);
        TWPllanta3.setText( P_llanta3);
        TWPllanta4.setText(P_llanta4);
        TWPllanta5.setText(P_llanta5);
        TWPllanta6.setText(P_llanta6);
        TWPllanta7.setText(P_llanta7);
        TWPllanta8.setText(P_llanta8);
        TWPllantasjumbo.setText(P_llantajumbo);
        TWPselloExtra.setText(P_selloExtra);
        TWPsello3.setText(P_sello3);
        TWPfecha.setText(P_fechaServidor);
        TWPusuario.setText(P_usuario);
        TWPpatio.setText(P_patio);
        TWPtransportista.setText(P_transportista);
        TWPunidad.setText(P_unidad);
        TWPoperador.setText(P_operador);
        TWPtipoMovimiento.setText(P_tipoMovimiento);
        TWPtipoOperacion.setText(P_tipoOperacion);
        TWPestatusRemolque.setText(P_estatusRemolque);
        TWPcomentario.setText(P_comentario);



        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comentario = comen.getText().toString();

                if(comentario.length()>0){


                    Post6 post6 = new Post6(user, password, 0, "1", idIntercambio, "", "", "",
                            "", "", "", "", "", "", "", 0, 0, 0, "", 3, comentario);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.4.87:80/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    Call<List<CEnvio2>> callInci = dxApi.getEnvio2(post6);

                    callInci.enqueue(new Callback<List<CEnvio2>>() {
                        @Override
                        public void onResponse(Call<List<CEnvio2>> call, Response<List<CEnvio2>> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(incidenciaActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(incidenciaActivity.this, "Incidencia Cancelada", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(incidenciaActivity.this, firmasActivity.class);
                            i.putExtra("folio", folio);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<List<CEnvio2>> call, Throwable t) {
                            Toast.makeText(incidenciaActivity.this, "Error 404 ", Toast.LENGTH_LONG).show();
                        }
                    });

                }else {
                    Toast.makeText(incidenciaActivity.this, "Comentario Vacio", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comentario = comen.getText().toString();

                if(comentario.length() >0){
                    Post6 post6 = new Post6(user, password, 0, "1", idIntercambio, "", "", "",
                            "", "", "", "", "", "", "", 0, 0, 0, "", 1, comentario);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.4.87:80/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    Call<List<CEnvio2>> callInci = dxApi.getEnvio2(post6);

                    callInci.enqueue(new Callback<List<CEnvio2>>() {
                        @Override
                        public void onResponse(Call<List<CEnvio2>> call, Response<List<CEnvio2>> response) {

                            if(!response.isSuccessful()){
                                Toast.makeText(incidenciaActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(incidenciaActivity.this, "Intercambio Confirmado", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(incidenciaActivity.this, splash.class);
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(Call<List<CEnvio2>> call, Throwable t) {
                            Toast.makeText(incidenciaActivity.this, "Error 404 ", Toast.LENGTH_LONG).show();
                        }
                    });
                }else {
                    Toast.makeText(incidenciaActivity.this, "Comentario Vacio", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    @Override
    public void onBackPressed() {

    }
}
