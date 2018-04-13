package com.rjt.android.ecommerce;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rjt.R;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private TextView mFirstName;
    private TextView mLastName;
    private TextView mMobile;
    private TextView mAddress;
    private TextView mEmail;
    private Button mEdit;
    private View mView;
    private onProfileFragmentChosenListener profileListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public void setOnPrfofileListener(onProfileFragmentChosenListener onProfileClickListener){
        this.profileListener = onProfileClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  (View)inflater.inflate(R.layout.fragment_profile, container, false);
        mFirstName = mView.findViewById(R.id.TextViewFName);
        mLastName = mView.findViewById(R.id.TextViewLName);
        mMobile = mView.findViewById(R.id.TextViewPhone);
        mAddress = mView.findViewById(R.id.TextViewAddr);
        mEmail = mView.findViewById(R.id.TextViewEm);
        mEdit = mView.findViewById(R.id.ButtonEditFile);
//        Toast.makeText((MenuActivity)getActivity().getApplicationContext(), "Profile Fragment Created", Toast.LENGTH_SHORT).show();

        return mView;
    }

    public void setProfie(String lname, String mobile){
        mLastName.setText(lname);
        mMobile.setText(mobile);
    }

}
