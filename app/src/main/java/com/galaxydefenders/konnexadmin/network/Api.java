package com.galaxydefenders.konnexadmin.network;

import com.galaxydefenders.konnexadmin.models.UserModel;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

        //Feedback
        @GET("feedback")
        Call<ResponseBody> getFeedback();

        //Bug
        @GET("bugs")
        Call<ResponseBody> getBug();

        //Announcements
        @POST("announcement")
        Call<ResponseBody> postAnnouncements(@Body RequestBody requestBody);

        //Login
        @POST("login")
        Call<ResponseBody> postLogin(@Body UserModel userModel);

        //Reg
        @POST("register")
        Call<ResponseBody> postRegister(@Body UserModel userModel);

}
