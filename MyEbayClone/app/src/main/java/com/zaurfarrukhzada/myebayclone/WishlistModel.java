package com.zaurfarrukhzada.myebayclone;

public class WishlistModel  {

    private String wishListProductImage;
    private String WishListProductTitle;
    private long WishListProductFreeCouPens;
    private String rating;
    private long totalRatings;
    private  String WishListProductPrice;
    private  String WishListProductOldPrice;
    private boolean COD;

    public WishlistModel(String wishListProductImage, String wishListProductTitle, long wishListProductFreeCouPens, String rating, long totalRatings, String wishListProductPrice, String wishListProductOldPrice, boolean COD) {
        this.wishListProductImage = wishListProductImage;
        WishListProductTitle = wishListProductTitle;
        WishListProductFreeCouPens = wishListProductFreeCouPens;
        this.rating = rating;
        this.totalRatings = totalRatings;
        WishListProductPrice = wishListProductPrice;
        WishListProductOldPrice = wishListProductOldPrice;
        this.COD = COD;
    }

    public String getWishListProductImage() {
        return wishListProductImage;
    }

    public void setWishListProductImage(String wishListProductImage) {
        this.wishListProductImage = wishListProductImage;
    }

    public String getWishListProductTitle() {
        return WishListProductTitle;
    }

    public void setWishListProductTitle(String wishListProductTitle) {
        WishListProductTitle = wishListProductTitle;
    }

    public long getWishListProductFreeCouPens() {
        return WishListProductFreeCouPens;
    }

    public void setWishListProductFreeCouPens(long wishListProductFreeCouPens) {
        WishListProductFreeCouPens = wishListProductFreeCouPens;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public long getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(long totalRatings) {
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

    public boolean isCOD() {
        return COD;
    }

    public void setCOD(boolean COD) {
        this.COD = COD;
    }
}
