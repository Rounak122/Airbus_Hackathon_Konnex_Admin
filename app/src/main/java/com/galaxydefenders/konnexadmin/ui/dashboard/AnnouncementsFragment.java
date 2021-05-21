package com.galaxydefenders.konnexadmin.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.galaxydefenders.konnexadmin.R;
import com.galaxydefenders.konnexadmin.network.RetrofitClient;
import com.galaxydefenders.konnexadmin.utils.PrefHandler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementsFragment extends Fragment {

    Button btn_submit;
    boolean isLoggedIn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_annoucements, container, false);

        final NavController navController = NavHostFragment.findNavController(this);
//        navController.navigate(R.id.action_navigation_announcements_to_loginFragment);

       isLoggedIn= PrefHandler.INSTANCE.getLogin(getContext());
       if(!isLoggedIn){
           navController.navigate(R.id.action_navigation_announcements_to_loginActivity2);
       }
        btn_submit = v.findViewById(R.id.bt_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText et_msg = v.findViewById(R.id.et_announcement);
                String response = et_msg.getText().toString();
                et_msg.setText("");


                if (response.equals("")){
                    Toast.makeText(getContext(), "Please enter your response", Toast.LENGTH_SHORT).show();
                }else {
                    postAnnouncement(response);
                }
            }
        });



        return v;
    }


    private void postAnnouncement(String response){

        String jsonString = "{\"announce\":\"" + response + "\"}";
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonString);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .postAnnouncements(requestBody);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Announcement Made", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("DASHBOARD", "onResponse: " + response.errorBody() +"   " + response.code() +"   " +response.message());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Announcement Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }




}