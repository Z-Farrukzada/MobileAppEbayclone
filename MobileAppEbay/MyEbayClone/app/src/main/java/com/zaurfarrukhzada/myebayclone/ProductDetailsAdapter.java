package com.zaurfarrukhzada.myebayclone;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private  int tabCount;

    public ProductDetailsAdapter(@NonNull FragmentManager fm,int tabCount) {
        super(fm,tabCount);
        this.tabCount=tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
              ProductDescriptionFragment productDescriptionFragment= new ProductDescriptionFragment();
              return productDescriptionFragment;
            case 1:
                ProductSpecificationFragment productSpecificationFragment = new ProductSpecificationFragment();
                return productSpecificationFragment;
            case 2:
                ProductDescriptionFragment productDescriptionFragment2= new ProductDescriptionFragment();
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
