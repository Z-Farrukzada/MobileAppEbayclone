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


public class MyOrderFragment extends Fragment {

    public MyOrderFragment() {
        // Required empty public constructor
    }

    private RecyclerView myOrderRecycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
        myOrderRecycleView = view.findViewById(R.id.my_order_recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrderRecycleView.setLayoutManager(linearLayoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.product,3,"Samsung Note 10 16gb Ram (BLACK)","Delivery on Friday , 10th Jan 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.product,0,"Samsung Note 10 32gb Ram (BLUE)","Delivery on Friday , 17th Jan 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.product,4,"Samsung Note 10 64gb Ram (RED)","Delivery on Friday , 24th Jan 2020"));


        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrderRecycleView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();
        return view;
    }
}