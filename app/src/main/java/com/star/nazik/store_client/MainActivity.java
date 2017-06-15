package com.star.nazik.store_client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.star.nazik.store_client.Entity.LoginResponse;
import com.star.nazik.store_client.Entity.TestModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(App.APP_PREFERENCES, Context.BIND_ABOVE_CLIENT);


        EditText etPhone = (EditText)findViewById(R.id.txtPhone);
        EditText etPassword = (EditText)findViewById(R.id.txtPassword);

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                clearResponseMessage();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                clearResponseMessage();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void clearResponseMessage(){
        TextView respMsg = (TextView)findViewById(R.id.responseText);
        respMsg.setText("");
    }

    public void getTokenData(View view){
        String token = mSettings.getString(App.APP_PREFERENCES_TOKEN, "DEFAULT");
        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
    }

    public  void login(View view){
        String phone = ((EditText)findViewById(R.id.txtPhone)).getText().toString();
        String password = ((EditText)findViewById(R.id.txtPassword)).getText().toString();

        App.getApi().loginUser(phone, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                TextView tv = (TextView)findViewById(R.id.responseText);
                String text = "";
                if(response.isSuccessful()){
                    LoginResponse lp = response.body();

                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString(App.APP_PREFERENCES_TOKEN, lp.getToken());
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);

                    return;

                } else {
                    JSONObject jObjError = null;
                    try {
                        jObjError = new JSONObject(response.errorBody().string());
                        text = "Помилка!\n" + jObjError.getString("error");
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                tv.setText(text);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
