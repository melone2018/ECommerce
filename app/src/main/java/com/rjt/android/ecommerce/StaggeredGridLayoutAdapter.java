//package com.rjt.android.ecommerce;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.BitmapFactory;
//import android.graphics.Point;
//import android.support.v4.view.ViewPager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Display;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.squareup.picasso.Picasso;
//import com.rjt.R;
//
//import java.util.ArrayList;
//
//public class StaggeredGridLayoutAdapter extends RecyclerView
//        .Adapter<RecyclerView.ViewHolder>
//{
//    private Activity activity;
//    private ArrayList<String> images;
//    private int screenWidth;
//
//    public StaggeredGridLayoutAdapter(Activity activity, ArrayList<String> images) {
//        this.activity = activity;
//        this.images = images;
//
//        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        screenWidth = size.x;
//    }
//
//    @Override
//    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(activity)
//                .inflate(R.layout.demo_images, parent, false);
//        Holder dataObjectHolder = new Holder(view);
//        return dataObjectHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        final Holder myHolder = (Holder) holder;
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(images.get(position), opts);
//        opts.inJustDecodeBounds = false;
//        int height;
//        if (position == 1 || position == (images.size() - 1)) {
//            height = 150;
//        } else {
//            height = 300;
//        }
//
//        Picasso.with(activity)
//                .load(images.get(position))
//                .error(R.drawable.ic_empty)
//                .placeholder(R.drawable.ic_launcher)
//                .resize(screenWidth / 2, height)
//                .centerCrop()
//                .into((myHolder.images));
//    }
//
//    @Override
//    public int getItemCount() {
//        if(mHeaderView==null)
//            return imageData.size();
//        else
//            return imageData.size() + 1;
//        //return 10;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//
//        if (position == 0) {
//            return TYPE_HEADER;
//        }
//        return TYPE_NORMAL;
//    }
//
//    public class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
//        private ViewPager headerVp;
//        private LinearLayout dotLl;
//
//        public HeaderViewHolder(View itemView) {
//            super(itemView);
//            headerVp = itemView.findViewById(R.id.viewPager);
//            dotLl = itemView.findViewById(R.id.SliderDots);
//            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            onItemHolderClick(this);
//        }
//
//        @Override
//        public boolean onLongClick(View v) {
//            onItemHolderLongClick(this);
//            return true;
//        }
//    }
//
//    public class NormalViewHolder extends RecyclerView.ViewHolder {
//        //        private ImageView categoryIv;
//        private ImageView imgView;
//
//        public NormalViewHolder(View itemView) {
//            super(itemView);
////            categoryIv = itemView.findViewById(R.id.ivItemGridImage);
//            imgView = itemView.findViewById(R.id.imageView);
//
//        }
//    }
//}
