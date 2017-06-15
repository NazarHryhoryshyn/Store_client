package com.star.nazik.store_client.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 2017-01 on 15.06.2017.
 */
public class TestModel {

    @SerializedName("login")
    @Expose
    private Boolean login;

    @SerializedName("massage")
    @Expose
    private String massage;

    @SerializedName("myInt")
    @Expose
    private Integer myInt;

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Integer getMyInt() {
        return myInt;
    }

    public void setMyInt(Integer myInt) {
        this.myInt = myInt;
    }
}
