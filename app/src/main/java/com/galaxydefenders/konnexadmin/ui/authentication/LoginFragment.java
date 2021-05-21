package com.galaxydefenders.konnexadmin.ui.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.galaxydefenders.konnexadmin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    TextInputEditText et_email;
    TextInputEditText et_password;
    TextInputEditText et_full_name;
    Button btn_signup;

    BottomNavigationView navView;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

//        ((MainActivity) requireActivity()).hideNav();

//        et_email = v.findViewById(R.id.)

//         navView = requireActivity().findViewById(R.id.nav_view);
//         navView.setVisibility(View.GONE);
//
//        btn_signup = v.findViewById(R.id.btn_reg);
//
//        btn_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                String username =
//
////                regUser();
//            }
//        });

        return v;

    }
}