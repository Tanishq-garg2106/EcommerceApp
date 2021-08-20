package com.tanishq.ecommerce;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.POSTER_SLIDER;
            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case HomePageModel.POSTER_SLIDER:
                View posterSliderView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliding_ad_layout, viewGroup, false);
                return new PosterSliderViewHolder(posterSliderView);
            case HomePageModel.STRIP_AD_BANNER:
                View stripAdView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.strip_ad_layout, viewGroup, false);
                return new StripAdViewHolder(stripAdView);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_layout, viewGroup, false);
                return new HorizontalProductViewHolder(horizontalProductView);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_product_layout, viewGroup, false);
                return new GridProductViewHolder(gridProductView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.POSTER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((PosterSliderViewHolder) viewHolder).setPosterSliderViewPager(sliderModelList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                int resource = homePageModelList.get(position).getResource();
                String color = homePageModelList.get(position).getBackgroundColor();
                ((StripAdViewHolder) viewHolder).setStripAd(resource, color);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizontalLayoutTitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((HorizontalProductViewHolder) viewHolder).setHorizontalProductLayout(horizontalProductScrollModelList, horizontalLayoutTitle);
                break;
           case HomePageModel.GRID_PRODUCT_VIEW:
               String gridLayoutTitle = homePageModelList.get(position).getTitle();
               List<HorizontalProductScrollModel> gridProductScrollModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
               ((GridProductViewHolder) viewHolder).setGridProductLayout(gridProductScrollModelList,gridLayoutTitle);
               break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class PosterSliderViewHolder extends RecyclerView.ViewHolder {

        private ViewPager posterSliderViewPager;
        private int currentPage = 2;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;

        public PosterSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            posterSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);

        }

        private void setPosterSliderViewPager(final List<SliderModel> sliderModelList) {

            SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
            posterSliderViewPager.setAdapter(sliderAdapter);
            posterSliderViewPager.setClipToPadding(false);
            posterSliderViewPager.setPageMargin(20);

            posterSliderViewPager.setCurrentItem(currentPage);

            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {
                }

                @Override
                public void onPageSelected(int i) {
                    currentPage = i;
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                    if (i == ViewPager.SCROLL_STATE_IDLE) {
                        pageLopper(sliderModelList);
                    }
                }
            };
            posterSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startPosterSlideShow(sliderModelList);
            posterSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    pageLopper(sliderModelList);
                    stopPosterSlideShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startPosterSlideShow(sliderModelList);
                    }
                    return false;
                }
            });
        }

        private void pageLopper(List<SliderModel> sliderModelList) {
            if (currentPage == sliderModelList.size() - 2) {
                currentPage = 2;
                posterSliderViewPager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                posterSliderViewPager.setCurrentItem(currentPage, false);
            }

        }

        private void startPosterSlideShow(List<SliderModel> sliderModelList) {
            Handler handler = new Handler();
            Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderModelList.size()) {
                        currentPage = 1;
                    }
                    posterSliderViewPager.setCurrentItem(currentPage++, true);
                }
            };

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopPosterSlideShow() {
            timer.cancel();
        }
    }

    public class StripAdViewHolder extends RecyclerView.ViewHolder {

        private ImageView stripAdImage;
        private ConstraintLayout stripAdContainer;

        public StripAdViewHolder(@NonNull View itemView) {
            super(itemView);

            stripAdImage = itemView.findViewById(R.id.strip_ad_image);
            stripAdContainer = itemView.findViewById(R.id.strip_ad_container);
        }

        public void setStripAd(int resource, String color) {
            stripAdImage.setImageResource(resource);
            stripAdContainer.setBackgroundColor(Color.parseColor(color));
        }
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {

        private TextView horizontalLayoutTitle;
        private Button horizontalLayoutViewAllBtn;
        private RecyclerView horizontalRecyclerView;

        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);

             horizontalLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_tittle);
             horizontalLayoutViewAllBtn = itemView.findViewById(R.id.horizontal_scroll_layout_btn);
             horizontalRecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recyclerview);
        }

        private void setHorizontalProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title) {
            horizontalLayoutTitle.setText(title);
            if (horizontalProductScrollModelList.size() > 8) {
                horizontalLayoutViewAllBtn.setVisibility(View.VISIBLE);
            } else {
                horizontalLayoutViewAllBtn.setVisibility(View.INVISIBLE);
            }
            HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);
            horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder {
        private TextView gridLayoutTitle;
        private Button gridLayoutViewBtn;
        private GridView gridView;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
            gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            gridLayoutViewBtn = itemView.findViewById(R.id.grid_product_view_btn);
            gridView = itemView.findViewById(R.id.grid_product_layout_gridview);
        }

        private void setGridProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title) {
            gridLayoutTitle.setText(title);
            gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));
        }
    }

}
