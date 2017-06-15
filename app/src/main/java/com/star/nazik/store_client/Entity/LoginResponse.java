package com.star.nazik.store_client.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 2017-01 on 15.06.2017.
 */

public class LoginResponse {
    @SerializedName("token")
    @Expose
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
