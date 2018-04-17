package com.rjt.android.ecommerce;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rjt.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;

public class ProductFragment extends Fragment {
    public void setDetailTv(String detailTv) {
        Log.d("TVDetail", detailTv);
        this.detailTv.setText(detailTv);
    }

    public void setDetailIv(String detailUrl) {
        Log.d("IMAGEPICASSO", detailUrl);
        Picasso.with(getActivity())
                .load(detailUrl)
                .fit()
                .into(detailIv);
    }

    public void setpNameTv(String pNameTv) {
        this.pNameTv.setText("Product: " + pNameTv);
    }

    public void setPriceTv(String priceTv) {
        this.priceTv.setText("Price: " + priceTv);
    }

    public void setStockTv(String stockTv) {
        this.stockTv.setText("Quantitiy: " + stockTv);
    }

    private TextView detailTv;
    private ImageView detailIv;
    private TextView pNameTv;
    private TextView priceTv;
    private TextView stockTv;
    private Button buyBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View mView = (View)inflater.inflate(R.layout.fragment_product, container, false);
        Bundle b = getArguments();
        detailTv = mView.findViewById(R.id.TextViewDetail);
        detailIv = mView.findViewById(R.id.ImageViewDetail);
        Log.d("IMAGEDetail", (detailIv==null)+"");
        pNameTv = mView.findViewById(R.id.TextViewPName);
        priceTv = mView.findViewById(R.id.TextViewPrice);
        stockTv = mView.findViewById(R.id.TextViewStock);
        buyBtn = mView.findViewById(R.id.ButtonBuy);
        this.setDetailIv(b.getString("URL"));
        this.setDetailTv(b.getString("DES"));
        this.setStockTv(b.getString("STO"));
        this.setPriceTv(b.getString("PRI"));
        this.setpNameTv(b.getString("NAM"));
        return mView;
    }



}
