package com.aditya.ctrl.foodpoint.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aditya.ctrl.foodpoint.R;

public class AdapterSlider extends PagerAdapter{

    Context context;
    int[] imageId = {
            R.drawable.ad_pict_banner_1,
            R.drawable.ad_pict_banner_2,
            R.drawable.ad_pict_banner_3,
            R.drawable.ad_pict_banner_4,
            R.drawable.ad_pict_banner_5};

    public AdapterSlider(Context context){
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.image_item, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.item_slider);
        imageView.setImageResource(imageId[position]);
        ((ViewPager)container).addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        return imageId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}