package com.zaurfarrukhzada.myebayclone;

import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.zaurfarrukhzada.myebayclone.DeliveryActivity.SELECT_ADDRESS;
import static com.zaurfarrukhzada.myebayclone.MyAccountFragment.MANAGE_ADDRESS;
import static com.zaurfarrukhzada.myebayclone.MyAddressActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> {

    private List<AddAddressModel> addAddressModelList;
    private int MODE;
    private  int preSelectedPosition = -1;

    public AddressesAdapter(List<AddAddressModel> addAddressModelList, int MODE) {
        this.addAddressModelList = addAddressModelList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
    public AddressesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addresses_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressesAdapter.ViewHolder holder, int position) {
         String username = addAddressModelList.get(position).getFullName();
         String userAddress = addAddressModelList.get(position).getAddress();
         String userPinCode =addAddressModelList.get(position).getPinCode();
         Boolean selected = addAddressModelList.get(position).getSelected();

         holder.setData(username,userAddress,userPinCode,selected,position);
    }

    @Override
    public int getItemCount() {
        return addAddressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullName;
        private TextView address;
        private TextView pinCode;
        private ImageView icon;
        private LinearLayout optionContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.address_item_name);
            address = itemView.findViewById(R.id.address_item_address);
            pinCode = itemView.findViewById(R.id.address_item_pincode);
            icon  = itemView.findViewById(R.id.address_icon_check);
            optionContainer = itemView.findViewById(R.id.address_option_container);
        }
        private  void setData(String username,String userAddress,String userPinCode,Boolean selected,final int position){
            fullName.setText(username);
            address.setText(userAddress);
            pinCode.setText(userPinCode);
            if(MODE == SELECT_ADDRESS){
                 icon.setImageResource(R.drawable.arrowdown);
                 if(selected){
                     icon.setVisibility(View.VISIBLE);
                     preSelectedPosition = position;
                 }else{
                     icon.setVisibility(View.GONE);
                 }
                 itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(preSelectedPosition != position) {
                             addAddressModelList.get(position).setSelected(true);
                             addAddressModelList.get(preSelectedPosition).setSelected(false);
                             refreshItem(preSelectedPosition, position);
                             preSelectedPosition = position;
                         }
                     }
                 });

            }else if(MODE == MANAGE_ADDRESS){
                 optionContainer.setVisibility(View.GONE);
                 icon.setImageResource(R.drawable.menu);
                 icon.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         optionContainer.setVisibility(View.VISIBLE);
                         refreshItem(preSelectedPosition,preSelectedPosition);
                         preSelectedPosition = position;
                     }
                 });
                 itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         refreshItem(preSelectedPosition,preSelectedPosition);
                         preSelectedPosition = -1;
                     }
                 });
            }
        }
    }
}
