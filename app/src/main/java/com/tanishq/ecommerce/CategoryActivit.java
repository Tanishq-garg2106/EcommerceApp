package com.tanishq.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivit extends AppCompatActivity implements CategoryActivity {

    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recycler_view);


        ///////////// Poster Slider

        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_settings_24, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_share_24, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_account_box_24, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_home_24, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_person_24, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_settings_24, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_share_24, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_account_box_24, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_home_24, "#077AE4"));


        ///////////// Poster Slider


        ///////////// Horizontal Product layout


        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image, "Iphone 11", "128GB Storage", "Rs.52000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image, "Iphone 5", "28GB Storage", "Rs.54000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image, "Iphone 6", "64GB Storage", "Rs.58000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image, "Iphone 7", "56GB Storage", "Rs.59000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image, "Iphone 8", "512GB Storage", "Rs.60000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image, "Iphone 8", "512GB Storage", "Rs.60000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image, "Iphone 9", "1024GB Storage", "Rs.520000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image, "Iphone 10", "128GB Storage", "Rs.525200/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image, "Iphone 11 pro", "20GB Storage", "Rs.2000/-"));


        ///////////// Horizontal Product layout
        /////////////////////

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();

        homePageModelList.add(new HomePageModel(1,R.mipmap.poster,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Deals Of The Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"#Trending",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.poster,"#ff0000"));
        homePageModelList.add(new HomePageModel(3,"#Trending",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deals Of The Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.poster,"#ffff00"));


        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //////////////////////
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.search_icon,menu);
        return true;
    }
    @Override
    public boolean onOptionItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.main_search_icon){
            //todo: search
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
