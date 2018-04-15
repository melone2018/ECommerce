package com.rjt.android.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView
        .Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 2;
    private View mHeaderView;
    private Context context;
    private int mDotscount;
    private ImageView[] dots;
    private ArrayList<String> mImageViews = new ArrayList<>();
    private LinearLayout sliderDotspanel;
    private ArrayList<String> imageData;
    private AdapterView.OnItemClickListener onItemClickListener;
    private AdapterView.OnItemLongClickListener onItemLongClickListener;


    public CustomRecyclerViewAdapter(Context context, ArrayList<String> imageData) {
        this.imageData = imageData;
        this.context = context;
        this.mDotscount = imageData.size();
        this.dots = new ImageView[this.mDotscount];

    }

    public void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    private void onItemHolderClick(HeaderViewHolder itemHolder) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    private void onItemHolderLongClick(HeaderViewHolder itemHolder) {
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View layout = LayoutInflater.from(context).inflate(R.layout.my_view_pager, parent, false);
            return new HeaderViewHolder(layout);
        }
        View layout = LayoutInflater.from(context).inflate(R.layout.item_normal, parent, false);

        return new NormalViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderViewHolder) {
                sliderDotspanel = ((HeaderViewHolder) holder).headerVp.findViewById(R.id.SliderDots);
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context, imageData);
                ((HeaderViewHolder) holder).headerVp.setAdapter(viewPagerAdapter);
//                mDotscount = viewPagerAdapter.getCount();
//                dots = new ImageView[mDotscount];
//                ((HeaderViewHolder) holder).dotLl.addView(new ImageView(context));
//                initializeView(((HeaderViewHolder) holder).headerVp, ((HeaderViewHolder) holder).dotLl);
                //mRecyclerView.addView(mViewPager);
            } else if (holder instanceof NormalViewHolder) {
                //((NormalViewHolder) holder).itemView.(position + "");
                ImageView imgView = ((NormalViewHolder)holder).imgView;
                Picasso.with(context)
                        .load(this.imageData.get(position-1))
                        .fit()
                        .centerCrop()
                        .into(imgView);
            }

    }

    public void initializeView(ViewPager headerVp, LinearLayout dotContainer) {
//        for (int i = 0; i < mDotscount; i++) {
//            dots[i] = new ImageView(context);
//            dots[i].setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.nonactive_dot));
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//            params.setMargins(10, 0, 10, 0);
//
//            sliderDotspanel.addView(dots[i], params);
//        }
        ImageView dots = new ImageView(context);
        dots.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nonactive_dot));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(10, 0, 10, 0);
        dotContainer.addView(dots, params);
        //        dots[0].setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.active_dot));

        headerVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                for(int i = 0; i< mDotscount; i++){
//                    dots[i].setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.nonactive_dot));
//                }
//                dots[position].setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mHeaderView==null)
            return imageData.size();
        else
            return imageData.size() + 1;
        //return 10;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ViewPager headerVp;
        private LinearLayout dotLl;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerVp = itemView.findViewById(R.id.viewPager);
            dotLl = itemView.findViewById(R.id.SliderDots);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemHolderClick(this);
        }

        @Override
        public boolean onLongClick(View v) {
            onItemHolderLongClick(this);
            return true;
        }
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        //        private ImageView categoryIv;
        private ImageView imgView;

        public NormalViewHolder(View itemView) {
            super(itemView);
//            categoryIv = itemView.findViewById(R.id.ivItemGridImage);
            imgView = itemView.findViewById(R.id.imageView);

        }
    }
}
