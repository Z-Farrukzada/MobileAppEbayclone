package com.zaurfarrukhzada.myebayclone;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HorizontalScrollProductAdapter extends RecyclerView.Adapter<HorizontalScrollProductAdapter.ViewHolder> {

    private List<HorizontalScrollProductModel> horizontalScrollProductModelList;

    public HorizontalScrollProductAdapter(List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    @NonNull
    @Override
    public HorizontalScrollProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalScrollProductAdapter.ViewHolder holder, int position) {
         String resource =horizontalScrollProductModelList.get(position).getProductImage();
         String title =horizontalScrollProductModelList.get(position).getProductTitle();
        String description =horizontalScrollProductModelList.get(position).getProductDescription();
        String price =horizontalScrollProductModelList.get(position).getProductPrice();

        holder.setData(resource,title,description,price);

    }

    @Override
    public int getItemCount() {
        if(horizontalScrollProductModelList.size() > 8){
            return 8;
        }else{
            return horizontalScrollProductModelList.size();
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.horizontal_v_product_image);
            productDescription= itemView.findViewById(R.id.horizontal_v_product_description);
            productPrice=itemView.findViewById(R.id.horizontal_v_product_price);
            productTitle=itemView.findViewById(R.id.horizontal_v_product_title);



        }
        private void setData(String resource,String title,String description,String price){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.camera)).into(productImage);
            productPrice.setText("US " +price+"$");
            productDescription.setText(description);
            productTitle.setText(title);
            if(!title.equals("")) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);

                    }
                });
            }
        }

    }

}
