package com.example.test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.test.Activity.Adaptor.CartListAdaptor;
import com.example.test.Activity.Helper.ManagementCart;
import com.example.test.Activity.Interface.ChangeNumberItemListener;
import com.example.test.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import kotlin.jvm.internal.PropertyReference0Impl;


public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter ;
    private RecyclerView recyclerViewList ;
    private ManagementCart managementCart;
    TextView totalFeeTxt , taxTxt ,deliveryTxt , totalTxt , emptyTxt;
    private  double tax ;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }
     private void bottomNavigation(){
         FloatingActionButton floatingActionButton= findViewById(R.id.cartBtn);
         LinearLayout homeBtn = findViewById(R.id.homeBtn);

         floatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(CartListActivity.this , CartListActivity.class));
             }
         });
         homeBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(CartListActivity.this , MainActivity.class));
             }
         });
     }

    private void initView() {
        totalFeeTxt= findViewById(R.id.totalFee);
        taxTxt = findViewById(R.id.taxFee);
        deliveryTxt = findViewById(R.id.deliveryFee);
        totalTxt = findViewById(R.id.totalFe);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView2);
        recyclerViewList =findViewById(R.id.cartView);

    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL , false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdaptor(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private  void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 10 ;

        tax = Math.round((managementCart.getTotalFee() * percentTax ) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery ) *100 )/ 100 ;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);

    }
}