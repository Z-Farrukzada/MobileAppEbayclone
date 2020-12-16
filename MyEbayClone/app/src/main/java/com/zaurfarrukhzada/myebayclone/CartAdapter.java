package com.zaurfarrukhzada.myebayclone;

import android.app.Dialog;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }
    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case CartItemModel.CART_ITEM:
              View CartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
              return new CartItemViewHolder(CartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View CartTotalAmountView =LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_product_price_layout,parent,false);
              return new CartTotalAmountViewHolder(CartTotalAmountView);
            default:
                return null;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (cartItemModelList.get(position).getType()){
            case CartItemModel.CART_ITEM:
                    int resource = cartItemModelList.get(position).getProductImage();
                    String title =cartItemModelList.get(position).getProductTitle();
                    int freeCouPens =cartItemModelList.get(position).getFreeCouPens();
                    String productPrice =cartItemModelList.get(position).getProductPrice();
                    String cutPrice =cartItemModelList.get(position).getCutPrice();
                    int offApplied =cartItemModelList.get(position).getOffApplied();
                ((CartItemViewHolder)holder).setItemDetails(resource,title,freeCouPens,productPrice,cutPrice,offApplied);
                break;
            case CartItemModel.TOTAL_AMOUNT:
                    String totalItems = cartItemModelList.get(position).getTotalItems();
                    String totalItemPrice = cartItemModelList.get(position).getTotalItemsPrice();
                    String deliveryPrice = cartItemModelList.get(position).getDeliveryPrice();
                    String savedAmount = cartItemModelList.get(position).getSaveAmount();
                    String totalAmount = cartItemModelList.get(position).getTotalAmount();
                ((CartTotalAmountViewHolder)holder).SetTotalAmount(totalItems,totalItemPrice,deliveryPrice,savedAmount,totalAmount);
                break;
            default:return;
        }
    }
    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }
    class  CartItemViewHolder extends  RecyclerView.ViewHolder{

        private ImageView productImage;
        private ImageView freeCouPenIcon;
        private TextView productTitle;
        private TextView freeCouPen;
        private TextView productPrice;
        private TextView cutPrice;
        private TextView offerApplied;
        private TextView coupApplied;
        private TextView productQuantity;


        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            productTitle=itemView.findViewById(R.id.product_cart_title);
            freeCouPen =itemView.findViewById(R.id.tv_free_coupen);
            freeCouPenIcon =itemView.findViewById(R.id.cart_free_coupon);
            productPrice=itemView.findViewById(R.id.cart_product_price);
            cutPrice=itemView.findViewById(R.id.cart_old_price);
            offerApplied=itemView.findViewById(R.id.offers_applied);
            coupApplied=itemView.findViewById(R.id.cart_coupen_applied);
            productQuantity=itemView.findViewById(R.id.cart_quantity_count);
        }
        private void setItemDetails(int resource,String title,int freeCouPensNo,String productPriceText,String cutPriceText,int offerAppliedNo){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(freeCouPensNo > 0){
                if(freeCouPensNo == 1){
                    freeCouPen.setVisibility(View.VISIBLE);
                    freeCouPenIcon.setVisibility(View.VISIBLE);

                    freeCouPen.setText("free "+freeCouPensNo+" Coupen");
                }else{
                    freeCouPen.setText("free "+freeCouPensNo+" Coupens");
                }
            }else{
                freeCouPen.setVisibility(View.INVISIBLE);
                freeCouPenIcon.setVisibility(View.INVISIBLE);
            }
            productPrice.setText(productPriceText);
            cutPrice.setText(cutPriceText);
            if(offerAppliedNo > 0){
                offerApplied.setVisibility(View.VISIBLE);
                offerApplied.setText(offerAppliedNo+" Offers Applied");
            }else{
                offerApplied.setVisibility(View.INVISIBLE);
            }
             productQuantity.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Dialog quantityDialog = new Dialog(itemView.getContext());
                     quantityDialog.setContentView(R.layout.quantity_dialog);
                     quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                     quantityDialog.setCancelable(false);
                     EditText quantityNo = quantityDialog.findViewById(R.id.quantity_number_input);
                     Button cancelBtn = quantityDialog.findViewById(R.id.dialog_cancel_btn);
                     Button OkBtn =quantityDialog.findViewById(R.id.dialog_ok_btn);

                     cancelBtn.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                            quantityDialog.dismiss();
                         }
                     });

                     OkBtn.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             productQuantity.setText("Qty:"+ quantityNo.getText());
                             quantityDialog.dismiss();
                         }
                     });
                     quantityDialog.show();

                 }
             });

        }
    }
    class  CartTotalAmountViewHolder extends RecyclerView.ViewHolder{

        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;

        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
            totalItems =itemView.findViewById(R.id.total_items);
            totalItemPrice=itemView.findViewById(R.id.total_items_price);
            deliveryPrice=itemView.findViewById(R.id.delivery_price);
            totalAmount=itemView.findViewById(R.id.total_amount_price);
            savedAmount =itemView.findViewById(R.id.saved_amount);
        }
        private void SetTotalAmount(String totalItemsText,String totalItemPriceText, String deliveryPriceText, String totalAmountText,String savedAmountText){
            totalItems.setText(totalItemsText);
            totalItemPrice.setText(totalItemPriceText);
            deliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }


    }
}
