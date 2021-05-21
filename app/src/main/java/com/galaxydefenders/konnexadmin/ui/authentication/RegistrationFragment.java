package com.galaxydefenders.konnexadmin.ui.authentication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.galaxydefenders.konnexadmin.R;
import com.galaxydefenders.konnexadmin.models.UserModel;
import com.galaxydefenders.konnexadmin.network.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFragment extends Fragment {

    TextInputEditText et_email;
    TextInputEditText et_password;
    TextInputEditText et_full_name;
    Button btn_signup;
    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_signup, container, false);


        et_email = v.findViewById(R.id.reg_email);
        et_password = v.findViewById(R.id.reg_pass);
        et_full_name = v.findViewById(R.id.reg_name);

        btn_signup = v.findViewById(R.id.btn_reg);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regUser(et_email.getText().toString(), et_password.getText().toString(), et_full_name.getText().toString());
            }
        });

        return v;
    }

    private void regUser(String email, String password, String fullname){

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
                        Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("DASHBOARD", "onResponse: " + response.errorBody() +"   " + response.code() +"   " +response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });

    }

}