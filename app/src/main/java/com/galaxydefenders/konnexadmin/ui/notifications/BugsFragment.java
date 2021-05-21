package com.galaxydefenders.konnexadmin.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.galaxydefenders.konnexadmin.R;
import com.galaxydefenders.konnexadmin.network.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BugsFragment extends Fragment {


    ListView bugs_listview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bugs, container, false);

        bugs_listview=v.findViewById(R.id.list_bugs);


        fetchBugs();

        return v;
    }


    private void fetchBugs(){

        ArrayList<String> bugs = new ArrayList<>();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .getBug();


        call.enqueue(new Callback<ResponseBody>() {
            @Override

            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if (response.isSuccessful()) {

                        Log.i("ANNOUNCE", "onResponse: SUCCESS");

                        JSONArray jsonarray = new JSONArray(response.body().string());
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            String a = jsonobject.getString("bugs");
                            bugs.add(a);

                            Log.i("ANNOUNCE", "onResponse: IN");
                        }

                        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, bugs);

                        bugs_listview.setAdapter(adapter);


                    }
                } catch (IOException | JSONException e) {

                    Log.i("ANNOUNCE", "onResponse: FAIL");

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Cannot load announcements", Toast.LENGTH_SHORT).show();
            }
        });

    }


}