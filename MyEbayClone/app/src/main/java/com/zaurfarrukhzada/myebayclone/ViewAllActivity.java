package com.zaurfarrukhzada.myebayclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView viewAllRecycleView;
    private GridView  viewAllGridView;
    public  static  List<WishlistModel> wishlistModelList;
    public static List<HorizontalScrollProductModel> horizontalScrollProductModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewAllRecycleView=findViewById(R.id.viewAll_recycleview);
        viewAllGridView = findViewById(R.id.viewAll_grid_view);

        int layoutCode = getIntent().getIntExtra("layout_name",-1);

        if(layoutCode == 0 ) {
            viewAllRecycleView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            viewAllRecycleView.setLayoutManager(layoutManager);



            WishListAdapter wishListAdapter = new WishListAdapter(wishlistModelList, false);
            viewAllRecycleView.setAdapter(wishListAdapter);
            wishListAdapter.notifyDataSetChanged();
        }
        else if(layoutCode == 1) {
            viewAllGridView.setVisibility(View.VISIBLE);
            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalScrollProductModelList);
            viewAllGridView.setAdapter(gridProductLayoutAdapter);
            gridProductLayoutAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}