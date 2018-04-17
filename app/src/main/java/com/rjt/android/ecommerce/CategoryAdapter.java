package com.rjt.android.ecommerce;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rjt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView
        .Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 2;
    private Context context;
    private CategoryClickListener listener;
    private ArrayList<String> imageData;
    private AdapterView.OnItemClickListener onItemClickListener;
    private AdapterView.OnItemLongClickListener onItemLongClickListener;

    public void setImageData(ArrayList<String> imageData) {
        this.imageData = imageData;
    }
    public void setListener(CategoryClickListener listener) {
        this.listener = listener;
    }
    public CategoryAdapter(Context context, ArrayList<String> imageData) {
        this.imageData = imageData;
        this.context = context;
    }

    private void onItemHolderClick(CategoryAdapter.HeaderViewHolder itemHolder) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    private void onItemHolderLongClick(CategoryAdapter.HeaderViewHolder itemHolder) {
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View layout = LayoutInflater.from(context).inflate(R.layout.my_view_pager, parent, false);
            return new CategoryAdapter.HeaderViewHolder(layout);
        }
        View layout = LayoutInflater.from(context).inflate(R.layout.item_normal, parent, false);
        return new CategoryAdapter.NormalViewHolder(layout);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) lp;
            slp.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CategoryAdapter.HeaderViewHolder) {
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context, imageData);
            ((CategoryAdapter.HeaderViewHolder) holder).headerVp.setAdapter(viewPagerAdapter);
        } else if (holder instanceof CategoryAdapter.NormalViewHolder) {
            ImageView imgView = ((CategoryAdapter.NormalViewHolder) holder).imgView;
            Picasso.with(context)
                    .load(this.imageData.get(position - 1))
                    .fit()
                    .centerCrop()
                    .into(imgView);
            ((CategoryAdapter.NormalViewHolder) holder).itemView.setTag(position - 1);
        }
    }

    @Override
    public int getItemCount() {
        return imageData.size() + 1;

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

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onCategoryClick(v, getLayoutPosition());
        }
    }
}
