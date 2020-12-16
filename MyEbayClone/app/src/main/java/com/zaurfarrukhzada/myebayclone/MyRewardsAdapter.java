package com.zaurfarrukhzada.myebayclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRewardsAdapter extends RecyclerView.Adapter<MyRewardsAdapter.ViewHolder> {

    private List<RewardsModel> rewardsModelList;
    private Boolean miniLayout =false;

    public MyRewardsAdapter(List<RewardsModel> rewardsModelList,Boolean miniLayout) {
        this.rewardsModelList = rewardsModelList;
        this.miniLayout = miniLayout;
    }

    @NonNull
    @Override
    public MyRewardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(miniLayout){
             view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_rewards_layout,parent,false);

        }else{
             view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout,parent,false);

        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRewardsAdapter.ViewHolder holder, int position) {
              String title  = rewardsModelList.get(position).getTitle();
              String rewardsBody = rewardsModelList.get(position).getRewardsBody();
              String rewardsDate = rewardsModelList.get(position).getRewardsDate();

              holder.setData(title,rewardsDate,rewardsBody);
    }

    @Override
    public int getItemCount() {
        return rewardsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView rewardsDate;
        private TextView rewardsBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rewards_coupen_title);
            rewardsBody = itemView.findViewById(R.id.rewards_coupen_body);
            rewardsDate = itemView.findViewById(R.id.rewards_coupen_date);

        }
        private void setData(String titleText,String rewardsDateText,String rewardsBodyText){
            title.setText(titleText);
            rewardsDate.setText(rewardsDateText);
            rewardsBody.setText(rewardsBodyText);
            if(miniLayout){
              itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ProductDetailActivity.couPenTitle.setText(titleText);
                      ProductDetailActivity.couPenBody.setText(rewardsBodyText);
                      ProductDetailActivity.couPenDate.setText(rewardsDateText);
                      ProductDetailActivity.showDialog();
                  }
              });
            }
            else{

            }
        }
    }
}
