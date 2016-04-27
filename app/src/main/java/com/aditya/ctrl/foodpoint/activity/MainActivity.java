package com.aditya.ctrl.foodpoint.activity;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aditya.ctrl.foodpoint.R;
import com.aditya.ctrl.foodpoint.adapter.AdapterSlider;
import com.aditya.ctrl.foodpoint.adapter.AdapterView;
import com.aditya.ctrl.foodpoint.helper.DatabaseHelper_Trending;
import com.aditya.ctrl.foodpoint.slider.CirclePageIndicator;
import com.aditya.ctrl.foodpoint.view.CustomRecyclerView;
import com.aditya.ctrl.foodpoint.view.HeaderDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment = null;
    private Class fragmentClass = null;
    private DrawerLayout drawer;
    private ScrollView scro;
    private RelativeLayout det;
    private int currentPage = 0;
    private ImageView dh_icon;
    private TextView dh_title;
    CustomRecyclerView recyclerView;
    AdapterView adapters;
    int totalScrolled = 0;

    // DATABASE //
    DatabaseHelper_Trending databaseAccess;
    Cursor c_MeatLover, c_Sushi;
    ArrayList<HashMap<String, String>> arraylistMeatLover = new ArrayList<>();
    ArrayList<HashMap<String, String>> arraylistSushi = new ArrayList<>();

    public static TypedArray BANNER_ML, BANNER_S;
    public static TypedArray RATING;
    public static String PLACE = "place";
    public static String ADDRESS = "address";
    public static String PRICE = "price";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scro = (ScrollView) findViewById(R.id.scroll_1);

        // Navigation Drawer And Toolbar //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        // Slider Home //
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter adapter = new AdapterSlider(this);
        if (viewPager != null) {
            viewPager.setAdapter(adapter);
        }
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        if (indicator != null) {
            indicator.setViewPager(viewPager);
        }
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageSelected(int position) { currentPage = position; }
            @Override public void onPageScrolled(int pos, float arg1, int arg2) {}
            @Override public void onPageScrollStateChanged(int pos) {}
        });

        // Get Database //
        databaseAccess = new DatabaseHelper_Trending(this);
        c_MeatLover = databaseAccess.getDataMeatLover();
        c_Sushi = databaseAccess.getDataSushi();
        BANNER_ML = getResources().obtainTypedArray(R.array.banner_meatlover);
        BANNER_S = getResources().obtainTypedArray(R.array.banner_sushi);
        RATING = getResources().obtainTypedArray(R.array.rating_meatlover);

        // Detail Trending Food //
        det = (RelativeLayout) findViewById(R.id.detail_trending);
        dh_icon = (ImageView) findViewById(R.id.h_icon);
        dh_title = (TextView) findViewById(R.id.h_title);

        recyclerView = (CustomRecyclerView) findViewById(R.id.recycler_view);
        CustomRecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(HeaderDecoration.with(recyclerView)
                    .inflate(R.layout.header)
                    .parallax(0.5f)
                    .dropShadowDp(3)
                    .build());
        }

    }

    private void getListMeatLover() {
        while (!c_MeatLover.isAfterLast()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("place", c_MeatLover.getString(1));
            map.put("address", c_MeatLover.getString(2));
            map.put("price", c_MeatLover.getString(3));
            arraylistMeatLover.add(map);
            c_MeatLover.moveToNext();
        }
        adapters = new AdapterView(this, BANNER_ML, RATING, arraylistMeatLover);
    }

    private void getListSushi() {
        while (!c_Sushi.isAfterLast()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("place", c_Sushi.getString(1));
            map.put("address", c_Sushi.getString(2));
            map.put("price", c_Sushi.getString(3));
            arraylistSushi.add(map);
            c_Sushi.moveToNext();
        }
        adapters = new AdapterView(this, BANNER_S, RATING, arraylistSushi);
    }

    public void onClickTrending(View v) {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        det.startAnimation(show);
        det.setVisibility(View.VISIBLE);
        scro.startAnimation(hide);
        scro.setVisibility(View.GONE);
        switch(v.getId()) {
            case R.id.tf_1: getListMeatLover(); break;
            case R.id.tf_2: getListSushi(); break;
            case R.id.tf_3: break;
            case R.id.tf_4: break;
            case R.id.tf_5: break;
            case R.id.tf_6: break;
        }
        recyclerView.setAdapter(adapters);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_cuisines_all:
                fragmentClass = Cuisines_All.class;
                break;
            case R.id.nav_cuisines_asian:
                fragmentClass = Cuisines_All.class;
                break;
            case R.id.nav_cuisines_indonesian:
                fragmentClass = Cuisines_All.class;
                break;
            case R.id.nav_cuisines_western:
                fragmentClass = Cuisines_All.class;
                break;
        }

        try { fragment = (Fragment) fragmentClass.newInstance(); }
        catch (InstantiationException e) { e.printStackTrace(); }
        catch (IllegalAccessException e) { e.printStackTrace(); }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (count != 0){
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        else if (det.getVisibility()==View.VISIBLE && count == 0) {
            Animation hide = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
            Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
            det.setVisibility(View.GONE);
            det.setAnimation(hide);
            scro.setVisibility(View.VISIBLE);
            scro.setAnimation(show);
        }
        else {
            System.exit(0);
        }
    }

}
