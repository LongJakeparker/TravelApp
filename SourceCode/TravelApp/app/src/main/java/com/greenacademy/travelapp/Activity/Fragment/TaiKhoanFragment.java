package com.greenacademy.travelapp.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.LoginActivity.LoginActivity;
import com.greenacademy.travelapp.R;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by DAVIDSON on 2/26/2017.
 */

public class TaiKhoanFragment extends Fragment {
    ProfilePictureView profileUser;
    ImageView imgAvatar;
    TextView txtEmail, txtName;
    FrameLayout frameLogout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taikhoan, container, false);
        profileUser = (ProfilePictureView) view.findViewById(R.id.profileUser);
        imgAvatar = (ImageView) view.findViewById(R.id.imageViewAvatarUser);
        txtEmail = (TextView) view.findViewById(R.id.textViewEmailUser);
        txtName = (TextView) view.findViewById(R.id.textViewNameUser);
        frameLogout = (FrameLayout) view.findViewById(R.id.frameLogout);

        switch (LoginActivity.saveData.startLayTypeLogin()){
            case Constant.TYPE_LOGIN_NORMAL:
                break;
            case Constant.TYPE_LOGIN_FACEBOOK:
                profileUser.setProfileId(LoginActivity.saveData.startLayId());
                break;
            case Constant.TYPE_LOGIN_GOOGLE:
                Picasso.with(getContext()).load(LoginActivity.saveData.startLayLinkPhoto()).into(imgAvatar);
                break;
        }

        txtEmail.setText(LoginActivity.saveData.startLayEmail());
        txtName.setText(LoginActivity.saveData.startLayHoTen());

        frameLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (LoginActivity.saveData.startLayTypeLogin()){
                    case Constant.TYPE_LOGIN_NORMAL:
                        break;
                    case Constant.TYPE_LOGIN_FACEBOOK:
                        LoginManager.getInstance().logOut();
                        break;
                    case Constant.TYPE_LOGIN_GOOGLE:
                        break;
                }

                LoginActivity.saveData.clearThongTin();
                toiManHinhLogin();
            }
        });

        return view;
    }

    private void toiManHinhLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
