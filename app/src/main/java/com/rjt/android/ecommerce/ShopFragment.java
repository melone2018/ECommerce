package com.rjt.android.ecommerce;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rjt.R;

import java.util.ArrayList;
import java.util.Map;

public class ShopFragment extends Fragment {
    private ViewPager mViewPager;
    private View mView;
    private LinearLayout sliderDotspanel;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CustomRecyclerViewAdapter mAdapter;
    private int mDotscount;
    private ImageView[] dots;
    private ArrayList<String> mImageViews = new ArrayList<>();
    private ArrayList<String> mImageIds = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.default_home, container, false);
        //mRecyclerView =  mView.findViewById(R.id.recyclerView);

        SharedPreferences pb = getActivity().getSharedPreferences("ecommerce", 0);
        Map<String, ?> allEntries = pb.getAll();
        for(Map.Entry<String, ?> entry : allEntries.entrySet()){
            mImageViews.add(entry.getValue().toString());
            mImageIds.add(entry.getKey());
        }
        mRecyclerView = mView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mLayoutManager = new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);
        //mRecyclerView.setLayoutManager(mLayoutManager);
//        Log.d("IMG", mImageViews.size()+"");
        mAdapter = new CustomRecyclerViewAdapter(getActivity(), mImageViews);
        mRecyclerView.setAdapter(mAdapter);

//        mViewPager = mView.findViewById(R.id.viewPager);
//        sliderDotspanel = mView.findViewById(R.id.SliderDots);
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(), mImageViews);
//        mViewPager.setAdapter(viewPagerAdapter);
//        mDotscount = viewPagerAdapter.getCount();
//        dots = new ImageView[mDotscount];
        //initializeView();
        // mRecyclerView.addView(mViewPager);
        return mView;
    }

//    public void initializeView()
//    {
//        for (int i = 0; i < mDotscount; i++) {
//            dots[i] = new ImageView(getActivity());
//            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//            params.setMargins(10, 0, 10, 0);
//
//            sliderDotspanel.addView(dots[i], params);
//        }
//        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                for(int i = 0; i< mDotscount; i++){
//                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));
//                }
//
//                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
}
