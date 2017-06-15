package com.star.nazik.store_client;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 2017-01 on 15.06.2017.
 */

public class App{
    private static StoreAPI storeAPI;
    private static Retrofit retrofit;
    public static final String APP_PREFERENCES = "savedToken";
    public static final String APP_PREFERENCES_TOKEN = "token";

    public static StoreAPI getApi() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dev-atick.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storeAPI = retrofit.create(StoreAPI.class);

        return storeAPI;
    }
}
