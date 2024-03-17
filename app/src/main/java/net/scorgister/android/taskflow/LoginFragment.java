package net.scorgister.android.taskflow;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment {


    @NonNull
    public static LoginFragment newInstance(String email) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("email", email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView tv = (TextView) view.findViewById(R.id.register_action);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.log_frag, new RegisterFragment()).commit();
            }
        });

        Button loginBtn = view.findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailText =  view.findViewById(R.id.email);
                EditText passwordText =  view.findViewById(R.id.password);

                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                if(email.equals("") || password.equals("")) {
                    return;
                }

                Connector connector = new Connector(email, password);
                connector.connect(() -> {
                    LoginActivity ac = (LoginActivity) getActivity();
                    ac.startHome(v);
                });

            }
        });

        return view;
    }
}