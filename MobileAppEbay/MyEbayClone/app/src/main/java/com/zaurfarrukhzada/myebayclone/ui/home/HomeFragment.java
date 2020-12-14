package com.zaurfarrukhzada.myebayclone.ui.home;

import android.content.Context;
import android.net.ConnectivityManager;
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
import com.zaurfarrukhzada.myebayclone.R;
import com.zaurfarrukhzada.myebayclone.SliderAdapter;
import com.zaurfarrukhzada.myebayclone.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.zaurfarrukhzada.myebayclone.DBdata.categoryModelList;
import static com.zaurfarrukhzada.myebayclone.DBdata.firebaseFirestore;
import static com.zaurfarrukhzada.myebayclone.DBdata.homePageModelList;
import static com.zaurfarrukhzada.myebayclone.DBdata.loadCategories;
import static com.zaurfarrukhzada.myebayclone.DBdata.loadFragmentData;


public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView homePageRecycleView;
    private HomePageAdapter homePageAdapter;
    private ImageView NoInternetConnect;



    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        NoInternetConnect = view.findViewById(R.id.no_internet_connection);
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected() == true) {
            NoInternetConnect.setVisibility(View.GONE);

            categoryRecyclerView=view.findViewById(R.id.category_recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            categoryRecyclerView.setLayoutManager(layoutManager);
            categoryAdapter = new CategoryAdapter(categoryModelList);
            categoryRecyclerView.setAdapter(categoryAdapter);
            if(categoryModelList.size() == 0){
                loadCategories(categoryAdapter,getContext());
            }else{
                categoryAdapter.notifyDataSetChanged();
            }

            homePageRecycleView =view.findViewById(R.id.Home_Page_Recycle_View);
            LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
            testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            homePageRecycleView.setLayoutManager(testingLayoutManager);

            homePageAdapter  =new HomePageAdapter(homePageModelList);
            homePageRecycleView.setAdapter(homePageAdapter);

            if(homePageModelList.size() == 0){
                loadFragmentData(homePageAdapter,getContext());
            }else{
                categoryAdapter.notifyDataSetChanged();
            }

        }else{
            Glide.with(this).load(R.drawable.caveman).into(NoInternetConnect);
            NoInternetConnect.setVisibility(View.VISIBLE);



        }


        ////////////////////////
        return view;
        };

    }
