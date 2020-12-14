package com.zaurfarrukhzada.myebayclone;

public class WishlistModel  {

    private int wishListProductImage;
    private String WishListProductTitle;
    private int WishListProductFreeCouPens;
    private String rating;
    private int totalRatings;
    private  String WishListProductPrice;
    private  String WishListProductOldPrice;
    private String WishListPaymentMethod;

    public WishlistModel(int wishListProductImage, String wishListProductTitle, int wishListProductFreeCouPens, String rating, int totalRatings, String wishListProductPrice, String wishListProductOldPrice, String wishListPaymentMethod) {
        this.wishListProductImage = wishListProductImage;
        WishListProductTitle = wishListProductTitle;
        WishListProductFreeCouPens = wishListProductFreeCouPens;
        this.rating = rating;
        this.totalRatings = totalRatings;
        WishListProductPrice = wishListProductPrice;
        WishListProductOldPrice = wishListProductOldPrice;
        WishListPaymentMethod = wishListPaymentMethod;
    }

    public int getWishListProductImage() {
        return wishListProductImage;
    }

    public void setWishListProductImage(int wishListProductImage) {
        this.wishListProductImage = wishListProductImage;
    }

    public String getWishListProductTitle() {
        return WishListProductTitle;
    }

    public void setWishListProductTitle(String wishListProductTitle) {
        WishListProductTitle = wishListProductTitle;
    }

    public int getWishListProductFreeCouPens() {
        return WishListProductFreeCouPens;
    }

    public void setWishListProductFreeCouPens(int wishListProductFreeCouPens) {
        WishListProductFreeCouPens = wishListProductFreeCouPens;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public String getWishListProductPrice() {
        return WishListProductPrice;
    }

    public void setWishListProductPrice(String wishListProductPrice) {
        WishListProductPrice = wishListProductPrice;
    }

    public String getWishListProductOldPrice() {
        return WishListProductOldPrice;
    }

    public void setWishListProductOldPrice(String wishListProductOldPrice) {
        WishListProductOldPrice = wishListProductOldPrice;
    }

    public String getWishListPaymentMethod() {
        return WishListPaymentMethod;
    }

    public void setWishListPaymentMethod(String wishListPaymentMethod) {
        WishListPaymentMethod = wishListPaymentMethod;
    }
}
