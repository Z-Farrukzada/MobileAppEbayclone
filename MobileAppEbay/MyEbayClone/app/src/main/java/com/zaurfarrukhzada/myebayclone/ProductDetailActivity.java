package com.zaurfarrukhzada.myebayclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.zaurfarrukhzada.myebayclone.MainActivity.showCart;

public class ProductDetailActivity extends AppCompatActivity {

    private  ViewPager productImageViewPager;
    private  Button couPenBtn;
    private  TabLayout viewpagerIndicator;

    //CouPen Dialog//
    public static TextView couPenTitle;
    public static TextView couPenDate;
    public static TextView couPenBody;

    private static RecyclerView couPenRecycleView;
    private static  LinearLayout selectCouPen;
    //CouPen Dialog//

    private static boolean Already_Added_To_WishList_Product = false;
    private FloatingActionButton addWishListProduct;

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;

    /////RATING LAYOUT START /////
    private LinearLayout rateNowLayout;
    //RATING LAYOUT END/////

    private Button buyNowBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImageViewPager=findViewById(R.id.product_images_viewpager);
        viewpagerIndicator=(TabLayout) findViewById(R.id.viewpage_indicater_tablayout);
        addWishListProduct=findViewById(R.id.add_to_wishlist);
        productDetailsViewPager=findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout=findViewById(R.id.product_details_tablayout);
        buyNowBtn = findViewById(R.id.buy_it_now_btn);
        couPenBtn = findViewById(R.id.coupen_use_button);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.product);
        productImages.add(R.drawable.product);
        productImages.add(R.drawable.product);
        productImages.add(R.drawable.product);

         ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImageViewPager.setAdapter(productImagesAdapter);


        viewpagerIndicator.setupWithViewPager(productImageViewPager,true);

        addWishListProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(Already_Added_To_WishList_Product){
                   Already_Added_To_WishList_Product=false;
                    addWishListProduct.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
               }else{
                   Already_Added_To_WishList_Product=true;
                   addWishListProduct.setSupportImageTintList(getResources().getColorStateList(R.color.colorRed));
               }

            }
        });

     productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount()));
     productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
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

        /////RATING LAYOUT START /////
        rateNowLayout =findViewById(R.id.rate_now_container);
        for(int x=0 ; x<rateNowLayout.getChildCount() ; x++){
          final int starPosition=x;
          rateNowLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  setRating(starPosition);
              }
          });

        }
        //RATING LAYOUT END/////

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(ProductDetailActivity.this,DeliveryActivity.class);
                startActivity(deliveryIntent);
            }
        });

        //CouPen Dialog
           Dialog coupenDialog = new Dialog(ProductDetailActivity.this);
                coupenDialog.setContentView(R.layout.coupen_dialog);
                coupenDialog.setCancelable(true);
                coupenDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                ImageView couPenDialogMenuBtn = coupenDialog.findViewById(R.id.toggle_coupen_menu_btn);
                couPenRecycleView = coupenDialog.findViewById(R.id.coupen_recycleview);
                 selectCouPen = coupenDialog.findViewById(R.id.coupen_container);

                couPenTitle = coupenDialog.findViewById(R.id.rewards_coupen_title);
                couPenBody = coupenDialog.findViewById(R.id.rewards_coupen_body);
                couPenDate =coupenDialog.findViewById(R.id.rewards_coupen_date);

                TextView originalPrice =coupenDialog.findViewById(R.id.original_price);
                TextView couPenPrice =coupenDialog.findViewById(R.id.after_coupen_price);

                LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                couPenRecycleView.setLayoutManager(layoutManager);

                List<RewardsModel> rewardsModelList = new ArrayList<>();

                rewardsModelList.add(new RewardsModel("Discount coupen","till 13th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
                rewardsModelList.add(new RewardsModel("Cashback","till 15th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
                rewardsModelList.add(new RewardsModel("Buy 1 Get 1 Free","till 18th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
                rewardsModelList.add(new RewardsModel("Discount coupen","till 13th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
                rewardsModelList.add(new RewardsModel("Cashback","till 15th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
                rewardsModelList.add(new RewardsModel("Buy 1 Get 1 Free","till 18th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));

                MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardsModelList,true);
                couPenRecycleView.setAdapter(myRewardsAdapter);
                myRewardsAdapter.notifyDataSetChanged();

                couPenDialogMenuBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog();
                    }
                });
        //CouPen Dialog
        couPenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                coupenDialog.show();

            }
        });

    }

    public static void showDialog(){
        if(couPenRecycleView.getVisibility() == GONE){
            couPenRecycleView.setVisibility(VISIBLE);
            selectCouPen.setVisibility(GONE);
        }else{
            couPenRecycleView.setVisibility(GONE);
            selectCouPen.setVisibility(VISIBLE);
        }
    }
    private void setRating(int starPosition) {
        for(int x=0;x<rateNowLayout.getChildCount();x++){
            ImageView starBtn =(ImageView) rateNowLayout.getChildAt(x);
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if(x <= starPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }  else if (id == R.id.main_search_icon) {
            //todo:search
            return true;
        }else if(id==R.id.main_cart_icon){
            //todo:cart
            Intent cartIntent = new Intent(ProductDetailActivity.this,MainActivity.class);
            showCart = true;
            startActivity(cartIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}