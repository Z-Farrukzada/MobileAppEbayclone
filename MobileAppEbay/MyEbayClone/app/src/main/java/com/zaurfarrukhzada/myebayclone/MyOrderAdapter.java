package com.zaurfarrukhzada.myebayclone;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private List<MyOrderItemModel> myOrderItemModelList;
    private LinearLayout rateNowLayout;

    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout,parent,false);
      return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {
               int resource = myOrderItemModelList.get(position).getProductImage();
               String title = myOrderItemModelList.get(position).getProductTitle();
               String deliveryStatus = myOrderItemModelList.get(position).getDeliveryStatus();
               int rating = myOrderItemModelList.get(position).getRating();
               holder.setData(resource,title,deliveryStatus,rating);
    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder{

        private ImageView productImage;
        private ImageView deliveryIndicate;
        private TextView productTitle;
        private TextView deliveryStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_order_image);
            deliveryIndicate = itemView.findViewById(R.id.order_delivery_indicator);
            productTitle = itemView.findViewById(R.id.product_title_order);
            deliveryStatus =itemView.findViewById(R.id.order_delivery_date);
            rateNowLayout =itemView.findViewById(R.id.rate_now_container);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderDetailsIntent = new Intent(itemView.getContext(),OrderDetailActivity.class);
                    itemView.getContext().startActivity(orderDetailsIntent);
                }
            });
        }
        private void setData(int resource,String title,String deliveryStatusText,int rating){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(deliveryStatusText.equals("Cancelled")) {
                deliveryIndicate.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorRed)));
            }else{
                deliveryIndicate.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.successGREEN)));
            }
            deliveryStatus.setText(deliveryStatusText);

            /////RATING LAYOUT START /////
            setRating(rating);
            for(int x=0 ; x<rateNowLayout.getChildCount() ; x++){
                final int starPosition=x;
                rateNowLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRating(starPosition);
                    }
                });

            }


            //RATING LAYOUT END/////
        }
        private void setRating(int starPosition) {
            for(int x=0;x<rateNowLayout.getChildCount();x++){
                ImageView starBtn =(ImageView) rateNowLayout.getChildAt(x);
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if(x <= starPosition){
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }
    }
}
