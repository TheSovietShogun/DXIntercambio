package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class etapa4_Activity extends AppCompatActivity {

    private CheckBox amortiguadorFrontal , bolsaFrontal , matracaFrontal , muelleFrontal , rotachamberFrontal
                    ,amortiguadorTrasera , bolsaTrasera , matracaTrasera , muelleTrasera , rotachamberTrasera, 
            eje1Birlos , llantaP1 , llantaP2 , eje1MasasYoyo, eje1Rin , eje1Lodera,
            eje2Birlos , llantaP5 , llantaP6 , eje2MasasYoyo, eje2Rin , eje2Lodera , jumbo;
    private Boolean check_amortiguadorFrontal = true ,
            check_bolsaFrontal = true,
            check_matracaFrontal = true,
            check_muelleFrontal = true,
            check_rotachamberFrontal = true,
            check_amortiguadorTrasera = true,
            check_bolsaTrasera = true,
            check_matracaTrasera = true,
            check_muelleTrasera = true,
            check_rotachamberTrasera = true,
            check_eje1Birlos = true, check_llantaP1 = true, check_llantaP2 = true, check_eje1MasasYoyo= true, check_eje1Rin= true , check_eje1Lodera= true,
            check_eje2Birlos = true, check_llantaP5= true , check_llantaP6 = true, check_eje2MasasYoyo= true, check_eje2Rin = true, check_eje2Lodera = true, check_jumbo =  false ;
    private String path ,string_amortiguadorFrontal , string_bolsaFrontal , string_matracaFrontal , string_muelleFrontal , string_rotachamberFrontal
            ,string_amortiguadorTrasera , string_bolsaTrasera , string_matracaTrasera , string_muelleTrasera , string_rotachamberTrasera,
            string_eje1Birlos , string_llantaP1 , string_llantaP2 , string_eje1MasasYoyo, string_eje1Rin , string_eje1Lodera,
            string_eje2Birlos , string_llantaP5 , string_llantaP6 , string_eje2MasasYoyo, string_eje2Rin , string_eje2Lodera , string_jumbo , string_comentarios;
    private ImageView IV_chasisIzqFrontal , IV_llantaEje1 , IV_llantaEje2 ,IV_chasisIzqTrasero , daño1,daño2,daño3,daño4;
    private Bitmap actual_chasisIzqFrontal , actual_llantaEje1 , actual_llantaEje2 ,actual_chasisIzqTrasero,actual_daño1,actual_daño2,actual_daño3,actual_daño4;
    private Button btnEtapa4 ;
    public static final int REQUEST_CHASIS_IZQ_FRONTAL = 100;
    public static final int REQUEST_LLANTAEJE1 = 101;
    public static final int REQUEST_LLANTAEJE2 = 102;
    public static final int REQUEST_CHASIS_IZQ_TRASERO = 103;
    public static final int REQUEST_DAÑO1 = 104;
    public static final int REQUEST_DAÑO2 = 105;
    public static final int REQUEST_DAÑO3 = 106;
    public static final int REQUEST_DAÑO4 = 107;
    private File imageFile;
    private int widthScreen;
    private Uri photoURI;
    private String  imageFileName , folio;
    private EditText comentarios;
    private Spinner llantaP1Marca , llantaP1Estatus, llantaP2Marca , llantaP2Estatus, llantaP5Marca , llantaP5Estatus,llantaP6Marca , llantaP6Estatus;
    private String [] estatusLlanta = new String[]{"Sin Seleccionar","Recapeada","Original"};
    private final int THUMBSIZE = 128;
    private String usuario;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa4);

        folio = getIntent().getStringExtra("folio");
        path = getIntent().getStringExtra("path");
        usuario = getIntent().getStringExtra("idUsuario");

        amortiguadorFrontal = (CheckBox) findViewById(R.id.CB_amortigadorIzqFrontal);
        bolsaFrontal = (CheckBox) findViewById(R.id.CB_bolsaIzqFrontal);
        matracaFrontal = (CheckBox) findViewById(R.id.CB_matracaIzqFrontal);
        muelleFrontal = (CheckBox) findViewById(R.id.CB_muelleIzqFrontal);
        rotachamberFrontal = (CheckBox) findViewById(R.id.CB_rotachamberIzqFrontal);
        amortiguadorTrasera = (CheckBox) findViewById(R.id.CB_amortigadorIzqTrasero);
        bolsaTrasera = (CheckBox) findViewById(R.id.CB_bolsaDerFrontal);
        matracaTrasera = (CheckBox) findViewById(R.id.CB_matracaIzqTasera);
        muelleTrasera = (CheckBox) findViewById(R.id.CB_muelleIzqTrasera2);
        rotachamberTrasera = (CheckBox) findViewById(R.id.CB_rotachamberIzqTrasera);
        eje1Birlos = (CheckBox) findViewById(R.id.CB_birlosEje1Izq);
        llantaP1 = (CheckBox) findViewById(R.id.CB_llantaP1);
        llantaP2 = (CheckBox) findViewById(R.id.CB_llantaP2);
        eje1MasasYoyo = (CheckBox) findViewById(R.id.CB_masaEje1Izq);
        eje1Rin = (CheckBox) findViewById(R.id.CB_rinesEje1izq);
        eje1Lodera = (CheckBox) findViewById(R.id.CB_loderaEje1Izq);
        eje2Birlos = (CheckBox) findViewById(R.id.CB_birlosEje2Izq);
        llantaP5 = (CheckBox) findViewById(R.id.CB_llantaP5);
        llantaP6 = (CheckBox) findViewById(R.id.CB_llantaP6);
        eje2MasasYoyo = (CheckBox) findViewById(R.id.CB_masaEje2Izq);
        eje2Rin = (CheckBox) findViewById(R.id.CB_rinesEje2Izq);
        eje2Lodera = (CheckBox) findViewById(R.id.CB_loderaEje2Izq);
        jumbo = (CheckBox) findViewById(R.id.CB_jumboIzq);

        IV_chasisIzqFrontal = (ImageView) findViewById(R.id.IV_chasisIzqFrontal);
        IV_llantaEje1 = (ImageView) findViewById(R.id.IV_llantaIzqEje1);
        IV_llantaEje2 = (ImageView) findViewById(R.id.IV_llantaIzqEje2);
        IV_chasisIzqTrasero = (ImageView) findViewById(R.id.IV_chasisIzqTrasero);
        daño1 = (ImageView) findViewById(R.id.IV_dañoIzq1);
        daño2 = (ImageView) findViewById(R.id.IV_dañoIzq2);
        daño3 = (ImageView) findViewById(R.id.IV_dañoIzq3);
        daño4 = (ImageView) findViewById(R.id.IV_dañoIzq4);

        llantaP1Marca = (Spinner) findViewById(R.id.S_marcaP1);
        llantaP2Marca = (Spinner) findViewById(R.id.S_marcaP2);
        llantaP5Marca = (Spinner) findViewById(R.id.S_marcaP5);
        llantaP6Marca = (Spinner) findViewById(R.id.S_marcaP6);

        llantaP1Estatus = (Spinner) findViewById(R.id.S_estatusP1);
        llantaP2Estatus = (Spinner) findViewById(R.id.S_estatusP2);
        llantaP5Estatus = (Spinner) findViewById(R.id.S_estatusP5);
        llantaP6Estatus = (Spinner) findViewById(R.id.S_estatusP6);

        comentarios = (EditText) findViewById(R.id.editTextTextMultiLine2);
        
        btnEtapa4 =  (Button) findViewById(R.id.btn_etapa4);

        widthScreen = getApplicationContext().getResources().getDisplayMetrics().widthPixels;

        DataBaseHelper dataBaseHelper = new DataBaseHelper(etapa4_Activity.this);

        List<CLlanta> llantaSelect = dataBaseHelper.selectLlanta();
        CLlanta cLlanta00 = new CLlanta("-1", "Sin Seleccionar");
        llantaSelect.add(0, cLlanta00);

        if (widthScreen > 480) {
            ArrayAdapter<CLlanta> adapter1 = new ArrayAdapter<CLlanta>(etapa4_Activity.this, R.layout.mspinner_item, llantaSelect);
            llantaP1Marca.setAdapter(adapter1);
            llantaP2Marca.setAdapter(adapter1);
            llantaP5Marca.setAdapter(adapter1);
            llantaP6Marca.setAdapter(adapter1);

            ArrayAdapter<String> adapter0 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
            llantaP1Estatus.setAdapter(adapter0);
            llantaP2Estatus.setAdapter(adapter0);
            llantaP5Estatus.setAdapter(adapter0);
            llantaP6Estatus.setAdapter(adapter0);
        } else {
            ArrayAdapter<CLlanta> adapter1 = new ArrayAdapter<CLlanta>(etapa4_Activity.this, R.layout.mspinner_item2, llantaSelect);
            llantaP1Marca.setAdapter(adapter1);
            llantaP2Marca.setAdapter(adapter1);
            llantaP5Marca.setAdapter(adapter1);
            llantaP6Marca.setAdapter(adapter1);

            ArrayAdapter<String> adapter0 = new ArrayAdapter<>(this, R.layout.mspinner_item2, estatusLlanta);
            llantaP1Estatus.setAdapter(adapter0);
            llantaP2Estatus.setAdapter(adapter0);
            llantaP5Estatus.setAdapter(adapter0);
            llantaP6Estatus.setAdapter(adapter0);
        }

        amortiguadorFrontal.setOnClickListener(view -> {

            check_amortiguadorFrontal = ((CheckBox) view).isChecked();

        });
        bolsaFrontal.setOnClickListener(view -> {

            check_bolsaFrontal = ((CheckBox) view).isChecked();

        });
        matracaFrontal.setOnClickListener(view -> {

            check_matracaFrontal = ((CheckBox) view).isChecked();

        });
        muelleFrontal.setOnClickListener(view -> {

            check_muelleFrontal = ((CheckBox) view).isChecked();

        });
        rotachamberFrontal.setOnClickListener(view -> {

            check_rotachamberFrontal = ((CheckBox) view).isChecked();

        });
        amortiguadorTrasera.setOnClickListener(view -> {

            check_amortiguadorTrasera = ((CheckBox) view).isChecked();

        });
        bolsaTrasera.setOnClickListener(view -> {

            check_bolsaTrasera = ((CheckBox) view).isChecked();

        });
        matracaTrasera.setOnClickListener(view -> {

            check_matracaTrasera = ((CheckBox) view).isChecked();

        });
        muelleTrasera.setOnClickListener(view -> {

            check_muelleTrasera = ((CheckBox) view).isChecked();

        });
        rotachamberTrasera.setOnClickListener(view -> {

            check_rotachamberTrasera = ((CheckBox) view).isChecked();

        });
        eje1Birlos.setOnClickListener(view -> {

            check_eje1Birlos = ((CheckBox) view).isChecked();

        });
        llantaP1.setOnClickListener(view -> {

            check_llantaP1 = ((CheckBox) view).isChecked();

        });
        llantaP2.setOnClickListener(view -> {

            check_llantaP2 = ((CheckBox) view).isChecked();

        });
        eje1MasasYoyo.setOnClickListener(view -> {

            check_eje1MasasYoyo = ((CheckBox) view).isChecked();

        });
        eje1Rin.setOnClickListener(view -> {

            check_eje1Rin = ((CheckBox) view).isChecked();

        });
        eje1Lodera.setOnClickListener(view -> {

            check_eje1Lodera = ((CheckBox) view).isChecked();

        });
        eje2Birlos.setOnClickListener(view -> {

            check_eje2Birlos = ((CheckBox) view).isChecked();

        });
        llantaP5.setOnClickListener(view -> {

            check_llantaP5 = ((CheckBox) view).isChecked();

        });
        llantaP6.setOnClickListener(view -> {

            check_llantaP6 = ((CheckBox) view).isChecked();

        });
        eje2MasasYoyo.setOnClickListener(view -> {

            check_eje2MasasYoyo= ((CheckBox) view).isChecked();

        });
        eje2Rin.setOnClickListener(view -> {

            check_eje2Rin = ((CheckBox) view).isChecked();

        });
        eje2Lodera.setOnClickListener(view -> {

            check_eje2Lodera = ((CheckBox) view).isChecked();

        });
        jumbo.setOnClickListener(view -> {

            check_jumbo = ((CheckBox) view).isChecked();

            if(check_jumbo){

                llantaP2Marca.setVisibility(View.INVISIBLE);
                llantaP6Marca.setVisibility(View.INVISIBLE);
                llantaP2Estatus.setVisibility(View.INVISIBLE);
                llantaP6Estatus.setVisibility(View.INVISIBLE);

                string_jumbo = "1";
            }else{
                llantaP2Marca.setVisibility(View.VISIBLE);
                llantaP6Marca.setVisibility(View.VISIBLE);
                llantaP2Estatus.setVisibility(View.VISIBLE);
                llantaP6Estatus.setVisibility(View.VISIBLE);

                string_jumbo = "0";
            }

        });

        IV_chasisIzqFrontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IV_chasisIzqFrontal.setEnabled(false);
                IV_chasisIzqFrontal.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IV_chasisIzqFrontal.setEnabled(true);
                        IV_chasisIzqFrontal.setClickable(true);

                    }
                }, TIME);
                imgClick("chasisIzqFrontal", REQUEST_CHASIS_IZQ_FRONTAL);

            }
        });

        IV_llantaEje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IV_llantaEje1.setEnabled(false);
                IV_llantaEje1.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IV_llantaEje1.setEnabled(true);
                        IV_llantaEje1.setClickable(true);

                    }
                }, TIME);
                imgClick("llantaIzqEje1", REQUEST_LLANTAEJE1);

            }
        });
        IV_llantaEje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IV_llantaEje2.setEnabled(false);
                IV_llantaEje2.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IV_llantaEje2.setEnabled(true);
                        IV_llantaEje2.setClickable(true);

                    }
                }, TIME);
                imgClick("llantaIzqEje2", REQUEST_LLANTAEJE2);

            }
        });

        IV_chasisIzqTrasero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IV_chasisIzqTrasero.setEnabled(false);
                IV_chasisIzqTrasero.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IV_chasisIzqTrasero.setEnabled(true);
                        IV_chasisIzqTrasero.setClickable(true);

                    }
                }, TIME);
                imgClick("chasisIzqTrasero", REQUEST_CHASIS_IZQ_TRASERO);

            }
        });

        daño1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daño1.setEnabled(false);
                daño1.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        daño1.setEnabled(true);
                        daño1.setClickable(true);

                    }
                }, TIME);
                imgClick("IzqDaño1", REQUEST_DAÑO1);

            }
        });

        daño2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daño2.setEnabled(false);
                daño2.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        daño2.setEnabled(true);
                        daño2.setClickable(true);

                    }
                }, TIME);
                imgClick("IzqDaño2", REQUEST_DAÑO2);

            }
        });

        daño3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daño3.setEnabled(false);
                daño3.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        daño3.setEnabled(true);
                        daño3.setClickable(true);

                    }
                }, TIME);
                imgClick("IzqDaño3", REQUEST_DAÑO3);

            }
        });

        daño4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daño4.setEnabled(false);
                daño4.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        daño4.setEnabled(true);
                        daño4.setClickable(true);

                    }
                }, TIME);
                imgClick("IzqDaño4", REQUEST_DAÑO4);

            }
        });


        btnEtapa4.setOnClickListener(v -> {


            btnEtapa4.setEnabled(false);
            btnEtapa4.setClickable(false);
            btnEtapa4.setText("Enviando...");
            btnEtapa4.setTextColor(Color.parseColor("#074EAB"));
            btnEtapa4.setBackgroundResource(R.drawable.round_btn2);

            int TIME = 10000;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    btnEtapa4.setEnabled(true);
                    btnEtapa4.setClickable(true);
                    btnEtapa4.setText("Siguiente");
                    btnEtapa4.setTextColor(Color.parseColor("#FFFFFF"));
                    btnEtapa4.setBackgroundResource(R.drawable.round_btn);

                }
            }, TIME);

            CLlanta cLlanta = (CLlanta) llantaP1Marca.getSelectedItem();
            String idLlantaP1 = cLlanta.getId();

            CLlanta cLlanta1 = (CLlanta) llantaP1Marca.getSelectedItem();
            String idLlantaP2 = cLlanta1.getId();

            CLlanta cLlanta2 = (CLlanta) llantaP1Marca.getSelectedItem();
            String idLlantaP5 = cLlanta2.getId();

            CLlanta cLlanta3 = (CLlanta) llantaP1Marca.getSelectedItem();
            String idLlantaP6 = cLlanta3.getId();


            String llantaEstatusP1 = llantaP1Estatus.getSelectedItem().toString();
            String llantaEstatusP2 = llantaP2Estatus.getSelectedItem().toString();
            String llantaEstatusP5 = llantaP5Estatus.getSelectedItem().toString();
            String llantaEstatusP6 = llantaP6Estatus.getSelectedItem().toString();

            String P1 = "";
            String P2 = "";
            String P5 = "";
            String P6 = "";

            switch (llantaEstatusP1){
                case "Sin Seleccionar":
                    llantaEstatusP1 = "3";
                    break;
                case "Recapeada":
                    llantaEstatusP1 = "1";
                    break;
                case "Original":
                    llantaEstatusP1 = "0";
                    break;

            }

            switch (llantaEstatusP2){
                case "Sin Seleccionar":
                    llantaEstatusP2 = "3";
                    break;
                case "Recapeada":
                    llantaEstatusP2 = "1";
                    break;
                case "Original":
                    llantaEstatusP2 = "0";
                    break;

            }

            switch (llantaEstatusP5){
                case "Sin Seleccionar":
                    llantaEstatusP5 = "3";
                    break;
                case "Recapeada":
                    llantaEstatusP5 = "1";
                    break;
                case "Original":
                    llantaEstatusP5 = "0";
                    break;

            }

            switch (llantaEstatusP6){
                case "Sin Seleccionar":
                    llantaEstatusP6 = "3";
                    break;
                case "Recapeada":
                    llantaEstatusP6 = "1";
                    break;
                case "Original":
                    llantaEstatusP6 = "0";
                    break;

            }


            String observacioens  = comentarios.getText().toString();

            if (check_amortiguadorFrontal) {
                string_amortiguadorFrontal = "1";
            }else{
                string_amortiguadorFrontal = "0";
            }
            if (check_bolsaFrontal) {
                string_bolsaFrontal = "1";
            }else{
                string_bolsaFrontal = "0";
            }
            if (check_matracaFrontal) {
                string_matracaFrontal = "1";
            }else{
                string_matracaFrontal = "0";
            }
            if (check_muelleFrontal) {
                string_muelleFrontal = "1";
            }else{
                string_muelleFrontal = "0";
            }
            if (check_rotachamberFrontal) {
                string_rotachamberFrontal = "1";
            }else{
                string_rotachamberFrontal = "0";
            }
            if (check_amortiguadorTrasera) {
                string_amortiguadorTrasera = "1";
            }else{
                string_amortiguadorTrasera = "0";
            }
            if (check_bolsaTrasera) {
                string_bolsaTrasera = "1";
            }else{
                string_bolsaTrasera = "0";
            }
            if (check_matracaTrasera) {
                string_matracaTrasera = "1";
            }else{
                string_matracaTrasera = "0";
            }
            if (check_muelleTrasera) {
                string_muelleTrasera = "1";
            }else{
                string_muelleTrasera = "0";
            }
            if (check_rotachamberTrasera) {
                string_rotachamberTrasera = "1";
            }else{
                string_rotachamberTrasera = "0";
            }
            if (check_eje1Birlos) {
                string_eje1Birlos = "1";
            }else{
                string_eje1Birlos = "0";
            }
            if (check_llantaP1) {
                string_llantaP1 = "1";
            }else{
                string_llantaP1 = "0";
            }
            if (check_llantaP2) {
                string_llantaP2 = "1";
            }else{
                string_llantaP2 = "0";
            }
            if (check_eje1MasasYoyo) {
                string_eje1MasasYoyo = "1";
            }else{
                string_eje1MasasYoyo = "0";
            }
            if (check_eje1Rin) {
                string_eje1Rin = "1";
            }else{
                string_eje1Rin = "0";
            }
            if (check_eje1Lodera) {
                string_eje1Lodera = "1";
            }else{
                string_eje1Lodera = "0";
            }
            if (check_eje2Birlos) {
                string_eje2Birlos = "1";
            }else{
                string_eje2Birlos = "0";
            }
            if (check_llantaP5) {
                string_llantaP5 = "1";
            }else{
                string_llantaP5 = "0";
            }
            if (check_llantaP6) {
                string_llantaP6 = "1";
            }else{
                string_llantaP6 = "0";
            }
            if (check_eje2MasasYoyo) {
                string_eje2MasasYoyo = "1";
            }else{
                string_eje2MasasYoyo = "0";
            }
            if (check_eje2Rin) {
                string_eje2Rin = "1";
            }else{
                string_eje2Rin = "0";
            }
            if (check_eje2Lodera) {
                string_eje2Lodera = "1";
            }else{
                string_eje2Lodera = "0";
            }

            if (check_jumbo) {
                string_jumbo = "1";
            }else{
                string_jumbo = "0";
            }

                String D1 = null ;
                String D2 = null ;
                String D3 = null ;
                String D4 = null ;

            if(actual_daño1 != null){
                D1 = "remolqueIzqDano1FotoUrl-"+folio;
            }

            if(actual_daño2 != null){
                D2 = "remolqueIzqDano2FotoUrl-"+folio;
            }

            if(actual_daño3 != null){
                D3 = "remolqueIzqDano3FotoUrl-"+folio;
            }

            if(actual_daño4 != null){
                D4 = "remolqueIzqDano4FotoUrl-"+folio;
            }


            if(string_jumbo.contains("1")){

                if(actual_chasisIzqFrontal == null || actual_chasisIzqTrasero == null ||
                        idLlantaP1== "-1" || idLlantaP5== "-1" ||
                        llantaEstatusP1 == "3" || llantaEstatusP5 == "3") {
                    Toast.makeText(etapa4_Activity.this, "Datos Incompletos", Toast.LENGTH_LONG).show();
                } else {

                    DataBaseHelper dataBaseHelper2 = new DataBaseHelper(etapa4_Activity.this);

                    long insertIntercambio1 = dataBaseHelper2.insertIntercambioElectronico4(
                            "remolqueLlantaIzqEje1FotoUrl-"+folio,
                            "remolqueLlantaIzqEje2FotoUrl-"+folio,
                            "remolqueChasisFrontalIzqFotoUrl-"+folio,
                            "remolqueChasisTraseroIzqFotoUrl-"+folio,
                            D1,
                            D2,
                            D3,
                            D4,
                            "5",folio,string_jumbo,
                            idLlantaP1,
                            llantaEstatusP1,
                            "",
                            "",

                            string_eje1Birlos,
                            string_llantaP1,
                            string_llantaP2,
                            string_eje1MasasYoyo,
                            string_eje1Rin,
                            string_eje1Lodera,

                            idLlantaP5,
                            llantaEstatusP5,
                            "",
                            "",

                            string_eje2Birlos,
                            string_llantaP5,
                            string_llantaP6,
                            string_eje2MasasYoyo,
                            string_eje2Rin,
                            string_eje2Lodera,

                            string_amortiguadorFrontal,
                            string_bolsaFrontal,
                            string_matracaFrontal,
                            string_muelleFrontal,
                            string_rotachamberFrontal,

                            string_amortiguadorTrasera,
                            string_bolsaTrasera,
                            string_matracaTrasera,
                            string_muelleTrasera,
                            string_rotachamberTrasera,

                            observacioens);

                    if(insertIntercambio1 == -1){
                        Toast.makeText(etapa4_Activity.this, "Error insertIntercambio4", Toast.LENGTH_LONG).show();
                    }else {
                        if(createDirectoryAndSaveFile( actual_chasisIzqFrontal,  "remolqueChasisFrontalIzqFotoUrl-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_chasisIzqTrasero,  "remolqueChasisTraseroIzqFotoUrl-"+folio+".jpg", path)
                        )
                        {
                            Intent i = new Intent(etapa4_Activity.this, etapa5_Activity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("folio", folio);
                            i.putExtra("path", path);
                            i.putExtra("idUsuario", usuario);
                            startActivity(i);
                        }else{
                            Toast.makeText(etapa4_Activity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }else {

                if (actual_chasisIzqFrontal == null || actual_chasisIzqTrasero == null
                        || idLlantaP1== "-1" || idLlantaP2== "-1" || idLlantaP5== "-1" || idLlantaP6== "-1"
                        || llantaEstatusP1 == "3" || llantaEstatusP2== "3" || llantaEstatusP5== "3" || llantaEstatusP6 == "3" ) {
                    Toast.makeText(etapa4_Activity.this, "Datos Incompletos", Toast.LENGTH_LONG).show();
                } else {

                    DataBaseHelper dataBaseHelper2 = new DataBaseHelper(etapa4_Activity.this);

                    long insertIntercambio1 = dataBaseHelper2.insertIntercambioElectronico4(
                            "remolqueLlantaIzqEje1FotoUrl-"+folio,
                            "remolqueLlantaIzqEje2FotoUrl-"+folio,
                            "remolqueChasisFrontalIzqFotoUrl-"+folio,
                            "remolqueChasisTraseroIzqFotoUrl-"+folio,
                            D1,
                            D2,
                            D3,
                            D4,
                            "5", folio, string_jumbo,
                            idLlantaP1,
                            llantaEstatusP1,
                            idLlantaP2,
                            llantaEstatusP2,

                            string_eje1Birlos,
                            string_llantaP1,
                            string_llantaP2,
                            string_eje1MasasYoyo,
                            string_eje1Rin,
                            string_eje1Lodera,

                            idLlantaP5,
                            llantaEstatusP5,
                            idLlantaP6,
                            llantaEstatusP6,

                            string_eje2Birlos,
                            string_llantaP5,
                            string_llantaP6,
                            string_eje2MasasYoyo,
                            string_eje2Rin,
                            string_eje2Lodera,

                            string_amortiguadorFrontal,
                            string_bolsaFrontal,
                            string_matracaFrontal,
                            string_muelleFrontal,
                            string_rotachamberFrontal,

                            string_amortiguadorTrasera,
                            string_bolsaTrasera,
                            string_matracaTrasera,
                            string_muelleTrasera,
                            string_rotachamberTrasera,

                            observacioens);

                    if (insertIntercambio1 == -1) {
                        Toast.makeText(etapa4_Activity.this, "Error insertIntercambio4", Toast.LENGTH_LONG).show();
                    } else {
                        if (createDirectoryAndSaveFile(actual_chasisIzqFrontal, "remolqueChasisFrontalIzqFotoUrl-" + folio + ".jpg", path) &&
                                createDirectoryAndSaveFile(actual_chasisIzqTrasero, "remolqueChasisTraseroIzqFotoUrl-" + folio + ".jpg", path)
                        ) {

                            if(actual_daño1 != null){
                                createDirectoryAndSaveFile(actual_daño1, "remolqueIzqDano1FotoUrl-" + folio + ".jpg", path);
                            }

                            if(actual_daño2 != null){
                                createDirectoryAndSaveFile(actual_daño2, "remolqueIzqDano2FotoUrl-" + folio + ".jpg", path);
                            }

                            if(actual_daño3 != null){
                                createDirectoryAndSaveFile(actual_daño3, "remolqueIzqDano3FotoUrl-" + folio + ".jpg", path);
                            }

                            if(actual_daño4 != null){
                                createDirectoryAndSaveFile(actual_daño4, "remolqueIzqDano4FotoUrl-" + folio + ".jpg", path);
                            }


                            Intent i = new Intent(etapa4_Activity.this, etapa5_Activity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("folio", folio);
                            i.putExtra("path", path);
                            i.putExtra("idUsuario", usuario);
                            startActivity(i);

                        } else {
                            Toast.makeText(etapa4_Activity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }

    private void imgClick(String photo, int code) {

        File destPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        imageFileName = null;
        imageFile = null;
        photoURI = null;
        imageFileName = photo + "-Folio" + folio;

        try {
            imageFile = File.createTempFile(
                    imageFileName,  // prefix
                    ".jpeg",         // suffix
                    destPath      // directory
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoURI = FileProvider.getUriForFile(getBaseContext(), getBaseContext().getApplicationContext().getPackageName() + ".provider", imageFile);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(camera, code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        switch (requestCode){
            case REQUEST_CHASIS_IZQ_FRONTAL:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_chasisIzqFrontal = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_chasisIzqFrontal,
                                THUMBSIZE,
                                THUMBSIZE);
                        IV_chasisIzqFrontal.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        IV_chasisIzqFrontal.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_LLANTAEJE1:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_llantaEje1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_llantaEje1,
                                THUMBSIZE,
                                THUMBSIZE);
                        IV_llantaEje1.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        IV_llantaEje1.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_LLANTAEJE2:
                if (resultCode == RESULT_OK) {
                    try {

                        actual_llantaEje2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_llantaEje2,
                                THUMBSIZE,
                                THUMBSIZE);
                        IV_llantaEje2.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        IV_llantaEje2.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_CHASIS_IZQ_TRASERO:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_chasisIzqTrasero = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_chasisIzqTrasero,
                                THUMBSIZE,
                                THUMBSIZE);
                        IV_chasisIzqTrasero.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        IV_chasisIzqTrasero.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO1:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_daño1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_daño1,
                                THUMBSIZE,
                                THUMBSIZE);
                        daño1.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        daño1.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO2:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_daño2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_daño2,
                                THUMBSIZE,
                                THUMBSIZE);
                        daño2.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        daño2.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO3:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_daño3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_daño3,
                                THUMBSIZE,
                                THUMBSIZE);
                        daño3.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        daño3.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO4:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_daño4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_daño4,
                                THUMBSIZE,
                                THUMBSIZE);
                        daño4.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        daño4.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, resultData);
    }

    private boolean createDirectoryAndSaveFile(Bitmap imageToSave, String fileName, String path) {

        File file = new File(path, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        IV_chasisIzqFrontal.setImageBitmap(null);
        IV_llantaEje1.setImageBitmap(null);
        IV_llantaEje2.setImageBitmap(null);
        IV_chasisIzqTrasero.setImageBitmap(null);
        daño1.setImageBitmap(null);
        daño2.setImageBitmap(null);
        daño3.setImageBitmap(null);
        daño4.setImageBitmap(null);
        actual_chasisIzqFrontal = null;
        actual_llantaEje1 = null;
        actual_llantaEje2 = null;
        actual_chasisIzqTrasero = null;
        actual_daño1 = null;
        actual_daño2 = null;
        actual_daño3 = null;
        actual_daño4 = null;

        photoURI = null;
        imageFile = null;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}