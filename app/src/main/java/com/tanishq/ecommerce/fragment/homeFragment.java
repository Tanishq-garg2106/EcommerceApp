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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
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


    ///////////// Poster Slider
    private ViewPager posterSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage =2;
    private Timer timer;
    final private long DELAY_TIME =3000;
    final private long PERIOD_TIME =3000;
    ///////////// Poster Slider
    ///////////// Strip Ad
    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;

    ///////////// Strip Ad

    ///////////// Horizontal Product layout
    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalRecyclerView;
    ///////////// Horizontal Product layout

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_home, container, false);

///////////// Poster Slider

        posterSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList= new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_settings_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_share_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_account_box_24, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_home_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_person_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_settings_24,"#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_share_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_account_box_24,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_home_24,"#077AE4"));

        SliderAdapter sliderAdapter= new SliderAdapter(sliderModelList);
        posterSliderViewPager.setAdapter(sliderAdapter);
        posterSliderViewPager.setClipToPadding(false);
        posterSliderViewPager.setPageMargin(20);

        posterSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener =new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i , float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE){
                    pageLopper();
                }
            }
        };
        posterSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startPosterSlideShow();
        posterSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                pageLopper();
                startPosterSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP){
                    startPosterSlideShow();
                }
                return false;
            }
        });
        ///////////// Poster Slider

        ///////////// Strip Ad
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.key);
        stripAdContainer.setBackgroundColor(Color.parseColor("#077AE4"));
        ///////////// Strip Ad

        ///////////// Horizontal Product layout
        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_tittle);
        horizontalLayoutViewAllBtn = view.findViewById(R.id.horizontal_scroll_layout_btn);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);

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


        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();
        ///////////// Horizontal Product layout

        ///////////// Grid Product Layout

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutViewBtn = view.findViewById(R.id.grid_product_view_btn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);

        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));

        ///////////// Grid Product Layout

        /////////////////////

        RecyclerView testing =view.findViewById(R.id.testing);
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

    ///////////// Poster Slider
    private void pageLopper(){
        if (currentPage== sliderModelList.size() - 2){
            currentPage = 2;
            posterSliderViewPager.setCurrentItem(currentPage,false);
        }
        if (currentPage== 1){
            currentPage = sliderModelList.size() - 3;
            posterSliderViewPager.setCurrentItem(currentPage,false);
        }

    }


    private void startPosterSlideShow(){
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                posterSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };

        timer = new Timer() ;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }

    private void stopPosterSlideShow(){
        timer.cancel();
    }
    ///////////// Poster Slider
 }
