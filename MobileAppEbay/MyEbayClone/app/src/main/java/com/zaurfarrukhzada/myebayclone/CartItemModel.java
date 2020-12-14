package com.zaurfarrukhzada.myebayclone;

public class CartItemModel {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT =1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //Cart Item Start

    private int productImage;
    private String productTitle;
    private int freeCouPens;
    private String productPrice;
    private String CutPrice;
    private int productQuantity;
    private int OffApplied;
    private int CoupApplied;


    public CartItemModel(int type, int productImage, String productTitle, int freeCouPens, String productPrice, String cutPrice,
                         int productQuantity, int offApplied, int coupApplied) {
        this.type = type;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCouPens = freeCouPens;
        this.productPrice = productPrice;
        CutPrice = cutPrice;
        this.productQuantity = productQuantity;
        OffApplied = offApplied;
        CoupApplied = coupApplied;
    }



    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getFreeCouPens() {
        return freeCouPens;
    }

    public void setFreeCouPens(int freeCouPens) {
        this.freeCouPens = freeCouPens;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCutPrice() {
        return CutPrice;
    }

    public void setCutPrice(String cutPrice) {
        CutPrice = cutPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getOffApplied() {
        return OffApplied;
    }

    public void setOffApplied(int offApplied) {
        OffApplied = offApplied;
    }

    public int getCoupApplied() {
        return CoupApplied;
    }

    public void setCoupApplied(int coupApplied) {
        CoupApplied = coupApplied;
    }
    //Cart Item End

    //Cart total start

    private String totalItemsPrice;
    private String totalItems;
    private String DeliveryPrice;
    private String saveAmount;
    private String totalAmount;


    public CartItemModel(int type, String totalItemsPrice, String totalItems, String deliveryPrice, String saveAmount, String totalAmount) {
        this.type = type;
        this.totalItemsPrice = totalItemsPrice;
        this.totalItems = totalItems;
        this.DeliveryPrice = deliveryPrice;
        this.saveAmount = saveAmount;
        this.totalAmount = totalAmount;
    }

    public String getTotalItemsPrice() {
        return totalItemsPrice;
    }

    public void setTotalItemsPrice(String totalItemsPrice) {
        this.totalItemsPrice = totalItemsPrice;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getDeliveryPrice() {
        return DeliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        DeliveryPrice = deliveryPrice;
    }

    public String getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(String saveAmount) {
        this.saveAmount = saveAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    //Cart total end
}
