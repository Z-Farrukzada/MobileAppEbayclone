package com.zaurfarrukhzada.myebayclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class DeliveryActivity extends AppCompatActivity {

    private RecyclerView deliveryRecycleView;
    private Button changeOrAddNewAddress;
    public static final int SELECT_ADDRESS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");


        deliveryRecycleView = findViewById(R.id.delivery_recycle_view);
        changeOrAddNewAddress=findViewById(R.id.add_or_change_address_btn);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        deliveryRecycleView.setLayoutManager(linearLayoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.product,"Samsung Note 10 16gb Ram (BLACK)",2,"US $150","US $200",1,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.product,"Samsung Note 10 8gb Ram (BLUE)",0,"US $170","US $220",1,1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.product,"Samsung Note 10 32gb Ram (WHITE)",1,"US $180","US $240",1,2,0));
        cartItemModelList.add(new CartItemModel(1,"Price (3 Items)","US $500","Free","Your Saved US $500","US $200"));


        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryRecycleView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeOrAddNewAddress.setVisibility(View.VISIBLE);
        changeOrAddNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressIntent  = new Intent(DeliveryActivity.this,MyAddressActivity.class);
                myAddressIntent.putExtra("MODE",SELECT_ADDRESS);
                startActivity(myAddressIntent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
         if(id == android.R.id.home){
             finish();
             return true;
         }


        return super.onOptionsItemSelected(item);
    }

}