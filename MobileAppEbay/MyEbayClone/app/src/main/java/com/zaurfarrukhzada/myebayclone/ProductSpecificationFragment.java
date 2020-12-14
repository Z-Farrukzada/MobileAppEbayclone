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


public class ProductSpecificationFragment extends Fragment {

    private RecyclerView productSpecificationRecycleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_product_specification, container, false);
        productSpecificationRecycleView =view.findViewById(R.id.product_specification_recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productSpecificationRecycleView.setLayoutManager(linearLayoutManager);

        List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();
        productSpecificationModelList.add(new ProductSpecificationModel(0,"General"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","16gb"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","16gb"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","16gb"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","16gb"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","16gb"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"Display"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","16gb"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","16gb"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","16gb"));


        ProductSpecificationAdapter productSpecificationAdapter = new ProductSpecificationAdapter(productSpecificationModelList);
        productSpecificationRecycleView.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();
        return view;
    }
}