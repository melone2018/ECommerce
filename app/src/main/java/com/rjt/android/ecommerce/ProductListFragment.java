package com.rjt.android.ecommerce;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjt.R;

public class ProductListFragment extends Fragment {
    private RecyclerView productListRv;
    private View mView;
    public ProductListFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = (View)inflater.inflate(R.layout.fragment_product_list, container, false);
        productListRv = mView.findViewById(R.id.RecyclerViewProductList);
      //  productListRv.setAdapter(new ProductListAdapter(this, ));
        return mView;
    }
}
