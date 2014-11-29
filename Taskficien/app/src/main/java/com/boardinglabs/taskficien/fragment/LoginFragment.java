package com.boardinglabs.taskficien.fragment;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.boardinglabs.taskficien.R;
import com.boardinglabs.taskficien.callback.FragmentCallback;


/**
 * Created by rizkygustinaldi on 11/27/14.
 */
public class LoginFragment extends BlurredFragment{

    private EditText usernameTF;
    private EditText passwordTF;


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    public static LoginFragment newInstance(FragmentCallback vcallback) {
        LoginFragment fragment = new LoginFragment();
        fragment.callback = vcallback;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        setLayoutInflater(inflater);
        setContentView(getLayoutInflater().inflate(R.layout.activity_front_login,
                container, false));


        usernameTF = (EditText) getContentView().findViewById(R.id.front_username_edit_text);
        passwordTF = (EditText) getContentView().findViewById(R.id.front_password_edit_text);

        Button loginButton = (Button) getContentView().findViewById(R.id.lv_login_button);

        AssetManager assetManager = getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager, "fonts/Exo-Medium.otf");

        usernameTF.setTypeface(typeface);
        passwordTF.setTypeface(typeface);
        loginButton.setTypeface(typeface);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.FragmentDidAction("didSuccessLogin", null);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }



}
