package com.dx.dxintercambio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class listAdapter extends ArrayAdapter<CPopulateList> {

    List<CPopulateList> intercambioList ;
    int resource ;
    Context mCtx ;


    public listAdapter( Context mCtx, int resource, List<CPopulateList> intercambioList) {
        super(mCtx, resource, intercambioList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.intercambioList = intercambioList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater infalter = LayoutInflater.from(mCtx);

        View view = infalter.inflate(R.layout.mlist_item, null);


        ImageView send = view.findViewById(R.id.imageView3);
        ImageView okSend = view.findViewById(R.id.imageView26);
        ProgressBar progressBar = view.findViewById(R.id.progressBar3);
        send.setImageResource(R.drawable.ic_subir);
        okSend.setImageResource(R.drawable.ic_ok);

        okSend.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        TextView remolque = view.findViewById(R.id.t3);
        TextView folio = view.findViewById(R.id.t1);
        TextView usuario = view.findViewById(R.id.t5);
        TextView unidad = view.findViewById(R.id.t4);
        TextView fecha = view.findViewById(R.id.t2);


        String folioData = "Folio Interno\n"+intercambioList.get(position).getFolioInterno();
        String fechaData = "Fecha y Hora\n"+intercambioList.get(position).getFechaFin();
        String remolqueData = "Remolque\n"+intercambioList.get(position).getRemolque();
        String unidadData = "Unidad\n"+intercambioList.get(position).getTracto();
        String usuarioData = "Usuario\n"+intercambioList.get(position).getUsuario();

        if(intercambioList.get(position).getRemolque()== null){
            remolqueData = "Remolque\n"+"Otro";
        }

        if(intercambioList.get(position).getTracto()== null){
            unidadData = "Unidad\n"+"Otro";
        }



        remolque.setText(remolqueData);
        folio.setText(folioData);
        usuario.setText(usuarioData);
        unidad.setText(unidadData);
        fecha.setText(fechaData);

        String Ufolio = intercambioList.get(position).getFolioInterno();

        send.setOnClickListener(v -> {

            progressBar.setVisibility(View.VISIBLE);

            DataBaseHelper dataBaseHelper = new DataBaseHelper(mCtx);

            List<CintercambioElectronico> intercambio = dataBaseHelper.selectIntercambio(Ufolio);

            String U_estatus= intercambio.get(0).getEstatus() ;
            String U_folio= intercambio.get(0).getFolio() ;
            String U_terminal= intercambio.get(0).getTerminal() ;
            String U_idUsuario= intercambio.get(0).getIdUsuario() ;
            String U_tipoOperacion= intercambio.get(0).getTipoOperacion() ;
            String U_tipoMovimiento= intercambio.get(0).getTipoMovimiento() ;
            String U_estatusRemolque= intercambio.get(0).getEstatusRemolque() ;
            String U_comentario2= intercambio.get(0).getComentario2() ;
            String U_nombreOperador= intercambio.get(0).getNombreOperador() ;
            String U_idOperador= intercambio.get(0).getIdOperador() ;
            String U_idTransportista= intercambio.get(0).getIdTransportista() ;
            String U_unidad= intercambio.get(0).getUnidad() ;
            String U_idUnidad= intercambio.get(0).getIdUnidad() ;
            String U_idLinea= intercambio.get(0).getIdLinea() ;
            String U_remolque= intercambio.get(0).getRemolque() ;
            String U_idRemolque= intercambio.get(0).getIdRemolque() ;
            String U_fechaInicio= intercambio.get(0).getFechaInicio() ;
            String U_tractoDefensa= intercambio.get(0).getTractoDefensa() ;
            String U_tractoCabina= intercambio.get(0).getTractoCabina() ;
            String U_tractoQuintaRueda= intercambio.get(0).getTractoQuintaRueda() ;
            String U_tractoTuboEscape= intercambio.get(0).getTractoTuboEscape() ;
            String U_tractoBaseRemolque= intercambio.get(0).getTractoBaseRemolque() ;
            String U_tractoTechos= intercambio.get(0).getTractoTechos() ;
            String U_tractoLlantas= intercambio.get(0).getTractoLlantas() ;
            String U_tractoTanqueDiesel= intercambio.get(0).getTractoTanqueDiesel() ;
            String U_tractoTanqueAire= intercambio.get(0).getTractoTanqueAire() ;
            String U_tractoEjeTransmision= intercambio.get(0).getTractoEjeTransmision() ;
            String U_tractoMotor= intercambio.get(0).getTractoMotor() ;
            String U_remolqueInspeccionMecanica= intercambio.get(0).getRemolqueInspeccionMecanica() ;
            String U_remolqueLucesIzquierda= intercambio.get(0).getRemolqueLucesIzquierda() ;
            String U_remolqueLucesGaliboIzqFrontalSup= intercambio.get(0).getRemolqueLucesGaliboIzqFrontalSup() ;
            String U_remolqueManitasIzq= intercambio.get(0).getRemolqueManitasIzq() ;
            String U_remolqueManivelaIzq= intercambio.get(0).getRemolqueManivelaIzq() ;
            String U_remolquePatinIzquierdo= intercambio.get(0).getRemolquePatinIzquierdo() ;
            String U_remolqueCuartoLadoIzq= intercambio.get(0).getRemolqueCuartoLadoIzq() ;
            String U_LoderaIzq= intercambio.get(0).getLoderaIzq() ;
            String U_remolqueLucesIzqP= intercambio.get(0).getRemolqueLucesIzqP() ;
            String U_LuzAbsIzq= intercambio.get(0).getLuzAbsIzq() ;
            String U_luzBarcoIzq= intercambio.get(0).getLuzBarcoIzq() ;
            String U_rompevientosIzq= intercambio.get(0).getRompevientosIzq() ;
            String U_remolqueLlantaIzqJumbo= intercambio.get(0).getRemolqueLlantaIzqJumbo() ;
            String U_remolqueLlantaIzqEje1Posicion1Marca= intercambio.get(0).getRemolqueLlantaIzqEje1Posicion1Marca() ;
            String U_remolqueLlantaIzqEje1Posicion1Estatus= intercambio.get(0).getRemolqueLlantaIzqEje1Posicion1Estatus() ;
            String U_remolqueLlantaIzqEje1Posicion2Marca= intercambio.get(0).getRemolqueLlantaIzqEje1Posicion2Marca() ;
            String U_remolqueLlantaIzqEje1Posicion2Estatus= intercambio.get(0).getRemolqueLlantaIzqEje1Posicion2Estatus() ;
            String U_remolqueLlantaIzqEje1BrilosPivote= intercambio.get(0).getRemolqueLlantaIzqEje1BrilosPivote() ;
            String U_remolqueLlantaIzqEje1Posicion1= intercambio.get(0).getRemolqueLlantaIzqEje1Posicion1() ;
            String U_remolqueLlantaIzqEje1Posicion2= intercambio.get(0).getRemolqueLlantaIzqEje1Posicion2() ;
            String U_remolqueLlantaIzqEje1MesaYoyo= intercambio.get(0).getRemolqueLlantaIzqEje1MesaYoyo() ;
            String U_remolqueLlantaIzqEje1Rin= intercambio.get(0).getRemolqueLlantaIzqEje1Rin() ;
            String U_remolqueLlantaIzqEje1Lodera= intercambio.get(0).getRemolqueLlantaIzqEje1Lodera() ;
            String U_remolqueLlantaIzqEje2Posicion5Marca= intercambio.get(0).getRemolqueLlantaIzqEje2Posicion5Marca() ;
            String U_remolqueLlantaIzqEje2Posicion5Estatus= intercambio.get(0).getRemolqueLlantaIzqEje2Posicion5Estatus() ;
            String U_remolqueLlantaIzqEje2Posicion6Marca= intercambio.get(0).getRemolqueLlantaIzqEje2Posicion6Marca() ;
            String U_remolqueLlantaIzqEje2Posicion6Estatus= intercambio.get(0).getRemolqueLlantaIzqEje2Posicion6Estatus() ;
            String U_remolqueLlantaIzqEje2BrilosPivote= intercambio.get(0).getRemolqueLlantaIzqEje2BrilosPivote() ;
            String U_remolqueLlantaIzqEje2Posicion5= intercambio.get(0).getRemolqueLlantaIzqEje2Posicion5() ;
            String U_remolqueLlantaIzqEje2Posicion6= intercambio.get(0).getRemolqueLlantaIzqEje2Posicion6() ;
            String U_remolqueLlantaIzqEje2MesaYoyo= intercambio.get(0).getRemolqueLlantaIzqEje2MesaYoyo() ;
            String U_remolqueLlantaIzqEje2Rin= intercambio.get(0).getRemolqueLlantaIzqEje2Rin() ;
            String U_remolqueLlantaIzqEje2Lodera= intercambio.get(0).getRemolqueLlantaIzqEje2Lodera() ;
            String U_remolqueChasisFrontalIzqAmortiguador= intercambio.get(0).getRemolqueChasisFrontalIzqAmortiguador() ;
            String U_remolqueChasisFrontalIzqBolsaAire= intercambio.get(0).getRemolqueChasisFrontalIzqBolsaAire() ;
            String U_remolqueChasisFrontalIzqGavilan= intercambio.get(0).getRemolqueChasisFrontalIzqGavilan() ;
            String U_remolqueChasisFrontalIzqMuelle= intercambio.get(0).getRemolqueChasisFrontalIzqMuelle() ;
            String U_remolqueChasisFrontalIzqRotachamber= intercambio.get(0).getRemolqueChasisFrontalIzqRotachamber() ;
            String U_remolqueChasisTraseroIzqAmortiguador= intercambio.get(0).getRemolqueChasisTraseroIzqAmortiguador() ;
            String U_remolqueChasisTraseroIzqBolsaAire= intercambio.get(0).getRemolqueChasisTraseroIzqBolsaAire() ;
            String U_remolqueChasisTraseroIzqGavilan= intercambio.get(0).getRemolqueChasisTraseroIzqGavilan() ;
            String U_remolqueChasisTraseroIzqMuelle= intercambio.get(0).getRemolqueChasisTraseroIzqMuelle() ;
            String U_remolqueChasisTraseroIzqRotachamber= intercambio.get(0).getRemolqueChasisTraseroIzqRotachamber() ;
            String U_remolqueIzqObservaciones= intercambio.get(0).getRemolqueIzqObservaciones() ;
            String U_Placas= intercambio.get(0).getPlacas() ;
            String U_Sello1= intercambio.get(0).getSello1() ;
            String U_Sello2= intercambio.get(0).getSello2() ;
            String U_Sello3= intercambio.get(0).getSello3() ;
            String U_remolquePuertasBisagrasTornillos= intercambio.get(0).getRemolquePuertasBisagrasTornillos() ;
            String U_remolquePuertasDefensa= intercambio.get(0).getRemolquePuertasDefensa() ;
            String U_remolquePuertasLuzGaliboSupTraseras= intercambio.get(0).getRemolquePuertasLuzGaliboSupTraseras() ;
            String U_remolquePuertasPlafonDerecho= intercambio.get(0).getRemolquePuertasPlafonDerecho() ;
            String U_remolquePuertasPlafonIzquierdo= intercambio.get(0).getRemolquePuertasPlafonIzquierdo() ;
            String U_remolquePlacasLuzPlaca= intercambio.get(0).getRemolquePlacasLuzPlaca() ;
            String U_remolquePlacasPlaca= intercambio.get(0).getRemolquePlacasPlaca() ;
            String U_remolqueSello1Sello= intercambio.get(0).getRemolqueSello1Sello() ;
            String U_remolqueSello1Seguridad= intercambio.get(0).getRemolqueSello1Seguridad() ;
            String U_remolqueSello2Escotilla= intercambio.get(0).getRemolqueSello2Escotilla() ;
            String U_remolqueSello2Sello= intercambio.get(0).getRemolqueSello2Sello() ;
            String U_remolqueSello2Seguridad= intercambio.get(0).getRemolqueSello2Seguridad() ;
            String U_remolqueSello2Vvtt= intercambio.get(0).getRemolqueSello2Vvtt() ;
            String U_remolqueTraseraObservaciones= intercambio.get(0).getRemolqueTraseraObservaciones() ;
            String U_remolqueChasisTraseroDerAmortiguador= intercambio.get(0).getRemolqueChasisTraseroDerAmortiguador() ;
            String U_remolqueChasisTraseroDerBolsaAire= intercambio.get(0).getRemolqueChasisTraseroDerBolsaAire() ;
            String U_remolqueChasisTraseroDerGavilan= intercambio.get(0).getRemolqueChasisTraseroDerGavilan() ;
            String U_remolqueChasisTraseroDerMuelle= intercambio.get(0).getRemolqueChasisTraseroDerMuelle() ;
            String U_remolqueChasisTraseroDerRotachamber= intercambio.get(0).getRemolqueChasisTraseroDerRotachamber() ;
            String U_remolqueLlantaDerEje2BrilosPivote= intercambio.get(0).getRemolqueLlantaDerEje2BrilosPivote() ;
            String U_remolqueLlantaDerEje2Posicion7Marca= intercambio.get(0).getRemolqueLlantaDerEje2Posicion7Marca() ;
            String U_remolqueLlantaDerEje2Posicion7Estatus= intercambio.get(0).getRemolqueLlantaDerEje2Posicion7Estatus() ;
            String U_remolqueLlantaDerEje2Posicion8Marca= intercambio.get(0).getRemolqueLlantaDerEje2Posicion8Marca() ;
            String U_remolqueLlantaDerEje2Posicion8Estatus= intercambio.get(0).getRemolqueLlantaDerEje2Posicion8Estatus() ;
            String U_remolqueLlantaDerEje2Posicion7= intercambio.get(0).getRemolqueLlantaDerEje2Posicion7() ;
            String U_remolqueLlantaDerEje2Posicion8= intercambio.get(0).getRemolqueLlantaDerEje2Posicion8();
            String U_remolqueLlantaDerEje2MasaYoyo= intercambio.get(0).getRemolqueLlantaDerEje2MasaYoyo() ;
            String U_remolqueLlantaDerEje2Rin= intercambio.get(0).getRemolqueLlantaDerEje2Rin() ;
            String U_remolqueLlantaDerEje2Lodera= intercambio.get(0).getRemolqueLlantaDerEje2Lodera() ;
            String U_remolqueLlantaDerEje1BrilosPivote= intercambio.get(0).getRemolqueLlantaDerEje1BrilosPivote() ;
            String U_remolqueLlantaDerEje1Posicion3Marca= intercambio.get(0).getRemolqueLlantaDerEje1Posicion3Marca() ;
            String U_remolqueLlantaDerEje1Posicion3Estatus= intercambio.get(0).getRemolqueLlantaDerEje1Posicion3Estatus() ;
            String U_remolqueLlantaDerEje1Posicion4Marca= intercambio.get(0).getRemolqueLlantaDerEje1Posicion4Marca() ;
            String U_remolqueLlantaDerEje1Posicion4Estatus= intercambio.get(0).getRemolqueLlantaDerEje1Posicion4Estatus() ;
            String U_remolqueLlantaDerEje1Posicion3= intercambio.get(0).getRemolqueLlantaDerEje1Posicion3() ;
            String U_remolqueLlantaDerEje1Posicion4= intercambio.get(0).getRemolqueLlantaDerEje1Posicion4() ;
            String U_remolqueLlantaDerEje1MasaYoyo= intercambio.get(0).getRemolqueLlantaDerEje1MasaYoyo() ;
            String U_remolqueLlantaDerEje1Rin= intercambio.get(0).getRemolqueLlantaDerEje1Rin() ;
            String U_remolqueLlantaDerEje1Lodera= intercambio.get(0).getRemolqueLlantaDerEje1Lodera() ;
            String U_remolqueChasisFrontalDerAmortiguador= intercambio.get(0).getRemolqueChasisFrontalDerAmortiguador() ;
            String U_remolqueChasisFrontalDerBolsaAire= intercambio.get(0).getRemolqueChasisFrontalDerBolsaAire() ;
            String U_remolqueChasisFrontalDerGavilan= intercambio.get(0).getRemolqueChasisFrontalDerGavilan() ;
            String U_remolqueChasisFrontalDerMuelle= intercambio.get(0).getRemolqueChasisFrontalDerMuelle() ;
            String U_remolqueChasisFrontalDerRotachamber= intercambio.get(0).getRemolqueChasisFrontalDerRotachamber() ;
            String U_remolquePisoPLagas= intercambio.get(0).getRemolquePisoPLagas() ;
            String U_remolqueTechoPlagas= intercambio.get(0).getRemolqueTechoPlagas() ;
            String U_remolqueDerLuces= intercambio.get(0).getRemolqueDerLuces() ;
            String U_remolqueDerGaliboFrontal= intercambio.get(0).getRemolqueDerGaliboFrontal() ;
            String U_remolqueDerParedPlagas= intercambio.get(0).getRemolqueDerParedPlagas() ;
            String U_remolqueDerIzqParedPlagas= intercambio.get(0).getRemolqueDerIzqParedPlagas() ;
            String U_remolqueDerPatin= intercambio.get(0).getRemolqueDerPatin() ;
            String U_remolqueCuartoLadoDer= intercambio.get(0).getRemolqueCuartoLadoDer() ;
            String U_LoderaDer= intercambio.get(0).getLoderaDer() ;
            String U_remolqueLucesDerP2= intercambio.get(0).getRemolqueLucesDerP2() ;
            String U_luzBarcoDer= intercambio.get(0).getLuzBarcoDer() ;
            String U_rompevientosDer= intercambio.get(0).getRompevientosDer() ;
            String U_remolqueLlantaDerJumbo= intercambio.get(0).getRemolqueLlantaDerJumbo();
            String U_remolqueDerObservaciones= intercambio.get(0).getRemolqueDerObservaciones() ;
            String U_licenciaUrl= intercambio.get(0).getLicenciaUrl() ;
            String U_tractoIzqUrl= intercambio.get(0).getTractoIzqUrl() ;
            String U_tractoFrenteUrl= intercambio.get(0).getTractoFrenteUrl() ;
            String U_tractoDerUrl= intercambio.get(0).getTractoDerUrl() ;
            String U_noEcoUrl= intercambio.get(0).getNoEcoUrl() ;
            String U_vinUrl= intercambio.get(0).getVinUrl() ;
            String U_remolqueCostadoTraseroIzqUrl= intercambio.get(0).getRemolqueCostadoTraseroIzqUrl() ;
            String U_remolqueCostadoFrenteIzquierdoUrl= intercambio.get(0).getRemolqueCostadoFrenteIzquierdoUrl() ;
            String U_remolqueLlantaIzqEje1FotoUrl= intercambio.get(0).getRemolqueLlantaIzqEje1FotoUrl() ;
            String U_remolqueLlantaIzqEje2FotoUrl= intercambio.get(0).getRemolqueLlantaIzqEje2FotoUrl() ;
            String U_remolqueChasisFrontalIzqFotoUrl= intercambio.get(0).getRemolqueChasisFrontalIzqFotoUrl() ;
            String U_remolqueChasisTraseroIzqFotoUrl= intercambio.get(0).getRemolqueChasisTraseroIzqFotoUrl() ;
            String U_remolqueIzqDano1FotoUrl= intercambio.get(0).getRemolqueIzqDano1FotoUrl() ;
            String U_remolqueIzqDano2FotoUrl= intercambio.get(0).getRemolqueIzqDano2FotoUrl() ;
            String U_remolqueIzqDano3FotoUrl= intercambio.get(0).getRemolqueIzqDano3FotoUrl() ;
            String U_remolqueIzqDano4FotoUrl= intercambio.get(0).getRemolqueIzqDano4FotoUrl() ;
            String U_remolquePuertasFotoUrl= intercambio.get(0).getRemolquePuertasFotoUrl() ;
            String U_remolquePlacasFotoUrl= intercambio.get(0).getRemolquePlacasFotoUrl() ;
            String U_remolqueSello1FotoUrl= intercambio.get(0).getRemolqueSello1FotoUrl() ;
            String U_remolqueSello2FotoUrl= intercambio.get(0).getRemolqueSello2FotoUrl() ;
            String U_remolqueSello3FotoUrl= intercambio.get(0).getRemolqueSello3FotoUrl() ;
            String U_remolqueTraseroDano1FotoUrl= intercambio.get(0).getRemolqueTraseroDano1FotoUrl() ;
            String U_remolqueTraseroDano2FotoUrl= intercambio.get(0).getRemolqueTraseroDano2FotoUrl() ;
            String U_remolqueTraseroDano3FotoUrl= intercambio.get(0).getRemolqueTraseroDano3FotoUrl() ;
            String U_remolqueTraseroDano4FotoUrl= intercambio.get(0).getRemolqueTraseroDano4FotoUrl() ;
            String U_remolqueCostadoTraseroDerUrl= intercambio.get(0).getRemolqueCostadoTraseroDerUrl() ;
            String U_remolqueCostadoFrenteDerechoUrl= intercambio.get(0).getRemolqueCostadoFrenteDerechoUrl() ;
            String U_remolqueLlantaDerEje1FotoUrl= intercambio.get(0).getRemolqueLlantaDerEje1FotoUrl() ;
            String U_remolqueLlantaDerEje2FotoUrl= intercambio.get(0).getRemolqueLlantaDerEje2FotoUrl() ;
            String U_remolqueChasisFrontalDerFotoUrl= intercambio.get(0).getRemolqueChasisFrontalDerFotoUrl() ;
            String U_remolqueChasisTraseroDerFotoUrl= intercambio.get(0).getRemolqueChasisTraseroDerFotoUrl() ;
            String U_remolqueDerDano1FotoUrl= intercambio.get(0).getRemolqueDerDano1FotoUrl() ;
            String U_remolqueDerDano2FotoUrl= intercambio.get(0).getRemolqueDerDano2FotoUrl() ;
            String U_remolqueDerDano3FotoUrl= intercambio.get(0).getRemolqueDerDano3FotoUrl() ;
            String U_remolqueDerDano4FotoUrl= intercambio.get(0).getRemolqueDerDano4FotoUrl() ;
            String U_firmaOperadorUrl= intercambio.get(0).getFirmaOperadorUrl() ;
            String U_firmaIntercambistaUrl= intercambio.get(0).getFirmaIntercambistaUrl() ;
            String U_fechaFin= intercambio.get(0).getFechaFin() ;

            CintercambioElectronico cintercambioElectronico = new CintercambioElectronico(
            U_estatus,
            U_folio,
            U_terminal,
            U_idUsuario,
            U_tipoOperacion,
            U_tipoMovimiento,
            U_estatusRemolque,
            U_comentario2,
            U_nombreOperador,
            U_idOperador,
            U_idTransportista,
            U_unidad,
            U_idUnidad,
            U_idLinea,
            U_remolque,
            U_idRemolque,
            U_fechaInicio,
            U_tractoDefensa,
            U_tractoCabina,
            U_tractoQuintaRueda,
            U_tractoTuboEscape,
            U_tractoBaseRemolque,
            U_tractoTechos,
            U_tractoLlantas,
            U_tractoTanqueDiesel,
            U_tractoTanqueAire,
            U_tractoEjeTransmision,
            U_tractoMotor,
            U_remolqueInspeccionMecanica,
            U_remolqueLucesIzquierda,
            U_remolqueLucesGaliboIzqFrontalSup,
            U_remolqueManitasIzq,
            U_remolqueManivelaIzq,
            U_remolquePatinIzquierdo,
            U_remolqueCuartoLadoIzq,
            U_LoderaIzq,
            U_remolqueLucesIzqP,
            U_LuzAbsIzq,
            U_luzBarcoIzq,
            U_rompevientosIzq,
            U_remolqueLlantaIzqJumbo,
            U_remolqueLlantaIzqEje1Posicion1Marca,
            U_remolqueLlantaIzqEje1Posicion1Estatus,
            U_remolqueLlantaIzqEje1Posicion2Marca,
            U_remolqueLlantaIzqEje1Posicion2Estatus,
            U_remolqueLlantaIzqEje1BrilosPivote,
            U_remolqueLlantaIzqEje1Posicion1,
            U_remolqueLlantaIzqEje1Posicion2,
            U_remolqueLlantaIzqEje1MesaYoyo,
            U_remolqueLlantaIzqEje1Rin,
            U_remolqueLlantaIzqEje1Lodera,
            U_remolqueLlantaIzqEje2Posicion5Marca,
            U_remolqueLlantaIzqEje2Posicion5Estatus,
            U_remolqueLlantaIzqEje2Posicion6Marca,
            U_remolqueLlantaIzqEje2Posicion6Estatus,
            U_remolqueLlantaIzqEje2BrilosPivote,
            U_remolqueLlantaIzqEje2Posicion5,
            U_remolqueLlantaIzqEje2Posicion6,
            U_remolqueLlantaIzqEje2MesaYoyo,
            U_remolqueLlantaIzqEje2Rin,
            U_remolqueLlantaIzqEje2Lodera,
            U_remolqueChasisFrontalIzqAmortiguador,
            U_remolqueChasisFrontalIzqBolsaAire,
            U_remolqueChasisFrontalIzqGavilan,
            U_remolqueChasisFrontalIzqMuelle,
            U_remolqueChasisFrontalIzqRotachamber,
            U_remolqueChasisTraseroIzqAmortiguador,
            U_remolqueChasisTraseroIzqBolsaAire,
            U_remolqueChasisTraseroIzqGavilan,
            U_remolqueChasisTraseroIzqMuelle,
            U_remolqueChasisTraseroIzqRotachamber,
            U_remolqueIzqObservaciones,
            U_Placas,
            U_Sello1,
            U_Sello2,
            U_Sello3,
            U_remolquePuertasBisagrasTornillos,
            U_remolquePuertasDefensa,
            U_remolquePuertasLuzGaliboSupTraseras,
            U_remolquePuertasPlafonDerecho,
            U_remolquePuertasPlafonIzquierdo,
            U_remolquePlacasLuzPlaca,
            U_remolquePlacasPlaca,
            U_remolqueSello1Sello,
            U_remolqueSello1Seguridad,
            U_remolqueSello2Escotilla,
            U_remolqueSello2Sello,
            U_remolqueSello2Seguridad,
            U_remolqueSello2Vvtt,
            U_remolqueTraseraObservaciones,
            U_remolqueChasisTraseroDerAmortiguador,
            U_remolqueChasisTraseroDerBolsaAire,
            U_remolqueChasisTraseroDerGavilan,
            U_remolqueChasisTraseroDerMuelle,
            U_remolqueChasisTraseroDerRotachamber,
            U_remolqueLlantaDerEje2BrilosPivote,
            U_remolqueLlantaDerEje2Posicion7Marca,
            U_remolqueLlantaDerEje2Posicion7Estatus,
            U_remolqueLlantaDerEje2Posicion8Marca,
            U_remolqueLlantaDerEje2Posicion8Estatus,
            U_remolqueLlantaDerEje2Posicion7,
            U_remolqueLlantaDerEje2Posicion8,
            U_remolqueLlantaDerEje2MasaYoyo,
            U_remolqueLlantaDerEje2Rin,
            U_remolqueLlantaDerEje2Lodera,
            U_remolqueLlantaDerEje1BrilosPivote,
            U_remolqueLlantaDerEje1Posicion3Marca,
            U_remolqueLlantaDerEje1Posicion3Estatus,
            U_remolqueLlantaDerEje1Posicion4Marca,
            U_remolqueLlantaDerEje1Posicion4Estatus,
            U_remolqueLlantaDerEje1Posicion3,
            U_remolqueLlantaDerEje1Posicion4,
            U_remolqueLlantaDerEje1MasaYoyo,
            U_remolqueLlantaDerEje1Rin,
            U_remolqueLlantaDerEje1Lodera,
            U_remolqueChasisFrontalDerAmortiguador,
            U_remolqueChasisFrontalDerBolsaAire,
            U_remolqueChasisFrontalDerGavilan,
            U_remolqueChasisFrontalDerMuelle,
            U_remolqueChasisFrontalDerRotachamber,
            U_remolquePisoPLagas,
            U_remolqueTechoPlagas,
            U_remolqueDerLuces,
            U_remolqueDerGaliboFrontal,
            U_remolqueDerParedPlagas,
            U_remolqueDerIzqParedPlagas,
            U_remolqueDerPatin,
            U_remolqueCuartoLadoDer,
            U_LoderaDer,
            U_remolqueLucesDerP2,
            U_luzBarcoDer,
            U_rompevientosDer,
            U_remolqueLlantaDerJumbo,
            U_remolqueDerObservaciones,
            U_licenciaUrl,
            U_tractoIzqUrl,
            U_tractoFrenteUrl,
            U_tractoDerUrl,
            U_noEcoUrl,
            U_vinUrl,
            U_remolqueCostadoTraseroIzqUrl,
            U_remolqueCostadoFrenteIzquierdoUrl,
            U_remolqueLlantaIzqEje1FotoUrl,
            U_remolqueLlantaIzqEje2FotoUrl,
            U_remolqueChasisFrontalIzqFotoUrl,
            U_remolqueChasisTraseroIzqFotoUrl,
            U_remolqueIzqDano1FotoUrl,
            U_remolqueIzqDano2FotoUrl,
            U_remolqueIzqDano3FotoUrl,
            U_remolqueIzqDano4FotoUrl,
            U_remolquePuertasFotoUrl,
            U_remolquePlacasFotoUrl,
            U_remolqueSello1FotoUrl,
            U_remolqueSello2FotoUrl,
            U_remolqueSello3FotoUrl,
            U_remolqueTraseroDano1FotoUrl,
            U_remolqueTraseroDano2FotoUrl,
            U_remolqueTraseroDano3FotoUrl,
            U_remolqueTraseroDano4FotoUrl,
            U_remolqueCostadoTraseroDerUrl,
            U_remolqueCostadoFrenteDerechoUrl,
            U_remolqueLlantaDerEje1FotoUrl,
            U_remolqueLlantaDerEje2FotoUrl,
            U_remolqueChasisFrontalDerFotoUrl,
            U_remolqueChasisTraseroDerFotoUrl,
            U_remolqueDerDano1FotoUrl,
            U_remolqueDerDano2FotoUrl,
            U_remolqueDerDano3FotoUrl,
            U_remolqueDerDano4FotoUrl,
            U_firmaOperadorUrl,
            U_firmaIntercambistaUrl,
            U_fechaFin
            );

             DxApi dxApi;

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .callTimeout(3, TimeUnit.MINUTES)
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES);

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://"+"dxxpress.net/API/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    ;

            builder.client(httpClient.build());
            Retrofit retrofit = builder.build();
            dxApi = retrofit.create(DxApi.class);

            Call<String> stringCall = dxApi.sendIntercambio(cintercambioElectronico);

            Toast.makeText(mCtx, "THE GAME", Toast.LENGTH_LONG).show();

          /*  stringCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(!response.isSuccessful()){

                        Toast.makeText(mCtx, "Error " + response.message(), Toast.LENGTH_LONG).show();
                        okSend.setImageResource(R.drawable.ic_baseline_error_24);
                        okSend.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        stringCall.cancel();

                    }else{

                        File mydir = mCtx.getDir("intercambios", Context.MODE_PRIVATE);
                        String dirPath = mydir.getPath()+"/"+Ufolio;
                        File folder = new File(dirPath);
                        if(folder.exists()) {


                            File[] allFiles = folder.listFiles(
                                    (dir, name) -> (name.endsWith(".jpg")));


                            for (File file : allFiles) {

                                RequestBody requestBody = RequestBody.create( file,MediaType.parse("multipart/form-data"));
                                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                                RequestBody carpeta = RequestBody.create( Ufolio ,MediaType.parse("text/plain"));

                                Call<String> sendImg = dxApi.imgMulipart(carpeta,fileToUpload);


                                sendImg.enqueue(new CallbackWithRetry<String>() {

                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        if(!response.isSuccessful()){

                                            Toast.makeText(mCtx, "Error " + response.message(), Toast.LENGTH_LONG).show();
                                            okSend.setImageResource(R.drawable.ic_baseline_error_24);
                                            okSend.setVisibility(View.VISIBLE);
                                            progressBar.setVisibility(View.INVISIBLE);
                                            stringCall.cancel();

                                        }else{

                                            String r = response.message();

                                            if(r.contains("OK")){
                                                file.delete();
                                            }

                                        }
                                    }


                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        super.onFailure(call,t);

                                        Toast.makeText(mCtx, "Error Subiendo Fotografia\nIntentando..."+ t.getMessage(), Toast.LENGTH_LONG).show();
                                        okSend.setImageResource(R.drawable.ic_baseline_error_24);
                                        okSend.setVisibility(View.VISIBLE);
                                        progressBar.setVisibility(View.INVISIBLE);


                                    }
                                });

                            }

                            progressBar.setVisibility(View.INVISIBLE);
                            okSend.setImageResource(R.drawable.ic_ok);
                            okSend.setVisibility(View.VISIBLE);

                        }else{

                            Toast.makeText(mCtx, "Error No se encuentra carpeta " + Ufolio, Toast.LENGTH_LONG).show();
                            okSend.setImageResource(R.drawable.ic_baseline_error_24);
                            okSend.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            stringCall.cancel();

                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    Toast.makeText(mCtx, "Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    okSend.setImageResource(R.drawable.ic_baseline_error_24);
                    okSend.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    stringCall.cancel();

                }
            });*/
        });



        return view;
    }


}
