package com.dx.dxintercambio;

import androidx.annotation.NonNull;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private CheckBox jumboRB1  , piso ,tanquedeComb , diferencial , cabina
    , cilindrosDeAire,mofleEscape , manivela ,puertasTraseras, sellos, lucesTraseras, cuartosRojos, lucesDeAltaTraseras, luzDePlace,
    placa, zoqueteras, guardaPolvo, loderas , remolque , lucesLateralesAmbar, chasis , lucesdeFrentem ,paredes , llantaDeRefaccion , cuartosAmbar;
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
    private CheckBox defensa ;
    private CheckBox llantas ;

    private CheckBox tanqueDiesel ;
    private CheckBox cabinaCompartimientos ;
    private CheckBox tanqueAire ;
    private CheckBox quintaRueda ;
    private CheckBox ejesTransmision ;
    private CheckBox tuboEscape ;
    private CheckBox motor ;
    private CheckBox baseRemolque ;

    private CheckBox techos ;

    private CheckBox vvtt ;

    private CheckBox IRP1_inspeccionMecanica ;
    private CheckBox IRP1_lucesCheck ;
    private CheckBox IRP1_luzGaliboIzqFrontalSup ;
    private CheckBox IRP1_manitas ;
    private CheckBox IRP1_manivela ;
    private CheckBox IRP1_patinIzq ;

    private CheckBox IRP2_cuartoLadoIzq ;
    private CheckBox IRP2_loderaIzq ;
    private CheckBox IRP2_lucesCheck ;
    private CheckBox IRP2_luzABS ;
    private CheckBox IRP2_luzBarcoIzq ;
    private CheckBox IRP2_rompevientosIzq ;

    private CheckBox LlIE1_birlosPivote ;
    private CheckBox LlIE1_llantasPos1 ;
    private CheckBox LlIE1_llantasPos2 ;
    private CheckBox LlIE1_masaYoyo ;
    private CheckBox LlIE1_rin ;
    private CheckBox LlIE1_tieneLodera ;

    private CheckBox Pu_bisagrasPuertas ;
    private CheckBox Pu_defensa ;
    private CheckBox Pu_luzGaliboSupTraseras ;
    private CheckBox Pu_plafonesDer ;
    private CheckBox Pu_plafonesIzq ;

    private CheckBox Pl_luzPlaca ;
    private CheckBox Pl_placa ;

    private CheckBox S1_sello1 ;
    private CheckBox S1_altaSeguridad ;

    private CheckBox LlDE1_birlosPivote ;
    private CheckBox LlDE1_llantasPos5 ;
    private CheckBox LlDE1_llantasPos6 ;
    private CheckBox LlDE1_masaYoyo ;
    private CheckBox LlDE1_rin ;
    private CheckBox LlDE1_tieneLodera ;

    private CheckBox DRP1_fondoPlaga ;
    private CheckBox DRP1_pisoPlaga ;
    private CheckBox DRP1_techoPlaga ;
    private CheckBox DRP1_lucesCheck ;
    private CheckBox DRP1_luzGaliboDerFrontalSup ;
    private CheckBox DRP1_derPlaga ;
    private CheckBox DRP1_izqPlaga ;
    private CheckBox DRP1_patinDer ;

    private CheckBox DRP2_cuartoLadoDer ;
    private CheckBox DRP2_loderaDer ;
    private CheckBox DRP2_lucesCheck ;
    private CheckBox DRP2_luzBarcoDer ;
    private CheckBox DRP2_rompevientosDer ;

    private CheckBox S2_escotilla ;
    private CheckBox S2_sello2 ;
    private CheckBox S2_altaSeguridad ;

    private CheckBox LlIE2_birlosPivote ;
    private CheckBox LlIE2_llantasPos3 ;
    private CheckBox LlIE2_llantasPos4 ;
    private CheckBox LlIE2_masaYoyo ;
    private CheckBox LlIE2_rin ;
    private CheckBox LlIE2_tieneLodera ;

    private CheckBox LlDE2_birlosPivote ;
    private CheckBox LlDE2_llantasPos7 ;
    private CheckBox LlDE2_llantasPos8 ;
    private CheckBox LlDE2_masaYoyo ;
    private CheckBox LlDE2_rin ;
    private CheckBox LlDE2_tieneLodera ;

    private CheckBox CFD_amortiguador ;
    private CheckBox CFD_bolsaAire ;
    private CheckBox CFD_gavilan ;
    private CheckBox CFD_muelle ;
    private CheckBox CFD_rotachamber ;

    private CheckBox CTD_amortiguador ;
    private CheckBox CTD_bolsaAire ;
    private CheckBox CTD_matraca ;
    private CheckBox CTD_muelle ;
    private CheckBox CTD_rotachamber ;

    private CheckBox CFI_amortiguador ;
    private CheckBox CFI_bolsaAire ;
    private CheckBox CFI_gavilan ;
    private CheckBox CFI_muelle ;
    private CheckBox CFI_rotachamber ;

    private CheckBox CTI_amortiguador ;
    private CheckBox CTI_bolsaAire ;
    private CheckBox CTI_matraca ;
    private CheckBox CTI_muelle ;
    private CheckBox CTI_rotachamber ;


    private String operacion ;
    private String NoUnidad ;
    private String NoCaja ;
    private String nombreLinea ;
    private String nombreTransportista ;
    private String folio ;
    private File imageFile ;
    private Uri photoURI;
    private ProgressBar progressBar1 ;
    private Button btnImg ;
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
    private Boolean resk;
    private final int THUMBSIZE = 128;
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
    private String [] Llantas ;
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
    private String defensaCh , motorCh, pisoCh, tanqueDeCombCh, llantasCh , diferencialCh , cabinaCh, cilindrosDeAireCh,mofleEscapeCh
            ,quintaRuedaCh, manivelaCh, puertasTraserasCh, sellosCh , lucesTraserasCh ,cuartosRojosCh , lucesDeAltaTraseraCh, luzDePlacaCh ,
    placaCh, zoqueterasCh, guardaPolvoCh, loderasCh, remolqueCh, chasisCh , paredesCh, lucesLateralesAmbarCh, lucesFrenteCh, llantaDeRefaccionCh , cuartosAmbarCh;
    private String derRemolqueP2Img, tarjertaImg ;
    private Spinner tipoLlanta1 ,tipoLlanta2 ,tipoLlanta3 ,tipoLlanta4 ,tipoLlanta5 ,tipoLlanta6 ,tipoLlanta7 ,tipoLlanta8 ;
    private DxApi dxApi;
    private String damage1Img ;
    private String damage2Img ;
    private String damage3Img;
    private String damage4Img ;
    private String idUsuario;
    boolean checked1;
    boolean checked2;
    private int lljumbo ;
    private int lljumboCheck ;
    private Object Network;
    private String user ;
    private String password ;
    private String ip ;
    private int mensaje;
    private String id ;
    private String imageFileName ;
    private String [] estatusLlanta = new String[]{"Sin Seleccionar","Recapeada","Original"};
    Bitmap bm1;

    @Override
    protected void onStart() {
        super.onStart();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);
        ip = preferences.getString("Aip","");
        user = preferences.getString("user","");
        password = preferences.getString("pass","");

        NoUnidad = getIntent().getStringExtra("NoUnidad");
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


        jumboRB1 = (CheckBox) findViewById(R.id.radioButton2);
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

        sello1ET = (EditText) findViewById(R.id.editText);
        sello2ET = (EditText) findViewById(R.id.editText2);
        sello3ET = (EditText) findViewById(R.id.editText4);


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

        comentario0 = (EditText) findViewById(R.id.editTextTextMultiLine);
        comentario1 = (EditText) findViewById(R.id.editTextTextMultiLine4);
        comentario2 = (EditText) findViewById(R.id.comentario2);


        btnImg = (Button) findViewById(R.id.btnImg);

        defensa = (CheckBox) findViewById(R.id.checkBox);
        llantas = (CheckBox) findViewById(R.id.checkBox8);
        //pisoTractor = (CheckBox) findViewById(R.id.checkBox2);
        tanqueDiesel = (CheckBox) findViewById(R.id.checkBox9);
        cabinaCompartimientos = (CheckBox) findViewById(R.id.checkBox3);
        tanqueAire = (CheckBox) findViewById(R.id.checkBox10);
        quintaRueda = (CheckBox) findViewById(R.id.checkBox4);
        ejesTransmision = (CheckBox) findViewById(R.id.checkBox11);
        tuboEscape = (CheckBox) findViewById(R.id.checkBox5);
        motor = (CheckBox) findViewById(R.id.checkBox7);
        baseRemolque = (CheckBox) findViewById(R.id.checkBox29);
        //paredLateralDerecha = (CheckBox) findViewById(R.id.checkBox31);
        //paredFrontal = (CheckBox) findViewById(R.id.checkBox33);
        //pisoInterno = (CheckBox) findViewById(R.id.checkBox35);
        //puerta = (CheckBox) findViewById(R.id.checkBox30);
        techos = (CheckBox) findViewById(R.id.checkBox32);
        //paredLateralIzquierda = (CheckBox) findViewById(R.id.checkBox34);
        vvtt = (CheckBox) findViewById(R.id.checkBox36);

        IRP1_inspeccionMecanica = (CheckBox) findViewById(R.id.cb_inspeccion);
        IRP1_lucesCheck = (CheckBox) findViewById(R.id.cb_lucesCheck);
        IRP1_luzGaliboIzqFrontalSup = (CheckBox) findViewById(R.id.checkBox13);
        IRP1_manitas = (CheckBox) findViewById(R.id.checkBox14);
        IRP1_manivela = (CheckBox) findViewById(R.id.checkBox15);
        IRP1_patinIzq = (CheckBox) findViewById(R.id.checkBox16);

        IRP2_cuartoLadoIzq = (CheckBox) findViewById(R.id.checkBox17);
        IRP2_loderaIzq = (CheckBox) findViewById(R.id.checkBox18);
        IRP2_lucesCheck = (CheckBox) findViewById(R.id.checkBox19);
        IRP2_luzABS = (CheckBox) findViewById(R.id.checkBox20);
        IRP2_luzBarcoIzq = (CheckBox) findViewById(R.id.checkBox21);
        IRP2_rompevientosIzq = (CheckBox) findViewById(R.id.checkBox22);


        LlIE1_birlosPivote = (CheckBox) findViewById(R.id.checkBox23);
        LlIE1_llantasPos1 = (CheckBox) findViewById(R.id.checkBox24);
        LlIE1_llantasPos2 = (CheckBox) findViewById(R.id.checkBox25);
        LlIE1_masaYoyo = (CheckBox) findViewById(R.id.checkBox26);
        LlIE1_rin = (CheckBox) findViewById(R.id.checkBox27);
        LlIE1_tieneLodera = (CheckBox) findViewById(R.id.checkBox28);

        LlIE2_birlosPivote = (CheckBox) findViewById(R.id.checkBox37);
        LlIE2_llantasPos3 = (CheckBox) findViewById(R.id.checkBox38);
        LlIE2_llantasPos4 = (CheckBox) findViewById(R.id.checkBox39);
        LlIE2_masaYoyo = (CheckBox) findViewById(R.id.checkBox40);
        LlIE2_rin = (CheckBox) findViewById(R.id.checkBox41);
        LlIE2_tieneLodera = (CheckBox) findViewById(R.id.checkBox42);

        CFI_amortiguador = (CheckBox) findViewById(R.id.checkBox44);
        CFI_bolsaAire = (CheckBox) findViewById(R.id.checkBox47);
        CFI_gavilan = (CheckBox) findViewById(R.id.checkBox43);
        CFI_muelle = (CheckBox) findViewById(R.id.checkBox45);
        CFI_rotachamber = (CheckBox) findViewById(R.id.checkBox46);

        CTI_amortiguador = (CheckBox) findViewById(R.id.checkBox48);
        CTI_bolsaAire = (CheckBox) findViewById(R.id.checkBox49);
        CTI_matraca = (CheckBox) findViewById(R.id.checkBox52);
        CTI_muelle = (CheckBox) findViewById(R.id.checkBox51);
        CTI_rotachamber = (CheckBox) findViewById(R.id.checkBox50);

        Pu_bisagrasPuertas = (CheckBox) findViewById(R.id.checkBox53);
        Pu_defensa = (CheckBox) findViewById(R.id.checkBox54);
        Pu_luzGaliboSupTraseras = (CheckBox) findViewById(R.id.checkBox55);
        Pu_plafonesDer = (CheckBox) findViewById(R.id.checkBox56);
        Pu_plafonesIzq = (CheckBox) findViewById(R.id.checkBox57);

        Pl_luzPlaca = (CheckBox) findViewById(R.id.checkBox59);
        Pl_placa = (CheckBox) findViewById(R.id.checkBox58);

        S1_sello1 = (CheckBox) findViewById(R.id.checkBox61);
        S1_altaSeguridad = (CheckBox) findViewById(R.id.checkBox60);

        S2_escotilla = (CheckBox) findViewById(R.id.checkBox62);
        S2_sello2 = (CheckBox) findViewById(R.id.checkBox64);
        S2_altaSeguridad = (CheckBox) findViewById(R.id.checkBox63);

        LlDE1_birlosPivote = (CheckBox) findViewById(R.id.checkBox66);
        LlDE1_llantasPos5 = (CheckBox) findViewById(R.id.checkBox67);
        LlDE1_llantasPos6 = (CheckBox) findViewById(R.id.checkBox68);
        LlDE1_masaYoyo = (CheckBox) findViewById(R.id.checkBox69);
        LlDE1_rin = (CheckBox) findViewById(R.id.checkBox70);
        LlDE1_tieneLodera = (CheckBox) findViewById(R.id.checkBox400);

        DRP1_fondoPlaga = (CheckBox) findViewById(R.id.checkBox71);
        DRP1_pisoPlaga = (CheckBox) findViewById(R.id.checkBox72);
        DRP1_techoPlaga = (CheckBox) findViewById(R.id.checkBox73);
        DRP1_lucesCheck = (CheckBox) findViewById(R.id.checkBox74);
        DRP1_luzGaliboDerFrontalSup = (CheckBox) findViewById(R.id.checkBox75);
        DRP1_derPlaga = (CheckBox) findViewById(R.id.checkBox76);
        DRP1_izqPlaga = (CheckBox) findViewById(R.id.checkBox77);
        DRP1_patinDer = (CheckBox) findViewById(R.id.checkBox78);

        DRP2_cuartoLadoDer = (CheckBox) findViewById(R.id.checkBox80);
        DRP2_loderaDer = (CheckBox) findViewById(R.id.checkBox81);
        DRP2_lucesCheck = (CheckBox) findViewById(R.id.checkBox82);
        DRP2_luzBarcoDer = (CheckBox) findViewById(R.id.checkBox83);
        DRP2_rompevientosDer = (CheckBox) findViewById(R.id.checkBox84);

        LlDE2_birlosPivote = (CheckBox) findViewById(R.id.checkBox85);
        LlDE2_llantasPos7 = (CheckBox) findViewById(R.id.checkBox86);
        LlDE2_llantasPos8 = (CheckBox) findViewById(R.id.checkBox87);
        LlDE2_masaYoyo = (CheckBox) findViewById(R.id.checkBox88);
        LlDE2_rin = (CheckBox) findViewById(R.id.checkBox89);
        LlDE2_tieneLodera = (CheckBox) findViewById(R.id.checkBox90);

        CFD_amortiguador = (CheckBox) findViewById(R.id.checkBox91);
        CFD_bolsaAire = (CheckBox) findViewById(R.id.checkBox92);
        CFD_gavilan = (CheckBox) findViewById(R.id.checkBox93);
        CFD_muelle = (CheckBox) findViewById(R.id.checkBox94);
        CFD_rotachamber = (CheckBox) findViewById(R.id.checkBox95);

        CTD_amortiguador = (CheckBox) findViewById(R.id.checkBox96);
        CTD_bolsaAire = (CheckBox) findViewById(R.id.checkBox97);
        CTD_matraca = (CheckBox) findViewById(R.id.checkBox98);
        CTD_muelle = (CheckBox) findViewById(R.id.checkBox99);
        CTD_rotachamber = (CheckBox) findViewById(R.id.checkBox100);


        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta1.setAdapter(adapter0);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta2.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta3.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta4.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta5.setAdapter(adapter4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta6.setAdapter(adapter5);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta7.setAdapter(adapter6);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusLlanta);
        tipoLlanta8.setAdapter(adapter7);

        Post post =  new Post(user,password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dxApi = retrofit.create(DxApi.class);

        Call<List<CLlanta>> callLlanta = dxApi.getLlanta(post);

        callLlanta.enqueue(new Callback<List<CLlanta>>() {
            @Override
            public void onResponse(Call<List<CLlanta>> call, Response<List<CLlanta>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                }

                List<CLlanta> CLlantas = response.body();


                ArrayAdapter<CLlanta> adapter2 = new ArrayAdapter<CLlanta>(imgActivity.this , R.layout.mspinner_item, CLlantas);
                llanta1SP .setAdapter(adapter2);
                llanta2SP .setAdapter(adapter2);
                llanta3SP  .setAdapter(adapter2);
                llanta4SP .setAdapter(adapter2);
                llanta5SP  .setAdapter(adapter2);
                llanta6SP .setAdapter(adapter2);
                llanta7SP  .setAdapter(adapter2);
                llanta8SP  .setAdapter(adapter2);


            }

            @Override
            public void onFailure(Call<List<CLlanta>> call, Throwable t) {

            }
        });


        jumboRB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
            }
        });



        defensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_defensa = "0";

                }else{
                    S_defensa = "1";
                }
            }
        });
        llantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_llantas = "0";

                }else{
                    S_llantas = "1";
                }
            }
        });

        tanqueDiesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_tanqueDiesel = "0";

                }else{
                    S_tanqueDiesel = "1";
                }
            }
        });
        cabinaCompartimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_cabinaCompartimientos = "0";

                }else{
                    S_cabinaCompartimientos = "1";
                }
            }
        });
        tanqueAire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_tanqueAire = "0";

                }else{
                    S_tanqueAire = "1";
                }
            }
        });
        quintaRueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_quintaRueda = "0";

                }else{
                    S_quintaRueda = "1";
                }
            }
        });
        ejesTransmision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_ejesTransmision = "0";

                }else{
                    S_ejesTransmision = "1";
                }
            }
        });
        tuboEscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_tuboEscape = "0";

                }else{
                    S_tuboEscape = "1";
                }
            }
        });
        motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_motor = "0";

                }else{
                    S_motor = "1";
                }
            }
        });
        baseRemolque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_baseRemolque = "0";

                }else{
                    S_baseRemolque = "1";
                }
            }
        });

        techos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_techos = "0";

                }else{
                    S_techos = "1";
                }
            }
        });

        vvtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_vvtt = "0";

                }else{
                    S_vvtt = "1";
                }
            }
        });
        IRP1_inspeccionMecanica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP1_inspeccionMecanica = "0";

                }else{
                    S_IRP1_inspeccionMecanica = "1";
                }
            }
        });
        IRP1_lucesCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP1_lucesCheck = "0";

                }else{
                    S_IRP1_lucesCheck = "1";
                }
            }
        });
        IRP1_luzGaliboIzqFrontalSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP1_luzGaliboIzqFrontalSup = "0";

                }else{
                    S_IRP1_luzGaliboIzqFrontalSup = "1";
                }
            }
        });
        IRP1_manitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP1_manitas = "0";

                }else{
                    S_IRP1_manitas = "1";
                }
            }
        });
        IRP1_manivela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP1_manivela = "0";

                }else{
                    S_IRP1_manivela = "1";
                }
            }
        });
        IRP1_patinIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP1_patinIzq = "0";

                }else{
                    S_IRP1_patinIzq = "1";
                }
            }
        });
        IRP2_cuartoLadoIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP2_cuartoLadoIzq = "0";

                }else{
                    S_IRP2_cuartoLadoIzq = "1";
                }
            }
        });
        IRP2_loderaIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP2_loderaIzq = "0";

                }else{
                    S_IRP2_loderaIzq = "1";
                }
            }
        });
        IRP2_lucesCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP2_lucesCheck = "0";

                }else{
                    S_IRP2_lucesCheck = "1";
                }
            }
        });
        IRP2_luzABS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP2_luzABS = "0";

                }else{
                    S_IRP2_luzABS = "1";
                }
            }
        });
        IRP2_luzBarcoIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP2_luzBarcoIzq = "0";

                }else{
                    S_IRP2_luzBarcoIzq = "1";
                }
            }
        });
        IRP2_rompevientosIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_IRP2_rompevientosIzq = "0";

                }else{
                    S_IRP2_rompevientosIzq = "1";
                }
            }
        });
        LlIE1_birlosPivote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE1_birlosPivote = "0";

                }else{
                    S_LlIE1_birlosPivote = "1";
                }
            }
        });
        LlIE1_llantasPos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE1_llantasPos1 = "0";

                }else{
                    S_LlIE1_llantasPos1 = "1";
                }
            }
        });
        LlIE1_llantasPos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE1_llantasPos2 = "0";

                }else{
                    S_LlIE1_llantasPos2 = "1";
                }
            }
        });
        LlIE1_masaYoyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE1_masaYoyo = "0";

                }else{
                    S_LlIE1_masaYoyo = "1";
                }
            }
        });
        LlIE1_rin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE1_rin = "0";

                }else{
                    S_LlIE1_rin = "1";
                }
            }
        });
        LlIE1_tieneLodera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE1_tieneLodera = "0";

                }else{
                    S_LlIE1_tieneLodera = "1";
                }
            }
        });
        Pu_bisagrasPuertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_Pu_bisagrasPuertas = "0";

                }else{
                    S_Pu_bisagrasPuertas = "1";
                }
            }
        });
        Pu_defensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_Pu_defensa = "0";

                }else{
                    S_Pu_defensa = "1";
                }
            }
        });
        Pu_luzGaliboSupTraseras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_Pu_luzGaliboSupTraseras = "0";

                }else{
                    S_Pu_luzGaliboSupTraseras = "1";
                }
            }
        });
        Pu_plafonesDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_Pu_plafonesDer = "0";

                }else{
                    S_Pu_plafonesDer = "1";
                }
            }
        });
        Pu_plafonesIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_Pu_plafonesIzq = "0";

                }else{
                    S_Pu_plafonesIzq = "1";
                }
            }
        });
        Pl_luzPlaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_Pl_luzPlaca = "0";

                }else{
                    S_Pl_luzPlaca = "1";
                }
            }
        });
        Pl_placa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_Pl_placa = "0";

                }else{
                    S_Pl_placa = "1";
                }
            }
        });
        S1_sello1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_S1_sello1 = "0";

                }else{
                    S_S1_sello1 = "1";
                }
            }
        });
        S1_altaSeguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_S1_altaSeguridad = "0";

                }else{
                    S_S1_altaSeguridad = "1";
                }
            }
        });
        LlDE1_birlosPivote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE1_birlosPivote = "0";

                }else{
                    S_LlDE1_birlosPivote = "1";
                }
            }
        });
        LlDE1_llantasPos5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE1_llantasPos5 = "0";

                }else{
                    S_LlDE1_llantasPos5 = "1";
                }
            }
        });
        LlDE1_llantasPos6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE1_llantasPos6 = "0";

                }else{
                    S_LlDE1_llantasPos6 = "1";
                }
            }
        });
        LlDE1_masaYoyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE1_masaYoyo = "0";

                }else{
                    S_LlDE1_masaYoyo = "1";
                }
            }
        });
        LlDE1_rin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE1_rin = "0";

                }else{
                    S_LlDE1_rin = "1";
                }
            }
        });
        LlDE1_tieneLodera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE1_tieneLodera = "0";

                }else{
                    S_LlDE1_tieneLodera = "1";
                }
            }
        });
        DRP1_fondoPlaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP1_fondoPlaga = "0";

                }else{
                    S_DRP1_fondoPlaga = "1";
                }
            }
        });
        DRP1_pisoPlaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP1_pisoPlaga = "0";

                }else{
                    S_DRP1_pisoPlaga = "1";
                }
            }
        });
        DRP1_techoPlaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP1_techoPlaga = "0";

                }else{
                    S_DRP1_techoPlaga = "1";
                }
            }
        });
        DRP1_lucesCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP1_lucesCheck = "0";

                }else{
                    S_DRP1_lucesCheck = "1";
                }
            }
        });
        DRP1_luzGaliboDerFrontalSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP1_luzGaliboDerFrontalSup = "0";

                }else{
                    S_DRP1_luzGaliboDerFrontalSup = "1";
                }
            }
        });
        DRP1_derPlaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP1_derPlaga = "0";

                }else{
                    S_DRP1_derPlaga = "1";
                }
            }
        });
        DRP1_izqPlaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP1_izqPlaga = "0";

                }else{
                    S_DRP1_izqPlaga = "1";
                }
            }
        });
        DRP1_patinDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP1_patinDer = "0";

                }else{
                    S_DRP1_patinDer = "1";
                }
            }
        });
        DRP2_cuartoLadoDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP2_cuartoLadoDer = "0";

                }else{
                    S_DRP2_cuartoLadoDer = "1";
                }
            }
        });
        DRP2_loderaDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP2_loderaDer = "0";

                }else{
                    S_DRP2_loderaDer = "1";
                }
            }
        });
        DRP2_lucesCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP2_lucesCheck = "0";

                }else{
                    S_DRP2_lucesCheck = "1";
                }
            }
        });
        DRP2_luzBarcoDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP2_luzBarcoDer = "0";

                }else{
                    S_DRP2_luzBarcoDer = "1";
                }
            }
        });
        DRP2_rompevientosDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_DRP2_rompevientosDer = "0";

                }else{
                    S_DRP2_rompevientosDer = "1";
                }
            }
        });
        S2_escotilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_S2_escotilla = "0";

                }else{
                    S_S2_escotilla = "1";
                }
            }
        });
        S2_sello2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_S2_sello2 = "0";

                }else{
                    S_S2_sello2 = "1";
                }
            }
        });
        S2_altaSeguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_S2_altaSeguridad = "0";

                }else{
                    S_S2_altaSeguridad = "1";
                }
            }
        });
        LlIE2_birlosPivote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE2_birlosPivote = "0";

                }else{
                    S_LlIE2_birlosPivote = "1";
                }
            }
        });
        LlIE2_llantasPos3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE2_llantasPos3 = "0";

                }else{
                    S_LlIE2_llantasPos3 = "1";
                }
            }
        });
        LlIE2_llantasPos4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE2_llantasPos4 = "0";

                }else{
                    S_LlIE2_llantasPos4 = "1";
                }
            }
        });
        LlIE2_masaYoyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE2_masaYoyo = "0";

                }else{
                    S_LlIE2_masaYoyo = "1";
                }
            }
        });
        LlIE2_rin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE2_rin = "0";

                }else{
                    S_LlIE2_rin = "1";
                }
            }
        });
        LlIE2_tieneLodera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlIE2_tieneLodera = "0";

                }else{
                    S_LlIE2_tieneLodera = "1";
                }
            }
        });
        LlDE2_birlosPivote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE2_birlosPivote = "0";

                }else{
                    S_LlDE2_birlosPivote = "1";
                }
            }
        });
        LlDE2_llantasPos7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE2_llantasPos7 = "0";

                }else{
                    S_LlDE2_llantasPos7 = "1";
                }
            }
        });
        LlDE2_llantasPos8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE2_llantasPos8 = "0";

                }else{
                    S_LlDE2_llantasPos8 = "1";
                }
            }
        });
        LlDE2_masaYoyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE2_masaYoyo = "0";

                }else{
                    S_LlDE2_masaYoyo = "1";
                }
            }
        });
        LlDE2_rin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE2_rin = "0";

                }else{
                    S_LlDE2_rin = "1";
                }
            }
        });
        LlDE2_tieneLodera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_LlDE2_tieneLodera = "0";

                }else{
                    S_LlDE2_tieneLodera = "1";
                }
            }
        });
        CFD_amortiguador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFD_amortiguador = "0";

                }else{
                    S_CFD_amortiguador = "1";
                }
            }
        });
        CFD_bolsaAire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFD_bolsaAire = "0";

                }else{
                    S_CFD_bolsaAire = "1";
                }
            }
        });
        CFD_gavilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFD_gavilan = "0";

                }else{
                    S_CFD_gavilan = "1";
                }
            }
        });
        CFD_muelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFD_muelle = "0";

                }else{
                    S_CFD_muelle = "1";
                }
            }
        });
        CFD_rotachamber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFD_rotachamber = "0";

                }else{
                    S_CFD_rotachamber = "1";
                }
            }
        });
        CTD_amortiguador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTD_amortiguador = "0";

                }else{
                    S_CTD_amortiguador = "1";
                }
            }
        });
        CTD_bolsaAire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTD_bolsaAire = "0";

                }else{
                    S_CTD_bolsaAire = "1";
                }
            }
        });
        CTD_matraca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTD_matraca = "0";

                }else{
                    S_CTD_matraca = "1";
                }
            }
        });
        CTD_muelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTD_muelle = "0";

                }else{
                    S_CTD_muelle = "1";
                }
            }
        });
        CTD_rotachamber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTD_rotachamber = "0";

                }else{
                    S_CTD_rotachamber = "1";
                }
            }
        });
        CFI_amortiguador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFI_amortiguador = "0";

                }else{
                    S_CFI_amortiguador = "1";
                }
            }
        });
        CFI_bolsaAire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFI_bolsaAire = "0";

                }else{
                    S_CFI_bolsaAire = "1";
                }
            }
        });
        CFI_gavilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFI_gavilan = "0";

                }else{
                    S_CFI_gavilan = "1";
                }
            }
        });
        CFI_muelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFI_muelle = "0";

                }else{
                    S_CFI_muelle = "1";
                }
            }
        });
        CFI_rotachamber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CFI_rotachamber = "0";

                }else{
                    S_CFI_rotachamber = "1";
                }
            }
        });
        CTI_amortiguador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTI_amortiguador = "0";

                }else{
                    S_CTI_amortiguador = "1";
                }
            }
        });
        CTI_bolsaAire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTI_bolsaAire = "0";

                }else{
                    S_CTI_bolsaAire = "1";
                }
            }
        });
        CTI_matraca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTI_matraca = "0";

                }else{
                    S_CTI_matraca = "1";
                }
            }
        });
        CTI_muelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTI_muelle = "0";

                }else{
                    S_CTI_muelle = "1";
                }
            }
        });
        CTI_rotachamber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    S_CTI_rotachamber = "0";

                }else{
                    S_CTI_rotachamber = "1";
                }
            }
        });




        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //300 VACIO
                //128 IMAGFN

              //  tractorImg =  String.valueOf(tractor.getDrawable().getBounds());
                tractorDerImg =  String.valueOf(tractoDer.getDrawable().getBounds());
                tractorIzqImg =  String.valueOf(tractoIzq.getDrawable().getBounds());
                tractorFrenteImg =  String.valueOf(tractoFrente.getDrawable().getBounds());
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

                String ll1 =  llanta1SP.getSelectedItem().toString();
                String ll2 =  llanta2SP.getSelectedItem().toString();
                String ll3 =  llanta3SP.getSelectedItem().toString();
                String ll4 =  llanta4SP.getSelectedItem().toString();
                String ll5 =  llanta5SP.getSelectedItem().toString();
                String ll6 =  llanta6SP.getSelectedItem().toString();
                String ll7 =  llanta7SP.getSelectedItem().toString();
                String ll8 =  llanta8SP.getSelectedItem().toString();

                String tll1 =  tipoLlanta1.getSelectedItem().toString();
                String tll2 =  tipoLlanta2.getSelectedItem().toString();
                String tll3 =  tipoLlanta3.getSelectedItem().toString();
                String tll4 =  tipoLlanta4.getSelectedItem().toString();
                String tll5 =  tipoLlanta5.getSelectedItem().toString();
                String tll6 =  tipoLlanta6.getSelectedItem().toString();
                String tll7 =  tipoLlanta7.getSelectedItem().toString();
                String tll8 =  tipoLlanta8.getSelectedItem().toString();

                 ll1 =  ll1 + "-" + tll1;
                 ll2 =  ll2 + "-" + tll2;
                 ll3 =  ll3 + "-" + tll3;
                 ll4 =  ll4 + "-" + tll4;
                 ll5 =  ll5 + "-" + tll5;
                 ll6 =  ll6 + "-" + tll6;
                 ll7 =  ll7 + "-" + tll7;
                 ll8 =  ll8 + "-" + tll8;

                String sello1S = sello1ET.getText().toString();
                String sello2S = sello2ET.getText().toString();
                String sello3S = sello3ET.getText().toString();

                String comen2 = comentario2.getText().toString();
                String comen1 = comentario1.getText().toString();
                String comen0 = comentario0.getText().toString();

                comentarios = "IZQUIERDA="+comen0 +
                        "     TRASERA="+comen1+
                        "     DERECHA="+comen2;


                 lljumbo =0;


                int selloExtra ;

                if(checked2){
                    lljumbo = 1;
                }


                if(sello3Img.contains("128") && sello3S.length() > 0){
                    selloExtra = 1;
                }else {
                    selloExtra = 0;
                }

                String placasDatosD = numeroDePlaca.getText().toString();

                // SI LAS LLANTAS SON JUMBO SOLO REVISA 4
                if(lljumbo == 1 ) {
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
                        Toast.makeText(getBaseContext(), "Datos Incompletos", Toast.LENGTH_SHORT).show();
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
                            comentarios.length() >0 &&
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
                                public void onResponse(Call<List<CEnvio>> call, Response<List<CEnvio>> response) {

                                    if (!response.isSuccessful()) {
                                        Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                                    }

                                    List<CEnvio> cEnvio2s = response.body();
                                    String mensaje = cEnvio2s.get(0).getMensaje();

                                    if (mensaje.contains("Enviado con exito")){

                                        Intent i = new Intent(imgActivity.this, firmasActivity.class);
                                        i.putExtra("folio", folio);
                                        i.putExtra("mensaje", id);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);

                                    }else{
                                        Toast.makeText(imgActivity.this, "Error al Enviar", Toast.LENGTH_LONG).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<List<CEnvio>> call, Throwable t) {
                                    Toast.makeText(imgActivity.this, "Error 404", Toast.LENGTH_LONG).show();
                                }
                            });

                        }catch (Exception e ){
                            Toast.makeText(imgActivity.this, "Error 404", Toast.LENGTH_LONG).show();
                        }


                    }
                }else if (lljumbo == 0) {
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
                        Toast.makeText(getBaseContext(), "Datos Incompletos", Toast.LENGTH_SHORT).show();
                    }   else if (
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
                            public void onResponse(Call<List<CEnvio>> call, Response<List<CEnvio>> response) {

                                if (!response.isSuccessful()) {
                                    Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                                }

                                List<CEnvio> cEnvio2s = response.body();
                                String mensaje = cEnvio2s.get(0).getMensaje();

                                if (mensaje.contains("Enviado con exito")){

                                    Intent i = new Intent(imgActivity.this, firmasActivity.class);
                                    i.putExtra("folio", folio);
                                    i.putExtra("mensaje", id);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);

                                }else{
                                    Toast.makeText(imgActivity.this, "Error al Enviar", Toast.LENGTH_LONG).show();
                                }


                            }

                            @Override
                            public void onFailure(Call<List<CEnvio>> call, Throwable t) {
                                Toast.makeText(imgActivity.this, "Error 404", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });

      /*  tractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tractor.setEnabled(false);
                imgClick("tractor" , REQUEST_TRACTOR);


            }
        });*/
        tractoIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tractoIzq.setEnabled(false);
                imgClick("tractorIzq" , REQUEST_TRACTO_IZQ);


            }
        });
        tractoDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tractoDer.setEnabled(false);
                imgClick("tractoDer" , REQUEST_TRACTO_DER);


            }
        });
        tractoFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tractoFrente.setEnabled(false);
                imgClick("tractorFrente" , REQUEST_TRACTO_FRENTE);


            }
        });
        noEconomico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noEconomico.setEnabled(false);
                imgClick("noEconomico" , REQUEST_NoECONOMICO);
            }
        });
        izqRemolqueP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                izqRemolqueP1.setEnabled(false);
                imgClick("izqRemolqueP1" , REQUEST_IZQ_REMOLQUE_P1);
            }
        });
        vin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vin.setEnabled(false);
                imgClick("vin" , REQUEST_VIN);
            }
        });
        chasisFrontalIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chasisFrontalIzq.setEnabled(false);
                imgClick("chasisFrontalIzq" , REQUEST_CHASIS_FRONTAL_IZQ);
            }
        });
        chasisTraseroIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chasisTraseroIzq.setEnabled(false);
                imgClick("chasisTraseroIzq" , REQUEST_CHASIS_TRASERO_IZQ);
            }
        });
        llantasIzqEje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llantasIzqEje1.setEnabled(false);
                imgClick("llantasIzqEje1" , REQUEST_LLANTAS_IZQ_EJE1);
            }
        });
        llantasIzqEje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llantasIzqEje2.setEnabled(false);
                imgClick("llantasIzqEje2" , REQUEST__LLANTAS_IZQ_EJE2);
            }
        });
        izqRemolqueP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                izqRemolqueP2.setEnabled(false);
                imgClick("izqRemolqueP2" , REQUEST_IZQ_REMOLQUE_P2);
            }
        });
        puertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puertas.setEnabled(false);
                imgClick("puertas" , REQUEST_PUERTAS);
            }
        });
        placas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placas.setEnabled(false);
                imgClick("placas" , REQUEST_PLACAS);
            }
        });
        sello1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sello1.setEnabled(false);
                imgClick("sello1" , REQUEST_SELLO1);
            }
        });
        sello2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sello2.setEnabled(false);
                imgClick("sello2" , REQUEST_SELLO2);
            }
        });
        sello3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sello3.setEnabled(false);
                imgClick("sello3" , REQUEST_SELLO3);
            }
        });
        derRemolqueP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                derRemolqueP1.setEnabled(false);
                imgClick("derRemolqueP1" , REQUEST_DER_REMOLQUE_P1);
            }
        });
        llantasDerEje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llantasDerEje2.setEnabled(false);
                imgClick("llantasDerEje2" , REQUEST_LLANTAS_DER_EJE2);
            }
        });
        llantasDerEje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llantasDerEje1.setEnabled(false);
                imgClick("llantasDerEje1" , REQUEST_LLANTAS_DER_EJE1);
            }
        });
        chasisTraseroDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chasisTraseroDer.setEnabled(false);
                imgClick("chasisTraseroDer" , REQUEST_CHASIS_TRASERO_DER);
            }
        });
        chasisFrontalDER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chasisFrontalDER.setEnabled(false);
                imgClick("chasisFrontalDER" , REQUEST_CHASIS_FRONTAL_DER);
            }
        });
        derRemolqueP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                derRemolqueP2.setEnabled(false);
                imgClick("derRemolqueP2" , REQUEST_DER_REMOLQUE_P2);
            }
        });
        damage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage1.setEnabled(false);
                imgClick("damage1IZQ" , DAMAGE1);
            }
        });
        damage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage2.setEnabled(false);
                imgClick("damage2IZQ" , DAMAGE2);
            }
        });
        damage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage3.setEnabled(false);
                imgClick("damage3IZQ" , DAMAGE3);
            }
        });
        damage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage4.setEnabled(false);
                imgClick("damage4IZQ" , DAMAGE4);
            }
        });
        damage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage5.setEnabled(false);
                imgClick("damage5TRA" , DAMAGE5);
            }
        });
        damage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage6.setEnabled(false);
                imgClick("damage6TRA" , DAMAGE6);
            }
        });
        damage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage7.setEnabled(false);
                imgClick("damage7TRA" , DAMAGE7);
            }
        });
        damage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage8.setEnabled(false);
                imgClick("damage8TRA" , DAMAGE8);
            }
        });
        damage9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage9.setEnabled(false);
                imgClick("damage9DER" , DAMAGE9);
            }
        });
        damage10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage10.setEnabled(false);
                imgClick("damage10DER" , DAMAGE10);
            }
        });
        damage11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage11.setEnabled(false);
                imgClick("damage11DER" , DAMAGE11);
            }
        });
        damage12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage12.setEnabled(false);
                imgClick("damage12DER" , DAMAGE12);
            }
        });
        /*tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tarjeta.setEnabled(false);
                imgClick("tarjeta" , REQUEST_TARJETA);
            }
        });*/
    }

    private void imgClick (String photo , int code){

        try{

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File destPath = new File(getBaseContext().getExternalFilesDir(null).getAbsolutePath());
        //File destPath = new File(String.valueOf(getBaseContext().getCacheDir()));
        imageFileName = null ;
        imageFile = null ;
        photoURI = null;
        imageFileName = photo+ "-Folio"+folio ;

            imageFile = File.createTempFile(
                    imageFileName,  // prefix
                    ".jpeg",         // suffix
                    destPath      // directory
            );
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoURI = FileProvider.getUriForFile(getBaseContext(), getBaseContext().getApplicationContext().getPackageName() + ".provider", imageFile);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(camera,code);

        }catch (Exception e ){

            logApp(e.getMessage(),"Img","Intercambios DX");

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        switch (requestCode) {

            case REQUEST_CODE_SIGN_IN:
                if (resultCode == Activity.RESULT_OK && resultData != null) {
                    handleSignInResult(resultData);
                }
                break;

           /* case REQUEST_TRACTOR:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_TRACTOR);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    tractor.setImageBitmap(thumbImage);
                }else {
                    tractor.setEnabled(true);
                }

                break;*/
            case REQUEST_TRACTO_DER:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_TRACTO_DER);

                    //uploadServer(REQUEST_TRACTO_DER);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);


                    tractoDer.setImageBitmap(thumbImage);
                }else {
                    tractoDer.setEnabled(true);
                }

                break;
            case REQUEST_TRACTO_FRENTE:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_TRACTO_FRENTE);

                    //uploadServer(REQUEST_TRACTO_FRENTE);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    tractoFrente.setImageBitmap(thumbImage);
                }else {
                    tractoFrente.setEnabled(true);
                }

                break;
            case REQUEST_TRACTO_IZQ:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_TRACTO_IZQ);

                   // uploadServer(REQUEST_TRACTO_IZQ);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    tractoIzq.setImageBitmap(thumbImage);
                }else {
                    tractoIzq.setEnabled(true);
                }

                break;
            case REQUEST_NoECONOMICO:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_NoECONOMICO);

                    //uploadServer(REQUEST_NoECONOMICO);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    noEconomico.setImageBitmap(thumbImage);
                } else {
                    noEconomico.setEnabled(true);
                }
                break;
            case REQUEST_IZQ_REMOLQUE_P1:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_IZQ_REMOLQUE_P1);

                   // uploadServer(REQUEST_IZQ_REMOLQUE_P1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);
                    izqRemolqueP1.setImageBitmap(thumbImage);


                }else {
                    izqRemolqueP1.setEnabled(true);
                }
                break;

            case REQUEST_VIN:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_VIN);

                    //uploadServer(REQUEST_VIN);
                    Bitmap  thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    vin.setImageBitmap(thumbImage);


                }else {
                    vin.setEnabled(true);
                }
                break;

            case REQUEST_CHASIS_FRONTAL_IZQ:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_CHASIS_FRONTAL_IZQ);

                    //uploadServer(REQUEST_CHASIS_FRONTAL_IZQ);
                    Bitmap  thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisFrontalIzq.setImageBitmap(thumbImage);


                }else {
                    chasisFrontalIzq.setEnabled(true);
                }
                break;

            case REQUEST_CHASIS_TRASERO_IZQ:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_CHASIS_TRASERO_IZQ);

                    //uploadServer(REQUEST_CHASIS_TRASERO_IZQ);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisTraseroIzq.setImageBitmap(thumbImage);

                }else {
                    chasisTraseroIzq.setEnabled(true);
                }
                break;
            case REQUEST_LLANTAS_IZQ_EJE1:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_LLANTAS_IZQ_EJE1);

                    //uploadServer(REQUEST_LLANTAS_IZQ_EJE1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasIzqEje1.setImageBitmap(thumbImage);



                }else {
                    llantasIzqEje1.setEnabled(true);
                }
                break;
            case REQUEST__LLANTAS_IZQ_EJE2:
                if (resultCode == Activity.RESULT_OK ) {


                    upload up = new upload();
                    up.execute(REQUEST__LLANTAS_IZQ_EJE2);

                    //uploadServer(REQUEST__LLANTAS_IZQ_EJE2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasIzqEje2.setImageBitmap(thumbImage);

                }else {
                    llantasIzqEje2.setEnabled(true);
                }
                break;
            case REQUEST_IZQ_REMOLQUE_P2:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_IZQ_REMOLQUE_P2);

                    //uploadServer(REQUEST_IZQ_REMOLQUE_P2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    izqRemolqueP2.setImageBitmap(thumbImage);

                }else {
                    izqRemolqueP2.setEnabled(true);
                }
                break;
            case REQUEST_PUERTAS:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_PUERTAS);

                    //uploadServer(REQUEST_PUERTAS);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    puertas.setImageBitmap(thumbImage);

                }else {
                    puertas.setEnabled(true);
                }
                break;
            case REQUEST_PLACAS:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_PLACAS);

                    //uploadServer(REQUEST_PLACAS);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    placas.setImageBitmap(thumbImage);

                }else {
                    placas.setEnabled(true);
                }
                break;
            case REQUEST_SELLO1:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_SELLO1);

                    //uploadServer(REQUEST_SELLO1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    sello1.setImageBitmap(thumbImage);

                }else {
                    sello1.setEnabled(true);
                }
                break;
            case REQUEST_SELLO2:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_SELLO2);

                    //uploadServer(REQUEST_SELLO2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    sello2.setImageBitmap(thumbImage);

                }else {
                    sello2.setEnabled(true);
                }
                break;
            case REQUEST_DER_REMOLQUE_P1:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_DER_REMOLQUE_P1);

                    //uploadServer(REQUEST_DER_REMOLQUE_P1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    derRemolqueP1.setImageBitmap(thumbImage);

                }else {
                    derRemolqueP1.setEnabled(true);
                }
                break;
            case REQUEST_LLANTAS_DER_EJE2:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_LLANTAS_DER_EJE2);

                    //uploadServer(REQUEST_LLANTAS_DER_EJE2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasDerEje2.setImageBitmap(thumbImage);

                }else {
                    llantasDerEje2.setEnabled(true);
                }
                break;
            case REQUEST_LLANTAS_DER_EJE1:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_LLANTAS_DER_EJE1);

                    //uploadServer(REQUEST_LLANTAS_DER_EJE1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasDerEje1.setImageBitmap(thumbImage);

                }else {
                    llantasDerEje1.setEnabled(true);
                }
                break;
            case REQUEST_CHASIS_TRASERO_DER:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_CHASIS_TRASERO_DER);

                    //uploadServer(REQUEST_CHASIS_TRASERO_DER);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisTraseroDer.setImageBitmap(thumbImage);
                }else {
                    chasisTraseroDer.setEnabled(true);
                }
                break;
            case REQUEST_CHASIS_FRONTAL_DER:
                if (resultCode == Activity.RESULT_OK ) {


                    upload up = new upload();
                    up.execute(REQUEST_CHASIS_FRONTAL_DER);

                    //uploadServer(REQUEST_CHASIS_FRONTAL_DER);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisFrontalDER.setImageBitmap(thumbImage);

                }else {
                    chasisFrontalDER.setEnabled(true);
                }
                break;
            case REQUEST_DER_REMOLQUE_P2:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_DER_REMOLQUE_P2);

                    //uploadServer(REQUEST_DER_REMOLQUE_P2);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    derRemolqueP2.setImageBitmap(thumbImage);

                }else {
                    derRemolqueP2.setEnabled(true);
                }
                break;
            case REQUEST_SELLO3:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(REQUEST_SELLO3);


                   // uploadServer(REQUEST_SELLO3);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    sello3.setImageBitmap(thumbImage);

                }else {
                    sello3.setEnabled(true);
                }
                break;
            case DAMAGE1:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE1);

                    //uploadServer(DAMAGE1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage1.setImageBitmap(thumbImage);

                }else {
                    damage1.setEnabled(true);
                }
                break;
            case DAMAGE2:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE2);

                    //uploadServer(DAMAGE2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage2.setImageBitmap(thumbImage);

                }else {
                    damage2.setEnabled(true);
                }
                break;
            case DAMAGE3:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE3);

                    //uploadServer(DAMAGE3);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage3.setImageBitmap(thumbImage);

                }else {
                    damage3.setEnabled(true);
                }
                break;
            case DAMAGE4:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE4);

                    //uploadServer(DAMAGE4);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage4.setImageBitmap(thumbImage);

                }else {
                    damage4.setEnabled(true);
                }
                break;
            case DAMAGE5:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE5);

                    //uploadServer(DAMAGE5);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage5.setImageBitmap(thumbImage);

                }else {
                    damage5.setEnabled(true);
                }
                break;
            case DAMAGE6:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE6);

                    //uploadServer(DAMAGE6);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage6.setImageBitmap(thumbImage);

                }else {
                    damage6.setEnabled(true);
                }
                break;
            case DAMAGE7:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE7);

                    //uploadServer(DAMAGE7);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage7.setImageBitmap(thumbImage);

                }else {
                    damage7.setEnabled(true);
                }
                break;
            case DAMAGE8:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE8);

                    //uploadServer(DAMAGE8);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage8.setImageBitmap(thumbImage);

                }else {
                    damage8.setEnabled(true);
                }
                break;
            case DAMAGE9:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE9);

                    //uploadServer(DAMAGE9);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage9.setImageBitmap(thumbImage);

                }else {
                    damage9.setEnabled(true);
                }
                break;
            case DAMAGE10:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE10);

                    //uploadServer(DAMAGE10);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage10.setImageBitmap(thumbImage);

                }else {
                    damage10.setEnabled(true);
                }
                break;
            case DAMAGE11:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE11);

                    //uploadServer(DAMAGE11);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage11.setImageBitmap(thumbImage);

                }else {
                    damage11.setEnabled(true);
                }
                break;
            case DAMAGE12:
                if (resultCode == Activity.RESULT_OK ) {

                    upload up = new upload();
                    up.execute(DAMAGE12);

                    //uploadServer(DAMAGE12);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage12.setImageBitmap(thumbImage);

                }else {
                    damage12.setEnabled(true);
                }
                break;



        }
        super.onActivityResult(requestCode, resultCode, resultData);
    }

    private void handleSignInResult(Intent result) {
        /*GoogleSignIn.getSignedInAccountFromIntent(result)
                .addOnSuccessListener(googleAccount -> {
                    Log.d(TAG, "Signed in as " + googleAccount.getEmail());

                    // Use the authenticated account to sign in to the Drive service.
                    GoogleAccountCredential credential =
                            GoogleAccountCredential.usingOAuth2(
                                    this, Collections.singleton(DriveScopes.DRIVE_FILE));
                    credential.setSelectedAccount(googleAccount.getAccount());
                    googleDriveService =
                            new Drive.Builder(
                                    AndroidHttp.newCompatibleTransport(),
                                    new GsonFactory(),
                                    credential)
                                    .setApplicationName("DXIntercambio")
                                    .build();

                    // The DriveServiceHelper encapsulates all REST API and SAF functionality.
                    // Its instantiation is required before handling any onClick actions.
                    //mDriveServiceHelper = new DriveServiceHelper(googleDriveService);
                })
                .addOnFailureListener(exception -> Log.e(TAG, "Unable to sign in.", exception));*/

    }

    @Override
    public void onBackPressed() {

    }

    private void uploadServer (int codigo){


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    if(bm1!=null)
                    {
                        bm1.recycle();
                        bm1=null;
                    }

                    String path = imageFile.getPath();
                    bm1 = BitmapFactory.decodeFile(path);


                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm1.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                    byte [] byte_arr = new byte[3000];
                    byte_arr = stream.toByteArray();
                    String base64 = new String(Base64.encode(byte_arr,2));



                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://"+ip+"/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    Post5 post5 = new Post5(user,password,imageFileName,base64,folio);
                    Call<String> callImg = dxApi.getImg(post5);

                    callImg.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            if(!response.isSuccessful()){
                                Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                            }

                            String cEnvios = String.valueOf(response);


                                    try {
                                        File destPath = new File(getBaseContext().getExternalFilesDir(null).getAbsolutePath());
                                        String path = destPath.toString();
                                        File directory = new File(path);
                                        File[] files = directory.listFiles();

                                        for(File file : files) {
                                            Log.d("Files", "FileName:" + file.getName());
                                            file.delete();
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                            Toast.makeText(getBaseContext(),"Error 400Img : "+ t.getMessage() ,Toast.LENGTH_SHORT).show();


                            switch (codigo){
                               /* case REQUEST_TRACTOR:

                                    tractor.setImageBitmap(null);
                                    tractor.setBackgroundColor(Color.parseColor("#074EAB"));
                                    tractor.setEnabled(true);

                                    break;*/
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

                        }
                    });

                } catch (Exception e) {

                    e.getMessage();

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getBaseContext(),"Error al enviar imagen",Toast.LENGTH_SHORT).show();


                            switch (codigo){
                               /* case REQUEST_TRACTOR:

                                    tractor.setImageBitmap(null);
                                    tractor.setBackgroundColor(Color.parseColor("#074EAB"));
                                    tractor.setEnabled(true);

                                    break;*/
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


                        }
                    });
                }



            }
        });
        t.start();



    }

    private void logApp (String mensaje, String pantalla, String app){
        try {
            DxApi dxApi;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://dxxpress.net/API/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            dxApi = retrofit.create(DxApi.class);

            PostLogApp postLogApp = new PostLogApp(user, password, mensaje, app, pantalla);

            Call<List<CEnvio>> call = dxApi.getLogApp(postLogApp);

            call.enqueue(new Callback<List<CEnvio>>() {
                @Override
                public void onResponse(Call<List<CEnvio>> call, Response<List<CEnvio>> response) {
                    if (!response.isSuccessful()) {
                        String khe = "papa nicolss ???";
                    }
                }

                @Override
                public void onFailure(Call<List<CEnvio>> call, Throwable t) {
                    String khe = "papa nicolss ???";
                }
            });


        }catch (Exception e){
            String khe = "papa nicolss ???";
        }

    }


    private class upload extends AsyncTask<Integer,Void,Void>{



        @Override
        protected Void doInBackground(Integer... integers) {

            int codigo = integers[0];

            try {

                if(bm1!=null)
                {
                    bm1.recycle();
                    bm1=null;
                }

                String path = imageFile.getPath();
                bm1 = BitmapFactory.decodeFile(path);


                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm1.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                byte [] byte_arr = new byte[3000];
                byte_arr = stream.toByteArray();
                String base64 = new String(Base64.encode(byte_arr,2));



                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://"+ip+"/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                dxApi = retrofit.create(DxApi.class);

                Post5 post5 = new Post5(user,password,imageFileName,base64,folio);
                Call<String> callImg = dxApi.getImg(post5);

                callImg.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                        }

                        String cEnvios = String.valueOf(response);


                        try {
                            File destPath = new File(getBaseContext().getExternalFilesDir(null).getAbsolutePath());
                            String path = destPath.toString();
                            File directory = new File(path);
                            File[] files = directory.listFiles();

                            for(File file : files) {
                                Log.d("Files", "FileName:" + file.getName());
                                file.delete();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        Toast.makeText(getBaseContext(),"Error 400Img : "+ t.getMessage() ,Toast.LENGTH_SHORT).show();


                        switch (codigo){
                               /* case REQUEST_TRACTOR:

                                    tractor.setImageBitmap(null);
                                    tractor.setBackgroundColor(Color.parseColor("#074EAB"));
                                    tractor.setEnabled(true);

                                    break;*/
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

                    }
                });

            } catch (Exception e) {

                e.getMessage();

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getBaseContext(),"Error al enviar imagen",Toast.LENGTH_SHORT).show();


                        switch (codigo){
                               /* case REQUEST_TRACTOR:

                                    tractor.setImageBitmap(null);
                                    tractor.setBackgroundColor(Color.parseColor("#074EAB"));
                                    tractor.setEnabled(true);

                                    break;*/
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


                    }
                });
            }

            return null;
        }
    }


}
