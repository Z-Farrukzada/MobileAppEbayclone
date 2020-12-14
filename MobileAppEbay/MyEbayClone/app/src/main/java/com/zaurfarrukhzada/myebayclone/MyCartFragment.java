package com.zaurfarrukhzada.myebayclone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRecycleView;
    private Button ContinueBtn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartItemsRecycleView = view.findViewById(R.id.cart_items_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        cartItemsRecycleView.setLayoutManager(linearLayoutManager);
        ContinueBtn = view.findViewById(R.id.cart_continue_btn);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.product,"Samsung Note 10 16gb Ram (BLACK)",2,"US $150","US $200",1,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.product,"Samsung Note 10 8gb Ram (BLUE)",0,"US $170","US $220",1,1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.product,"Samsung Note 10 32gb Ram (WHITE)",1,"US $180","US $240",1,2,0));
        cartItemModelList.add(new CartItemModel(1,"Price (3 Items)","US $500","Free","Your Saved US $500","US $200"));


        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecycleView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        ContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(getContext(),AddressActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });
        return view;
    }
}