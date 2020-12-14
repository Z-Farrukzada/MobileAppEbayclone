package com.zaurfarrukhzada.myebayclone;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zaurfarrukhzada.myebayclone.ui.home.HomePageAdapter;
import com.zaurfarrukhzada.myebayclone.ui.home.HomePageModel;

import java.util.ArrayList;
import java.util.List;

public class DBdata {

    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static   List<CategoryModel> categoryModelList = new ArrayList<>();
    public static   List<HomePageModel>  homePageModelList = new ArrayList<>();
    public static void loadCategories(final CategoryAdapter categoryAdapter,final Context context){
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                        categoryModelList.add(new CategoryModel(queryDocumentSnapshot.get("icon").toString(),queryDocumentSnapshot.get("categoryName").toString()));
                    }
                    categoryAdapter.notifyDataSetChanged();
                }else{
                    String error = task.getException().getMessage();
                    Toast.makeText(context,error,Toast.LENGTH_LONG).show();
                }
            }
        });
    };
    public static void loadFragmentData(final HomePageAdapter homePageAdapter,Context context){

        firebaseFirestore.collection("CATEGORIES")
                .document("HOME")
                .collection("TOP_DEALS")
                .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                        if((long)queryDocumentSnapshot.get("view_type")==0){
                            List<SliderModel> sliderModelList = new ArrayList<>();
                            long no_of_banners = (long)queryDocumentSnapshot.get("no_of_banners");
                            for(long x = 1; x<no_of_banners+1;x++){
                                sliderModelList.add(new SliderModel(queryDocumentSnapshot.get("banner_"+x).toString()));
                            }
                            homePageModelList.add(new HomePageModel(0,sliderModelList));
                        }
                        else if ((long)queryDocumentSnapshot.get("view_type")==1){
                            homePageModelList.add(new HomePageModel(1,queryDocumentSnapshot.get("strip_banner").toString()));
                        }else if((long)queryDocumentSnapshot.get("view_type")==2){
                            List<HorizontalScrollProductModel> horizontalScrollProductModelList = new ArrayList<>();
                            long no_of_products = (long)queryDocumentSnapshot.get("no_of_products");
                            for(long x = 1; x<no_of_products+1;x++){
                                horizontalScrollProductModelList.add(new HorizontalScrollProductModel(queryDocumentSnapshot.get("product_ID_"+x).toString()
                                        ,queryDocumentSnapshot.get("product_image_"+x).toString()
                                        ,queryDocumentSnapshot.get("product_title_"+x).toString()
                                        ,queryDocumentSnapshot.get("product_subtitle_"+x).toString()
                                        ,queryDocumentSnapshot.get("product_price_"+x).toString()));
                            }
                            homePageModelList.add(new HomePageModel(2,queryDocumentSnapshot.get("layout_title").toString()
                                    ,queryDocumentSnapshot.get("layout_background").toString(),horizontalScrollProductModelList));
                        }
                        else if((long)queryDocumentSnapshot.get("view_type")==3){
                            List<HorizontalScrollProductModel> GridLayoutModelList = new ArrayList<>();
                            long no_of_products = (long)queryDocumentSnapshot.get("no_of_products");
                            for(long x = 1; x<no_of_products+1;x++){
                                GridLayoutModelList.add(new HorizontalScrollProductModel(queryDocumentSnapshot.get("product_ID_"+x).toString()
                                        ,queryDocumentSnapshot.get("product_image_"+x).toString()
                                        ,queryDocumentSnapshot.get("product_title_"+x).toString()
                                        ,queryDocumentSnapshot.get("product_subtitle_"+x).toString()
                                        ,queryDocumentSnapshot.get("product_price_"+x).toString()));
                            }
                            homePageModelList.add(new HomePageModel(3,queryDocumentSnapshot.get("layout_title").toString()
                                    ,queryDocumentSnapshot.get("layout_background").toString(),GridLayoutModelList));
                        }

                    }
                    homePageAdapter.notifyDataSetChanged();
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(context,error,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
