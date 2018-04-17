package com.rjt.android.ecommerce;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rjt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    public ArrayList<Product> getProducts() {
        return mProducts;
    }

    private ArrayList<Product> mProducts;
    private ProductClickListener listener;
    public ProductListAdapter(Context context, ArrayList<Product> products){
        this.mContext = context;
        this.mProducts = products;
    }
    public void setProductListener(ProductClickListener listener) {
        this.listener = listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.product_item_layout, parent, false);
        return new MyViewHolder(mView);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageView imgView = ((MyViewHolder)holder).productIv;
        TextView namePriceTv = ((MyViewHolder)holder).namePriceTv;
        Picasso.with(mContext)
                .load(this.mProducts.get(position).getImageUrl())
                .fit()
                .centerCrop()
                .into(imgView);
        Product p = this.mProducts.get(position);
        namePriceTv.setText(p.getProductName() + ":  " + p.getPrice() + "$");
        ((MyViewHolder)holder).itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productIv;
        private TextView namePriceTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.productIv = itemView.findViewById(R.id.ImageViewProduct);
            this.namePriceTv = itemView.findViewById(R.id.TextViewNamePrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onProductClicked(v, getLayoutPosition());
        }
    }

}
