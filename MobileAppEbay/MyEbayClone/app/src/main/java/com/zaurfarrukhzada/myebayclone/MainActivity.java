package com.zaurfarrukhzada.myebayclone;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.zaurfarrukhzada.myebayclone.ui.home.HomeFragment;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.zaurfarrukhzada.myebayclone.RegisterActivity.setOnSignInFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDER_FRAGMENT = 2;
    private static final int WISH_FRAGMENT = 3;
    private static final int REWARDS_FRAGMENT = 4;
    private static final int ACCOUNT_FRAGMENT = 5;
    public static Boolean showCart = false;

    private Window window;

    private AppBarConfiguration mAppBarConfiguration;
    private FrameLayout frameLayout;
    private  NavController navController;
    private ImageView actionBarLogo;
    private  Toolbar toolbar;
    private  int currentFragment = -1;
    private NavigationView navigationView;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_toolbar);
        actionBarLogo = findViewById(R.id.actionbar_logo);
        setSupportActionBar(toolbar);

        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        frameLayout=findViewById(R.id.main_frame_layout);

        if (showCart) {
            drawer.setDrawerLockMode(1);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            goToFragment("My Cart", new MyCartFragment(), -2);
        } else {
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                    .setDrawerLayout(drawer)
                    .build();
            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.getMenu().getItem(0).setChecked(true);
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(currentFragment == HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            if(currentFragment == HOME_FRAGMENT){
                currentFragment = -1;
                super.onBackPressed();
            }else{
                if(showCart){
                      showCart = false;
                      finish();
                }else{
                    actionBarLogo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new HomeFragment(),HOME_FRAGMENT);
                    navigationView.getMenu().getItem(0).setChecked(true);
                }

            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search_icon) {
            //todo:search
            return true;
        } else if (id == R.id.main_notification_icon) {

            //todo:notification
            return true;
        } else if (id == R.id.main_cart_icon) {
            //todo:cart

            Dialog signInDialog = new Dialog(MainActivity.this);
            signInDialog.setContentView(R.layout.sign_in_dialog);
            signInDialog.setCancelable(true);
            signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            Button dialogSignInBtn =  signInDialog.findViewById(R.id.dialog_cancel_btn);
            Button dialogSignUpBtn =  signInDialog.findViewById(R.id.dialog_ok_btn);

            Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);

            dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInDialog.dismiss();
                    setOnSignInFragment=false;
                    startActivity(registerIntent);
                }
            });
            dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInDialog.dismiss();
                    setOnSignInFragment=true;
                    startActivity(registerIntent);
                }
            });
            signInDialog.show();
            //goToFragment("My Cart",new MyCartFragment(),CART_FRAGMENT);
            return true;
        }else if(id == android.R.id.home){
            if(showCart) {
                showCart = false;
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }



    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_my_mall) {

             actionBarLogo.setVisibility(View.VISIBLE);
             invalidateOptionsMenu();
             setFragment(new HomeFragment(),HOME_FRAGMENT);

        } else if (id == R.id.nav_my_orders) {
              goToFragment("My Orders",new MyOrderFragment(),ORDER_FRAGMENT);
        } else if (id == R.id.nav_my_rewards) {
              goToFragment("My Rewards",new MyRewardsFragment(),REWARDS_FRAGMENT);
        } else if (id == R.id.nav_my_cart) {
              goToFragment("My Cart",new MyCartFragment(),CART_FRAGMENT);
        } else if (id == R.id.nav_my_wishlist) {
              goToFragment("My WishList", new MyWishlistFragment(),WISH_FRAGMENT);
        } else if (id == R.id.nav_my_account) {
              goToFragment("My account",new MyAccountFragment(),ACCOUNT_FRAGMENT);
        } else if (id == R.id.nav_my_sign_out) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setFragment(Fragment fragment,int fragmentNo){
        if(fragmentNo != currentFragment){
            if(fragmentNo == REWARDS_FRAGMENT){
                window.setStatusBarColor(Color.parseColor("#E91E63"));
                toolbar.setBackgroundColor(Color.parseColor("#E91E63"));
            }else{
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(),fragment);
            fragmentTransaction.commit();
        }

    }
    private void goToFragment(String title,Fragment fragment,int fragmentNo) {
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment,fragmentNo);
        if(fragmentNo == CART_FRAGMENT) {
            navigationView.getMenu().getItem(3).setChecked(true);
        }
    }

}