package com.rjt.android.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rjt.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class CategoryFragment extends Fragment {
    private View mView;
    private LinearLayout sliderDotspanel;
    private RecyclerView mRecyclerView;
    private CustomRecyclerViewAdapter mAdapter;
    private ImageView[] dots;
    private String apiKey;
    private String userId;
    private String mobile;
    private Boolean isCategory;
    private SharedPreferences pb;
    private RequestQueue mRequestQueue;
    private String password;
    private PassProductInfoListener listener;
    private ArrayList<Product> mProducts;
    private ArrayList<String> mImageViews = new ArrayList<>();
    private ArrayList<String> mImageIds = new ArrayList<>();
    private ArrayList<String> mSubCategoryIds = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.default_home, container, false);
        Bundle b = getArguments();
        Log.d("USERID", b.getString("USERID"));
        Log.d("APIKEY", b.getString("APIKEY"));
        Log.d("CID", b.getString("CID"));
        final String userId = b.getString("USERID");
        final String apiKey = b.getString("APIKEY");
        final String cid = b.getString("CID");
        this.mImageViews = b.getStringArrayList("IMAGES");
        this.mSubCategoryIds = b.getStringArrayList("SCIDS");
        Log.d("IMAGEVIEWSIZE", this.mImageViews.size()+"");
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRecyclerView = mView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new CustomRecyclerViewAdapter(getActivity(), this.mImageViews);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setListener(new CategoryClickListener() {
            @Override
            public void onCategoryClick(View v, int position) {
                requestCategory(userId, apiKey, cid, mSubCategoryIds.get(position-1));
            }
        });

        return mView;
    }

    public void requestCategory(String userId, String apiKey, String categoryId, String subCategoryId) {
     //   isCategory = !isCategory;
        String sub_cateory_url = "http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=" + categoryId
                + "&scid=" + subCategoryId + "&api_key=" + apiKey + "&user_id=" + userId;
        Log.d("Sub_Category_Url", sub_cateory_url);
        final ArrayList<Product> productInfo = new ArrayList<>();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                sub_cateory_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("products");
                            Log.d("JSONARRAY", jsonArray.toString());
                            ArrayList<String> itemId = new ArrayList<>();
                            ArrayList<String> itemNames = new ArrayList<>();
                            ArrayList<String> itemQuantity = new ArrayList<>();
                            ArrayList<String> itemPrices = new ArrayList<>();
                            ArrayList<String> productImages = new ArrayList<>();
                            ArrayList<String> productDes = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                itemId.add(jsonObject.getString("id"));
                                itemNames.add(jsonObject.getString("pname"));
                                itemQuantity.add(jsonObject.getString("quantity"));
                                itemPrices.add(jsonObject.getString("prize"));
                                productDes.add(jsonObject.getString("discription"));
                                productImages.add(jsonObject.getString("image"));
                            }
                            Log.d("ImageInfo", productImages.toString());
                            ProductListFragment plFragment = new ProductListFragment();
                            Bundle b = new Bundle();
                            b.putStringArrayList("ItemName", itemNames);
                            b.putStringArrayList("ItemId", itemId);
                            b.putStringArrayList("ItemPrices",itemPrices);
                            b.putStringArrayList("ItemQuantity",itemQuantity);
                            b.putStringArrayList("ItemImage", productImages);
                            b.putStringArrayList("ItemDes", productDes);
                            plFragment.setArguments(b);
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragmentContainer, plFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } catch (JSONException e) {
                            Log.d("ERROR!!!!", e.getMessage());
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP909", "Failed ");
            }
        });
        mRequestQueue.add(jsonObjReq);
    }

    public void setProducts(ArrayList<Product> products){
        this.mProducts = products;
    }

    public void setImages(ArrayList<String> imags){
        Log.d("NUMIMAGE", imags.size()+"");
        this.mImageViews = imags;
    }
}
