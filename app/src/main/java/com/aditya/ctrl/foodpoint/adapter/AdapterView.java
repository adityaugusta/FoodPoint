package com.aditya.ctrl.foodpoint.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aditya.ctrl.foodpoint.R;
import com.aditya.ctrl.foodpoint.activity.DetailActivity;
import com.aditya.ctrl.foodpoint.activity.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterView extends RecyclerView.Adapter<AdapterView.ViewHolder> {
    private Context context;
    private TypedArray banner;
    private TypedArray rating;
    private ArrayList<HashMap<String, String>> list;
    private HashMap<String, String> item_data = new HashMap<>();
    private int lastPosition = -1;

    public AdapterView(Context context, TypedArray banner, TypedArray rating, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.banner = banner;
        this.rating	= rating;
        this.list = list;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hol_place, hol_Address, hol_Price;
        ImageView hol_Banner, hol_Rating;

        public ViewHolder(View view) {
            super(view);
            hol_place   = (TextView) view.findViewById(R.id.hol_title);
            hol_Address = (TextView) view.findViewById(R.id.hol_address);
            hol_Price   = (TextView) view.findViewById(R.id.hol_price);
            hol_Banner  = (ImageView) view.findViewById(R.id.hol_banner);
            hol_Rating  = (ImageView) view.findViewById(R.id.hol_rating);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public AdapterView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View viewItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_detail, viewGroup, false);
        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if(list != null){
            item_data = list.get(position);
            viewHolder.hol_place.setText(item_data.get(MainActivity.PLACE));
            viewHolder.hol_Address.setText(item_data.get(MainActivity.ADDRESS));
            viewHolder.hol_Price.setText(item_data.get(MainActivity.PRICE));
        }

        viewHolder.hol_Banner.setImageResource(banner.getResourceId(position, -1));
        viewHolder.hol_Rating.setImageResource(rating.getResourceId(position, -1));

        viewHolder.hol_Banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_data = list.get(position);
                String s_place = item_data.get(MainActivity.PLACE);
                String s_desc = item_data.get(MainActivity.ADDRESS);
                String s_price = item_data.get(MainActivity.PRICE);

                Intent intent = new Intent(context, DetailActivity.class)
                        .putExtra("TEXT_1", s_place)
                        .putExtra("TEXT_2", s_desc)
                        .putExtra("TEXT_3", s_price)
                        .putExtra("IMG", position)
                        .putExtra("RATING", position);
                context.startActivity(intent);
            }
        });

        animate(viewHolder.itemView, position);
    }

    private void animate(View view, final int pos) {
        view.animate().cancel();
        view.setTranslationY(300);
        view.setAlpha(0);
        view.animate().alpha(1.0f).translationY(0).setDuration(500).setStartDelay(pos * 100);
    }

}
