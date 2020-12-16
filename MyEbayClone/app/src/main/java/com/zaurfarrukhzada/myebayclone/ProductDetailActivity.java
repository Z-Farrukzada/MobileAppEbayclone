package com.zaurfarrukhzada.myebayclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.zaurfarrukhzada.myebayclone.MainActivity.showCart;

public class ProductDetailActivity extends AppCompatActivity {

    private  ViewPager productImageViewPager;
    private  Button couPenBtn;
    private  TabLayout viewpagerIndicator;
    private TextView productTitle;
    private TextView averageRating;
    private TextView productPrice;
    private TextView productCutPrice;
    private TextView totalRatingView;
    private ImageView CodAvailableIndicator;
    private TextView CodAvailableIndicatorText;
    private TextView averageRatingTotal;

    private TextView rewardsTitle;
    private TextView rewardsBody;

    //Product Description Start
    private ConstraintLayout productDetailsContainer;
    private ConstraintLayout productDetailsTabContainer;
    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;
    private TextView productOnlyDetailsBody;

    private String productDescription;
    private String productOtherDetails;
    private List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();
    //Product Description End

    //CouPen Dialog//
    public static TextView couPenTitle;
    public static TextView couPenDate;
    public static TextView couPenBody;

    private static RecyclerView couPenRecycleView;
    private static  LinearLayout selectCouPen;
    //CouPen Dialog//

    private static boolean Already_Added_To_WishList_Product = false;
    private FloatingActionButton addWishListProduct;



    /////RATING LAYOUT START /////
    private LinearLayout rateNowLayout;
    private TextView ratingTotal;
    private LinearLayout ratingNumberContainer;
    private  TextView allRatingTotal;
    private LinearLayout ProgreesBarContainer;

    //RATING LAYOUT END/////

    private Button buyNowBtn;
    private FirebaseFirestore firebaseFirestore;


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
        productTitle = findViewById(R.id.product_title);
        averageRating = findViewById(R.id.tv_product_rating_count);
        totalRatingView =findViewById(R.id.total_rating_count);
        productPrice = findViewById(R.id.product_price);
        productCutPrice = findViewById(R.id.cutted_price);
        CodAvailableIndicator = findViewById(R.id.available_indicatoru_imageview);
        CodAvailableIndicatorText = findViewById(R.id.tv_available_indicator);
        rewardsTitle = findViewById(R.id.reward_title);
        rewardsBody =findViewById(R.id.reward_body);
        productDetailsTabContainer = findViewById(R.id.product_details_tab_container);
        productDetailsContainer =findViewById(R.id.product_details_container);
        productOnlyDetailsBody =findViewById(R.id.product_details_body);
        ratingTotal = findViewById(R.id.total_ratings);
        ratingNumberContainer = findViewById(R.id.ratings_numbers_container);
        allRatingTotal = findViewById(R.id.total_all_rating_numbers);
        ProgreesBarContainer = findViewById(R.id.rating_progressbar_container);
        averageRatingTotal = findViewById(R.id.average_rating);
        firebaseFirestore = FirebaseFirestore.getInstance();


        List<String> productImages = new ArrayList<>();
        firebaseFirestore.collection("PRODUCTS").document("dmw6C2OTGc53aANkzUP7")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                 if(task.isSuccessful()){
                      DocumentSnapshot documentSnapshot = task.getResult();
                      for(long x=1;x <(long)documentSnapshot.get("no_of_products_image")+1;x++){
                           productImages.add(documentSnapshot.get("product_image_"+x).toString());
                      }
                     ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
                     productImageViewPager.setAdapter(productImagesAdapter);
                     productTitle.setText(documentSnapshot.get("product_title").toString());
                     averageRating.setText(documentSnapshot.get("average_rating").toString());
                     totalRatingView.setText("("+(long)documentSnapshot.get("total_ratings")+")ratings");
                     productPrice.setText("US "+ documentSnapshot.get("product_price").toString()+" $");
                     productCutPrice.setText("US "+ documentSnapshot.get("cutted_price").toString()+" $");
                     if((boolean)documentSnapshot.get("COD")){
                         CodAvailableIndicator.setVisibility(VISIBLE);
                         CodAvailableIndicatorText.setVisibility(VISIBLE);
                     }else{
                         CodAvailableIndicator.setVisibility(View.INVISIBLE);
                         CodAvailableIndicatorText.setVisibility(View.INVISIBLE);
                     }
                     rewardsTitle.setText((long)documentSnapshot.get("free_coupens") + documentSnapshot.get("free_coupens_title").toString());
                     rewardsBody.setText(documentSnapshot.get("free_coupen_body").toString());
                     if((boolean)documentSnapshot.get("use_tab_layout")){
                           productDetailsTabContainer.setVisibility(VISIBLE);
                           productDetailsContainer.setVisibility(GONE);
                         productDescription = documentSnapshot.get("product_description").toString();
                           productOtherDetails=documentSnapshot.get("product_other_details").toString();

                           for(long x=1 ; x < (long)documentSnapshot.get("total_spec_title")+1;x++){
                               productSpecificationModelList.add(new ProductSpecificationModel(0,documentSnapshot.get("spec_title_"+x).toString()));
                               for(long y=1 ; y<(long)documentSnapshot.get("spec_title_"+ x +"_total_fields")+1;y++){
                                   productSpecificationModelList.add(new ProductSpecificationModel(1,documentSnapshot.get("spec_title_"+x+"_fields_"+y+"_name").toString()
                                                   ,documentSnapshot.get("spec_title_"+x+"_fields_"+y+"_value").toString()));
                               }

                           }
                     }else{
                         productDetailsTabContainer.setVisibility(GONE);
                         productDetailsContainer.setVisibility(VISIBLE);
                         productOnlyDetailsBody.setText(documentSnapshot.get("product_description").toString());
                     }
                     ratingTotal.setText((long)documentSnapshot.get("total_ratings")+"ratings");
                     for(int z = 0; z < 5 ;z++){
                        TextView ratingNo = (TextView) ratingNumberContainer.getChildAt(z);
                        ratingNo.setText(String.valueOf((long)documentSnapshot.get(5-z+"_star")));

                         ProgressBar progressBar  = (ProgressBar) ProgreesBarContainer.getChildAt(z);
                          int maxProgress = Integer.parseInt(String.valueOf((long)documentSnapshot.get("total_ratings")));
                         progressBar.setMax(maxProgress);
                         progressBar.setProgress(Integer.parseInt(String.valueOf((long)documentSnapshot.get(5-z+"_star"))));

                     }
                     allRatingTotal.setText(String.valueOf((long)documentSnapshot.get("total_ratings")));
                     averageRatingTotal.setText(documentSnapshot.get("average_rating").toString());
                     productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount(),productDescription,productOtherDetails,productSpecificationModelList));


                 }else{
                     String error = task.getException().getMessage();
                     Toast.makeText(ProductDetailActivity.this,error,Toast.LENGTH_SHORT).show();
                 }
            }
        });
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