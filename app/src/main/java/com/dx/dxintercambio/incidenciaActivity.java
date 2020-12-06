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

    private TextView TWAdefensa ;
    private TextView TWAmotor ;
    private TextView TWApiso ;
    private TextView TWAtanqueDeComb ;
    private TextView TWAllantas ;
    private TextView TWAdiferencial ;
    private TextView TWAcabina ;
    private TextView TWAcilindrosDeAire ;
    private TextView TWAmofleEscape ;
    private TextView TWAquintaRueda ;
    private TextView TWAremolqueC ;
    private TextView TWAchasis ;
    private TextView TWApuertasTraseras ;
    private TextView TWAparedes ;
    private TextView TWAsellos ;
    private TextView TWAlucesLatAmbar ;
    private TextView TWAlucesDeFrente ;
    private TextView TWAcuartosAmbar ;
    private TextView TWAlucesTraseras ;
    private TextView TWAcuartosRojos ;
    private TextView TWAlucesDeAltoTraseros ;
    private TextView TWAluzDePlaca ;
    private TextView TWAzoqueteas ;
    private TextView TWAmanivela ;
    private TextView TWAguardaPolvos ;
    private TextView TWAloderas ;
    private TextView TWAllantaDeRefaccion ;
    private TextView TWAplacas ;
    private TextView TWAplacasDatos ;
    private EditText TWAcomentario2 ;


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


    private TextView TWPdefensa ;
    private TextView TWPmotor ;
    private TextView TWPpiso ;
    private TextView TWPtanqueDeComb ;
    private TextView TWPllantas ;
    private TextView TWPdiferencial ;
    private TextView TWPcabina ;
    private TextView TWPcilindrosDeAire ;
    private TextView TWPmofleEscape ;
    private TextView TWPquintaRueda ;
    private TextView TWPremolqueC ;
    private TextView TWPchasis ;
    private TextView TWPpuertasTraseras ;
    private TextView TWPparedes ;
    private TextView TWPsellos ;
    private TextView TWPlucesLatAmbar ;
    private TextView TWPlucesDeFrente ;
    private TextView TWPcuartosAmbar ;
    private TextView TWPlucesTraseras ;
    private TextView TWPcuartosRojos ;
    private TextView TWPlucesDeAltoTraseros ;
    private TextView TWPluzDePlaca ;
    private TextView TWPzoqueteas ;
    private TextView TWPmanivela ;
    private TextView TWPguardaPolvos ;
    private TextView TWPloderas ;
    private TextView TWPllantaDeRefaccion ;
    private TextView TWPplacas ;
    private TextView TWPplacasDatos ;
    private EditText  TWPcomentario2;

    private Button btnCancelar ;
    private Button btnConfirmar ;

    private EditText comen ;

    private String user ;
    private String comentario ;
    private String password ;
    private DxApi dxApi;
    private String folio;
    private int mensaje;


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

        String A_defensa = getIntent().getStringExtra("A_defensa");
        String A_motor = getIntent().getStringExtra("A_motor");
        String A_piso = getIntent().getStringExtra("A_piso");
        String A_tanqueDeComb = getIntent().getStringExtra("A_tanqueDeComb");
        String A_llantas =getIntent().getStringExtra("A_llantas");
        String A_diferencial = getIntent().getStringExtra("A_diferencial");
        String A_cabina = getIntent().getStringExtra("A_cabina");
        String A_cilindrosDeAire = getIntent().getStringExtra("A_cilindrosDeAire");
        String A_mofleEscape =getIntent().getStringExtra("A_mofleEscape");
        String A_quintaRueda = getIntent().getStringExtra("A_quintaRueda");
        String A_remolqueC = getIntent().getStringExtra("A_remolqueC");
        String A_chasis =getIntent().getStringExtra("A_chasis");
        String A_puertasTraseras =getIntent().getStringExtra("A_puertasTraseras");
        String A_paredes =getIntent().getStringExtra("A_paredes");
        String A_sellos = getIntent().getStringExtra("A_sellos");
        String A_lucesLatAmbar =getIntent().getStringExtra("A_lucesLatAmbar");
        String A_lucesDeFrente = getIntent().getStringExtra("A_lucesDeFrente");
        String A_cuartosAmbar = getIntent().getStringExtra("A_cuartosAmbar");
        String A_lucesTraseras = getIntent().getStringExtra("A_lucesTraseras");
        String A_cuartosRojos = getIntent().getStringExtra("A_cuartosRojos");
        String A_lucesDeAltoTraseroas = getIntent().getStringExtra("A_lucesDeAltoTraseros");
        String A_luzDePlaca = getIntent().getStringExtra("A_luzDePlaca");
        String A_zoqueteras = getIntent().getStringExtra("A_zoqueteras");
        String A_manivela = getIntent().getStringExtra("A_manivela");
        String A_guardaPolvos = getIntent().getStringExtra("A_guardaPolvos");
        String A_loderas = getIntent().getStringExtra("A_loderas");
        String A_llantaDeRefaccion = getIntent().getStringExtra("A_llantaDeRefaccion");
        String A_placas = getIntent().getStringExtra("A_placas");
        String A_placasDatos = getIntent().getStringExtra("A_placasDatos");
        String A_comentario2 = getIntent().getStringExtra("A_comentario2");

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

        String P_defensa = getIntent().getStringExtra("P_defensa");
        String P_motor = getIntent().getStringExtra("P_motor");
        String P_piso = getIntent().getStringExtra("P_piso");
        String P_tanqueDeComb = getIntent().getStringExtra("P_tanqueDeComb");
        String P_llantas =getIntent().getStringExtra("P_llantas");
        String P_diferencial = getIntent().getStringExtra("P_diferencial");
        String P_cabina = getIntent().getStringExtra("P_cabina");
        String P_cilindrosDeAire = getIntent().getStringExtra("P_cilindrosDeAire");
        String P_mofleEscape =getIntent().getStringExtra("P_mofleEscape");
        String P_quintaRueda = getIntent().getStringExtra("P_quintaRueda");
        String P_remolqueC = getIntent().getStringExtra("P_remolqueC");
        String P_chasis =getIntent().getStringExtra("P_chasis");
        String P_puertasTraseras =getIntent().getStringExtra("P_puertasTraseras");
        String P_paredes =getIntent().getStringExtra("P_paredes");
        String P_sellos = getIntent().getStringExtra("P_sellos");
        String P_lucesLatAmbar =getIntent().getStringExtra("P_lucesLatAmbar");
        String P_lucesDeFrente = getIntent().getStringExtra("P_lucesDeFrente");
        String P_cuartosAmbar = getIntent().getStringExtra("P_cuartosAmbar");
        String P_lucesTraseras = getIntent().getStringExtra("P_lucesTraseras");
        String P_cuartosRojos = getIntent().getStringExtra("P_cuartosRojos");
        String P_lucesDeAltoTraseroas = getIntent().getStringExtra("P_lucesDeAltoTraseros");
        String P_luzDePlaca = getIntent().getStringExtra("P_luzDePlaca");
        String P_zoqueteras = getIntent().getStringExtra("P_zoqueteras");
        String P_manivela = getIntent().getStringExtra("P_manivela");
        String P_guardaPolvos = getIntent().getStringExtra("P_guardaPolvos");
        String P_loderas = getIntent().getStringExtra("P_loderas");
        String P_llantaDeRefaccion = getIntent().getStringExtra("P_llantaDeRefaccion");
        String P_placas = getIntent().getStringExtra("P_placas");
        String P_placasDatos = getIntent().getStringExtra("P_placasDatos");
        String P_comentario2 = getIntent().getStringExtra("P_comentario2");


        folio = getIntent().getStringExtra("folio");
        mensaje = getIntent().getIntExtra("mensaje",0);

        int idIntercambio = getIntent().getIntExtra("idIntercambio",0);

        try {

            if (A_defensa.contains("1")) {
                A_defensa = "Si";
            } else if (A_defensa.contains("0") || A_defensa== null ) {
                A_defensa = "No";
            }
            if (A_motor.contains("1")) {
                A_motor = "Si";
            } else if (A_motor.contains("0") || A_motor== null ) {
                A_motor = "No";
            }
            if (A_piso.contains("1")) {
                A_piso = "Si";
            } else if (A_piso.contains("0") || A_piso== null) {
                A_piso = "No";
            }
            if (A_tanqueDeComb.contains("1")) {
                A_tanqueDeComb = "Si";
            } else if (A_tanqueDeComb.contains("0")|| A_tanqueDeComb== null) {
                A_tanqueDeComb = "No";
            }
            if (A_llantas.contains("1")) {
                A_llantas = "Si";
            } else if (A_llantas.contains("0")|| A_llantas== null) {
                A_llantas = "No";
            }
            if (A_diferencial.contains("1")) {
                A_diferencial = "Si";
            } else if (A_diferencial.contains("0")|| A_diferencial== null) {
                A_diferencial = "No";
            }
            if (A_cabina.contains("1")) {
                A_cabina = "Si";
            } else if (A_cabina.contains("0")|| A_cabina== null) {
                A_cabina = "No";
            }
            if (A_cilindrosDeAire.contains("1")) {
                A_cilindrosDeAire = "Si";
            } else if (A_cilindrosDeAire.contains("0")|| A_cilindrosDeAire== null) {
                A_cilindrosDeAire = "No";

            }
            if (A_mofleEscape.contains("1")) {
                A_mofleEscape = "Si";
            } else if (A_mofleEscape.contains("0")|| A_mofleEscape== null) {
                A_mofleEscape = "No";
            }
            if (A_quintaRueda.contains("1")) {
                A_quintaRueda = "Si";
            } else if (A_quintaRueda.contains("0")|| A_quintaRueda== null) {
                A_quintaRueda = "No";
            }
            if (A_remolqueC.contains("1")) {
                A_remolqueC = "Si";
            } else if (A_remolqueC.contains("0")|| A_remolqueC== null) {
                A_remolqueC = "No";


            }
            if (A_chasis.contains("1")) {
                A_chasis = "Si";
            } else if (A_chasis.contains("0")|| A_chasis== null) {
                A_chasis = "No";
            }
            if (A_puertasTraseras.contains("1")) {
                A_puertasTraseras = "Si";
            } else if (A_puertasTraseras.contains("0")|| A_puertasTraseras== null) {
                A_puertasTraseras = "No";
            }
            if (A_paredes.contains("1")) {
                A_paredes = "Si";
            } else if (A_paredes.contains("0")|| A_paredes== null) {
                A_paredes = "No";
            }
            if (A_sellos.contains("1")) {
                A_sellos = "Si";
            } else if (A_sellos.contains("0")|| A_sellos== null) {
                A_sellos = "No";
            }
            if (A_lucesLatAmbar.contains("1")) {
                A_lucesLatAmbar = "Si";
            } else if (A_lucesLatAmbar.contains("0")|| A_lucesLatAmbar== null) {
                A_lucesLatAmbar = "No";
            }
            if (A_lucesDeFrente.contains("1")) {
                A_lucesDeFrente = "Si";
            } else if (A_lucesDeFrente.contains("0")|| A_lucesDeFrente== null) {
                A_lucesDeFrente = "No";
            }
            if (A_cuartosAmbar.contains("1")) {
                A_cuartosAmbar = "Si";
            } else if (A_cuartosAmbar.contains("0")|| A_cuartosAmbar== null) {
                A_cuartosAmbar = "No";
            }
            if (A_lucesTraseras.contains("1")) {
                A_lucesTraseras = "Si";
            } else if (A_lucesTraseras.contains("0")|| A_lucesTraseras== null) {
                A_lucesTraseras = "No";
            }
            if (A_cuartosRojos.contains("1")) {
                A_cuartosRojos = "Si";
            } else if (A_cuartosRojos.contains("0")|| A_cuartosRojos== null) {
                A_cuartosRojos = "No";
            }
            if (A_lucesDeAltoTraseroas.contains("1")) {
                A_lucesDeAltoTraseroas = "Si";
            } else if (A_lucesDeAltoTraseroas.contains("0")|| A_lucesDeAltoTraseroas== null) {
                A_lucesDeAltoTraseroas = "No";
            }
            if (A_luzDePlaca.contains("1")) {
                A_luzDePlaca = "Si";
            } else if (A_luzDePlaca.contains("0")|| A_luzDePlaca== null) {
                A_luzDePlaca = "No";
            }
            if (A_zoqueteras.contains("1")) {
                A_zoqueteras = "Si";
            } else if (A_zoqueteras.contains("0")|| A_zoqueteras== null) {
                A_zoqueteras = "No";
            }
            if (A_manivela.contains("1")) {
                A_manivela = "Si";
            } else if (A_manivela.contains("0")|| A_manivela== null) {
                A_manivela = "No";
            }
            if (A_guardaPolvos.contains("1")) {
                A_guardaPolvos = "Si";
            } else if (A_guardaPolvos.contains("0")|| A_guardaPolvos== null) {
                A_guardaPolvos = "No";
            }
            if (A_loderas.contains("1")) {
                A_loderas = "Si";
            } else if (A_loderas.contains("0")|| A_loderas== null) {
                A_loderas = "No";
            }
            if (A_llantaDeRefaccion.contains("1")) {
                A_llantaDeRefaccion = "Si";
            } else if (A_llantaDeRefaccion.contains("0")|| A_llantaDeRefaccion== null) {
                A_llantaDeRefaccion = "No";
            }
            if (A_placas.contains("1")) {
                A_placas = "Si";
            } else if (A_placas.contains("0")|| A_placas== null) {
                A_placas = "No";
            }
            if (A_placasDatos.contains("1")) {
                A_placasDatos = "Si";
            } else if (A_placasDatos.contains("0")|| A_placasDatos== null) {
                A_placasDatos = "No";
            }


            if (P_defensa.contains("1")) {
                P_defensa = "Si";
            } else if (P_defensa.contains("0")|| P_defensa== null) {
                P_defensa = "No";
            }
            if (P_motor.contains("1")) {
                P_motor = "Si";
            } else if (P_motor.contains("0")|| P_motor== null) {
                P_motor = "No";
            }
            if (P_piso.contains("1")) {
                P_piso = "Si";
            } else if (P_piso.contains("0")|| P_piso== null) {
                P_piso = "No";
            }
            if (P_tanqueDeComb.contains("1")) {
                P_tanqueDeComb = "Si";
            } else if (P_tanqueDeComb.contains("0")|| P_tanqueDeComb== null) {
                P_tanqueDeComb = "No";
            }
            if (P_llantas.contains("1")) {
                P_llantas = "Si";
            } else if (P_llantas.contains("0")|| P_llantas== null) {
                P_llantas = "No";
            }
            if (P_diferencial.contains("1")) {
                P_diferencial = "Si";
            } else if (P_diferencial.contains("0")|| P_diferencial== null) {
                P_diferencial = "No";
            }
            if (P_cabina.contains("1")) {
                P_cabina = "Si";
            } else if (P_cabina.contains("0")|| P_cabina== null) {
                P_cabina = "No";
            }
            if (P_cilindrosDeAire.contains("1")) {
                P_cilindrosDeAire = "Si";
            } else if (P_cilindrosDeAire.contains("0")|| P_cilindrosDeAire== null) {
                P_cilindrosDeAire = "No";

            }
            if (P_mofleEscape.contains("1")) {
                P_mofleEscape = "Si";
            } else if (P_mofleEscape.contains("0")|| P_mofleEscape== null) {
                P_mofleEscape = "No";
            }
            if (P_quintaRueda.contains("1")) {
                P_quintaRueda = "Si";
            } else if (P_quintaRueda.contains("0")|| P_quintaRueda== null) {
                P_quintaRueda = "No";
            }
            if (P_remolqueC.contains("1")) {
                P_remolqueC = "Si";
            } else if (P_remolqueC.contains("0")|| P_remolqueC== null) {
                P_remolqueC = "No";


            }
            if (P_chasis.contains("1")) {
                P_chasis = "Si";
            } else if (P_chasis.contains("0")|| P_chasis== null) {
                P_chasis = "No";
            }
            if (P_puertasTraseras.contains("1")) {
                P_puertasTraseras = "Si";
            } else if (P_puertasTraseras.contains("0")|| P_puertasTraseras== null) {
                P_puertasTraseras = "No";
            }
            if (P_paredes.contains("1")) {
                P_paredes = "Si";
            } else if (P_paredes.contains("0")|| P_paredes== null) {
                P_paredes = "No";
            }
            if (P_sellos.contains("1")) {
                P_sellos = "Si";
            } else if (P_sellos.contains("0")|| P_sellos== null) {
                P_sellos = "No";
            }
            if (P_lucesLatAmbar.contains("1")) {
                P_lucesLatAmbar = "Si";
            } else if (P_lucesLatAmbar.contains("0")|| P_lucesLatAmbar== null) {
                P_lucesLatAmbar = "No";
            }
            if (P_lucesDeFrente.contains("1")) {
                P_lucesDeFrente = "Si";
            } else if (P_lucesDeFrente.contains("0")|| P_lucesDeFrente== null) {
                P_lucesDeFrente = "No";
            }
            if (P_cuartosAmbar.contains("1")) {
                P_cuartosAmbar = "Si";
            } else if (P_cuartosAmbar.contains("0")|| P_cuartosAmbar== null) {
                P_cuartosAmbar = "No";
            }
            if (P_lucesTraseras.contains("1")) {
                P_lucesTraseras = "Si";
            } else if (P_lucesTraseras.contains("0")|| P_lucesTraseras== null) {
                P_lucesTraseras = "No";
            }
            if (P_cuartosRojos.contains("1")) {
                P_cuartosRojos = "Si";
            } else if (P_cuartosRojos.contains("0")|| P_cuartosRojos== null) {
                P_cuartosRojos = "No";
            }
            if (P_lucesDeAltoTraseroas.contains("1")) {
                P_lucesDeAltoTraseroas = "Si";
            } else if (P_lucesDeAltoTraseroas.contains("0")|| P_lucesDeAltoTraseroas== null) {
                P_lucesDeAltoTraseroas = "No";
            }
            if (P_luzDePlaca.contains("1")) {
                P_luzDePlaca = "Si";
            } else if (P_luzDePlaca.contains("0")|| P_luzDePlaca== null) {
                P_luzDePlaca = "No";
            }
            if (P_zoqueteras.contains("1")) {
                P_zoqueteras = "Si";
            } else if (P_zoqueteras.contains("0")|| P_zoqueteras== null) {
                P_zoqueteras = "No";
            }
            if (P_manivela.contains("1")) {
                P_manivela = "Si";
            } else if (P_manivela.contains("0")|| P_manivela== null) {
                P_manivela = "No";
            }
            if (P_guardaPolvos.contains("1")) {
                P_guardaPolvos = "Si";
            } else if (P_guardaPolvos.contains("0")|| P_guardaPolvos== null) {
                P_guardaPolvos = "No";
            }
            if (P_loderas.contains("1")) {
                P_loderas = "Si";
            } else if (P_loderas.contains("0")|| P_loderas== null) {
                P_loderas = "No";
            }
            if (P_llantaDeRefaccion.contains("1")) {
                P_llantaDeRefaccion = "Si";
            } else if (P_llantaDeRefaccion.contains("0")|| P_llantaDeRefaccion== null) {
                P_llantaDeRefaccion = "No";
            }
            if (P_placas.contains("1")) {
                P_placas = "Si";
            } else if (P_placas.contains("0")|| P_placas== null) {
                P_placas = "No";
            }
            if (P_placasDatos.contains("1")) {
                P_placasDatos = "Si";
            } else if (P_placasDatos.contains("0")|| P_placasDatos== null) {
                P_placasDatos = "No";
            }


            if (P_llantajumbo.contains("1")) {
                P_llantajumbo = "Si";
            } else if (P_llantajumbo.contains("0")|| P_llantajumbo== null) {
                P_llantajumbo = "No";
            }

            if (A_llantajumbo.contains("1")) {
                A_llantajumbo = "Si";
            } else if (A_llantajumbo.contains("0")|| A_llantajumbo== null) {
                A_llantajumbo = "No";
            }


            if (P_selloExtra.contains("1")) {
                P_selloExtra = "Si";
            } else if (P_selloExtra.contains("0")|| P_selloExtra== null) {
                P_selloExtra = "No";
            }

            if (A_selloExtra.contains("1")) {
                A_selloExtra = "Si";
            } else if (A_selloExtra.contains("0")|| A_selloExtra== null) {
                A_selloExtra = "No";
            }

        }catch (Exception e){
            String asd = "asdas";
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

          TWAdefensa = (TextView) findViewById(R.id.defensa_Actual);
          TWAmotor  = (TextView) findViewById(R.id.motor_Actual);
          TWApiso  = (TextView) findViewById(R.id.piso_Actual);
          TWAtanqueDeComb = (TextView) findViewById(R.id.tanqueDeComb_Actual) ;
          TWAllantas  = (TextView) findViewById(R.id.llantas_Actual);
          TWAdiferencial  = (TextView) findViewById(R.id.diferencial_Actual);
          TWAcabina  = (TextView) findViewById(R.id.cabina_Actual);
          TWAcilindrosDeAire = (TextView) findViewById(R.id.cilindrosDeAire_Actual) ;
          TWAmofleEscape = (TextView) findViewById(R.id.mofleEscape_Actual) ;
          TWAquintaRueda  = (TextView) findViewById(R.id.quintaRueda_Actual);
          TWAremolqueC  = (TextView) findViewById(R.id.remolqueC_Actual);
          TWAchasis  = (TextView) findViewById(R.id.chasis_Actual);
          TWApuertasTraseras = (TextView) findViewById(R.id.puertasTraseras_Actual) ;
          TWAparedes = (TextView) findViewById(R.id.paredes_Actual) ;
          TWAsellos = (TextView) findViewById(R.id.sellos_Actual) ;
          TWAlucesLatAmbar  = (TextView) findViewById(R.id.lucesLatAmbar_Actual);
          TWAlucesDeFrente  = (TextView) findViewById(R.id.lucesDeFrente_Actual);
          TWAcuartosAmbar = (TextView) findViewById(R.id.cuartosAmbar_Actual) ;
          TWAlucesTraseras = (TextView) findViewById(R.id.lucesTraseras_Actual) ;
          TWAcuartosRojos  = (TextView) findViewById(R.id.cuartosRojos_Actual);
          TWAlucesDeAltoTraseros  = (TextView) findViewById(R.id.lucesDeAltoTrasero_Actual);
          TWAluzDePlaca = (TextView) findViewById(R.id.luzDePlaca_Actual);
          TWAzoqueteas  = (TextView) findViewById(R.id.zoqueteras_Actual);
          TWAmanivela  = (TextView) findViewById(R.id.manivela_Actual);
          TWAguardaPolvos  = (TextView) findViewById(R.id.guardaPolvos_Actual);
          TWAloderas  = (TextView) findViewById(R.id.loderas_Actual);
          TWAllantaDeRefaccion = (TextView) findViewById(R.id.llantaDeRefaccion_Actual) ;
          TWAplacas  = (TextView) findViewById(R.id.placas_Actual);
          TWAplacasDatos  = (TextView) findViewById(R.id.placasDatos_Actual);
        TWAcomentario2  = (EditText) findViewById(R.id.comentario2_Actual);



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

        TWPdefensa = (TextView) findViewById(R.id.defensa_Pasado);
        TWPmotor  = (TextView) findViewById(R.id.motor_Pasado);
        TWPpiso  = (TextView) findViewById(R.id.piso_Pasado);
        TWPtanqueDeComb = (TextView) findViewById(R.id.tanqueDeComb_Pasado) ;
        TWPllantas  = (TextView) findViewById(R.id.llantas_Pasado);
        TWPdiferencial  = (TextView) findViewById(R.id.diferencial_Pasado);
        TWPcabina  = (TextView) findViewById(R.id.cabina_Pasado);
        TWPcilindrosDeAire = (TextView) findViewById(R.id.cilindrosDeAire_Pasado) ;
        TWPmofleEscape = (TextView) findViewById(R.id.mofleEscape_Pasado) ;
        TWPquintaRueda  = (TextView) findViewById(R.id.quintaRueda_Pasado);
        TWPremolqueC  = (TextView) findViewById(R.id.remolqueC_Pasado);
        TWPchasis  = (TextView) findViewById(R.id.chasis_Pasado);
        TWPpuertasTraseras = (TextView) findViewById(R.id.puertasTraseras_Pasado) ;
        TWPparedes = (TextView) findViewById(R.id.paredes_Pasado) ;
        TWPsellos = (TextView) findViewById(R.id.sellos_Pasado) ;
        TWPlucesLatAmbar  = (TextView) findViewById(R.id.lucesLatAmbar_Pasado);
        TWPlucesDeFrente  = (TextView) findViewById(R.id.lucesDeFrente_Pasado);
        TWPcuartosAmbar = (TextView) findViewById(R.id.cuartosAmbar_Pasado) ;
        TWPlucesTraseras = (TextView) findViewById(R.id.lucesTraseras_Pasado) ;
        TWPcuartosRojos  = (TextView) findViewById(R.id.cuartosRojos_Pasado);
        TWPlucesDeAltoTraseros  = (TextView) findViewById(R.id.lucesDeAltoTrasero_Pasado);
        TWPluzDePlaca = (TextView) findViewById(R.id.luzDePlaca_Pasado);
        TWPzoqueteas  = (TextView) findViewById(R.id.zoqueteras_Pasado);
        TWPmanivela  = (TextView) findViewById(R.id.manivela_Pasado);
        TWPguardaPolvos  = (TextView) findViewById(R.id.guardaPolvos_Pasado);
        TWPloderas  = (TextView) findViewById(R.id.loderas_Pasado);
        TWPllantaDeRefaccion = (TextView) findViewById(R.id.llantaDeRefaccion_Pasado) ;
        TWPplacas  = (TextView) findViewById(R.id.placas_Pasado);
        TWPplacasDatos  = (TextView) findViewById(R.id.placasDatos_Pasado);
        TWPcomentario2  = (EditText) findViewById(R.id.comentario2_Pasado);

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

        TWAdefensa.setText(A_defensa);
        TWAmotor.setText(A_motor);
        TWApiso.setText(A_piso);
        TWAtanqueDeComb.setText(A_tanqueDeComb);
        TWAllantas.setText(A_llantas);
        TWAdiferencial.setText(A_diferencial);
        TWAcabina.setText(A_cabina);
        TWAcilindrosDeAire.setText(A_cilindrosDeAire);
        TWAmofleEscape.setText(A_mofleEscape);
        TWAquintaRueda.setText(A_quintaRueda);
        TWAremolqueC.setText(A_remolqueC);
        TWAchasis.setText(A_chasis);
        TWApuertasTraseras.setText(A_puertasTraseras);
        TWAparedes.setText(A_paredes);
        TWAsellos.setText(A_sellos);
        TWAlucesLatAmbar.setText(A_lucesLatAmbar);
        TWAlucesDeFrente.setText(A_lucesDeFrente);
        TWAcuartosAmbar.setText(A_cuartosAmbar);
        TWAlucesTraseras.setText(A_lucesTraseras);
        TWAcuartosRojos.setText(A_cuartosRojos);
        TWAlucesDeAltoTraseros.setText(A_lucesDeAltoTraseroas);
        TWAluzDePlaca.setText(A_luzDePlaca);
        TWAzoqueteas.setText(A_zoqueteras);
        TWAmanivela.setText(A_manivela);
        TWAguardaPolvos.setText(A_guardaPolvos);
        TWAloderas.setText(A_loderas);
        TWAllantaDeRefaccion.setText(A_llantaDeRefaccion);
        TWAplacas.setText(A_placas);
        TWAplacasDatos.setText(A_placasDatos);
        TWAcomentario2.setText(A_comentario2);

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

        TWPdefensa.setText(P_defensa);
        TWPmotor.setText(P_motor);
        TWPpiso.setText(P_piso);
        TWPtanqueDeComb.setText(P_tanqueDeComb);
        TWPllantas.setText(P_llantas);
        TWPdiferencial.setText(P_diferencial);
        TWPcabina.setText(P_cabina);
        TWPcilindrosDeAire.setText(P_cilindrosDeAire);
        TWPmofleEscape.setText(P_mofleEscape);
        TWPquintaRueda.setText(P_quintaRueda);
        TWPremolqueC.setText(P_remolqueC);
        TWPchasis.setText(P_chasis);
        TWPpuertasTraseras.setText(P_puertasTraseras);
        TWPparedes.setText(P_paredes);
        TWPsellos.setText(P_sellos);
        TWPlucesLatAmbar.setText(P_lucesLatAmbar);
        TWPlucesDeFrente.setText(P_lucesDeFrente);
        TWPcuartosAmbar.setText(P_cuartosAmbar);
        TWPlucesTraseras.setText(P_lucesTraseras);
        TWPcuartosRojos.setText(P_cuartosRojos);
        TWPlucesDeAltoTraseros.setText(P_lucesDeAltoTraseroas);
        TWPluzDePlaca.setText(P_luzDePlaca);
        TWPzoqueteas.setText(P_zoqueteras);
        TWPmanivela.setText(P_manivela);
        TWPguardaPolvos.setText(P_guardaPolvos);
        TWPloderas.setText(P_loderas);
        TWPllantaDeRefaccion.setText(P_llantaDeRefaccion);
        TWPplacas.setText(P_placas);
        TWPplacasDatos.setText(P_placasDatos);
        TWPcomentario2.setText(P_comentario2);


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comentario = comen.getText().toString();

                if(comentario.length()>0){


                    Post6 post6 = new Post6(user, password, 0, "1", idIntercambio, "", "", "",
                            "", "", "", "", "", "", "", 0, 0, 0, "", 3, comentario,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "","","","","","","","","","","","",
                            "","","","","","","","","",
                    "","","",
                            "","","","","","","","","","","","","","","","","","","","","","","","","","","","",
                            "","","","","","","","","","","","","",
                            "","","","","","","","","","","","","",
                            "","","","","","","","","","","","","",
                            ""

                    );





                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.4.92:80/api/")
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
                            i.putExtra("mensaje", mensaje);
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
                            "", "", "", "", "", "", "", 0, 0, 0, "", 1, comentario,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "","","","","","","","","","","","",
                            "","","","","","","","","",
                            "","","",
                            "","","","","","","","","","","","","","","","","","","","","","","","","","","","",
                            "","","","","","","","","","","","","",
                            "","","","","","","","","","","","","",
                            "","","","","","","","","","","","","",
                            ""

                    );

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.4.92:80/api/")
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
