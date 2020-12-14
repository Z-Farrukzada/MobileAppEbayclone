package com.zaurfarrukhzada.myebayclone;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private List<WishlistModel> wishlistModelList;
    private Boolean wishList;

    public WishListAdapter(List<WishlistModel> wishlistModelList,Boolean wishList) {
        this.wishlistModelList = wishlistModelList;
        this.wishList = wishList;
    }

    @NonNull
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);
       return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.ViewHolder holder, int position) {
        int resource = wishlistModelList.get(position).getWishListProductImage();
        String title =wishlistModelList.get(position).getWishListProductTitle();
        int freeCouPens =wishlistModelList.get(position).getWishListProductFreeCouPens();
        String rating =wishlistModelList.get(position).getRating();
        int totalRatings =wishlistModelList.get(position).getTotalRatings();
        String productPriceText =wishlistModelList.get(position).getWishListProductPrice();
        String productOdlPrice =wishlistModelList.get(position).getWishListProductOldPrice();
        String paymentMethod=wishlistModelList.get(position).getWishListPaymentMethod();
        
       holder.setData(resource,title,freeCouPens,rating,totalRatings,productPriceText,productOdlPrice,paymentMethod);
    }

    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCouPens;
        private ImageView CouPenIcon;
        private TextView productPrice;
        private View cutPriceDivider;
        private TextView productOldPrice;
        private TextView paymentMethod;
        private TextView ratings;
        private TextView totalRatings;
        private ImageButton deleteProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.wishlist_product_image);
            productTitle =itemView.findViewById(R.id.wishlist_product_title);
            freeCouPens = itemView.findViewById(R.id.wishlist_free_coupen);
            CouPenIcon =itemView.findViewById(R.id.wishlist_product_coupen_image);
            ratings = itemView.findViewById(R.id.tv_product_rating_count);
            totalRatings =itemView.findViewById(R.id.wishlist_total_ratings);
            productPrice = itemView.findViewById(R.id.wishlist_product_price);
            productOldPrice=itemView.findViewById(R.id.wishlist_old_price);
            paymentMethod = itemView.findViewById(R.id.wishlist_payment_method);
            deleteProduct =itemView.findViewById(R.id.wishlist_delete_btn);
            cutPriceDivider =itemView.findViewById(R.id.wishlist_divider_price);
        }
        private void setData(int resource, String title, int freeCouPensNo, String averageRating, int totalRatingsNo, String productPriceText, String oldPrice, String payMethod){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(freeCouPensNo != 0){
                CouPenIcon.setVisibility(View.VISIBLE);
                if(freeCouPensNo == 1){
                    freeCouPens.setText("free "+freeCouPensNo+" coupen");
                }else{
                    freeCouPens.setText("free "+freeCouPensNo+" coupens");
                }
            }
            else{
                CouPenIcon.setVisibility(View.INVISIBLE);
                freeCouPens.setVisibility(View.INVISIBLE);
            }
            ratings.setText(averageRating);
            totalRatings.setText(totalRatingsNo+"(ratings)");
            productPrice.setText(productPriceText);
            productOldPrice.setText(oldPrice);
            paymentMethod.setText(payMethod);

            if(wishList){
                deleteProduct.setVisibility(View.VISIBLE);
            }else{
                deleteProduct.setVisibility(View.GONE);
            }

            deleteProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),"Delete",Toast.LENGTH_LONG).show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailIntent = new Intent(itemView.getContext(),ProductDetailActivity.class);
                    itemView.getContext().startActivity(productDetailIntent);
                }
            });
        }
    }
}
