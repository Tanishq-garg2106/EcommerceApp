package com.tanishq.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivit extends AppCompatActivity implements ProductDetailsActivity {

    private ViewPager productImageViewPager;
    private TabLayout viewPagerIndicator;

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;

    ///////rating number

    private LinearLayout rateNowContainer;
    ///////rating number

    private static boolean ALREADY_ADD_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImageViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishlistBtn = findViewById(R.id.Add_to_wishlist_btn);
        productDetailsViewPager = findViewById(R.id.product_detail_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_details_tablayout);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.phone_image);
        productImages.add(R.mipmap.poster);
        productImages.add(R.mipmap.grocery);
        productImages.add(R.mipmap.loginbg);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImageViewPager.setAdapter(productImagesAdapter);

        viewPagerIndicator.setupWithViewPager(productImageViewPager,true);
        addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ALREADY_ADD_TO_WISHLIST){
                    ALREADY_ADD_TO_WISHLIST = false;
                    addToWishlistBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#E12727")));
                }else {
                    ALREADY_ADD_TO_WISHLIST = true;
                    addToWishlistBtn.setImageTintList(getResources().getColorStateList(R.color.purple_200));
                }

            }

        });
        productDetailsViewPager.setAdapter(new ProductDetailAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount()));
        productDetailsViewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ///////rating number

        rateNowContainer = findViewById(R.id.rate_now_container);
        for (int x = 0 ;x <rateNowContainer.getChildCount();x++)
        {
            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setRating(starPosition);
                }
            });
        }
        ///////rating number

    }

    private void setRating(int starPosition) {
        for (int x = 0 ;x <rateNowContainer.getChildCount();x++){
            ImageView starBtn = (ImageView)rateNowContainer.getChildAt(starPosition);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if(x <= starPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.search_cart_icon,menu);
        return true;
    }
    @Override
    public boolean onOptionItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.nav_home) {
        finish();
        return true;
        }

        if (id == R.id.main_search_icon){
            //todo: search
            return true;
        }
        else if (id == R.id.nav_shopping_cart){
            //todo: search
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}