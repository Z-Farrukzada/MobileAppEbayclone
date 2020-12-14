package com.zaurfarrukhzada.myebayclone;


import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderModel> sliderModelList;
    private ViewPager2 viewPager2;
    public SliderAdapter(List<SliderModel> sliderModelList, ViewPager2 viewPager2){
        this.sliderModelList=sliderModelList;
        this.viewPager2=viewPager2;
    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout,parent,false);

        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
           holder.setImage(sliderModelList.get(position),position);
    }

    @Override
    public int getItemCount() {
       return sliderModelList.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{

        private ImageView banner;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            banner =itemView.findViewById(R.id.banner_slide);


        }

        void setImage(SliderModel sliderModel, int position)
        {
            Glide.with(itemView.getContext()).load(sliderModelList.get(position).getBanner())
                    .apply(new RequestOptions().placeholder(R.drawable.home)).into(banner);

        }
    }
}
