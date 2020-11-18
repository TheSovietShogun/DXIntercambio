package com.dx.dxintercambio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

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
   Call<List<CEnvio2>> getEnvio2(@Body Post6 post6);

    @POST("img")
    Call<String> getImg(@Body Post5 post5);

    @POST("img2")
    Call<String> getImg2(@Body Post7 post7);

    @POST("llanta")
    Call<List<CLlanta>> getLlanta(@Body Post post);

    @POST("llanta")
    Call<List<CEnvio>> getTerminado(@Body Post post);

}
