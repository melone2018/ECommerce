package com.rjt.android.ecommerce;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rjt.R;

import java.util.ArrayList;

public class ProductListFragment extends Fragment {
    private RecyclerView productListRv;
    private View mView;
    private ProductListAdapter pLAdapter;
    public ProductListFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Enter", "Ca");
        mView = (View)inflater.inflate(R.layout.fragment_product_list, container, false);
        productListRv = mView.findViewById(R.id.RecyclerViewProductList);
        Log.d("ImageSizdddd", "Nothing");
        Bundle b = getArguments();
        ArrayList<String> names = b.getStringArrayList("ItemName");
        ArrayList<String> ids = b.getStringArrayList("ItemId");
        ArrayList<String> prices = b.getStringArrayList("ItemPrices");
        ArrayList<String> quantity = b.getStringArrayList("ItemQuantity");
        ArrayList<String> imgs = b.getStringArrayList("ItemImage");
        ArrayList<String> descriptions = b.getStringArrayList("ItemDes");
        Log.d("ImageSize9999", imgs.size()+"");
        ArrayList<Product> products = new ArrayList<>();
        for(int i = 0; i < names.size(); i++){
            products.add(new Product(ids.get(i), names.get(i), quantity.get(i), prices.get(i), imgs.get(i), descriptions.get(i)));
        }

        productListRv.setHasFixedSize(true);
        productListRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        pLAdapter = new ProductListAdapter(getActivity(), products);
        productListRv.setAdapter(pLAdapter);

        pLAdapter.setProductListener(new ProductClickListener() {
            @Override
            public void onProductClicked(View v, int position) {
                Log.d("ListenProduct", position+"");
                Product p = pLAdapter.getProducts().get(position);
                String description = p.getDescription();
                if(description==null){
                    p.setDescription("No Description Available for this product");
                }
                String imageSource = p.getImageUrl();
                String productName = p.getProductName();
                Toast.makeText(getActivity(), productName, Toast.LENGTH_SHORT).show();
                String price = p.getPrice();
                String stock = p.getQuantity();

                ProductFragment pfragment = new ProductFragment();
                Bundle b = new Bundle();
                Log.d("DESSS", description);
                b.putString("DES", description);
                b.putString("URL", imageSource);
                b.putString("NAM", productName);
                b.putString("PRI", price);
                b.putString("STO", stock);
                pfragment.setArguments(b);
                FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, pfragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return mView;
    }
}
