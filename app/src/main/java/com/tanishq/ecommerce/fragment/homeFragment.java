package com.tanishq.ecommerce.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanishq.ecommerce.CategoryAdapter;
import com.tanishq.ecommerce.CategoryModel;
import com.tanishq.ecommerce.GridProductLayoutAdapter;
import com.tanishq.ecommerce.HomePageAdapter;
import com.tanishq.ecommerce.HomePageModel;
import com.tanishq.ecommerce.HorizontalProductScrollAdapter;
import com.tanishq.ecommerce.HorizontalProductScrollModel;
import com.tanishq.ecommerce.R;
import com.tanishq.ecommerce.SliderAdapter;
import com.tanishq.ecommerce.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class homeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public homeFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_home, container, false);

         categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
         LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
         layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
         categoryRecyclerView.setLayoutManager(layoutManager);

         final List<CategoryModel>categoryModelList = new ArrayList<CategoryModel>();
         categoryModelList.add(new CategoryModel("link"," Home"));
        categoryModelList.add(new CategoryModel("link","Electronics"));
        categoryModelList.add(new CategoryModel("link","Appliances"));
        categoryModelList.add(new CategoryModel("link","Furniture"));
        categoryModelList.add(new CategoryModel("link","Fashion"));
        categoryModelList.add(new CategoryModel("link","Toy"));
        categoryModelList.add(new CategoryModel("link","Sports"));
        categoryModelList.add(new CategoryModel("link","WallArt"));
        categoryModelList.add(new CategoryModel("link","Books"));
        categoryModelList.add(new CategoryModel("link","Shoes"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

///////////// Poster Slider

        List<SliderModel>sliderModelList= new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_settings_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_share_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_account_box_24, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_home_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_person_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_settings_24,"#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_share_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_account_box_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_home_24,"#077AE4"));


        ///////////// Poster Slider


        ///////////// Horizontal Product layout


        List<HorizontalProductScrollModel> horizontalProductScrollModelList =  new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image,"Iphone 11","128GB Storage","Rs.52000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image,"Iphone 5","28GB Storage","Rs.54000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image,"Iphone 6","64GB Storage","Rs.58000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image,"Iphone 7","56GB Storage","Rs.59000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image,"Iphone 8","512GB Storage","Rs.60000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image,"Iphone 8","512GB Storage","Rs.60000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image,"Iphone 9","1024GB Storage","Rs.520000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image,"Iphone 10","128GB Storage","Rs.525200/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone_image,"Iphone 11 pro","20GB Storage","Rs.2000/-"));



        ///////////// Horizontal Product layout

        /////////////////////

        testing =view.findViewById(R.id.Home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.poster,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Deals Of The Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"#Trending",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.poster,"#ff0000"));
        homePageModelList.add(new HomePageModel(3,"#Trending",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deals Of The Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.poster,"#ffff00"));
        homePageModelList.add(new HomePageModel(0,sliderModelList));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //////////////////////

        return view;

    }
 }
