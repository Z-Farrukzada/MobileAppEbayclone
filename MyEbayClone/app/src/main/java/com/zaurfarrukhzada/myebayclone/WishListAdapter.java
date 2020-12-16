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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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
        String resource = wishlistModelList.get(position).getWishListProductImage();
        String title =wishlistModelList.get(position).getWishListProductTitle();
        long freeCouPens =wishlistModelList.get(position).getWishListProductFreeCouPens();
        String rating =wishlistModelList.get(position).getRating();
        long totalRatings =wishlistModelList.get(position).getTotalRatings();
        String productPriceText =wishlistModelList.get(position).getWishListProductPrice();
        String productOdlPrice =wishlistModelList.get(position).getWishListProductOldPrice();
        boolean COD=wishlistModelList.get(position).isCOD();
        
       holder.setData(resource,title,freeCouPens,rating,totalRatings,productPriceText,productOdlPrice,COD);
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
        private void setData(String resource, String title, long freeCouPensNo, String averageRating, long totalRatingsNo, String productPriceText, String oldPrice, boolean COD){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.home)).into(productImage);
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
            totalRatings.setText("("+totalRatingsNo+")(ratings)");
            productPrice.setText("US "+ productPriceText+" $");
            productOldPrice.setText("US "+ oldPrice +" $");
            if(COD){
                paymentMethod.setVisibility(View.VISIBLE);
            }else{
               paymentMethod.setVisibility(View.GONE);
            }

            if(wishList){
                deleteProduct.setVisibility(View.VISIBLE);
            }else{
                deleteProduct.setVisibility(View.INVISIBLE);
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
