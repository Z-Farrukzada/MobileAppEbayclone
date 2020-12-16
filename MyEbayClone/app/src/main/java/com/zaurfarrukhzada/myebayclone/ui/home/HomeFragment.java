package com.zaurfarrukhzada.myebayclone.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zaurfarrukhzada.myebayclone.CategoryAdapter;
import com.zaurfarrukhzada.myebayclone.CategoryModel;
import com.zaurfarrukhzada.myebayclone.GridProductLayoutAdapter;
import com.zaurfarrukhzada.myebayclone.HorizontalScrollProductAdapter;
import com.zaurfarrukhzada.myebayclone.HorizontalScrollProductModel;
import com.zaurfarrukhzada.myebayclone.MainActivity;
import com.zaurfarrukhzada.myebayclone.R;
import com.zaurfarrukhzada.myebayclone.SliderAdapter;
import com.zaurfarrukhzada.myebayclone.SliderModel;
import com.zaurfarrukhzada.myebayclone.WishlistModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.zaurfarrukhzada.myebayclone.DBdata.categoryModelList;
import static com.zaurfarrukhzada.myebayclone.DBdata.firebaseFirestore;
import static com.zaurfarrukhzada.myebayclone.DBdata.lists;
import static com.zaurfarrukhzada.myebayclone.DBdata.loadCategories;
import static com.zaurfarrukhzada.myebayclone.DBdata.loadCategoriesName;
import static com.zaurfarrukhzada.myebayclone.DBdata.loadFragmentData;


public class HomeFragment extends Fragment {

    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;

    public static SwipeRefreshLayout swipeRefreshLayout;
    private HomeViewModel homeViewModel;
    private RecyclerView categoryRecyclerView;
    private List<CategoryModel> categoryModelFakeList = new ArrayList<>();
    private List<HomePageModel> homePageModelFakeList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private RecyclerView homePageRecycleView;
    private HomePageAdapter homePageAdapter;
    private ImageView NoInternetConnect;
    private Button retryBtn;


    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.colorPrimary)
                ,getContext().getResources().getColor(R.color.colorPrimary),getContext().getResources().getColor(R.color.colorPrimary));
        homePageRecycleView = view.findViewById(R.id.Home_Page_Recycle_View);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        retryBtn = view.findViewById(R.id.retry_button);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);


        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecycleView.setLayoutManager(testingLayoutManager);

        ////category fake list\\\\\
        categoryModelFakeList.add(new CategoryModel("null",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        ////category fake list\\\\\
        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add(new SliderModel("null"));
        sliderModelFakeList.add(new SliderModel("null"));
        sliderModelFakeList.add(new SliderModel("null"));
        sliderModelFakeList.add(new SliderModel("null"));
        sliderModelFakeList.add(new SliderModel("null"));


        categoryAdapter = new CategoryAdapter(categoryModelFakeList);


        homePageAdapter = new HomePageAdapter(homePageModelFakeList);


        NoInternetConnect = view.findViewById(R.id.no_internet_connection);
         connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
         networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawer.setDrawerLockMode(0);
            NoInternetConnect.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homePageRecycleView.setVisibility(View.VISIBLE);

            if (categoryModelList.size() == 0) {
                loadCategories(categoryRecyclerView, getContext());
            } else {
                CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
                categoryAdapter.notifyDataSetChanged();
            }
            categoryRecyclerView.setAdapter(categoryAdapter);
            if (lists.size() == 0) {
                loadCategoriesName.add("HOME");
                lists.add(new ArrayList<HomePageModel>());

                loadFragmentData(homePageRecycleView, getContext(),0,"Home");

            } else {
                homePageAdapter = new HomePageAdapter(lists.get(0));
                homePageAdapter.notifyDataSetChanged();
            }
            homePageRecycleView.setAdapter(homePageAdapter);
        } else {
            MainActivity.drawer.setDrawerLockMode(1);
            categoryRecyclerView.setVisibility(View.GONE);
            homePageRecycleView.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.caveman).into(NoInternetConnect);
            NoInternetConnect.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);


        }
        ///Refresh Layout Start

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                reloadPage();
            }
        });

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadPage();
            }
        });

        //Refresh Layout End
        return view;
    }
    @SuppressLint("WrongConstant")
    private void reloadPage(){
        networkInfo = connectivityManager.getActiveNetworkInfo();
        categoryModelList.clear();
        lists.clear();
        loadCategoriesName.clear();

        if (networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawer.setDrawerLockMode(0);
            NoInternetConnect.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homePageRecycleView.setVisibility(View.VISIBLE);

            categoryAdapter = new CategoryAdapter(categoryModelFakeList);
            homePageAdapter = new HomePageAdapter(homePageModelFakeList);
            categoryRecyclerView.setAdapter(categoryAdapter);
            homePageRecycleView.setAdapter(homePageAdapter);

            loadCategories(categoryRecyclerView, getContext());
            loadCategoriesName.add("HOME");
            lists.add(new ArrayList<HomePageModel>());

            loadFragmentData(homePageRecycleView, getContext(),0,"Home");
        }else {
            MainActivity.drawer.setDrawerLockMode(1);
            Toast.makeText(getContext(),"No internet connect",Toast.LENGTH_SHORT).show();
            categoryRecyclerView.setVisibility(View.GONE);
            homePageRecycleView.setVisibility(View.GONE);
            Glide.with(getContext()).load(R.drawable.caveman).into(NoInternetConnect);
            NoInternetConnect.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);


        }
    }


}
