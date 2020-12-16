package com.zaurfarrukhzada.myebayclone;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zaurfarrukhzada.myebayclone.ui.home.HomeFragment;
import com.zaurfarrukhzada.myebayclone.ui.home.HomePageAdapter;
import com.zaurfarrukhzada.myebayclone.ui.home.HomePageModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.google.firebase.firestore.FirebaseFirestore.getInstance;

public class DBdata {


    public static FirebaseFirestore firebaseFirestore = getInstance();
    public static List<CategoryModel> categoryModelList = new ArrayList<>();
    public static List<List<HomePageModel>> lists = new ArrayList<>();
    public static List<String> loadCategoriesName = new ArrayList<>();
    public static List<String> wishList = new ArrayList<>();

    public static void loadCategories(RecyclerView categoryRecycleView, final Context context) {
        firebaseFirestore.collection("CATEGORIES").orderBy("index")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        categoryModelList.add(new CategoryModel(queryDocumentSnapshot.get("icon").toString(), queryDocumentSnapshot.get("categoryName").toString()));
                    }
                    CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
                    categoryRecycleView.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public static void loadFragmentData(RecyclerView homeRecycleView, final Context context, final int index, String categoryName) {

        firebaseFirestore.collection("CATEGORIES")
                .document(categoryName.toUpperCase())
                .collection("TOP_DEALS")
                .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult())
                        if ((long) queryDocumentSnapshot.get("view_type") == 0) {
                            List<SliderModel> sliderModelList = new ArrayList<>();
                            long no_of_banners = (long) queryDocumentSnapshot.get("no_of_banners");
                            for (long x = 1; x < no_of_banners + 1; x++) {
                                sliderModelList.add(new SliderModel(queryDocumentSnapshot.get("banner_" + x).toString()));
                            }
                            lists.get(index).add(new HomePageModel(0, sliderModelList));
                        } else if ((long) queryDocumentSnapshot.get("view_type") == 1) {
                            lists.get(index).add(new HomePageModel(1, queryDocumentSnapshot.get("strip_banner").toString()));
                        } else if ((long) queryDocumentSnapshot.get("view_type") == 2) {
                            List<WishlistModel> viewAllProductList = new ArrayList<>();
                            List<HorizontalScrollProductModel> horizontalScrollProductModelList = new ArrayList<>();
                            long no_of_products = (long) queryDocumentSnapshot.get("no_of_products");
                            for (long x = 1; x < no_of_products + 1; x++) {
                                horizontalScrollProductModelList.add(new HorizontalScrollProductModel(queryDocumentSnapshot.get("product_ID_" + x).toString()
                                        , queryDocumentSnapshot.get("product_image_" + x).toString()
                                        , queryDocumentSnapshot.get("product_title_" + x).toString()
                                        , queryDocumentSnapshot.get("product_subtitle_" + x).toString()
                                        , queryDocumentSnapshot.get("product_price_" + x).toString()));

                                viewAllProductList.add(new WishlistModel(queryDocumentSnapshot.get("product_image_" + x).toString()
                                        , queryDocumentSnapshot.get("product_head_title_" + x).toString()
                                        , (long) queryDocumentSnapshot.get("free_coupens_" + x)
                                        , queryDocumentSnapshot.get("average_rating_" + x).toString()
                                        , (long) queryDocumentSnapshot.get("total_ratings_" + x)
                                        , queryDocumentSnapshot.get("product_price_" + x).toString()
                                        , queryDocumentSnapshot.get("cutted_price_" + x).toString()
                                        , (boolean) queryDocumentSnapshot.get("COD_" + x)));

                            }
                            lists.get(index).add(new HomePageModel(2, queryDocumentSnapshot.get("layout_title").toString()
                                    , queryDocumentSnapshot.get("layout_background").toString(), horizontalScrollProductModelList, viewAllProductList));
                        } else if ((long) queryDocumentSnapshot.get("view_type") == 3) {
                            List<HorizontalScrollProductModel> GridLayoutModelList = new ArrayList<>();
                            long no_of_products = (long) queryDocumentSnapshot.get("no_of_products");
                            for (long x = 1; x < no_of_products + 1; x++) {
                                GridLayoutModelList.add(new HorizontalScrollProductModel(queryDocumentSnapshot.get("product_ID_" + x).toString()
                                        , queryDocumentSnapshot.get("product_image_" + x).toString()
                                        , queryDocumentSnapshot.get("product_title_" + x).toString()
                                        , queryDocumentSnapshot.get("product_subtitle_" + x).toString()
                                        , queryDocumentSnapshot.get("product_price_" + x).toString()));
                            }
                            lists.get(index).add(new HomePageModel(3, queryDocumentSnapshot.get("layout_title").toString()
                                    , queryDocumentSnapshot.get("layout_background").toString(), GridLayoutModelList));
                        }
                    HomePageAdapter homePageAdapter = new HomePageAdapter(lists.get(index));
                    homeRecycleView.setAdapter(homePageAdapter);
                    homePageAdapter.notifyDataSetChanged();
                    HomeFragment.swipeRefreshLayout.setRefreshing(false);
                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public static void loadWishList(final Context context, Dialog dialog){
        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA")
                .document("MY_WISHLIST").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                   for(long x=0; x < (long)task.getResult().get("list_size");x++){
                       wishList.add(task.getResult().get("product_ID_"+x).toString());
                   }
                }else{
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();

            }
        });
    }
}
