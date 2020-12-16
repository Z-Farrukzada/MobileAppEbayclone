package com.zaurfarrukhzada.myebayclone.ui.home;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zaurfarrukhzada.myebayclone.GridProductLayoutAdapter;
import com.zaurfarrukhzada.myebayclone.HorizontalScrollProductAdapter;
import com.zaurfarrukhzada.myebayclone.HorizontalScrollProductModel;
import com.zaurfarrukhzada.myebayclone.ProductDetailActivity;
import com.zaurfarrukhzada.myebayclone.R;
import com.zaurfarrukhzada.myebayclone.SliderAdapter;
import com.zaurfarrukhzada.myebayclone.SliderModel;
import com.zaurfarrukhzada.myebayclone.ViewAllActivity;
import com.zaurfarrukhzada.myebayclone.WishlistModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private int LastPosition = -1;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();

    }
    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()){
            case 0:
                return HomePageModel.BANNER_SLIDER;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case HomePageModel.BANNER_SLIDER:
                View bannerSlideView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_add_layout,parent,false);
                return new BannerSlideViewHolder(bannerSlideView);
            case HomePageModel.STRIP_AD_BANNER:
                View stripView = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_add_layout,parent,false);
                return new StripAddBannerViewHolder(stripView);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout,parent,false);
                return new HorizontalProductViewHolder(horizontalProductView);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout,parent,false);
                return new GridProductViewHolder(gridProductView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (homePageModelList.get(position).getType()){
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSlideViewHolder)holder).setBannerSliderViewPager(sliderModelList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                String resource =homePageModelList.get(position).getResource();
                ((StripAddBannerViewHolder)holder).setStripAdd(resource);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizontalLayoutTitle =homePageModelList.get(position).getTitle();
                List<HorizontalScrollProductModel> horizontalScrollProductModelList =
                        homePageModelList.get(position).getHorizontalScrollProductModelList();
                String layoutColor = homePageModelList.get(position).getBackgroundColor();
                List<WishlistModel> viewAllProductModel = homePageModelList.get(position).getViewAllProductModelList();
                ((HorizontalProductViewHolder)holder).setHorizontalProductLayout(horizontalScrollProductModelList,horizontalLayoutTitle,layoutColor,viewAllProductModel);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridLayoutTitle =homePageModelList.get(position).getTitle();
                String gridLayoutColor = homePageModelList.get(position).getBackgroundColor();
                List<HorizontalScrollProductModel> gridScrollProductModelList = homePageModelList.get(position)
                        .getHorizontalScrollProductModelList();
                ((GridProductViewHolder)holder).setGridProductLayout(gridScrollProductModelList,gridLayoutTitle,gridLayoutColor);
            default:
                return;

        }
        if(LastPosition < position){
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            LastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }


    public class BannerSlideViewHolder extends RecyclerView.ViewHolder{
        private ViewPager2 bannerSliderViewPager2;

        private Timer timer;
        private int currentPage;
        final private long DELAY_TIME =3000;
        final private long PERIOD_TIME =3000;
        private List<SliderModel> arrangeList;

        public BannerSlideViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager2=itemView.findViewById(R.id.banner_slider_view_page);
        }

        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList){
              currentPage = 2;
            if(timer !=null){
                timer.cancel();
            }
            arrangeList = new ArrayList<>();
            for(int x=0;x<sliderModelList.size();x++){
                    arrangeList.add(x,sliderModelList.get(x));
            }
            arrangeList.add(0,sliderModelList.get(sliderModelList.size() - 2));
            arrangeList.add(1,sliderModelList.get(sliderModelList.size() - 1));
            arrangeList.add(sliderModelList.get(0));
            arrangeList.add(sliderModelList.get(1));
            bannerSliderViewPager2.setAdapter(new SliderAdapter(arrangeList,bannerSliderViewPager2));
            bannerSliderViewPager2.setClipToPadding(false);
            bannerSliderViewPager2.setPageTransformer(new MarginPageTransformer(20));

            ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageSelected(int position) {
                    currentPage=position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if(state == ViewPager.SCROLL_STATE_IDLE){
                        pageLooper(arrangeList);
                    }
                }
            };
            bannerSliderViewPager2.registerOnPageChangeCallback(onPageChangeCallback);
            startBannerSlideShow(arrangeList);
            bannerSliderViewPager2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(arrangeList);
                    stopBannerSlideShow();
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        startBannerSlideShow(arrangeList);
                    }
                    return false;
                }
            });

        }
        private void pageLooper(List<SliderModel> sliderModelList){
            if(currentPage==sliderModelList.size()-2){
                currentPage=2;
                bannerSliderViewPager2.setCurrentItem(currentPage,false);
            }
            if(currentPage==1){
                currentPage=sliderModelList.size()-3;
                bannerSliderViewPager2.setCurrentItem(currentPage,false);
            }
        }
        private void startBannerSlideShow(final List<SliderModel> sliderModelList){
            final Handler handler =new Handler();
            final Runnable update =new Runnable() {
                @Override
                public void run() {
                    if(currentPage >=sliderModelList.size()){
                        currentPage=1;
                    }
                    bannerSliderViewPager2.setCurrentItem(currentPage++,true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            },DELAY_TIME,PERIOD_TIME );
        }
        private void stopBannerSlideShow(){
            timer.cancel();
        }
    }
    public class StripAddBannerViewHolder extends RecyclerView.ViewHolder{
        ///Strip Add
        private ImageView stripAdImage;
        private ConstraintLayout stripAdContainer;
        ///Strip Add

        public StripAddBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            stripAdImage =itemView.findViewById(R.id.strip_add_Image);
            stripAdContainer=itemView.findViewById(R.id.strip_add_container);

        }

        private  void setStripAdd(String  resource){
            Glide.with(itemView.getContext()).load(resource)
                    .apply(new RequestOptions().placeholder(R.drawable.defaultimage)).into(stripAdImage);



        }
    }
    public class HorizontalProductViewHolder extends  RecyclerView.ViewHolder{

        private ConstraintLayout container;
        private TextView horizontalLayoutTitle;
        private Button horizontalProductViewAllBtn;
        private  RecyclerView horizontalRecyclerView;


        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            horizontalLayoutTitle=itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontalProductViewAllBtn=itemView.findViewById(R.id.horizontal_scroll_layout_button_viewall);
            horizontalRecyclerView=itemView.findViewById(R.id.horizontal_layout_rv);
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);
        }
        public void setHorizontalProductLayout(List<HorizontalScrollProductModel> horizontalScrollProductModelList, String title, String color, List<WishlistModel> viewAllProductModelList){
             container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            horizontalLayoutTitle.setText(title);

            if(horizontalScrollProductModelList.size() > 8){
                horizontalProductViewAllBtn.setVisibility(View.VISIBLE);
                horizontalProductViewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewAllActivity.wishlistModelList = viewAllProductModelList;
                        Intent viewAllIntent  = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_name",0);
                        viewAllIntent.putExtra("title",title);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            }
            else {
                horizontalProductViewAllBtn.setVisibility(View.INVISIBLE);
            }

            HorizontalScrollProductAdapter horizontalScrollProductAdapter = new HorizontalScrollProductAdapter(horizontalScrollProductModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

            horizontalRecyclerView.setLayoutManager(linearLayoutManager);
            horizontalRecyclerView.setAdapter(horizontalScrollProductAdapter);
            horizontalScrollProductAdapter.notifyDataSetChanged();
        }
    }
    public class GridProductViewHolder extends  RecyclerView.ViewHolder{

        private ConstraintLayout container;
        private TextView gridLayoutTitle;
        private Button gridLayoutProductViewAllBtn;
        private GridLayout gridLayout;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
             container = itemView.findViewById(R.id.container);
             gridLayoutTitle =itemView.findViewById(R.id.grid_layout_product_title);
             gridLayoutProductViewAllBtn =itemView.findViewById(R.id.grid_layout_product_viewall_button);
             gridLayout = (GridLayout) itemView.findViewById(R.id.grid_layout);


        }
        private void setGridProductLayout(List<HorizontalScrollProductModel> horizontalScrollProductModelList,String title,String color){
            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            gridLayoutTitle.setText(title);

            for (int x=0; x<4 ; x++){
                ImageView productImage = gridLayout.getChildAt(x).findViewById(R.id.horizontal_v_product_image);
                TextView productTitle = gridLayout.getChildAt(x).findViewById(R.id.horizontal_v_product_title);
                TextView productDescription = gridLayout.getChildAt(x).findViewById(R.id.horizontal_v_product_description);
                TextView productPrice = gridLayout.getChildAt(x).findViewById(R.id.horizontal_v_product_price);

                Glide.with(itemView.getContext()).load(horizontalScrollProductModelList.get(x).getProductImage())
                        .apply(new RequestOptions().placeholder(R.drawable.camera)).into(productImage);

                productTitle.setText(horizontalScrollProductModelList.get(x).getProductTitle());
                productDescription.setText(horizontalScrollProductModelList.get(x).getProductDescription());
                productPrice.setText("US "+horizontalScrollProductModelList.get(x).getProductPrice() + " $");

                gridLayout.getChildAt(x).setBackgroundColor(Color.parseColor("#ffffff"));
                if(!title.equals("")) {
                     int finalX = x;
                    gridLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailActivity.class);
                            productDetailsIntent.putExtra("PRODUCT_ID",horizontalScrollProductModelList.get(finalX).getProductID());
                            itemView.getContext().startActivity(productDetailsIntent);
                        }
                    });
                }
            }

            if(!title.equals("")) {
                gridLayoutProductViewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewAllActivity.horizontalScrollProductModelList = horizontalScrollProductModelList;
                        Intent viewAllGridIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllGridIntent.putExtra("layout_name", 1);
                        viewAllGridIntent.putExtra("title", title);
                        itemView.getContext().startActivity(viewAllGridIntent);
                    }
                });
            }
        }
    }





}
