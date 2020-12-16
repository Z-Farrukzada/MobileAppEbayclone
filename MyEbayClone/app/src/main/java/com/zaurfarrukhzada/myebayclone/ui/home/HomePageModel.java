package com.zaurfarrukhzada.myebayclone.ui.home;

import com.zaurfarrukhzada.myebayclone.HorizontalScrollProductModel;
import com.zaurfarrukhzada.myebayclone.SliderModel;
import com.zaurfarrukhzada.myebayclone.WishlistModel;

import java.util.List;

public class HomePageModel {

    public static final  int BANNER_SLIDER = 0;
    public static  final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW=2;
    public static final int GRID_PRODUCT_VIEW=3;
    private int type;
    private String backgroundColor;

    /////Banner Slider ////
    private List<SliderModel> sliderModelList;


    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

////Banner Slider///

    //STRIP ADD///

    private  String resource;
    public HomePageModel(int type, String resource) {
        this.type = type;
        this.resource = resource;


    }


    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }

    //STRIP ADD//




    private String title;
    private List<HorizontalScrollProductModel> horizontalScrollProductModelList;
    //HORIZONTAL PRODUCT   START
    private  List<WishlistModel> viewAllProductModelList;

    public HomePageModel(int type, String title, String backgroundColor , List<HorizontalScrollProductModel> horizontalScrollProductModelList, List<WishlistModel> viewAllProductModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
        this.viewAllProductModelList=viewAllProductModelList;
    }

    public List<WishlistModel> getViewAllProductModelList() {
        return viewAllProductModelList;
    }

    public void setViewAllProductModelList(List<WishlistModel> viewAllProductModelList) {
        this.viewAllProductModelList = viewAllProductModelList;
    }
    //HORIZONTAL PRODUCT END

    //GRID PRODUCT   START
    public HomePageModel(int type, String title, String backgroundColor , List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<HorizontalScrollProductModel> getHorizontalScrollProductModelList() {
        return horizontalScrollProductModelList;
    }
    public void setHorizontalScrollProductModelList(List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }
    //GRID PRODUCT END


}
