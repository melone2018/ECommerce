package com.rjt.android.ecommerce;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import java.util.Iterator;
import java.util.Map;


public class DefaultHomeFragment extends Fragment implements MenuActivity.onReceiveUserInfoListener {
    private ViewPager mViewPager;
    private View mView;
    private LinearLayout sliderDotspanel;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CustomRecyclerViewAdapter mAdapter;
    private int mDotscount;
    private ImageView[] dots;
    private String apiKey;
    private String userId;
    private String mobile;
    private SharedPreferences pb;
    private RequestQueue mRequestQueue;
    private String password;
    private ArrayList<String> mImageViews = new ArrayList<>();
    private ArrayList<String> mImageIds = new ArrayList<>();
//    private ArrayList<String> subImageViews;
//    private ArrayList<String> subImageIds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.default_home, container, false);
        //mRecyclerView =  mView.findViewById(R.id.recyclerView);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        final ArrayList<String> subImageViews = new ArrayList<>();
        final ArrayList<String> subImageIds = new ArrayList<>();

        pb = getActivity().getSharedPreferences("ecommerce", 0);
        Map<String, ?> allEntries = pb.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().substring(0, 3).equals("cid")) {
                mImageViews.add(entry.getValue().toString());
                mImageIds.add(entry.getKey().substring(3, 6));
            }
        }
        mRecyclerView = mView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new CustomRecyclerViewAdapter(getActivity(), mImageViews);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setListener(new CategoryClickListener() {
            @Override
            public void onCategoryClick(View v, int position) {
                Log.d("Position", (position - 1) + "");
                Toast.makeText(getActivity(), mImageViews.get(position - 1), Toast.LENGTH_SHORT).show();
                String categoryId = mImageIds.get(position - 1);
                ArrayList<String> subImageViews = new ArrayList<>();
                ArrayList<String> subImageIds = new ArrayList<>();
                requestCategory(categoryId, subImageIds, subImageViews);
            }
        });

        return mView;
    }

    @Override
    public void onUserInfoReceived(String mobile, String password, String apiKey, String userId) {
        this.mobile = mobile;
        this.userId = userId;
        this.apiKey = apiKey;
        this.password = password;
    }

    public void requestCategory(String categoryId, final ArrayList<String> subImageIds, final ArrayList<String> subImageViews) {
        String sub_cateory_url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id="
                + categoryId + "&api_key=" + apiKey + "&user_id=" + userId;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                sub_cateory_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("subcategory");
                            ArrayList<String> images = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String scid = jsonObject.getString("scid");
                                String cname = jsonObject.getString("scname");
                                String sdiscription = jsonObject.getString("scdiscription");
                                String cimage = jsonObject.getString("scimageurl");

                                pb.edit().putString("scid" + scid, cimage).apply();
                                subImageIds.add(scid);
                                subImageViews.add(cimage);
                            }
                            mAdapter.setImageData(subImageViews);
                            mAdapter.notifyDataSetChanged();
                            Log.d("SUBImageIds", subImageIds.size() + "");
                        } catch (JSONException e) {
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
}
