package com.star.nazik.store_client;

import com.star.nazik.store_client.Entity.LoginResponse;
import com.star.nazik.store_client.Entity.TestModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 2017-01 on 15.06.2017.
 */

public interface StoreAPI {
    @GET("/api/test")
    Call<TestModel> getData();

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> loginUser(@Field("phone") String phone, @Field("password") String password);
}
