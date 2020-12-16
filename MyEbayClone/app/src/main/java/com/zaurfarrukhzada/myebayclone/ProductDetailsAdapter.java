package com.zaurfarrukhzada.myebayclone;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private  int tabCount;
    private String productDescription;
    private String productOther;
    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductDetailsAdapter(@NonNull FragmentManager fm, int tabCount, String productDescription, String productOther, List<ProductSpecificationModel> productSpecificationModelList) {
        super(fm);
        this.tabCount = tabCount;
        this.productDescription = productDescription;
        this.productOther = productOther;
        this.productSpecificationModelList = productSpecificationModelList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
              ProductDescriptionFragment productDescriptionFragment= new ProductDescriptionFragment();
              productDescriptionFragment.body = productDescription;
              return productDescriptionFragment;
            case 1:
                ProductSpecificationFragment productSpecificationFragment = new ProductSpecificationFragment();
                productSpecificationFragment.productSpecificationModelList = productSpecificationModelList;
                return productSpecificationFragment;
            case 2:
                ProductDescriptionFragment productDescriptionFragment2= new ProductDescriptionFragment();
                productDescriptionFragment2.body = productOther;
                return productDescriptionFragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
