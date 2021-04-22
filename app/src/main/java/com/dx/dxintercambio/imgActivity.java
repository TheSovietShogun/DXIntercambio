package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class imgActivity extends AppCompatActivity {

    private ImageView tractor , noEconomico, izqRemolqueP1 , vin , chasisFrontalIzq , chasisTraseroIzq , llantasIzqEje1
            ,llantasIzqEje2 , izqRemolqueP2 , puertas , placas , sello1 , sello2 , derRemolqueP1 , llantasDerEje2
            , llantasDerEje1 , chasisTraseroDer , chasisFrontalDER , derRemolqueP2 , damage1 , damage2 , damage3 , damage4, sello3
            ,tractoFrente,tractoIzq,tractoDer,
            damage5 , damage6 , damage7 , damage8,
            damage9 , damage10 , damage11 , damage12 ;
    private static final int REQUEST_CODE_SIGN_IN = 1;
    private static final String TAG = "envioActivity";
    private Spinner llanta1SP, llanta2SP , llanta3SP ,llanta4SP,llanta5SP ,llanta6SP ,llanta7SP,llanta8SP;

    private EditText sello1ET ,sello2ET, sello3ET , numeroDePlaca , comentario2, comentario0 , comentario1;
    private String S_defensa = "1";
    private String S_llantas = "1";
    private String S_pisoTractor = "10";
    private String S_tanqueDiesel = "1";
    private String S_cabinaCompartimientos= "1" ;
    private String S_tanqueAire = "1";
    private String S_quintaRueda = "1";
    private String S_ejesTransmision= "1" ;
    private String S_tuboEscape = "1";
    private String S_motor = "1";
    private String S_baseRemolque = "1";
    private String S_puerta = "10";
    private String S_paredLateralDerecha = "10";
    private String S_techos = "1";
    private String S_paredFrontal= "10" ;
    private String S_paredLateralIzquierda = "10";
    private String S_pisoInterno = "1";
    private String S_vvtt= "1" ;
    private String S_IRP1_inspeccionMecanica = "1";
    private String S_IRP1_lucesCheck= "1" ;
    private String S_IRP1_luzGaliboIzqFrontalSup = "1";
    private String S_IRP1_manitas = "1";
    private String S_IRP1_manivela= "1" ;
    private String S_IRP1_patinIzq = "1";
    private String S_IRP2_cuartoLadoIzq = "1";
    private String S_IRP2_loderaIzq= "1" ;
    private String S_IRP2_lucesCheck= "1" ;
    private String S_IRP2_luzABS = "1";
    private String S_IRP2_luzBarcoIzq= "1" ;
    private String S_IRP2_rompevientosIzq = "1";
    private String S_LlIE1_birlosPivote = "1";
    private String S_LlIE1_llantasPos1 = "1";
    private String S_LlIE1_llantasPos2 = "1";
    private String S_LlIE1_masaYoyo = "1";
    private String S_LlIE1_rin = "1";
    private String S_LlIE1_tieneLodera = "1";
    private String S_Pu_bisagrasPuertas = "1";
    private String S_Pu_defensa = "1";
    private String S_Pu_luzGaliboSupTraseras = "1";
    private String S_Pu_plafonesDer = "1";
    private String S_Pu_plafonesIzq = "1";
    private String S_Pl_luzPlaca = "1";
    private String S_Pl_placa = "1";
    private String S_S1_sello1= "1" ;
    private String S_S1_altaSeguridad= "1" ;
    private String S_LlDE1_birlosPivote= "1" ;
    private String S_LlDE1_llantasPos5 = "1";
    private String S_LlDE1_llantasPos6 = "1";
    private String S_LlDE1_masaYoyo= "1" ;
    private String S_LlDE1_rin = "1";
    private String S_LlDE1_tieneLodera = "1";
    private String S_DRP1_fondoPlaga = "1";
    private String S_DRP1_pisoPlaga = "1";
    private String S_DRP1_techoPlaga = "1";
    private String S_DRP1_lucesCheck = "1";
    private String S_DRP1_luzGaliboDerFrontalSup= "1" ;
    private String S_DRP1_derPlaga = "1";
    private String S_DRP1_izqPlaga = "1";
    private String S_DRP1_patinDer = "1";
    private String S_DRP2_cuartoLadoDer = "1";
    private String S_DRP2_loderaDer = "1";
    private String S_DRP2_lucesCheck = "1";
    private String S_DRP2_luzBarcoDer = "1";
    private String S_DRP2_rompevientosDer= "1" ;
    private String S_S2_escotilla = "1";
    private String S_S2_sello2= "1" ;
    private String S_S2_altaSeguridad = "1";
    private String S_LlIE2_birlosPivote = "1";
    private String S_LlIE2_llantasPos3 = "1";
    private String S_LlIE2_llantasPos4 = "1";
    private String S_LlIE2_masaYoyo = "1";
    private String S_LlIE2_rin = "1";
    private String S_LlIE2_tieneLodera= "1" ;
    private String S_LlDE2_birlosPivote = "1";
    private String S_LlDE2_llantasPos7 = "1";
    private String S_LlDE2_llantasPos8 = "1";
    private String S_LlDE2_masaYoyo = "1";
    private String S_LlDE2_rin = "1";
    private String S_LlDE2_tieneLodera = "1";
    private String S_CFD_amortiguador= "1" ;
    private String S_CFD_bolsaAire= "1" ;
    private String S_CFD_gavilan= "1" ;
    private String S_CFD_muelle = "1";
    private String S_CFD_rotachamber= "1" ;
    private String S_CTD_amortiguador = "1";
    private String S_CTD_bolsaAire = "1";
    private String S_CTD_matraca = "1";
    private String S_CTD_muelle= "1" ;
    private String S_CTD_rotachamber= "1";
    private String S_CFI_amortiguador= "1" ;
    private String S_CFI_bolsaAire = "1";
    private String S_CFI_gavilan= "1" ;
    private String S_CFI_muelle= "1" ;
    private String S_CFI_rotachamber= "1" ;
    private String S_CTI_amortiguador = "1";
    private String S_CTI_bolsaAire = "1";
    private String S_CTI_matraca= "1" ;
    private String S_CTI_muelle= "1" ;
    private String S_CTI_rotachamber= "1" ;

    private CheckBox LlIE1_llantasPos2 ;

    private CheckBox LlDE1_llantasPos6 ;

    private CheckBox LlIE2_llantasPos4 ;

    private CheckBox LlDE2_llantasPos8 ;

    private String NoCaja ;
    private String folio ;
    private File imageFile ;
    private static final int REQUEST_TRACTOR = 200;
    private static final int REQUEST_NoECONOMICO = 210;
    private static final int REQUEST_IZQ_REMOLQUE_P1 = 220;
    private static final int REQUEST_VIN = 230;
    private static final int REQUEST_CHASIS_FRONTAL_IZQ = 240;
    private static final int REQUEST_CHASIS_TRASERO_IZQ = 250;
    private static final int REQUEST_LLANTAS_IZQ_EJE1= 260;
    private static final int REQUEST__LLANTAS_IZQ_EJE2 = 270;
    private static final int REQUEST_IZQ_REMOLQUE_P2 = 280;
    private static final int REQUEST_PUERTAS = 290;
    private static final int REQUEST_PLACAS = 300;
    private static final int REQUEST_SELLO1 = 310;
    private static final int REQUEST_SELLO2 = 320;
    private static final int REQUEST_DER_REMOLQUE_P1 = 330;
    private static final int REQUEST_LLANTAS_DER_EJE2 = 340;
    private static final int REQUEST_LLANTAS_DER_EJE1 = 350;
    private static final int REQUEST_CHASIS_TRASERO_DER = 360;
    private static final int REQUEST_CHASIS_FRONTAL_DER = 370;
    private static final int REQUEST_DER_REMOLQUE_P2 = 380;
    private static final int REQUEST_TRACTO_FRENTE = 800;
    private static final int REQUEST_TRACTO_DER = 810;
    private static final int REQUEST_TRACTO_IZQ = 820;
    private static final int REQUEST_SELLO3 = 400;
    private static final int DAMAGE1= 500;
    private static final int DAMAGE2 = 501;
    private static final int DAMAGE3 = 502;
    private static final int DAMAGE4 = 503;
    private static final int DAMAGE5= 504;
    private static final int DAMAGE6 = 505;
    private static final int DAMAGE7 = 506;
    private static final int DAMAGE8 = 507;
    private static final int DAMAGE9= 508;
    private static final int DAMAGE10 = 509;
    private static final int DAMAGE11= 510;
    private static final int DAMAGE12 = 511;
    private int res = 0 ;
    //private String tractorImg ;
    private String tractorDerImg ;
    private String tractorFrenteImg ;
    private String tractorIzqImg ;
    private String noEconomicoImg;
    private String izqRemolqueP1Img;
    private String vinImg;
    private String chasisFrontalIzqImg;
    private String chasisTraseroIzqImg;
    private  String llantasIzqEje1Img;
    private String llantasIzqEje2Img;
    private String izqRemolqueP2Img;
    private String puertasImg;
    private String placasImg;
    private String sello3Img;
    private String sello1Img;
    private String comentarios;
    private int idRemolque;
    private String sello2Img;
    private String derRemolqueP1Img;
    private String llantasDerEje2Img;
    private String llantasDerEje1Img;
    private String chasisTraseroDerImg;
    private  String chasisFrontalDERImg;
    private String derRemolqueP2Img ;
    private Spinner tipoLlanta1 ,tipoLlanta2 ,tipoLlanta3 ,tipoLlanta4 ,tipoLlanta5 ,tipoLlanta6 ,tipoLlanta7 ,tipoLlanta8 ;
    private DxApi dxApi;
    private String damage1Img ;
    private String damage2Img ;
    private String damage3Img;
    private String damage4Img ;
    boolean checked1;
    boolean checked2;
    private int lljumbo ;
    private String user ;
    private String password ;
    private String ip ;
    private int mensaje;
    private String id ;
    private String imageFileName ;
    private String [] estatusLlanta = new String[]{"Sin Seleccionar","Recapeada","Original"};
    Bitmap bm1;
    private  AlertDialog alert ;
    private Context context;
    File destPath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        context = getBaseContext();
        Context appContext = getApplicationContext();
        destPath = new File(context.getExternalFilesDir(null).getAbsolutePath());

        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);
        ip = preferences.getString("Aip","");
        user = preferences.getString("user","");
        password = preferences.getString("pass","");

        String noUnidad = getIntent().getStringExtra("NoUnidad");
        idRemolque = getIntent().getIntExtra("NoCaja",0);
        folio = getIntent().getStringExtra("folio");
        mensaje = getIntent().getIntExtra("mensaje",0);
        id = String.valueOf(mensaje);


        noEconomico = (ImageView) findViewById(R.id.imageView4);
        izqRemolqueP1 = (ImageView) findViewById(R.id.imageView5);
        vin = (ImageView) findViewById(R.id.imageView11);
        chasisFrontalIzq = (ImageView) findViewById(R.id.imageView7);
        chasisTraseroIzq = (ImageView) findViewById(R.id.imageView2);
        llantasIzqEje1 = (ImageView) findViewById(R.id.imageView6);
        llantasIzqEje2 = (ImageView) findViewById(R.id.imageView8);
        izqRemolqueP2 = (ImageView) findViewById(R.id.imageView10);
        puertas = (ImageView) findViewById(R.id.imageView9);
        placas = (ImageView) findViewById(R.id.imageView12);
        sello1 = (ImageView) findViewById(R.id.imageView13);
        sello2 = (ImageView) findViewById(R.id.imageView17);
        derRemolqueP1 = (ImageView) findViewById(R.id.imageView16);
        llantasDerEje2 = (ImageView) findViewById(R.id.imageView15);
        llantasDerEje1 = (ImageView) findViewById(R.id.imageView14);
        chasisTraseroDer = (ImageView) findViewById(R.id.imageView19);
        chasisFrontalDER = (ImageView) findViewById(R.id.imageView20);
        derRemolqueP2 = (ImageView) findViewById(R.id.imageView22);
        numeroDePlaca = (EditText) findViewById(R.id.editTextTextPersonName);
        sello3 = (ImageView) findViewById(R.id.imageView30);


        CheckBox jumboRB1 = (CheckBox) findViewById(R.id.radioButton2);
        llanta1SP = (Spinner) findViewById(R.id.spinner6);
        llanta2SP = (Spinner) findViewById(R.id.spinner9);
        llanta3SP = (Spinner) findViewById(R.id.spinner5);
        llanta4SP = (Spinner) findViewById(R.id.spinner8);
        llanta5SP = (Spinner) findViewById(R.id.spinner12);
        llanta6SP = (Spinner) findViewById(R.id.spinner13);
        llanta7SP = (Spinner) findViewById(R.id.spinner14);
        llanta8SP = (Spinner) findViewById(R.id.spinner11);

        tipoLlanta1  = (Spinner) findViewById(R.id.spinner18);
        tipoLlanta2  = (Spinner) findViewById(R.id.spinner17);
        tipoLlanta3  = (Spinner) findViewById(R.id.spinner16);
        tipoLlanta4  = (Spinner) findViewById(R.id.spinner15);
        tipoLlanta5  = (Spinner) findViewById(R.id.spinner22);
        tipoLlanta6  = (Spinner) findViewById(R.id.spinner21);
        tipoLlanta7  = (Spinner) findViewById(R.id.spinner20);
        tipoLlanta8  = (Spinner) findViewById(R.id.spinner19);

        damage1 = (ImageView) findViewById(R.id.imageView18);
        damage2 = (ImageView) findViewById(R.id.imageView21);
        damage3 = (ImageView) findViewById(R.id.imageView23);
        damage4 = (ImageView) findViewById(R.id.imageView24);

        damage5 = (ImageView) findViewById(R.id.imageView42);
        damage6 = (ImageView) findViewById(R.id.imageView43);
        damage7 = (ImageView) findViewById(R.id.imageView44);
        damage8 = (ImageView) findViewById(R.id.imageView45);

        damage9 = (ImageView) findViewById(R.id.imageView46);
        damage10 = (ImageView) findViewById(R.id.imageView47);
        damage11 = (ImageView) findViewById(R.id.imageView48);
        damage12 = (ImageView) findViewById(R.id.imageView49);

        tractoFrente= (ImageView) findViewById(R.id.imageView33);
        tractoDer= (ImageView) findViewById(R.id.imageView34);
        tractoIzq= (ImageView) findViewById(R.id.imageView29);

        sello1ET = (EditText) findViewById(R.id.editText);
        sello2ET = (EditText) findViewById(R.id.editText2);
        sello3ET = (EditText) findViewById(R.id.editText4);

        comentario0 = (EditText) findViewById(R.id.editTextTextMultiLine);
        comentario1 = (EditText) findViewById(R.id.editTextTextMultiLine4);
        comentario2 = (EditText) findViewById(R.id.comentario2);


        Button btnImg = (Button) findViewById(R.id.btnImg);

        CheckBox defensa = (CheckBox) findViewById(R.id.checkBox);
        CheckBox llantas = (CheckBox) findViewById(R.id.checkBox8);
        //pisoTractor = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox tanqueDiesel = (CheckBox) findViewById(R.id.checkBox9);
        CheckBox cabinaCompartimientos = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox tanqueAire = (CheckBox) findViewById(R.id.checkBox10);
        CheckBox quintaRueda = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox ejesTransmision = (CheckBox) findViewById(R.id.checkBox11);
        CheckBox tuboEscape = (CheckBox) findViewById(R.id.checkBox5);
        CheckBox motor = (CheckBox) findViewById(R.id.checkBox7);
        CheckBox baseRemolque = (CheckBox) findViewById(R.id.checkBox29);
        //paredLateralDerecha = (CheckBox) findViewById(R.id.checkBox31);
        //paredFrontal = (CheckBox) findViewById(R.id.checkBox33);
        //pisoInterno = (CheckBox) findViewById(R.id.checkBox35);
        //puerta = (CheckBox) findViewById(R.id.checkBox30);
        CheckBox techos = (CheckBox) findViewById(R.id.checkBox32);
        //paredLateralIzquierda = (CheckBox) findViewById(R.id.checkBox34);
        CheckBox vvtt = (CheckBox) findViewById(R.id.checkBox36);

        CheckBox IRP1_inspeccionMecanica = (CheckBox) findViewById(R.id.cb_inspeccion);
        CheckBox IRP1_lucesCheck = (CheckBox) findViewById(R.id.cb_lucesCheck);
        CheckBox IRP1_luzGaliboIzqFrontalSup = (CheckBox) findViewById(R.id.checkBox13);
        CheckBox IRP1_manitas = (CheckBox) findViewById(R.id.checkBox14);
        CheckBox IRP1_manivela = (CheckBox) findViewById(R.id.checkBox15);
        CheckBox IRP1_patinIzq = (CheckBox) findViewById(R.id.checkBox16);

        CheckBox IRP2_cuartoLadoIzq = (CheckBox) findViewById(R.id.checkBox17);
        CheckBox IRP2_loderaIzq = (CheckBox) findViewById(R.id.checkBox18);
        CheckBox IRP2_lucesCheck = (CheckBox) findViewById(R.id.checkBox19);
        CheckBox IRP2_luzABS = (CheckBox) findViewById(R.id.checkBox20);
        CheckBox IRP2_luzBarcoIzq = (CheckBox) findViewById(R.id.checkBox21);
        CheckBox IRP2_rompevientosIzq = (CheckBox) findViewById(R.id.checkBox22);


        CheckBox llIE1_birlosPivote = (CheckBox) findViewById(R.id.checkBox23);
        CheckBox llIE1_llantasPos1 = (CheckBox) findViewById(R.id.checkBox24);
        LlIE1_llantasPos2 = (CheckBox) findViewById(R.id.checkBox25);
        CheckBox llIE1_masaYoyo = (CheckBox) findViewById(R.id.checkBox26);
        CheckBox llIE1_rin = (CheckBox) findViewById(R.id.checkBox27);
        CheckBox llIE1_tieneLodera = (CheckBox) findViewById(R.id.checkBox28);


        CheckBox llIE2_birlosPivote = (CheckBox) findViewById(R.id.checkBox37);
        CheckBox llIE2_llantasPos3 = (CheckBox) findViewById(R.id.checkBox38);
        LlIE2_llantasPos4 = (CheckBox) findViewById(R.id.checkBox39);
        CheckBox llIE2_masaYoyo = (CheckBox) findViewById(R.id.checkBox40);
        CheckBox llIE2_rin = (CheckBox) findViewById(R.id.checkBox41);
        CheckBox llIE2_tieneLodera = (CheckBox) findViewById(R.id.checkBox42);

        CheckBox CFI_amortiguador = (CheckBox) findViewById(R.id.checkBox44);
        CheckBox CFI_bolsaAire = (CheckBox) findViewById(R.id.checkBox47);
        CheckBox CFI_gavilan = (CheckBox) findViewById(R.id.checkBox43);
        CheckBox CFI_muelle = (CheckBox) findViewById(R.id.checkBox45);
        CheckBox CFI_rotachamber = (CheckBox) findViewById(R.id.checkBox46);

        CheckBox CTI_amortiguador = (CheckBox) findViewById(R.id.checkBox48);
        CheckBox CTI_bolsaAire = (CheckBox) findViewById(R.id.checkBox49);
        CheckBox CTI_matraca = (CheckBox) findViewById(R.id.checkBox52);
        CheckBox CTI_muelle = (CheckBox) findViewById(R.id.checkBox51);
        CheckBox CTI_rotachamber = (CheckBox) findViewById(R.id.checkBox50);

        CheckBox pu_bisagrasPuertas = (CheckBox) findViewById(R.id.checkBox53);
        CheckBox pu_defensa = (CheckBox) findViewById(R.id.checkBox54);
        CheckBox pu_luzGaliboSupTraseras = (CheckBox) findViewById(R.id.checkBox55);
        CheckBox pu_plafonesDer = (CheckBox) findViewById(R.id.checkBox56);
        CheckBox pu_plafonesIzq = (CheckBox) findViewById(R.id.checkBox57);

        CheckBox pl_luzPlaca = (CheckBox) findViewById(R.id.checkBox59);
        CheckBox pl_placa = (CheckBox) findViewById(R.id.checkBox58);

        CheckBox s1_sello1 = (CheckBox) findViewById(R.id.checkBox61);
        CheckBox s1_altaSeguridad = (CheckBox) findViewById(R.id.checkBox60);

        CheckBox s2_escotilla = (CheckBox) findViewById(R.id.checkBox62);
        CheckBox s2_sello2 = (CheckBox) findViewById(R.id.checkBox64);
        CheckBox s2_altaSeguridad = (CheckBox) findViewById(R.id.checkBox63);

        CheckBox llDE1_birlosPivote = (CheckBox) findViewById(R.id.checkBox66);
        CheckBox llDE1_llantasPos5 = (CheckBox) findViewById(R.id.checkBox67);
        LlDE1_llantasPos6 = (CheckBox) findViewById(R.id.checkBox68);
        CheckBox llDE1_masaYoyo = (CheckBox) findViewById(R.id.checkBox69);
        CheckBox llDE1_rin = (CheckBox) findViewById(R.id.checkBox70);
        CheckBox llDE1_tieneLodera = (CheckBox) findViewById(R.id.checkBox400);

        CheckBox DRP1_fondoPlaga = (CheckBox) findViewById(R.id.checkBox71);
        CheckBox DRP1_pisoPlaga = (CheckBox) findViewById(R.id.checkBox72);
        CheckBox DRP1_techoPlaga = (CheckBox) findViewById(R.id.checkBox73);
        CheckBox DRP1_lucesCheck = (CheckBox) findViewById(R.id.checkBox74);
        CheckBox DRP1_luzGaliboDerFrontalSup = (CheckBox) findViewById(R.id.checkBox75);
        CheckBox DRP1_derPlaga = (CheckBox) findViewById(R.id.checkBox76);
        CheckBox DRP1_izqPlaga = (CheckBox) findViewById(R.id.checkBox77);
        CheckBox DRP1_patinDer = (CheckBox) findViewById(R.id.checkBox78);

        CheckBox DRP2_cuartoLadoDer = (CheckBox) findViewById(R.id.checkBox80);
        CheckBox DRP2_loderaDer = (CheckBox) findViewById(R.id.checkBox81);
        CheckBox DRP2_lucesCheck = (CheckBox) findViewById(R.id.checkBox82);
        CheckBox DRP2_luzBarcoDer = (CheckBox) findViewById(R.id.checkBox83);
        CheckBox DRP2_rompevientosDer = (CheckBox) findViewById(R.id.checkBox84);

        CheckBox llDE2_birlosPivote = (CheckBox) findViewById(R.id.checkBox85);
        CheckBox llDE2_llantasPos7 = (CheckBox) findViewById(R.id.checkBox86);
        LlDE2_llantasPos8 = (CheckBox) findViewById(R.id.checkBox87);
        CheckBox llDE2_masaYoyo = (CheckBox) findViewById(R.id.checkBox88);
        CheckBox llDE2_rin = (CheckBox) findViewById(R.id.checkBox89);
        CheckBox llDE2_tieneLodera = (CheckBox) findViewById(R.id.checkBox90);

        CheckBox CFD_amortiguador = (CheckBox) findViewById(R.id.checkBox91);
        CheckBox CFD_bolsaAire = (CheckBox) findViewById(R.id.checkBox92);
        CheckBox CFD_gavilan = (CheckBox) findViewById(R.id.checkBox93);
        CheckBox CFD_muelle = (CheckBox) findViewById(R.id.checkBox94);
        CheckBox CFD_rotachamber = (CheckBox) findViewById(R.id.checkBox95);

        CheckBox CTD_amortiguador = (CheckBox) findViewById(R.id.checkBox96);
        CheckBox CTD_bolsaAire = (CheckBox) findViewById(R.id.checkBox97);
        CheckBox CTD_matraca = (CheckBox) findViewById(R.id.checkBox98);
        CheckBox CTD_muelle = (CheckBox) findViewById(R.id.checkBox99);
        CheckBox CTD_rotachamber = (CheckBox) findViewById(R.id.checkBox100);


        ArrayAdapter<String> adapter0 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta1.setAdapter(adapter0);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta2.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta3.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta4.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta5.setAdapter(adapter4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta6.setAdapter(adapter5);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta7.setAdapter(adapter6);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta8.setAdapter(adapter7);

        Post post =  new Post(user,password);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://"+ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create())
                ;

        builder.client(httpClient.build());

        Retrofit retrofit = builder.build();

        dxApi = retrofit.create(DxApi.class);

        Call<List<CLlanta>> callLlanta = dxApi.getLlanta(post);

        callLlanta.enqueue(new Callback<List<CLlanta>>() {
            @Override
            public void onResponse(@NotNull Call<List<CLlanta>> call, @NotNull Response<List<CLlanta>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                }

                try{
                    List<CLlanta> CLlantas = response.body();

                    ArrayAdapter<CLlanta> adapter2 = new ArrayAdapter<>(imgActivity.this, R.layout.mspinner_item, CLlantas);
                    llanta1SP .setAdapter(adapter2);
                    llanta2SP .setAdapter(adapter2);
                    llanta3SP  .setAdapter(adapter2);
                    llanta4SP .setAdapter(adapter2);
                    llanta5SP  .setAdapter(adapter2);
                    llanta6SP .setAdapter(adapter2);
                    llanta7SP  .setAdapter(adapter2);
                    llanta8SP  .setAdapter(adapter2);
                }catch (Exception e){
                    Toast.makeText(imgActivity.this, "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(@NotNull Call<List<CLlanta>> call, @NotNull Throwable t) {

            }
        });

        jumboRB1.setOnClickListener(view -> {

             checked2 = ((CheckBox) view).isChecked();

            if (checked2) {

                llanta3SP.setVisibility(View.INVISIBLE);
                llanta4SP.setVisibility(View.INVISIBLE);
                llanta7SP.setVisibility(View.INVISIBLE);
                llanta8SP.setVisibility(View.INVISIBLE);

                LlIE2_llantasPos4.setEnabled(false);
                LlDE1_llantasPos6.setEnabled(false);
                LlIE1_llantasPos2.setEnabled(false);
                LlDE2_llantasPos8.setEnabled(false);

                tipoLlanta2.setVisibility(View.INVISIBLE);
                tipoLlanta4.setVisibility(View.INVISIBLE);
                tipoLlanta6.setVisibility(View.INVISIBLE);
                tipoLlanta8.setVisibility(View.INVISIBLE);

            }else{
                llanta3SP.setVisibility(View.VISIBLE);
                llanta4SP.setVisibility(View.VISIBLE);
                llanta7SP.setVisibility(View.VISIBLE);
                llanta8SP.setVisibility(View.VISIBLE);

                LlIE2_llantasPos4.setEnabled(true);
                LlDE1_llantasPos6.setEnabled(true);
                LlIE1_llantasPos2.setEnabled(true);
                LlDE2_llantasPos8.setEnabled(true);

                tipoLlanta2.setVisibility(View.VISIBLE);
                tipoLlanta4.setVisibility(View.VISIBLE);
                tipoLlanta6.setVisibility(View.VISIBLE);
                tipoLlanta8.setVisibility(View.VISIBLE);
            }
        });

        defensa.setOnClickListener(view -> {

            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_defensa = "0";

            }else{
                S_defensa = "1";
            }
        });
        llantas.setOnClickListener(view -> {

            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_llantas = "0";

            }else{
                S_llantas = "1";
            }
        });
        tanqueDiesel.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_tanqueDiesel = "0";

            }else{
                S_tanqueDiesel = "1";
            }
        });
        cabinaCompartimientos.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_cabinaCompartimientos = "0";

            }else{
                S_cabinaCompartimientos = "1";
            }
        });
        tanqueAire.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_tanqueAire = "0";

            }else{
                S_tanqueAire = "1";
            }
        });
        quintaRueda.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_quintaRueda = "0";

            }else{
                S_quintaRueda = "1";
            }
        });
        ejesTransmision.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_ejesTransmision = "0";

            }else{
                S_ejesTransmision = "1";
            }
        });
        tuboEscape.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_tuboEscape = "0";

            }else{
                S_tuboEscape = "1";
            }
        });
        motor.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_motor = "0";

            }else{
                S_motor = "1";
            }
        });
        baseRemolque.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_baseRemolque = "0";

            }else{
                S_baseRemolque = "1";
            }
        });
        techos.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_techos = "0";

            }else{
                S_techos = "1";
            }
        });
        vvtt.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_vvtt = "0";

            }else{
                S_vvtt = "1";
            }
        });
        IRP1_inspeccionMecanica.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP1_inspeccionMecanica = "0";

            }else{
                S_IRP1_inspeccionMecanica = "1";
            }
        });
        IRP1_lucesCheck.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP1_lucesCheck = "0";

            }else{
                S_IRP1_lucesCheck = "1";
            }
        });
        IRP1_luzGaliboIzqFrontalSup.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP1_luzGaliboIzqFrontalSup = "0";

            }else{
                S_IRP1_luzGaliboIzqFrontalSup = "1";
            }
        });
        IRP1_manitas.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP1_manitas = "0";

            }else{
                S_IRP1_manitas = "1";
            }
        });
        IRP1_manivela.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP1_manivela = "0";

            }else{
                S_IRP1_manivela = "1";
            }
        });
        IRP1_patinIzq.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP1_patinIzq = "0";

            }else{
                S_IRP1_patinIzq = "1";
            }
        });
        IRP2_cuartoLadoIzq.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP2_cuartoLadoIzq = "0";

            }else{
                S_IRP2_cuartoLadoIzq = "1";
            }
        });
        IRP2_loderaIzq.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP2_loderaIzq = "0";

            }else{
                S_IRP2_loderaIzq = "1";
            }
        });
        IRP2_lucesCheck.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP2_lucesCheck = "0";

            }else{
                S_IRP2_lucesCheck = "1";
            }
        });
        IRP2_luzABS.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP2_luzABS = "0";

            }else{
                S_IRP2_luzABS = "1";
            }
        });
        IRP2_luzBarcoIzq.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP2_luzBarcoIzq = "0";

            }else{
                S_IRP2_luzBarcoIzq = "1";
            }
        });
        IRP2_rompevientosIzq.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_IRP2_rompevientosIzq = "0";

            }else{
                S_IRP2_rompevientosIzq = "1";
            }
        });
        llIE1_birlosPivote.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE1_birlosPivote = "0";

            }else{
                S_LlIE1_birlosPivote = "1";
            }
        });
        llIE1_llantasPos1.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE1_llantasPos1 = "0";

            }else{
                S_LlIE1_llantasPos1 = "1";
            }
        });
        LlIE1_llantasPos2.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE1_llantasPos2 = "0";

            }else{
                S_LlIE1_llantasPos2 = "1";
            }
        });
        llIE1_masaYoyo.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE1_masaYoyo = "0";

            }else{
                S_LlIE1_masaYoyo = "1";
            }
        });
        llIE1_rin.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE1_rin = "0";

            }else{
                S_LlIE1_rin = "1";
            }
        });
        llIE1_tieneLodera.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE1_tieneLodera = "0";

            }else{
                S_LlIE1_tieneLodera = "1";
            }
        });
        pu_bisagrasPuertas.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_Pu_bisagrasPuertas = "0";

            }else{
                S_Pu_bisagrasPuertas = "1";
            }
        });
        pu_defensa.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_Pu_defensa = "0";

            }else{
                S_Pu_defensa = "1";
            }
        });
        pu_luzGaliboSupTraseras.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_Pu_luzGaliboSupTraseras = "0";

            }else{
                S_Pu_luzGaliboSupTraseras = "1";
            }
        });
        pu_plafonesDer.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_Pu_plafonesDer = "0";

            }else{
                S_Pu_plafonesDer = "1";
            }
        });
        pu_plafonesIzq.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_Pu_plafonesIzq = "0";

            }else{
                S_Pu_plafonesIzq = "1";
            }
        });
        pl_luzPlaca.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_Pl_luzPlaca = "0";

            }else{
                S_Pl_luzPlaca = "1";
            }
        });
        pl_placa.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_Pl_placa = "0";

            }else{
                S_Pl_placa = "1";
            }
        });
        s1_sello1.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_S1_sello1 = "0";

            }else{
                S_S1_sello1 = "1";
            }
        });
        s1_altaSeguridad.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_S1_altaSeguridad = "0";

            }else{
                S_S1_altaSeguridad = "1";
            }
        });
        llDE1_birlosPivote.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE1_birlosPivote = "0";

            }else{
                S_LlDE1_birlosPivote = "1";
            }
        });
        llDE1_llantasPos5.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE1_llantasPos5 = "0";

            }else{
                S_LlDE1_llantasPos5 = "1";
            }
        });
        LlDE1_llantasPos6.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE1_llantasPos6 = "0";

            }else{
                S_LlDE1_llantasPos6 = "1";
            }
        });
        llDE1_masaYoyo.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE1_masaYoyo = "0";

            }else{
                S_LlDE1_masaYoyo = "1";
            }
        });
        llDE1_rin.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE1_rin = "0";

            }else{
                S_LlDE1_rin = "1";
            }
        });
        llDE1_tieneLodera.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE1_tieneLodera = "0";

            }else{
                S_LlDE1_tieneLodera = "1";
            }
        });
        DRP1_fondoPlaga.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP1_fondoPlaga = "0";

            }else{
                S_DRP1_fondoPlaga = "1";
            }
        });
        DRP1_pisoPlaga.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP1_pisoPlaga = "0";

            }else{
                S_DRP1_pisoPlaga = "1";
            }
        });
        DRP1_techoPlaga.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP1_techoPlaga = "0";

            }else{
                S_DRP1_techoPlaga = "1";
            }
        });
        DRP1_lucesCheck.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP1_lucesCheck = "0";

            }else{
                S_DRP1_lucesCheck = "1";
            }
        });
        DRP1_luzGaliboDerFrontalSup.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP1_luzGaliboDerFrontalSup = "0";

            }else{
                S_DRP1_luzGaliboDerFrontalSup = "1";
            }
        });
        DRP1_derPlaga.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP1_derPlaga = "0";

            }else{
                S_DRP1_derPlaga = "1";
            }
        });
        DRP1_izqPlaga.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP1_izqPlaga = "0";

            }else{
                S_DRP1_izqPlaga = "1";
            }
        });
        DRP1_patinDer.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP1_patinDer = "0";

            }else{
                S_DRP1_patinDer = "1";
            }
        });
        DRP2_cuartoLadoDer.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP2_cuartoLadoDer = "0";

            }else{
                S_DRP2_cuartoLadoDer = "1";
            }
        });
        DRP2_loderaDer.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP2_loderaDer = "0";

            }else{
                S_DRP2_loderaDer = "1";
            }
        });
        DRP2_lucesCheck.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP2_lucesCheck = "0";

            }else{
                S_DRP2_lucesCheck = "1";
            }
        });
        DRP2_luzBarcoDer.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP2_luzBarcoDer = "0";

            }else{
                S_DRP2_luzBarcoDer = "1";
            }
        });
        DRP2_rompevientosDer.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_DRP2_rompevientosDer = "0";

            }else{
                S_DRP2_rompevientosDer = "1";
            }
        });
        s2_escotilla.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_S2_escotilla = "0";

            }else{
                S_S2_escotilla = "1";
            }
        });
        s2_sello2.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_S2_sello2 = "0";

            }else{
                S_S2_sello2 = "1";
            }
        });
        s2_altaSeguridad.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_S2_altaSeguridad = "0";

            }else{
                S_S2_altaSeguridad = "1";
            }
        });
        llIE2_birlosPivote.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE2_birlosPivote = "0";

            }else{
                S_LlIE2_birlosPivote = "1";
            }
        });
        llIE2_llantasPos3.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE2_llantasPos3 = "0";

            }else{
                S_LlIE2_llantasPos3 = "1";
            }
        });
        LlIE2_llantasPos4.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE2_llantasPos4 = "0";

            }else{
                S_LlIE2_llantasPos4 = "1";
            }
        });
        llIE2_masaYoyo.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE2_masaYoyo = "0";

            }else{
                S_LlIE2_masaYoyo = "1";
            }
        });
        llIE2_rin.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE2_rin = "0";

            }else{
                S_LlIE2_rin = "1";
            }
        });
        llIE2_tieneLodera.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlIE2_tieneLodera = "0";

            }else{
                S_LlIE2_tieneLodera = "1";
            }
        });
        llDE2_birlosPivote.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE2_birlosPivote = "0";

            }else{
                S_LlDE2_birlosPivote = "1";
            }
        });
        llDE2_llantasPos7.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE2_llantasPos7 = "0";

            }else{
                S_LlDE2_llantasPos7 = "1";
            }
        });
        LlDE2_llantasPos8.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE2_llantasPos8 = "0";

            }else{
                S_LlDE2_llantasPos8 = "1";
            }
        });
        llDE2_masaYoyo.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE2_masaYoyo = "0";

            }else{
                S_LlDE2_masaYoyo = "1";
            }
        });
        llDE2_rin.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE2_rin = "0";

            }else{
                S_LlDE2_rin = "1";
            }
        });
        llDE2_tieneLodera.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_LlDE2_tieneLodera = "0";

            }else{
                S_LlDE2_tieneLodera = "1";
            }
        });
        CFD_amortiguador.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFD_amortiguador = "0";

            }else{
                S_CFD_amortiguador = "1";
            }
        });
        CFD_bolsaAire.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFD_bolsaAire = "0";

            }else{
                S_CFD_bolsaAire = "1";
            }
        });
        CFD_gavilan.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFD_gavilan = "0";

            }else{
                S_CFD_gavilan = "1";
            }
        });
        CFD_muelle.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFD_muelle = "0";

            }else{
                S_CFD_muelle = "1";
            }
        });
        CFD_rotachamber.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFD_rotachamber = "0";

            }else{
                S_CFD_rotachamber = "1";
            }
        });
        CTD_amortiguador.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTD_amortiguador = "0";

            }else{
                S_CTD_amortiguador = "1";
            }
        });
        CTD_bolsaAire.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTD_bolsaAire = "0";

            }else{
                S_CTD_bolsaAire = "1";
            }
        });
        CTD_matraca.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTD_matraca = "0";

            }else{
                S_CTD_matraca = "1";
            }
        });
        CTD_muelle.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTD_muelle = "0";

            }else{
                S_CTD_muelle = "1";
            }
        });
        CTD_rotachamber.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTD_rotachamber = "0";

            }else{
                S_CTD_rotachamber = "1";
            }
        });
        CFI_amortiguador.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFI_amortiguador = "0";

            }else{
                S_CFI_amortiguador = "1";
            }
        });
        CFI_bolsaAire.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFI_bolsaAire = "0";

            }else{
                S_CFI_bolsaAire = "1";
            }
        });
        CFI_gavilan.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFI_gavilan = "0";

            }else{
                S_CFI_gavilan = "1";
            }
        });
        CFI_muelle.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFI_muelle = "0";

            }else{
                S_CFI_muelle = "1";
            }
        });
        CFI_rotachamber.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CFI_rotachamber = "0";

            }else{
                S_CFI_rotachamber = "1";
            }
        });
        CTI_amortiguador.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTI_amortiguador = "0";

            }else{
                S_CTI_amortiguador = "1";
            }
        });
        CTI_bolsaAire.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTI_bolsaAire = "0";

            }else{
                S_CTI_bolsaAire = "1";
            }
        });
        CTI_matraca.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTI_matraca = "0";

            }else{
                S_CTI_matraca = "1";
            }
        });
        CTI_muelle.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTI_muelle = "0";

            }else{
                S_CTI_muelle = "1";
            }
        });
        CTI_rotachamber.setOnClickListener(view -> {
            // Is the button now checked?
            checked1 = ((CheckBox) view).isChecked();

            if (checked1) {
                S_CTI_rotachamber = "0";

            }else{
                S_CTI_rotachamber = "1";
            }
        });

        btnImg.setOnClickListener(view -> {

            //300 VACIO
            //128 IMAGN

            try {

                tractorDerImg = String.valueOf(tractoDer.getDrawable().getBounds());
                tractorIzqImg = String.valueOf(tractoIzq.getDrawable().getBounds());
                tractorFrenteImg = String.valueOf(tractoFrente.getDrawable().getBounds());
                noEconomicoImg = String.valueOf(noEconomico.getDrawable().getBounds());
                izqRemolqueP1Img = String.valueOf(izqRemolqueP1.getDrawable().getBounds());
                vinImg = String.valueOf(vin.getDrawable().getBounds());
                chasisFrontalIzqImg = String.valueOf(chasisFrontalIzq.getDrawable().getBounds());
                chasisTraseroIzqImg = String.valueOf(chasisTraseroIzq.getDrawable().getBounds());
                llantasIzqEje1Img = String.valueOf(llantasIzqEje1.getDrawable().getBounds());
                llantasIzqEje2Img = String.valueOf(llantasIzqEje2.getDrawable().getBounds());
                izqRemolqueP2Img = String.valueOf(izqRemolqueP2.getDrawable().getBounds());
                puertasImg = String.valueOf(puertas.getDrawable().getBounds());
                placasImg = String.valueOf(placas.getDrawable().getBounds());
                sello1Img = String.valueOf(sello1.getDrawable().getBounds());
                sello2Img = String.valueOf(sello2.getDrawable().getBounds());
                derRemolqueP1Img = String.valueOf(derRemolqueP1.getDrawable().getBounds());
                llantasDerEje2Img = String.valueOf(llantasDerEje2.getDrawable().getBounds());
                llantasDerEje1Img = String.valueOf(llantasDerEje1.getDrawable().getBounds());
                chasisTraseroDerImg = String.valueOf(chasisTraseroDer.getDrawable().getBounds());
                chasisFrontalDERImg = String.valueOf(chasisFrontalDER.getDrawable().getBounds());
                derRemolqueP2Img = String.valueOf(derRemolqueP2.getDrawable().getBounds());
                sello3Img = String.valueOf(sello3.getDrawable().getBounds());

                damage1Img = String.valueOf(damage1.getDrawable().getBounds());
                damage2Img = String.valueOf(damage2.getDrawable().getBounds());
                damage3Img = String.valueOf(damage3.getDrawable().getBounds());
                damage4Img = String.valueOf(damage4.getDrawable().getBounds());

                String ll1 = llanta1SP.getSelectedItem().toString();
                String ll2 = llanta2SP.getSelectedItem().toString();
                String ll3 = llanta3SP.getSelectedItem().toString();
                String ll4 = llanta4SP.getSelectedItem().toString();
                String ll5 = llanta5SP.getSelectedItem().toString();
                String ll6 = llanta6SP.getSelectedItem().toString();
                String ll7 = llanta7SP.getSelectedItem().toString();
                String ll8 = llanta8SP.getSelectedItem().toString();

                String tll1 = tipoLlanta1.getSelectedItem().toString();
                String tll2 = tipoLlanta2.getSelectedItem().toString();
                String tll3 = tipoLlanta3.getSelectedItem().toString();
                String tll4 = tipoLlanta4.getSelectedItem().toString();
                String tll5 = tipoLlanta5.getSelectedItem().toString();
                String tll6 = tipoLlanta6.getSelectedItem().toString();
                String tll7 = tipoLlanta7.getSelectedItem().toString();
                String tll8 = tipoLlanta8.getSelectedItem().toString();

                ll1 = ll1 + "-" + tll1;
                ll2 = ll2 + "-" + tll2;
                ll3 = ll3 + "-" + tll3;
                ll4 = ll4 + "-" + tll4;
                ll5 = ll5 + "-" + tll5;
                ll6 = ll6 + "-" + tll6;
                ll7 = ll7 + "-" + tll7;
                ll8 = ll8 + "-" + tll8;

                String sello1S = sello1ET.getText().toString();
                String sello2S = sello2ET.getText().toString();
                String sello3S = sello3ET.getText().toString();

                String comen2 = comentario2.getText().toString();
                String comen1 = comentario1.getText().toString();
                String comen0 = comentario0.getText().toString();

                comentarios = "IZQUIERDA=" + comen0 +
                        "     TRASERA=" + comen1 +
                        "     DERECHA=" + comen2;


                lljumbo = 0;


                int selloExtra;

                if (checked2) {
                    lljumbo = 1;
                }


                if (sello3Img.contains("128") && sello3S.length() > 0) {
                    selloExtra = 1;
                } else {
                    selloExtra = 0;
                }

                String placasDatosD = numeroDePlaca.getText().toString();


                // SI LAS LLANTAS SON JUMBO SOLO REVISA 4
                if (lljumbo == 1) {
                    if (
                            !tractorDerImg.contains("128") ||
                                    !tractorIzqImg.contains("128") ||
                                    !tractorFrenteImg.contains("128") ||
                                    !noEconomicoImg.contains("128") ||
                                    !izqRemolqueP1Img.contains("128") ||
                                    !vinImg.contains("128") ||
                                    !chasisFrontalIzqImg.contains("128") ||
                                    !chasisTraseroIzqImg.contains("128") ||
                                    !llantasIzqEje1Img.contains("128") ||
                                    !llantasIzqEje2Img.contains("128") ||
                                    !izqRemolqueP2Img.contains("128") ||
                                    !puertasImg.contains("128") ||
                                    !placasImg.contains("128") ||
                                    !sello1Img.contains("128") ||
                                    !sello2Img.contains("128") ||
                                    !derRemolqueP1Img.contains("128") ||
                                    !llantasDerEje2Img.contains("128") ||
                                    !llantasDerEje1Img.contains("128") ||
                                    !chasisTraseroDerImg.contains("128") ||
                                    !chasisFrontalDERImg.contains("128") ||
                                    !derRemolqueP2Img.contains("128") ||
                                    sello1S.length() == 0 ||
                                    sello2S.length() == 0 ||
                                    placasDatosD.length() == 0 ||
                                    comentarios.length() == 0 ||

                                    ll1 == "Sin Seleccionar" ||
                                    ll2 == "Sin Seleccionar" ||
                                    ll6 == "Sin Seleccionar" ||
                                    ll5 == "Sin Seleccionar"

                    ) {
                        Toast.makeText(context, "Datos Incompletos", Toast.LENGTH_SHORT).show();
                    } else if (
                            tractorDerImg.contains("128") &&
                                    tractorIzqImg.contains("128") &&
                                    tractorFrenteImg.contains("128") &&
                                    noEconomicoImg.contains("128") &&
                                    izqRemolqueP1Img.contains("128") &&
                                    vinImg.contains("128") &&
                                    chasisFrontalIzqImg.contains("128") &&
                                    chasisTraseroIzqImg.contains("128") &&
                                    llantasIzqEje1Img.contains("128") &&
                                    llantasIzqEje2Img.contains("128") &&
                                    izqRemolqueP2Img.contains("128") &&
                                    puertasImg.contains("128") &&
                                    placasImg.contains("128") &&
                                    sello1Img.contains("128") &&
                                    sello2Img.contains("128") &&
                                    derRemolqueP1Img.contains("128") &&
                                    llantasDerEje2Img.contains("128") &&
                                    llantasDerEje1Img.contains("128") &&
                                    chasisTraseroDerImg.contains("128") &&
                                    chasisFrontalDERImg.contains("128") &&
                                    derRemolqueP2Img.contains("128") &&
                                    sello1S.length() > 0 &&
                                    sello2S.length() > 0 &&
                                    placasDatosD.length() > 0 &&
                                    comentarios.length() > 0 &&
                                    ll1 != "Sin Seleccionar" &&
                                    ll2 != "Sin Seleccionar" &&
                                    ll6 != "Sin Seleccionar" &&
                                    ll5 != "Sin Seleccionar") {


                        try {
                            Post6 post6 = new Post6(user, password, idRemolque, "2", mensaje, sello1S, sello2S, ll1,
                                    ll2, ll6, ll5, "", "", "", "", lljumbo, 0, selloExtra, sello3S, 0, "",
                                    placasDatosD,
                                    comentarios,
                                    S_defensa,
                                    S_llantas,
                                    S_pisoTractor,
                                    S_tanqueDiesel,
                                    S_cabinaCompartimientos,
                                    S_tanqueAire,
                                    S_quintaRueda,
                                    S_ejesTransmision,
                                    S_tuboEscape,
                                    S_motor,
                                    S_baseRemolque,
                                    S_puerta,
                                    S_paredLateralDerecha,
                                    S_techos,
                                    S_paredFrontal,
                                    S_paredLateralIzquierda,
                                    S_pisoInterno,
                                    S_vvtt,
                                    S_IRP1_inspeccionMecanica,
                                    S_IRP1_lucesCheck,
                                    S_IRP1_luzGaliboIzqFrontalSup,
                                    S_IRP1_manitas,
                                    S_IRP1_manivela,
                                    S_IRP1_patinIzq,
                                    S_IRP2_cuartoLadoIzq,
                                    S_IRP2_loderaIzq,
                                    S_IRP2_lucesCheck,
                                    S_IRP2_luzABS,
                                    S_IRP2_luzBarcoIzq,
                                    S_IRP2_rompevientosIzq,
                                    S_LlIE1_birlosPivote,
                                    S_LlIE1_llantasPos1,
                                    S_LlIE1_llantasPos2,
                                    S_LlIE1_masaYoyo,
                                    S_LlIE1_rin,
                                    S_LlIE1_tieneLodera,
                                    S_Pu_bisagrasPuertas,
                                    S_Pu_defensa,
                                    S_Pu_luzGaliboSupTraseras,
                                    S_Pu_plafonesDer,
                                    S_Pu_plafonesIzq,
                                    S_Pl_luzPlaca,
                                    S_Pl_placa,
                                    S_S1_sello1,
                                    S_S1_altaSeguridad,
                                    S_LlDE1_birlosPivote,
                                    S_LlDE1_llantasPos5,
                                    S_LlDE1_llantasPos6,
                                    S_LlDE1_masaYoyo,
                                    S_LlDE1_rin,
                                    S_LlDE1_tieneLodera,
                                    S_DRP1_fondoPlaga,
                                    S_DRP1_pisoPlaga,
                                    S_DRP1_techoPlaga,
                                    S_DRP1_lucesCheck,
                                    S_DRP1_luzGaliboDerFrontalSup,
                                    S_DRP1_derPlaga,
                                    S_DRP1_izqPlaga,
                                    S_DRP1_patinDer,
                                    S_DRP2_cuartoLadoDer,
                                    S_DRP2_loderaDer,
                                    S_DRP2_lucesCheck,
                                    S_DRP2_luzBarcoDer,
                                    S_DRP2_rompevientosDer,
                                    S_S2_escotilla,
                                    S_S2_sello2,
                                    S_S2_altaSeguridad,
                                    S_LlIE2_birlosPivote,
                                    S_LlIE2_llantasPos3,
                                    S_LlIE2_llantasPos4,
                                    S_LlIE2_masaYoyo,
                                    S_LlIE2_rin,
                                    S_LlIE2_tieneLodera,
                                    S_LlDE2_birlosPivote,
                                    S_LlDE2_llantasPos7,
                                    S_LlDE2_llantasPos8,
                                    S_LlDE2_masaYoyo,
                                    S_LlDE2_rin,
                                    S_LlDE2_tieneLodera,
                                    S_CFD_amortiguador,
                                    S_CFD_bolsaAire,
                                    S_CFD_gavilan,
                                    S_CFD_muelle,
                                    S_CFD_rotachamber,
                                    S_CTD_amortiguador,
                                    S_CTD_bolsaAire,
                                    S_CTD_matraca,
                                    S_CTD_muelle,
                                    S_CTD_rotachamber,
                                    S_CFI_amortiguador,
                                    S_CFI_bolsaAire,
                                    S_CFI_gavilan,
                                    S_CFI_muelle,
                                    S_CFI_rotachamber,
                                    S_CTI_amortiguador,
                                    S_CTI_bolsaAire,
                                    S_CTI_matraca,
                                    S_CTI_muelle,
                                    S_CTI_rotachamber
                            );


                            Call<List<CEnvio>> callenvio2 = dxApi.getEnvio2(post6);

                            callenvio2.enqueue(new Callback<List<CEnvio>>() {
                                @Override
                                public void onResponse(@NotNull Call<List<CEnvio>> call, @NotNull Response<List<CEnvio>> response) {

                                    if (!response.isSuccessful()) {
                                        Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                                    }

                                    List<CEnvio> cEnvio2s = response.body();
                                    String mensaje = cEnvio2s.get(0).getMensaje();

                                    if (mensaje.contains("Enviado con exito")) {
                                        Intent i = new Intent(imgActivity.this, firmasActivity.class);
                                        i.putExtra("folio", folio);
                                        i.putExtra("mensaje", id);

                                        startActivity(i);
                                    } else {
                                        Toast.makeText(imgActivity.this, "Error al Enviar", Toast.LENGTH_LONG).show();
                                    }

                                }

                                @Override
                                public void onFailure(@NotNull Call<List<CEnvio>> call, @NotNull Throwable t) {
                                    Toast.makeText(imgActivity.this, "Error 404", Toast.LENGTH_LONG).show();
                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(imgActivity.this, "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                } else if (lljumbo == 0) {
                    if (
                            !tractorDerImg.contains("128") ||
                                    !tractorIzqImg.contains("128") ||
                                    !tractorFrenteImg.contains("128") ||
                                    !noEconomicoImg.contains("128") ||
                                    !izqRemolqueP1Img.contains("128") ||
                                    !vinImg.contains("128") ||
                                    !chasisFrontalIzqImg.contains("128") ||
                                    !chasisTraseroIzqImg.contains("128") ||
                                    !llantasIzqEje1Img.contains("128") ||
                                    !llantasIzqEje2Img.contains("128") ||
                                    !izqRemolqueP2Img.contains("128") ||
                                    !puertasImg.contains("128") ||
                                    !placasImg.contains("128") ||
                                    !sello1Img.contains("128") ||
                                    !sello2Img.contains("128") ||
                                    !derRemolqueP1Img.contains("128") ||
                                    !llantasDerEje2Img.contains("128") ||
                                    !llantasDerEje1Img.contains("128") ||
                                    !chasisTraseroDerImg.contains("128") ||
                                    !chasisFrontalDERImg.contains("128") ||
                                    !derRemolqueP2Img.contains(" 128") ||
                                    sello1S.length() == 0 ||
                                    sello2S.length() == 0 ||
                                    placasDatosD.length() == 0 ||
                                    comentarios.length() == 0 ||
                                    ll1 == "Sin Seleccionar" ||
                                    ll2 == "Sin Seleccionar" ||
                                    ll3 == "Sin Seleccionar" ||
                                    ll4 == "Sin Seleccionar" ||
                                    ll5 == "Sin Seleccionar" ||
                                    ll6 == "Sin Seleccionar" ||
                                    ll7 == "Sin Seleccionar" ||
                                    ll8 == "Sin Seleccionar"

                    ) {
                        Toast.makeText(context, "Datos Incompletos", Toast.LENGTH_SHORT).show();
                    } else if (
                            tractorDerImg.contains("128") &&
                                    tractorIzqImg.contains("128") &&
                                    tractorFrenteImg.contains("128") &&
                                    noEconomicoImg.contains("128") &&
                                    izqRemolqueP1Img.contains("128") &&
                                    vinImg.contains("128") &&
                                    chasisFrontalIzqImg.contains("128") &&
                                    chasisTraseroIzqImg.contains("128") &&
                                    llantasIzqEje1Img.contains("128") &&
                                    llantasIzqEje2Img.contains("128") &&
                                    izqRemolqueP2Img.contains("128") &&
                                    puertasImg.contains("128") &&
                                    placasImg.contains("128") &&
                                    sello1Img.contains("128") &&
                                    sello2Img.contains("128") &&
                                    derRemolqueP1Img.contains("128") &&
                                    llantasDerEje2Img.contains("128") &&
                                    llantasDerEje1Img.contains("128") &&
                                    chasisTraseroDerImg.contains("128") &&
                                    chasisFrontalDERImg.contains("128") &&
                                    derRemolqueP2Img.contains("128") &&
                                    sello1S.length() > 0 &&
                                    sello2S.length() > 0 &&
                                    placasDatosD.length() > 0 &&
                                    comentarios.length() > 0 &&
                                    ll1 != "Sin Seleccionar" &&
                                    ll2 != "Sin Seleccionar" &&
                                    ll3 != "Sin Seleccionar" &&
                                    ll4 != "Sin Seleccionar" &&
                                    ll5 != "Sin Seleccionar" &&
                                    ll6 != "Sin Seleccionar" &&
                                    ll7 != "Sin Seleccionar" &&
                                    ll8 != "Sin Seleccionar"

                    ) {


                        Post6 post6 = new Post6(user, password, idRemolque, "2", mensaje, sello1S, sello2S, ll1,
                                ll2, ll3, ll4, ll5, ll6, ll7, ll8, lljumbo, 0, selloExtra, sello3S, 0, "",
                                placasDatosD,
                                comentarios,
                                S_defensa,
                                S_llantas,
                                S_pisoTractor,
                                S_tanqueDiesel,
                                S_cabinaCompartimientos,
                                S_tanqueAire,
                                S_quintaRueda,
                                S_ejesTransmision,
                                S_tuboEscape,
                                S_motor,
                                S_baseRemolque,
                                S_puerta,
                                S_paredLateralDerecha,
                                S_techos,
                                S_paredFrontal,
                                S_paredLateralIzquierda,
                                S_pisoInterno,
                                S_vvtt,
                                S_IRP1_inspeccionMecanica,
                                S_IRP1_lucesCheck,
                                S_IRP1_luzGaliboIzqFrontalSup,
                                S_IRP1_manitas,
                                S_IRP1_manivela,
                                S_IRP1_patinIzq,
                                S_IRP2_cuartoLadoIzq,
                                S_IRP2_loderaIzq,
                                S_IRP2_lucesCheck,
                                S_IRP2_luzABS,
                                S_IRP2_luzBarcoIzq,
                                S_IRP2_rompevientosIzq,
                                S_LlIE1_birlosPivote,
                                S_LlIE1_llantasPos1,
                                S_LlIE1_llantasPos2,
                                S_LlIE1_masaYoyo,
                                S_LlIE1_rin,
                                S_LlIE1_tieneLodera,
                                S_Pu_bisagrasPuertas,
                                S_Pu_defensa,
                                S_Pu_luzGaliboSupTraseras,
                                S_Pu_plafonesDer,
                                S_Pu_plafonesIzq,
                                S_Pl_luzPlaca,
                                S_Pl_placa,
                                S_S1_sello1,
                                S_S1_altaSeguridad,
                                S_LlDE1_birlosPivote,
                                S_LlDE1_llantasPos5,
                                S_LlDE1_llantasPos6,
                                S_LlDE1_masaYoyo,
                                S_LlDE1_rin,
                                S_LlDE1_tieneLodera,
                                S_DRP1_fondoPlaga,
                                S_DRP1_pisoPlaga,
                                S_DRP1_techoPlaga,
                                S_DRP1_lucesCheck,
                                S_DRP1_luzGaliboDerFrontalSup,
                                S_DRP1_derPlaga,
                                S_DRP1_izqPlaga,
                                S_DRP1_patinDer,
                                S_DRP2_cuartoLadoDer,
                                S_DRP2_loderaDer,
                                S_DRP2_lucesCheck,
                                S_DRP2_luzBarcoDer,
                                S_DRP2_rompevientosDer,
                                S_S2_escotilla,
                                S_S2_sello2,
                                S_S2_altaSeguridad,
                                S_LlIE2_birlosPivote,
                                S_LlIE2_llantasPos3,
                                S_LlIE2_llantasPos4,
                                S_LlIE2_masaYoyo,
                                S_LlIE2_rin,
                                S_LlIE2_tieneLodera,
                                S_LlDE2_birlosPivote,
                                S_LlDE2_llantasPos7,
                                S_LlDE2_llantasPos8,
                                S_LlDE2_masaYoyo,
                                S_LlDE2_rin,
                                S_LlDE2_tieneLodera,
                                S_CFD_amortiguador,
                                S_CFD_bolsaAire,
                                S_CFD_gavilan,
                                S_CFD_muelle,
                                S_CFD_rotachamber,
                                S_CTD_amortiguador,
                                S_CTD_bolsaAire,
                                S_CTD_matraca,
                                S_CTD_muelle,
                                S_CTD_rotachamber,
                                S_CFI_amortiguador,
                                S_CFI_bolsaAire,
                                S_CFI_gavilan,
                                S_CFI_muelle,
                                S_CFI_rotachamber,
                                S_CTI_amortiguador,
                                S_CTI_bolsaAire,
                                S_CTI_matraca,
                                S_CTI_muelle,
                                S_CTI_rotachamber
                        );

                        Call<List<CEnvio>> callenvio2 = dxApi.getEnvio2(post6);

                        callenvio2.enqueue(new Callback<List<CEnvio>>() {
                            @Override
                            public void onResponse(@NotNull Call<List<CEnvio>> call, @NotNull Response<List<CEnvio>> response) {


                                if (!response.isSuccessful()) {
                                    Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                                }


                                List<CEnvio> cEnvio2s = response.body();
                                String mensaje = cEnvio2s.get(0).getMensaje();

                                if (mensaje.contains("Enviado con exito")) {
                                    Intent i = new Intent(imgActivity.this, firmasActivity.class);
                                    i.putExtra("folio", folio);
                                    i.putExtra("mensaje", id);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(imgActivity.this, "Error al Enviar", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(@NotNull Call<List<CEnvio>> call, @NotNull Throwable t) {
                                Toast.makeText(imgActivity.this, "Error 404", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

            }catch (Exception e){

                Toast.makeText(imgActivity.this, "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

        tractoIzq.setOnClickListener(view -> {

            tractoIzq.setEnabled(false);
            imgClick("tractorIzq" , REQUEST_TRACTO_IZQ);


        });
        tractoDer.setOnClickListener(view -> {

            tractoDer.setEnabled(false);
            imgClick("tractoDer" , REQUEST_TRACTO_DER);


        });
        tractoFrente.setOnClickListener(view -> {

            tractoFrente.setEnabled(false);
            imgClick("tractorFrente" , REQUEST_TRACTO_FRENTE);


        });
        noEconomico.setOnClickListener(view -> {

            noEconomico.setEnabled(false);
            imgClick("noEconomico" , REQUEST_NoECONOMICO);
        });
        izqRemolqueP1.setOnClickListener(view -> {
            izqRemolqueP1.setEnabled(false);
            imgClick("izqRemolqueP1" , REQUEST_IZQ_REMOLQUE_P1);
        });
        vin.setOnClickListener(view -> {
            vin.setEnabled(false);
            imgClick("vin" , REQUEST_VIN);
        });
        chasisFrontalIzq.setOnClickListener(view -> {
            chasisFrontalIzq.setEnabled(false);
            imgClick("chasisFrontalIzq" , REQUEST_CHASIS_FRONTAL_IZQ);
        });
        chasisTraseroIzq.setOnClickListener(view -> {
            chasisTraseroIzq.setEnabled(false);
            imgClick("chasisTraseroIzq" , REQUEST_CHASIS_TRASERO_IZQ);
        });
        llantasIzqEje1.setOnClickListener(view -> {
            llantasIzqEje1.setEnabled(false);
            imgClick("llantasIzqEje1" , REQUEST_LLANTAS_IZQ_EJE1);
        });
        llantasIzqEje2.setOnClickListener(view -> {
            llantasIzqEje2.setEnabled(false);
            imgClick("llantasIzqEje2" , REQUEST__LLANTAS_IZQ_EJE2);
        });
        izqRemolqueP2.setOnClickListener(view -> {
            izqRemolqueP2.setEnabled(false);
            imgClick("izqRemolqueP2" , REQUEST_IZQ_REMOLQUE_P2);
        });
        puertas.setOnClickListener(view -> {
            puertas.setEnabled(false);
            imgClick("puertas" , REQUEST_PUERTAS);
        });
        placas.setOnClickListener(view -> {
            placas.setEnabled(false);
            imgClick("placas" , REQUEST_PLACAS);
        });
        sello1.setOnClickListener(view -> {
            sello1.setEnabled(false);
            imgClick("sello1" , REQUEST_SELLO1);
        });
        sello2.setOnClickListener(view -> {
            sello2.setEnabled(false);
            imgClick("sello2" , REQUEST_SELLO2);
        });
        sello3.setOnClickListener(view -> {
            sello3.setEnabled(false);
            imgClick("sello3" , REQUEST_SELLO3);
        });
        derRemolqueP1.setOnClickListener(view -> {
            derRemolqueP1.setEnabled(false);
            imgClick("derRemolqueP1" , REQUEST_DER_REMOLQUE_P1);
        });
        llantasDerEje2.setOnClickListener(view -> {
            llantasDerEje2.setEnabled(false);
            imgClick("llantasDerEje2" , REQUEST_LLANTAS_DER_EJE2);
        });
        llantasDerEje1.setOnClickListener(view -> {
            llantasDerEje1.setEnabled(false);
            imgClick("llantasDerEje1" , REQUEST_LLANTAS_DER_EJE1);
        });
        chasisTraseroDer.setOnClickListener(view -> {
            chasisTraseroDer.setEnabled(false);
            imgClick("chasisTraseroDer" , REQUEST_CHASIS_TRASERO_DER);
        });
        chasisFrontalDER.setOnClickListener(view -> {
            chasisFrontalDER.setEnabled(false);
            imgClick("chasisFrontalDER" , REQUEST_CHASIS_FRONTAL_DER);
        });
        derRemolqueP2.setOnClickListener(view -> {
            derRemolqueP2.setEnabled(false);
            imgClick("derRemolqueP2" , REQUEST_DER_REMOLQUE_P2);
        });
        damage1.setOnClickListener(view -> {
            damage1.setEnabled(false);
            imgClick("damage1IZQ" , DAMAGE1);
        });
        damage2.setOnClickListener(view -> {
            damage2.setEnabled(false);
            imgClick("damage2IZQ" , DAMAGE2);
        });
        damage3.setOnClickListener(view -> {
            damage3.setEnabled(false);
            imgClick("damage3IZQ" , DAMAGE3);
        });
        damage4.setOnClickListener(view -> {
            damage4.setEnabled(false);
            imgClick("damage4IZQ" , DAMAGE4);
        });
        damage5.setOnClickListener(view -> {
            damage5.setEnabled(false);
            imgClick("damage5TRA" , DAMAGE5);
        });
        damage6.setOnClickListener(view -> {
            damage6.setEnabled(false);
            imgClick("damage6TRA" , DAMAGE6);
        });
        damage7.setOnClickListener(view -> {
            damage7.setEnabled(false);
            imgClick("damage7TRA" , DAMAGE7);
        });
        damage8.setOnClickListener(view -> {
            damage8.setEnabled(false);
            imgClick("damage8TRA" , DAMAGE8);
        });
        damage9.setOnClickListener(view -> {
            damage9.setEnabled(false);
            imgClick("damage9DER" , DAMAGE9);
        });
        damage10.setOnClickListener(view -> {
            damage10.setEnabled(false);
            imgClick("damage10DER" , DAMAGE10);
        });
        damage11.setOnClickListener(view -> {
            damage11.setEnabled(false);
            imgClick("damage11DER" , DAMAGE11);
        });
        damage12.setOnClickListener(view -> {
            damage12.setEnabled(false);
            imgClick("damage12DER" , DAMAGE12);
        });

    }

    private void imgClick (String photo , int code){

        try{
        imageFileName = null ;
        imageFile = null ;
            Uri photoURI;
        imageFileName = photo+ "-Folio"+folio ;

            imageFile = File.createTempFile(
                    imageFileName,  // prefix
                    ".jpeg",         // suffix
                    destPath      // directory
            );
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", imageFile);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(camera,code);

        }catch (Exception e ){

            Toast.makeText(imgActivity.this, "Error" + e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        try{
            int THUMBSIZE = 128;
            switch (requestCode) {

                case REQUEST_CODE_SIGN_IN:
                    if (resultCode == Activity.RESULT_OK && resultData != null) {
                        String a = "";
                    }
                    break;

                case REQUEST_TRACTO_DER:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_TRACTO_DER);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        tractoDer.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_TRACTO_DER);

                    }else {
                        tractoDer.setEnabled(true);
                    }

                    break;
                case REQUEST_TRACTO_FRENTE:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_TRACTO_FRENTE);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        tractoFrente.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_TRACTO_FRENTE);

                    }else {
                        tractoFrente.setEnabled(true);
                    }

                    break;
                case REQUEST_TRACTO_IZQ:
                    if (resultCode == Activity.RESULT_OK ) {

                        // uploadServer(REQUEST_TRACTO_IZQ);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        tractoIzq.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_TRACTO_IZQ);

                    }else {
                        tractoIzq.setEnabled(true);
                    }

                    break;
                case REQUEST_NoECONOMICO:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_NoECONOMICO);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        noEconomico.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_NoECONOMICO);

                    } else {
                        noEconomico.setEnabled(true);
                    }
                    break;
                case REQUEST_IZQ_REMOLQUE_P1:
                    if (resultCode == Activity.RESULT_OK ) {

                        // uploadServer(REQUEST_IZQ_REMOLQUE_P1);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);
                        izqRemolqueP1.setImageBitmap(thumbImage);

                        upload up = new upload(this);
                        up.execute(REQUEST_IZQ_REMOLQUE_P1);


                    }else {
                        izqRemolqueP1.setEnabled(true);
                    }
                    break;

                case REQUEST_VIN:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_VIN);
                        Bitmap  thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        vin.setImageBitmap(thumbImage);

                        upload up = new upload(this);
                        up.execute(REQUEST_VIN);


                    }else {
                        vin.setEnabled(true);
                    }
                    break;

                case REQUEST_CHASIS_FRONTAL_IZQ:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_CHASIS_FRONTAL_IZQ);
                        Bitmap  thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        chasisFrontalIzq.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_CHASIS_FRONTAL_IZQ);



                    }else {
                        chasisFrontalIzq.setEnabled(true);
                    }
                    break;

                case REQUEST_CHASIS_TRASERO_IZQ:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_CHASIS_TRASERO_IZQ);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        chasisTraseroIzq.setImageBitmap(thumbImage);

                        upload up = new upload(this);
                        up.execute(REQUEST_CHASIS_TRASERO_IZQ);

                    }else {
                        chasisTraseroIzq.setEnabled(true);
                    }
                    break;
                case REQUEST_LLANTAS_IZQ_EJE1:
                    if (resultCode == Activity.RESULT_OK ) {
                        //uploadServer(REQUEST_LLANTAS_IZQ_EJE1);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        llantasIzqEje1.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_LLANTAS_IZQ_EJE1);





                    }else {
                        llantasIzqEje1.setEnabled(true);
                    }
                    break;
                case REQUEST__LLANTAS_IZQ_EJE2:
                    if (resultCode == Activity.RESULT_OK ) {


                        //uploadServer(REQUEST__LLANTAS_IZQ_EJE2);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        llantasIzqEje2.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST__LLANTAS_IZQ_EJE2);


                    }else {
                        llantasIzqEje2.setEnabled(true);
                    }
                    break;
                case REQUEST_IZQ_REMOLQUE_P2:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_IZQ_REMOLQUE_P2);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        izqRemolqueP2.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_IZQ_REMOLQUE_P2);


                    }else {
                        izqRemolqueP2.setEnabled(true);
                    }
                    break;
                case REQUEST_PUERTAS:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_PUERTAS);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        puertas.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_PUERTAS);


                    }else {
                        puertas.setEnabled(true);
                    }
                    break;
                case REQUEST_PLACAS:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_PLACAS);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        placas.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_PLACAS);


                    }else {
                        placas.setEnabled(true);
                    }
                    break;
                case REQUEST_SELLO1:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_SELLO1);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        sello1.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_SELLO1);


                    }else {
                        sello1.setEnabled(true);
                    }
                    break;
                case REQUEST_SELLO2:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_SELLO2);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        sello2.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_SELLO2);


                    }else {
                        sello2.setEnabled(true);
                    }
                    break;
                case REQUEST_DER_REMOLQUE_P1:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_DER_REMOLQUE_P1);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        derRemolqueP1.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_DER_REMOLQUE_P1);


                    }else {
                        derRemolqueP1.setEnabled(true);
                    }
                    break;
                case REQUEST_LLANTAS_DER_EJE2:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_LLANTAS_DER_EJE2);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        llantasDerEje2.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_LLANTAS_DER_EJE2);


                    }else {
                        llantasDerEje2.setEnabled(true);
                    }
                    break;
                case REQUEST_LLANTAS_DER_EJE1:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_LLANTAS_DER_EJE1);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        llantasDerEje1.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_LLANTAS_DER_EJE1);


                    }else {
                        llantasDerEje1.setEnabled(true);
                    }
                    break;
                case REQUEST_CHASIS_TRASERO_DER:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_CHASIS_TRASERO_DER);

                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        chasisTraseroDer.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_CHASIS_TRASERO_DER);

                    }else {
                        chasisTraseroDer.setEnabled(true);
                    }
                    break;
                case REQUEST_CHASIS_FRONTAL_DER:
                    if (resultCode == Activity.RESULT_OK ) {


                        //uploadServer(REQUEST_CHASIS_FRONTAL_DER);

                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        chasisFrontalDER.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_CHASIS_FRONTAL_DER);


                    }else {
                        chasisFrontalDER.setEnabled(true);
                    }
                    break;
                case REQUEST_DER_REMOLQUE_P2:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(REQUEST_DER_REMOLQUE_P2);

                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        derRemolqueP2.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_DER_REMOLQUE_P2);


                    }else {
                        derRemolqueP2.setEnabled(true);
                    }
                    break;
                case REQUEST_SELLO3:
                    if (resultCode == Activity.RESULT_OK ) {

                        // uploadServer(REQUEST_SELLO3);

                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        sello3.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(REQUEST_SELLO3);



                    }else {
                        sello3.setEnabled(true);
                    }
                    break;
                case DAMAGE1:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE1);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage1.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE1);


                    }else {
                        damage1.setEnabled(true);
                    }
                    break;
                case DAMAGE2:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE2);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage2.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE2);


                    }else {
                        damage2.setEnabled(true);
                    }
                    break;
                case DAMAGE3:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE3);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage3.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE3);


                    }else {
                        damage3.setEnabled(true);
                    }
                    break;
                case DAMAGE4:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE4);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage4.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE4);


                    }else {
                        damage4.setEnabled(true);
                    }
                    break;
                case DAMAGE5:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE5);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage5.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE5);


                    }else {
                        damage5.setEnabled(true);
                    }
                    break;
                case DAMAGE6:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE6);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage6.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE6);


                    }else {
                        damage6.setEnabled(true);
                    }
                    break;
                case DAMAGE7:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE7);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage7.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE7);


                    }else {
                        damage7.setEnabled(true);
                    }
                    break;
                case DAMAGE8:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE8);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage8.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE8);


                    }else {
                        damage8.setEnabled(true);
                    }
                    break;
                case DAMAGE9:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE9);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage9.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE9);


                    }else {
                        damage9.setEnabled(true);
                    }
                    break;
                case DAMAGE10:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE10);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage10.setImageBitmap(thumbImage);

                        upload up = new upload(this);
                        up.execute(DAMAGE10);


                    }else {
                        damage10.setEnabled(true);
                    }
                    break;
                case DAMAGE11:
                    if (resultCode == Activity.RESULT_OK ) {

                        //uploadServer(DAMAGE11);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage11.setImageBitmap(thumbImage);

                        upload up = new upload(this);
                        up.execute(DAMAGE11);


                    }else {
                        damage11.setEnabled(true);
                    }
                    break;
                case DAMAGE12:
                    if (resultCode == Activity.RESULT_OK ) {


                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                                BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                                THUMBSIZE,
                                THUMBSIZE);

                        damage12.setImageBitmap(thumbImage);
                        upload up = new upload(this);
                        up.execute(DAMAGE12);



                    }else {
                        damage12.setEnabled(true);
                    }
                    break;



            }
        }catch (Exception e ){

            Toast.makeText(imgActivity.this, "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
        }




        super.onActivityResult(requestCode, resultCode, resultData);
    }



    @Override
    public void onBackPressed() {

    }



    private static  class upload extends AsyncTask<Integer,Void,Void>{

        boolean exists = false;
        boolean paso = false ;
        int codigo2 ;

        private WeakReference<imgActivity> activityReference;

        upload(imgActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

          /* if(paso == false ){
                switch (codigo2){
                    case REQUEST_TRACTO_DER:

                        tractoDer.setImageBitmap(null);
                        tractoDer.setBackgroundColor(Color.parseColor("#074EAB"));
                        tractoDer.setEnabled(true);

                        break;
                    case REQUEST_TRACTO_FRENTE:

                        tractoFrente.setImageBitmap(null);
                        tractoFrente.setBackgroundColor(Color.parseColor("#074EAB"));
                        tractoFrente.setEnabled(true);

                        break;
                    case REQUEST_TRACTO_IZQ:

                        tractoIzq.setImageBitmap(null);
                        tractoIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                        tractoIzq.setEnabled(true);

                        break;
                    case REQUEST_NoECONOMICO:

                        noEconomico.setImageBitmap(null);
                        noEconomico.setBackgroundColor(Color.parseColor("#074EAB"));
                        noEconomico.setEnabled(true);

                        break;
                    case REQUEST_IZQ_REMOLQUE_P1:


                        izqRemolqueP1.setImageBitmap(null);
                        izqRemolqueP1.setBackgroundColor(Color.parseColor("#074EAB"));
                        izqRemolqueP1.setEnabled(true);



                        break;
                    case REQUEST_VIN:

                        vin.setImageBitmap(null);
                        vin.setBackgroundColor(Color.parseColor("#074EAB"));
                        vin.setEnabled(true);


                        break;
                    case REQUEST_CHASIS_FRONTAL_IZQ:


                        chasisFrontalIzq.setImageBitmap(null);
                        chasisFrontalIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                        chasisFrontalIzq.setEnabled(true);

                        break;
                    case REQUEST_CHASIS_TRASERO_IZQ:


                        chasisTraseroIzq.setImageBitmap(null);
                        chasisTraseroIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                        chasisTraseroIzq.setEnabled(true);

                        break;
                    case REQUEST_LLANTAS_IZQ_EJE1:


                        llantasIzqEje1.setImageBitmap(null);
                        llantasIzqEje1.setBackgroundColor(Color.parseColor("#074EAB"));
                        llantasIzqEje1.setEnabled(true);



                        break;
                    case REQUEST__LLANTAS_IZQ_EJE2:

                        llantasIzqEje2.setImageBitmap(null);
                        llantasIzqEje2.setBackgroundColor(Color.parseColor("#074EAB"));
                        llantasIzqEje2.setEnabled(true);

                        break;
                    case REQUEST_IZQ_REMOLQUE_P2:


                        izqRemolqueP2.setImageBitmap(null);
                        izqRemolqueP2.setBackgroundColor(Color.parseColor("#074EAB"));
                        izqRemolqueP2.setEnabled(true);



                        break;
                    case REQUEST_PUERTAS:


                        puertas.setImageBitmap(null);
                        puertas.setBackgroundColor(Color.parseColor("#074EAB"));
                        puertas.setEnabled(true);

                        break;
                    case REQUEST_PLACAS:

                        placas.setImageBitmap(null);
                        placas.setBackgroundColor(Color.parseColor("#074EAB"));
                        placas.setEnabled(true);

                        break;
                    case REQUEST_SELLO1:


                        sello1.setImageBitmap(null);
                        sello1.setBackgroundColor(Color.parseColor("#074EAB"));
                        sello1.setEnabled(true);



                        break;
                    case REQUEST_SELLO2:


                        sello2.setImageBitmap(null);
                        sello2.setBackgroundColor(Color.parseColor("#074EAB"));
                        sello2.setEnabled(true);

                        break;
                    case REQUEST_DER_REMOLQUE_P1:



                        derRemolqueP1.setImageBitmap(null);
                        derRemolqueP1.setBackgroundColor(Color.parseColor("#074EAB"));
                        derRemolqueP1.setEnabled(true);

                        break;
                    case REQUEST_LLANTAS_DER_EJE2:


                        llantasDerEje2.setImageBitmap(null);
                        llantasDerEje2.setBackgroundColor(Color.parseColor("#074EAB"));
                        llantasDerEje2.setEnabled(true);

                        break;
                    case REQUEST_LLANTAS_DER_EJE1:


                        llantasDerEje1.setImageBitmap(null);
                        llantasDerEje1.setBackgroundColor(Color.parseColor("#074EAB"));
                        llantasDerEje1.setEnabled(true);

                        break;
                    case REQUEST_CHASIS_TRASERO_DER:


                        chasisTraseroDer.setImageBitmap(null);
                        chasisTraseroDer.setBackgroundColor(Color.parseColor("#074EAB"));
                        chasisTraseroDer.setEnabled(true);

                        break;
                    case REQUEST_CHASIS_FRONTAL_DER:


                        chasisFrontalDER.setImageBitmap(null);
                        chasisFrontalDER.setBackgroundColor(Color.parseColor("#074EAB"));
                        chasisFrontalDER.setEnabled(true);

                        break;
                    case REQUEST_DER_REMOLQUE_P2:


                        derRemolqueP2.setImageBitmap(null);
                        derRemolqueP2.setBackgroundColor(Color.parseColor("#074EAB"));
                        derRemolqueP2.setEnabled(true);

                        break;
                    case REQUEST_SELLO3:


                        sello3.setImageBitmap(null);
                        sello3.setBackgroundColor(Color.parseColor("#074EAB"));
                        sello3.setEnabled(true);

                        break;
                    case DAMAGE1:


                        damage1.setImageBitmap(null);
                        damage1.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage1.setEnabled(true);

                        break;
                    case DAMAGE2:


                        damage2.setImageBitmap(null);
                        damage2.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage2.setEnabled(true);

                        break;
                    case DAMAGE3:


                        damage3.setImageBitmap(null);
                        damage3.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage3.setEnabled(true);

                        break;
                    case DAMAGE4:


                        damage4.setImageBitmap(null);
                        damage4.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage4.setEnabled(true);

                        break;
                    case DAMAGE5:


                        damage5.setImageBitmap(null);
                        damage5.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage5.setEnabled(true);

                        break;
                    case DAMAGE6:


                        damage6.setImageBitmap(null);
                        damage6.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage6.setEnabled(true);

                        break;
                    case DAMAGE7:


                        damage7.setImageBitmap(null);
                        damage7.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage7.setEnabled(true);

                        break;
                    case DAMAGE8:


                        damage8.setImageBitmap(null);
                        damage8.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage8.setEnabled(true);

                        break;
                    case DAMAGE9:


                        damage9.setImageBitmap(null);
                        damage9.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage9.setEnabled(true);

                        break;
                    case DAMAGE10:


                        damage10.setImageBitmap(null);
                        damage10.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage10.setEnabled(true);

                        break;
                    case DAMAGE11:


                        damage11.setImageBitmap(null);
                        damage11.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage11.setEnabled(true);

                        break;
                    case DAMAGE12:


                        damage12.setImageBitmap(null);
                        damage12.setBackgroundColor(Color.parseColor("#074EAB"));
                        damage12.setEnabled(true);

                        break;


                }
                AlertDialog.Builder builder = new AlertDialog.Builder(imgActivity.this);
                builder.setMessage("Sin respuesta del server ")
                        .setCancelable(false)
                        .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alert = builder.create();
                alert.show();
            }*/



        }

        @Override
        protected Void doInBackground(Integer... integers) {

            int codigo = integers[0];
            codigo2 = codigo;

            imgActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return null;


            try {

                if(activity.bm1!=null)
                {
                    activity.bm1.recycle();
                    activity.bm1=null;
                }

                String path = activity.imageFile.getPath();
                activity.bm1 = BitmapFactory.decodeFile(path);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                activity.bm1.compress(Bitmap.CompressFormat.JPEG, 30, stream);
                byte [] byte_arr = new byte[3000];
                byte_arr = stream.toByteArray();
                String base64 = new String(Base64.encode(byte_arr,Base64.NO_WRAP));

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                        .callTimeout(3, TimeUnit.MINUTES)
                        .connectTimeout(3, TimeUnit.MINUTES)
                        .readTimeout(2, TimeUnit.MINUTES)
                        .writeTimeout(2, TimeUnit.MINUTES);

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://"+activity.ip+"/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        ;

                builder.client(httpClient.build());

                Retrofit retrofit = builder.build();

                activity.dxApi = retrofit.create(DxApi.class);


                Post5 post5 = new Post5(activity.user,activity.password,activity.imageFileName,base64,activity.folio);
                Call<String> callImg = activity.dxApi.getImg(post5);

                callImg.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(activity, "Error 500", Toast.LENGTH_LONG).show();
                        }


                        Toast.makeText(activity, "Imagen Cargada Correctamente", Toast.LENGTH_LONG).show();


                    }

                    @Override
                    public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage("Sin respuesta del server " + t.getMessage())
                                .setCancelable(false)
                                .setPositiveButton("Entendido", (dialog, which) -> dialog.cancel());
                        activity.alert = builder.create();
                        activity.alert.show();
                        switch (codigo){
                            case REQUEST_TRACTO_DER:

                                activity.tractoDer.setImageBitmap(null);
                                activity.tractoDer.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.tractoDer.setEnabled(true);

                                break;
                            case REQUEST_TRACTO_FRENTE:

                                activity.tractoFrente.setImageBitmap(null);
                                activity.tractoFrente.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.tractoFrente.setEnabled(true);

                                break;
                            case REQUEST_TRACTO_IZQ:

                                activity.tractoIzq.setImageBitmap(null);
                                activity.tractoIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.tractoIzq.setEnabled(true);

                                break;
                            case REQUEST_NoECONOMICO:

                                activity.noEconomico.setImageBitmap(null);
                                activity.noEconomico.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.noEconomico.setEnabled(true);

                                break;
                            case REQUEST_IZQ_REMOLQUE_P1:


                                activity.izqRemolqueP1.setImageBitmap(null);
                                activity.izqRemolqueP1.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.izqRemolqueP1.setEnabled(true);



                                break;
                            case REQUEST_VIN:

                                activity.vin.setImageBitmap(null);
                                activity.vin.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.vin.setEnabled(true);


                                break;
                            case REQUEST_CHASIS_FRONTAL_IZQ:


                                activity.chasisFrontalIzq.setImageBitmap(null);
                                activity.chasisFrontalIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.chasisFrontalIzq.setEnabled(true);

                                break;
                            case REQUEST_CHASIS_TRASERO_IZQ:


                                activity.chasisTraseroIzq.setImageBitmap(null);
                                activity.chasisTraseroIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.chasisTraseroIzq.setEnabled(true);

                                break;
                            case REQUEST_LLANTAS_IZQ_EJE1:


                                activity.llantasIzqEje1.setImageBitmap(null);
                                activity.llantasIzqEje1.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.llantasIzqEje1.setEnabled(true);



                                break;
                            case REQUEST__LLANTAS_IZQ_EJE2:

                                activity.llantasIzqEje2.setImageBitmap(null);
                                activity.llantasIzqEje2.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.llantasIzqEje2.setEnabled(true);

                                break;
                            case REQUEST_IZQ_REMOLQUE_P2:


                                activity.izqRemolqueP2.setImageBitmap(null);
                                activity.izqRemolqueP2.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.izqRemolqueP2.setEnabled(true);



                                break;
                            case REQUEST_PUERTAS:


                                activity.puertas.setImageBitmap(null);
                                activity.puertas.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.puertas.setEnabled(true);

                                break;
                            case REQUEST_PLACAS:

                                activity.placas.setImageBitmap(null);
                                activity.placas.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.placas.setEnabled(true);

                                break;
                            case REQUEST_SELLO1:


                                activity.sello1.setImageBitmap(null);
                                activity.sello1.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.sello1.setEnabled(true);



                                break;
                            case REQUEST_SELLO2:


                                activity.sello2.setImageBitmap(null);
                                activity.sello2.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.sello2.setEnabled(true);

                                break;
                            case REQUEST_DER_REMOLQUE_P1:



                                activity.derRemolqueP1.setImageBitmap(null);
                                activity.derRemolqueP1.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.derRemolqueP1.setEnabled(true);

                                break;
                            case REQUEST_LLANTAS_DER_EJE2:


                                activity.llantasDerEje2.setImageBitmap(null);
                                activity.llantasDerEje2.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.llantasDerEje2.setEnabled(true);

                                break;
                            case REQUEST_LLANTAS_DER_EJE1:


                                activity.llantasDerEje1.setImageBitmap(null);
                                activity.llantasDerEje1.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.llantasDerEje1.setEnabled(true);

                                break;
                            case REQUEST_CHASIS_TRASERO_DER:


                                activity.chasisTraseroDer.setImageBitmap(null);
                                activity.chasisTraseroDer.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.chasisTraseroDer.setEnabled(true);

                                break;
                            case REQUEST_CHASIS_FRONTAL_DER:


                                activity.chasisFrontalDER.setImageBitmap(null);
                                activity.chasisFrontalDER.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.chasisFrontalDER.setEnabled(true);

                                break;
                            case REQUEST_DER_REMOLQUE_P2:


                                activity.derRemolqueP2.setImageBitmap(null);
                                activity.derRemolqueP2.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.derRemolqueP2.setEnabled(true);

                                break;
                            case REQUEST_SELLO3:


                                activity.sello3.setImageBitmap(null);
                                activity.sello3.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.sello3.setEnabled(true);

                                break;
                            case DAMAGE1:


                                activity.damage1.setImageBitmap(null);
                                activity.damage1.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage1.setEnabled(true);

                                break;
                            case DAMAGE2:


                                activity.damage2.setImageBitmap(null);
                                activity.damage2.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage2.setEnabled(true);

                                break;
                            case DAMAGE3:


                                activity.damage3.setImageBitmap(null);
                                activity.damage3.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage3.setEnabled(true);

                                break;
                            case DAMAGE4:


                                activity.damage4.setImageBitmap(null);
                                activity.damage4.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage4.setEnabled(true);

                                break;
                            case DAMAGE5:


                                activity.damage5.setImageBitmap(null);
                                activity.damage5.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage5.setEnabled(true);

                                break;
                            case DAMAGE6:


                                activity.damage6.setImageBitmap(null);
                                activity.damage6.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage6.setEnabled(true);

                                break;
                            case DAMAGE7:


                                activity.damage7.setImageBitmap(null);
                                activity.damage7.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage7.setEnabled(true);

                                break;
                            case DAMAGE8:


                                activity.damage8.setImageBitmap(null);
                                activity.damage8.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage8.setEnabled(true);

                                break;
                            case DAMAGE9:


                                activity.damage9.setImageBitmap(null);
                                activity.damage9.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage9.setEnabled(true);

                                break;
                            case DAMAGE10:


                                activity.damage10.setImageBitmap(null);
                                activity.damage10.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage10.setEnabled(true);

                                break;
                            case DAMAGE11:


                                activity.damage11.setImageBitmap(null);
                                activity.damage11.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage11.setEnabled(true);

                                break;
                            case DAMAGE12:


                                activity.damage12.setImageBitmap(null);
                                activity.damage12.setBackgroundColor(Color.parseColor("#074EAB"));
                                activity.damage12.setEnabled(true);

                                break;


                        }


                    }
                });
            } catch (Exception e) {

                String exe = e.getMessage();

                ((imgActivity)activity.context).runOnUiThread(() -> {

                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("Sin respuesta del server " + exe )
                            .setCancelable(false)
                            .setPositiveButton("Entendido", (dialog, which) -> dialog.cancel());
                    activity.alert = builder.create();
                    activity.alert.show();
                    switch (codigo){
                        case REQUEST_TRACTO_DER:

                            activity.tractoDer.setImageBitmap(null);
                            activity.tractoDer.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.tractoDer.setEnabled(true);

                            break;
                        case REQUEST_TRACTO_FRENTE:

                            activity.tractoFrente.setImageBitmap(null);
                            activity.tractoFrente.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.tractoFrente.setEnabled(true);

                            break;
                        case REQUEST_TRACTO_IZQ:

                            activity.tractoIzq.setImageBitmap(null);
                            activity.tractoIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.tractoIzq.setEnabled(true);

                            break;
                        case REQUEST_NoECONOMICO:

                            activity.noEconomico.setImageBitmap(null);
                            activity.noEconomico.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.noEconomico.setEnabled(true);

                            break;
                        case REQUEST_IZQ_REMOLQUE_P1:


                            activity.izqRemolqueP1.setImageBitmap(null);
                            activity.izqRemolqueP1.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.izqRemolqueP1.setEnabled(true);



                            break;
                        case REQUEST_VIN:

                            activity.vin.setImageBitmap(null);
                            activity.vin.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.vin.setEnabled(true);


                            break;
                        case REQUEST_CHASIS_FRONTAL_IZQ:


                            activity.chasisFrontalIzq.setImageBitmap(null);
                            activity.chasisFrontalIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.chasisFrontalIzq.setEnabled(true);

                            break;
                        case REQUEST_CHASIS_TRASERO_IZQ:


                            activity.chasisTraseroIzq.setImageBitmap(null);
                            activity.chasisTraseroIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.chasisTraseroIzq.setEnabled(true);

                            break;
                        case REQUEST_LLANTAS_IZQ_EJE1:


                            activity.llantasIzqEje1.setImageBitmap(null);
                            activity.llantasIzqEje1.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.llantasIzqEje1.setEnabled(true);



                            break;
                        case REQUEST__LLANTAS_IZQ_EJE2:

                            activity.llantasIzqEje2.setImageBitmap(null);
                            activity.llantasIzqEje2.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.llantasIzqEje2.setEnabled(true);

                            break;
                        case REQUEST_IZQ_REMOLQUE_P2:


                            activity.izqRemolqueP2.setImageBitmap(null);
                            activity.izqRemolqueP2.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.izqRemolqueP2.setEnabled(true);



                            break;
                        case REQUEST_PUERTAS:


                            activity.puertas.setImageBitmap(null);
                            activity.puertas.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.puertas.setEnabled(true);

                            break;
                        case REQUEST_PLACAS:

                            activity.placas.setImageBitmap(null);
                            activity.placas.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.placas.setEnabled(true);

                            break;
                        case REQUEST_SELLO1:


                            activity.sello1.setImageBitmap(null);
                            activity.sello1.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.sello1.setEnabled(true);



                            break;
                        case REQUEST_SELLO2:


                            activity.sello2.setImageBitmap(null);
                            activity.sello2.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.sello2.setEnabled(true);

                            break;
                        case REQUEST_DER_REMOLQUE_P1:



                            activity.derRemolqueP1.setImageBitmap(null);
                            activity.derRemolqueP1.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.derRemolqueP1.setEnabled(true);

                            break;
                        case REQUEST_LLANTAS_DER_EJE2:


                            activity.llantasDerEje2.setImageBitmap(null);
                            activity.llantasDerEje2.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.llantasDerEje2.setEnabled(true);

                            break;
                        case REQUEST_LLANTAS_DER_EJE1:


                            activity.llantasDerEje1.setImageBitmap(null);
                            activity.llantasDerEje1.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.llantasDerEje1.setEnabled(true);

                            break;
                        case REQUEST_CHASIS_TRASERO_DER:


                            activity.chasisTraseroDer.setImageBitmap(null);
                            activity.chasisTraseroDer.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.chasisTraseroDer.setEnabled(true);

                            break;
                        case REQUEST_CHASIS_FRONTAL_DER:


                            activity.chasisFrontalDER.setImageBitmap(null);
                            activity.chasisFrontalDER.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.chasisFrontalDER.setEnabled(true);

                            break;
                        case REQUEST_DER_REMOLQUE_P2:


                            activity.derRemolqueP2.setImageBitmap(null);
                            activity.derRemolqueP2.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.derRemolqueP2.setEnabled(true);

                            break;
                        case REQUEST_SELLO3:


                            activity.sello3.setImageBitmap(null);
                            activity.sello3.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.sello3.setEnabled(true);

                            break;
                        case DAMAGE1:


                            activity.damage1.setImageBitmap(null);
                            activity.damage1.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage1.setEnabled(true);

                            break;
                        case DAMAGE2:


                            activity.damage2.setImageBitmap(null);
                            activity.damage2.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage2.setEnabled(true);

                            break;
                        case DAMAGE3:


                            activity.damage3.setImageBitmap(null);
                            activity.damage3.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage3.setEnabled(true);

                            break;
                        case DAMAGE4:


                            activity.damage4.setImageBitmap(null);
                            activity.damage4.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage4.setEnabled(true);

                            break;
                        case DAMAGE5:


                            activity.damage5.setImageBitmap(null);
                            activity.damage5.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage5.setEnabled(true);

                            break;
                        case DAMAGE6:


                            activity.damage6.setImageBitmap(null);
                            activity.damage6.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage6.setEnabled(true);

                            break;
                        case DAMAGE7:


                            activity.damage7.setImageBitmap(null);
                            activity.damage7.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage7.setEnabled(true);

                            break;
                        case DAMAGE8:


                            activity.damage8.setImageBitmap(null);
                            activity.damage8.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage8.setEnabled(true);

                            break;
                        case DAMAGE9:


                            activity.damage9.setImageBitmap(null);
                            activity.damage9.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage9.setEnabled(true);

                            break;
                        case DAMAGE10:


                            activity.damage10.setImageBitmap(null);
                            activity.damage10.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage10.setEnabled(true);

                            break;
                        case DAMAGE11:


                            activity.damage11.setImageBitmap(null);
                            activity.damage11.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage11.setEnabled(true);

                            break;
                        case DAMAGE12:


                            activity.damage12.setImageBitmap(null);
                            activity.damage12.setBackgroundColor(Color.parseColor("#074EAB"));
                            activity.damage12.setEnabled(true);

                            break;


                    }
                });



            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
                    tractoDer.setImageBitmap(null);
                    tractoFrente.setImageBitmap(null);
                    tractoIzq.setImageBitmap(null);
                    noEconomico.setImageBitmap(null);
                    izqRemolqueP1.setImageBitmap(null);
                    vin.setImageBitmap(null);
                    chasisFrontalIzq.setImageBitmap(null);
                    chasisTraseroIzq.setImageBitmap(null);
                    llantasIzqEje1.setImageBitmap(null);
                    llantasIzqEje2.setImageBitmap(null);
                    izqRemolqueP2.setImageBitmap(null);
                    puertas.setImageBitmap(null);
                    placas.setImageBitmap(null);
                    sello1.setImageBitmap(null);
                    sello2.setImageBitmap(null);
                    derRemolqueP1.setImageBitmap(null);
                    llantasDerEje2.setImageBitmap(null);
                    llantasDerEje1.setImageBitmap(null);
                    chasisTraseroDer.setImageBitmap(null);
                    chasisFrontalDER.setImageBitmap(null);
                    derRemolqueP2.setImageBitmap(null);
                    sello3.setImageBitmap(null);
                    damage1.setImageBitmap(null);
                    damage2.setImageBitmap(null);
                    damage3.setImageBitmap(null);
                    damage4.setImageBitmap(null);
                    damage5.setImageBitmap(null);
                    damage6.setImageBitmap(null);
                    damage7.setImageBitmap(null);
                    damage8.setImageBitmap(null);
                    damage9.setImageBitmap(null);
                    damage10.setImageBitmap(null);
                    damage11.setImageBitmap(null);
                    damage12.setImageBitmap(null);
                    context = null ;

    }
}
