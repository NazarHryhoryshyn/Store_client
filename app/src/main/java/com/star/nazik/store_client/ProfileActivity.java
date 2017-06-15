package com.star.nazik.store_client;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.star.nazik.store_client.Entity.TestModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mSettings = getSharedPreferences(App.APP_PREFERENCES, Context.BIND_ABOVE_CLIENT);
    }

    public void getTokenData(View view){
        String token = mSettings.getString(App.APP_PREFERENCES_TOKEN, "DEFAULT");
        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
    }

    public void getTestData(View view){
        App.getApi().getData().enqueue(new Callback<TestModel>() {
            @Override
            public void onResponse(Call<TestModel> call, Response<TestModel> response) {
                if(response.body() != null){
                    String text = "Прийнятий обєкт:\n";
                    TestModel tm = response.body();
                    text += "login: " + tm.getLogin() + "\n";
                    text += "message: " + tm.getMassage() + "\n";
                    text += "myInt: " + tm.getMyInt() + "\n";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Сервер нічого не відіслав!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<TestModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
