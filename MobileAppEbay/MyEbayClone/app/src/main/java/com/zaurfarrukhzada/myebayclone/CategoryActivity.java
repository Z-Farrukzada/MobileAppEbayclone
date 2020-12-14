package com.zaurfarrukhzada.myebayclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.zaurfarrukhzada.myebayclone.ui.home.HomePageAdapter;
import com.zaurfarrukhzada.myebayclone.ui.home.HomePageModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar =findViewById(R.id.my_toolbar);
         setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecycleView =findViewById(R.id.category_recyclerview);
        categoryRecycleView =findViewById(R.id.category_recycleview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecycleView.setLayoutManager(testingLayoutManager);
        List<HomePageModel>  homePageModelList = new ArrayList<>();
        HomePageAdapter adapter =new HomePageAdapter(homePageModelList);
        categoryRecycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search_icon) {
            //todo:search
            return true;
        }else if(id==android.R.id.home){
            finish();
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }


}