package com.rjt.android.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rjt.R;

public class ProductActivity extends AppCompatActivity {
    private ProductListFragment mProductListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mProductListFragment = new ProductListFragment();

    }
}
