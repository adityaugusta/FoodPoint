package com.aditya.ctrl.foodpoint.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aditya.ctrl.foodpoint.R;

public class DetailActivity extends AppCompatActivity {

    static int [] l_banner_1 = {
            R.drawable.img_bd_1,
            R.drawable.img_bd_3,
            R.drawable.img_bd_2
    };
    static int [] l_rating_1 = {
            R.drawable.rate_4,
            R.drawable.rate_3,
            R.drawable.rate_4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }

    public static class DetailFragment extends Fragment {
        public DetailFragment() {}
        private String s_t = null;
        private String s_d = null;
        private String s_p = null;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            TextView tet = (TextView) rootView.findViewById(R.id.text_tit);
            TextView des = (TextView) rootView.findViewById(R.id.text_desc);
            TextView pri = (TextView) rootView.findViewById(R.id.text_price);
            ImageView iii = (ImageView) rootView.findViewById(R.id.ii);
            ImageView iii2 = (ImageView) rootView.findViewById(R.id.ii2);

            Intent intent = getActivity().getIntent();

            if (intent != null) {
                s_t = intent.getStringExtra("TEXT_1");
                s_d = intent.getStringExtra("TEXT_2");
                s_p = intent.getStringExtra("TEXT_3");
                int banner = intent.getExtras().getInt("IMG");
                int rating = intent.getExtras().getInt("RATING");
                iii.setImageResource(l_banner_1[banner]);
                iii2.setImageResource(l_rating_1[rating]);
            }

            tet.setText(s_t);
            des.setText(s_d);
            pri.setText(s_p);


            return rootView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }

}