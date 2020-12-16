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

import static com.zaurfarrukhzada.myebayclone.DBdata.lists;
import static com.zaurfarrukhzada.myebayclone.DBdata.loadCategoriesName;
import static com.zaurfarrukhzada.myebayclone.DBdata.loadFragmentData;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecycleView;
    private HomePageAdapter homePageAdapter;
    private List<HomePageModel> homePageModelFakeList = new ArrayList<>();


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

        homePageAdapter = new HomePageAdapter(homePageModelFakeList);
        categoryRecycleView.setAdapter(homePageAdapter);
        ////homepage fake list\\\\\
        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add(new SliderModel("null"));
        sliderModelFakeList.add(new SliderModel("null"));
        sliderModelFakeList.add(new SliderModel("null"));
        sliderModelFakeList.add(new SliderModel("null"));
        sliderModelFakeList.add(new SliderModel("null"));

        List<HorizontalScrollProductModel> horizontalScrollProductFakeModelList = new ArrayList<>();
        horizontalScrollProductFakeModelList.add(new HorizontalScrollProductModel("","","","",""));
        horizontalScrollProductFakeModelList.add(new HorizontalScrollProductModel("","","","",""));
        horizontalScrollProductFakeModelList.add(new HorizontalScrollProductModel("","","","",""));
        horizontalScrollProductFakeModelList.add(new HorizontalScrollProductModel("","","","",""));
        horizontalScrollProductFakeModelList.add(new HorizontalScrollProductModel("","","","",""));
        horizontalScrollProductFakeModelList.add(new HorizontalScrollProductModel("","","","",""));
        horizontalScrollProductFakeModelList.add(new HorizontalScrollProductModel("","","","",""));

        homePageModelFakeList.add(new HomePageModel(0,sliderModelFakeList));
        homePageModelFakeList.add(new HomePageModel(1,""));
        homePageModelFakeList.add(new HomePageModel(2,"","#ffffff",horizontalScrollProductFakeModelList,new ArrayList<WishlistModel>()));
        homePageModelFakeList.add(new HomePageModel(3,"","#ffffff",horizontalScrollProductFakeModelList));
        ////homepage fake list\\\\\
        int listPosition = 0;
        for(int x=0; x < loadCategoriesName.size() ; x++){
            if(loadCategoriesName.get(x).equals(title.toUpperCase())){
                listPosition = x;
            }
        }
        if(listPosition == 0){
            loadCategoriesName.add(title.toUpperCase());
            lists.add(new ArrayList<HomePageModel>());

            loadFragmentData(categoryRecycleView, this,loadCategoriesName.size() - 1,title);
        }
        else{
            homePageAdapter = new HomePageAdapter(lists.get(listPosition));

        }

        categoryRecycleView.setAdapter(homePageAdapter);
        homePageAdapter.notifyDataSetChanged();

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