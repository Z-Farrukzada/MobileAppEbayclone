package com.zaurfarrukhzada.myebayclone;

public class HorizontalScrollProductModel {

    private String productID;
    private String productImage;
    private String productDescription;
    private String productTitle;
    private String productPrice;


    public HorizontalScrollProductModel(String productID, String productImage, String productDescription, String productTitle, String productPrice) {
        this.productID = productID;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productTitle = productTitle;
        this.productPrice = productPrice;

    }


    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
