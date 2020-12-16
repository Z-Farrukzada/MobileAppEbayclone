package com.zaurfarrukhzada.myebayclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.zaurfarrukhzada.myebayclone.DeliveryActivity.SELECT_ADDRESS;


public class MyAddressActivity extends AppCompatActivity {

    private RecyclerView myAddressRecycleView;
    private Button deliverBtn;
    private static AddressesAdapter addressesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Address");


        myAddressRecycleView = findViewById(R.id.addresses_recycleview);
        deliverBtn = findViewById(R.id.delivered_here_btn);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressRecycleView.setLayoutManager(linearLayoutManager);

        List<AddAddressModel> addAddressModelList =new ArrayList<>();
        addAddressModelList.add(new AddAddressModel("Zaur Farrukhzada","2345","Azerbaijan,Baku",true));
        addAddressModelList.add(new AddAddressModel("Zaur Farruk","5432","Azerbaijan,Baku",false));
        addAddressModelList.add(new AddAddressModel("Zaur Farruk1","5342","Azerbaijan,Baku",false));
        addAddressModelList.add(new AddAddressModel("Zaur Farruk2","2434","Azerbaijan,Baku",false));

        int mode = getIntent().getIntExtra("MODE",-1);
        if(mode == SELECT_ADDRESS){
            deliverBtn.setVisibility(View.VISIBLE);
        }else{
            deliverBtn.setVisibility(View.GONE);
        }
        addressesAdapter = new AddressesAdapter(addAddressModelList,mode);
        myAddressRecycleView.setAdapter(addressesAdapter);
        ((SimpleItemAnimator)myAddressRecycleView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressesAdapter.notifyDataSetChanged();

    }
    public static void refreshItem(int deSelect ,int Select){
           addressesAdapter.notifyItemChanged(deSelect);
           addressesAdapter.notifyItemChanged(Select);
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