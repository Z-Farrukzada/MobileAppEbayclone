package com.zaurfarrukhzada.myebayclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyRewardsFragment extends Fragment {




    public MyRewardsFragment() {
        // Required empty public constructor
    }


    private RecyclerView myRewardsRecycleView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_rewards, container, false);
        myRewardsRecycleView = view.findViewById(R.id.my_rewards_recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRewardsRecycleView.setLayoutManager(linearLayoutManager);

        List<RewardsModel> rewardsModelList = new ArrayList<>();

        rewardsModelList.add(new RewardsModel("Discount coupen","till 13th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
        rewardsModelList.add(new RewardsModel("Cashback","till 15th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
        rewardsModelList.add(new RewardsModel("Buy 1 Get 1 Free","till 18th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
        rewardsModelList.add(new RewardsModel("Discount coupen","till 13th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
        rewardsModelList.add(new RewardsModel("Cashback","till 15th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));
        rewardsModelList.add(new RewardsModel("Buy 1 Get 1 Free","till 18th,JAN 2020","Get 20% off  any product  above  US $50  and below  US $2000."));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardsModelList,false);
        myRewardsRecycleView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        return view;
    }
}