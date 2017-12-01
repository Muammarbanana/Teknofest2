package com.example.ahmadmuammarfanani.teknofest2.tambahan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.ahmadmuammarfanani.teknofest2.R;
import com.squareup.picasso.Picasso;

/**
 * Created by John on 11/21/2017.
 * untuk tampilan awal yang atasnya bisa digeser-geser
 */

public class SlidePage extends PagerAdapter {
    private Context ctx;
    private LayoutInflater layoutInflater;
    int count;
    int i;
    private String[] images;

    public SlidePage(Context context , int c) {
        i = 0;
        count = c;
        images = new String[count];
        this.ctx = context;
    }

    public void STRAdd(String s){
        images[i] = s;
        i++;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container , int position){
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.costom_layout_adds , null);
        ImageView mv = (ImageView) view.findViewById(R.id.imageView);
        //mv.setImageResource(images[position]);
        //mv.setImageBitmap(images[position]);
        WindowManager wm = (WindowManager)ctx.getSystemService(ctx.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Picasso.with(ctx)
                .load(images[position])
                .error(R.drawable.line)      // optional
                .resize(width , height/3)
                .into(mv);
        ViewPager vp = (ViewPager) container;
        vp.addView(view , 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container , int position , Object obj){
        ViewPager vp = (ViewPager) container;
        View view = (View) obj;
        vp.removeView(view);

    }
}
