package com.dx.dxintercambio;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DxApi {

   @POST("login")
    Call<List<CUsuario>> createPost(@Body Post post);

   @POST("linea")
    Call<List<CLinea>> getLinea(@Body Post post);

   @POST("operador")
    Call<List<COperador>> getOperador(@Body Post post);

    @POST("flota")
    Call<List<CFlota>> getFlota(@Body Post post);

    @POST("unidad")
    Call<List<CUnidad>> getUnidad(@Body Post2 post2);

    @POST("remolques")
    Call<List<CRemolque>> getRemolque(@Body Post3 post3);

    @POST("IntercambioRemolque")
    Call<List<CEnvio>> getEnvio(@Body Post4 post4);

   @POST("IntercambioRemolque2")
   Call<List<CEnvio>> getEnvio2(@Body Post6 post6);

    @POST("img")
    Call<String> getImg(@Body Post5 post5);

    @POST("imgTest")
    Call<String> getImgTest(@Body Post00 post00);

    @POST("img2")
    Call<String> getImg2(@Body Post7 post7);

    @POST("llanta")
    Call<List<CLlanta>> getLlanta(@Body Post post);

    @POST("IntercambioRemolque3")
    Call<List<CEnvio>> getTerminado(@Body Post8 post8);

    @POST("logApp")
    Call<List<CEnvio>> getLogApp(@Body PostLogApp postLogApp);

    @POST("transportista")
    Call<List<CFlota>> getTranspo(@Body Post post);

    @POST("usuarioRel")
    Call<List<CUsuarioRel>> getUsuarioRel(@Body Post Post);

 @POST("IntercambioOffline")
 Call<List<CEnvio3>> sendIntercambio(@Body CintercambioElectronicoSend cintercambioElectronicoSend);

 @Multipart
    @POST("ImageTest/UploadFile")
    Call<String> imgMulipart(@Part("carpeta") RequestBody carpeta,
                             @Part MultipartBody.Part image );

}
