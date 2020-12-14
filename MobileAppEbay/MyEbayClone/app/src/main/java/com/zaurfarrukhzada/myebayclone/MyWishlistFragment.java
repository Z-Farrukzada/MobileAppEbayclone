package com.zaurfarrukhzada.myebayclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyWishlistFragment extends Fragment {


    public MyWishlistFragment() {
        // Required empty public constructor
    }


    private RecyclerView myWishListRecycleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_wishlist, container, false);

        myWishListRecycleView = view.findViewById(R.id.my_wishlist_recycleview);

        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myWishListRecycleView.setLayoutManager(linearLayoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();

        wishlistModelList.add(new WishlistModel(R.drawable.product,"Samsung Note 10 16gb Ram",1,"3",145,"US $120","US $200","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.product,"Samsung Note 10 8gb Ram",2,"4",125,"US $220","US $300","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.product,"Samsung Note 10 32gb Ram",3,"2",115,"US $320","US $400","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.product,"Samsung Note 10 64gb Ram",4,"1",105,"US $420","US $500","Cash on delivery"));

        WishListAdapter wishListAdapter = new WishListAdapter(wishlistModelList,true);
        myWishListRecycleView.setAdapter(wishListAdapter);
        wishListAdapter.notifyDataSetChanged();

        return view;
    }
}