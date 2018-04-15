//package com.rjt.android.ecommerce;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.BitmapFactory;
//import android.graphics.Point;
//import android.support.v7.widget.RecyclerView;
//import android.view.Display;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.ImageView;
//
//import com.squareup.picasso.Picasso;
//import com.rjt.R;
//
//import java.util.ArrayList;
//
//public class StaggeredGridLayoutAdapter extends CustomRecyclerViewAdapter {
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
//    public class Holder extends NormalViewHolder {
//        private ImageView images;
//
//        public Holder(View itemView) {
//            super(itemView);
//            images = itemView.findViewById(R.id.ivItemGridImage);
//        }
//    }
//}
