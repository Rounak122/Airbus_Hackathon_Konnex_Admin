package com.galaxydefenders.konnexadmin.ui.authentication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxydefenders.konnexadmin.R;
import com.galaxydefenders.konnexadmin.models.UserModel;
import com.galaxydefenders.konnexadmin.network.RetrofitClient;
import com.galaxydefenders.konnexadmin.utils.PrefHandler;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView tv_welcomeback;
    TextView tv_signinText;
    TextView tv_forgotPassword;
    TextView tv_newUser;
    TextView tv_btn_signup;
    TextView tv_name;
    TextInputEditText et_fullname;
    TextInputEditText et_email;
    TextInputEditText et_password;
    Button btn_login;

    boolean isLogin = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        tv_welcomeback = findViewById(R.id.tv_Welcome_back);
        tv_forgotPassword = findViewById(R.id.forgot_password);
        tv_newUser = findViewById(R.id.tv_newUser);
        tv_signinText = findViewById(R.id.tv_toHide);
        tv_btn_signup = findViewById(R.id.sign_up);
        btn_login = findViewById(R.id.btn_LR);
        tv_name = findViewById(R.id.tv_name);
        et_fullname = findViewById(R.id.et_fullName);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.password);


        tv_name.setVisibility(View.INVISIBLE);
        et_fullname.setVisibility(View.INVISIBLE);


        tv_btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin){
                    convertToReg();
                    isLogin=false;
                }else{
                    convertToLogin();
                    isLogin=true;
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin){
                    loginUser();
                }else{
                    regUser();
                }
            }
        });


    }

    private void convertToReg(){

        tv_welcomeback.setText("Register");
        tv_forgotPassword.setVisibility(View.INVISIBLE);
        tv_newUser.setText("Already Registerd? ");
        tv_btn_signup.setText("Login");
        tv_signinText.setVisibility(View.INVISIBLE);
        btn_login.setText("REGISTER");
        tv_name.setVisibility(View.VISIBLE);
        et_fullname.setVisibility(View.VISIBLE);

    }

    private void convertToLogin(){

        tv_welcomeback.setText("Welcome Back,");
        tv_forgotPassword.setVisibility(View.VISIBLE);
        tv_newUser.setText("New User? ");
        tv_btn_signup.setText("Sign Up");
        tv_signinText.setVisibility(View.VISIBLE);
        btn_login.setText("LOGIN");
        tv_name.setVisibility(View.INVISIBLE);
        et_fullname.setVisibility(View.INVISIBLE);

    }

    private void regUser(){

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        UserModel userModel = new UserModel(email,password);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .postRegister(userModel);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                        PrefHandler.INSTANCE.saveLogin(true,getApplicationContext());
                        finish();
                    }else{
                        Log.i("DASHBOARD", "onResponse: " + response.errorBody() +"   " + response.code() +"   " +response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void loginUser(){

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        UserModel userModel = new UserModel(email,password);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .postLogin(userModel);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {

                        String res = response.body().string();
                        JSONObject responseJSON = new JSONObject(res);
                        boolean error = responseJSON.getBoolean("status");
                        if (error) {
                            //good
                            PrefHandler.INSTANCE.saveLogin(true,getApplicationContext());
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Log.i("DASHBOARD", "onResponse: " + response.errorBody() +"   " + response.code() +"   " +response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });

    }


}