package com.zaurfarrukhzada.myebayclone;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

     List<HorizontalScrollProductModel> horizontalScrollProductModelList;

    public GridProductLayoutAdapter(List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    @Override
     public int getCount() {
         return horizontalScrollProductModelList.size();
     }

     @Override
     public Object getItem(int position) {
         return null;
     }

     @Override
     public long getItemId(int position) {
         return 0;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(parent.getContext(),ProductDetailActivity.class);
                    parent.getContext().startActivity(productDetailsIntent);

                }
            });


            ImageView productImage =view.findViewById(R.id.horizontal_v_product_image);
            TextView productTitle =view.findViewById(R.id.horizontal_v_product_title);
            TextView productDescription =view.findViewById(R.id.horizontal_v_product_description);
            TextView productPrice =view.findViewById(R.id.horizontal_v_product_price);

            Glide.with(parent.getContext()).load(horizontalScrollProductModelList.get(position).getProductImage())
                    .apply(new RequestOptions().placeholder(R.drawable.home)).into(productImage);
            productTitle.setText(horizontalScrollProductModelList.get(position).getProductTitle());
            productDescription.setText(horizontalScrollProductModelList.get(position).getProductDescription());
            productPrice.setText("US "+ horizontalScrollProductModelList.get(position).getProductPrice()+" $");

        }else{
         view=convertView;
        }
        return view;
     }
 }
