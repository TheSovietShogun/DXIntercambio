package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;

public class etapa6_Activity extends AppCompatActivity {

    private CheckBox amortiguadorFrontal , bolsaFrontal , matracaFrontal , muelleFrontal , rotachamberFrontal
            ,amortiguadorTrasera , bolsaTrasera , matracaTrasera , muelleTrasera , rotachamberTrasera,
            eje1Birlos , llantaP7 , llantaP8 , eje1MasasYoyo, eje1Rin , eje1Lodera,
            eje2Birlos , llantaP3 , llantaP4 , eje2MasasYoyo, eje2Rin , eje2Lodera , jumbo;
    private Boolean check_amortiguadorFrontal , check_bolsaFrontal , check_matracaFrontal , check_muelleFrontal , check_rotachamberFrontal
            ,check_amortiguadorTrasera , check_bolsaTrasera , check_matracaTrasera , check_muelleTrasera , check_rotachamberTrasera,
            check_eje1Birlos , check_llantaP7 , check_llantaP8 , check_eje1MasasYoyo, check_eje1Rin , check_eje1Lodera,
            check_eje2Birlos , check_llantaP3 , check_llantaP4 , check_eje2MasasYoyo, check_eje2Rin , check_eje2Lodera , check_jumbo;
    private String path ,string_amortiguadorFrontal , string_bolsaFrontal , string_matracaFrontal , string_muelleFrontal , string_rotachamberFrontal
            ,string_amortiguadorTrasera , string_bolsaTrasera , string_matracaTrasera , string_muelleTrasera , string_rotachamberTrasera,
            string_eje1Birlos , string_llantaP7 , string_llantaP8 , string_eje1MasasYoyo, string_eje1Rin , string_eje1Lodera,
            string_eje2Birlos , string_llantaP3 , string_llantaP4 , string_eje2MasasYoyo, string_eje2Rin , string_eje2Lodera , string_jumbo , string_comentarios;
    private ImageView IV_chasisDerFrontal , IV_llantaEje1 , IV_llantaEje2 ,IV_chasisDerTrasero ;
    private Bitmap actual_chasisDerFrontal , actual_llantaEje1 , actual_llantaEje2 ,actual_chasisDerTrasero ;
    private Button btnEtapa6 ;
    public static final int REQUEST_CHASIS_DER_FRONTAL = 100;
    public static final int REQUEST_LLANTAEJE1 = 101;
    public static final int REQUEST_LLANTAEJE2 = 102;
    public static final int REQUEST_CHASIS_DER_TRASERO = 103;
    private File imageFile;
    private int widthScreen;
    private Uri photoURI;
    private String  imageFileName , folio;
    private Spinner llantaP3Marca , llantaP3Estatus, llantaP4Marca , llantaP4Estatus, llantaP7Marca , llantaP7Estatus,llantaP8Marca , llantaP8Estatus;
    private String [] estatusLlanta = new String[]{"Sin Seleccionar","Recapeada","Original"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa6);

     /*   folio = getIntent().getStringExtra("folio");
        path = getIntent().getStringExtra("path");

        amortiguadorFrontal = (CheckBox) findViewById(R.id.);
        bolsaFrontal = (CheckBox) findViewById(R.id.);
        matracaFrontal = (CheckBox) findViewById(R.id.);
        muelleFrontal = (CheckBox) findViewById(R.id.);
        rotachamberFrontal = (CheckBox) findViewById(R.id.);

        amortiguadorTrasera = (CheckBox) findViewById(R.id.);
        bolsaTrasera = (CheckBox) findViewById(R.id.);
        matracaTrasera = (CheckBox) findViewById(R.id.);
        muelleTrasera = (CheckBox) findViewById(R.id.);
        rotachamberTrasera = (CheckBox) findViewById(R.id.);


        eje1Birlos = (CheckBox) findViewById(R.id.);
        llantaP3 = (CheckBox) findViewById(R.id.);
        llantaP4 = (CheckBox) findViewById(R.id.);
        eje1MasasYoyo = (CheckBox) findViewById(R.id.);
        eje1Rin = (CheckBox) findViewById(R.id.);
        eje1Lodera = (CheckBox) findViewById(R.id.);


        eje2Birlos = (CheckBox) findViewById(R.id.);
        llantaP7 = (CheckBox) findViewById(R.id.);
        llantaP8 = (CheckBox) findViewById(R.id.);
        eje2MasasYoyo = (CheckBox) findViewById(R.id.);
        eje2Rin = (CheckBox) findViewById(R.id.);
        eje2Lodera = (CheckBox) findViewById(R.id.);

        jumbo = (CheckBox) findViewById(R.id.);



        IV_chasisDerFrontal = (ImageView) findViewById(R.id.);
        IV_llantaEje1 = (ImageView) findViewById(R.id.);
        IV_llantaEje2 = (ImageView) findViewById(R.id.);
        IV_chasisDerTrasero = (ImageView) findViewById(R.id.);


        llantaP3Marca = (Spinner) findViewById(R.id.);
        llantaP4Marca = (Spinner) findViewById(R.id.);
        llantaP7Marca = (Spinner) findViewById(R.id.);
        llantaP8Marca = (Spinner) findViewById(R.id.);

        llantaP3Estatus = (Spinner) findViewById(R.id.);
        llantaP4Estatus = (Spinner) findViewById(R.id.);
        llantaP7Estatus = (Spinner) findViewById(R.id.);
        llantaP8Estatus = (Spinner) findViewById(R.id.);

        btnEtapa6 =  (Button) findViewById(R.id.btn_etapa6);*/

    }
}