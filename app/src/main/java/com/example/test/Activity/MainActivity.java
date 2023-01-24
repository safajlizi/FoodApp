package com.example.test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Activity.Adaptor.CategoryAdaptor;
import com.example.test.Activity.Adaptor.PopularAdaptor;
import com.example.test.Activity.Domaine.CategoryDomaine;
import com.example.test.Activity.Domaine.FoodDomaine;
import com.example.test.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter ,adapter2;
    private RecyclerView recyclerViewCategoryList ,recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();

    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton= findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , MainActivity.class));
            }
        });
    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
       recyclerViewCategoryList = findViewById(R.id.recycleView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomaine> category = new ArrayList<>();
        category.add(new CategoryDomaine("Pizza","cat_1"));
        category.add(new CategoryDomaine("Burger","cat_2"));
        category.add(new CategoryDomaine("Hotdog","cat_3"));
        category.add(new CategoryDomaine("Drink","cat_4"));
        category.add(new CategoryDomaine("Donut","cat_5"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);

    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.RecycleView);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomaine> foodList = new ArrayList<>();
        foodList.add(new FoodDomaine("Pepperoni pizza","pop_1","slices pepperoni, mozzerella cheese , fresh oregano , ground black pepper , pizza sauce ",9.76));
        foodList.add(new FoodDomaine("Cheese Burger ","pop_2"," beef , Gouda Cheese , Special sauce ,Lettuce , tomate",8.79));
        foodList.add(new FoodDomaine("Vegetable  pizza","pop_3","olive oil , Vegetable oil , pitted kalamate , cherry tomatoes , fresh oregano ,basil  ",8.5));


        adapter2 = new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);

    }
}