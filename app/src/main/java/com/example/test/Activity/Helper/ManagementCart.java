package com.example.test.Activity.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.test.Activity.Domaine.FoodDomaine;
import com.example.test.Activity.Interface.ChangeNumberItemListener;

import java.sql.Array;
import java.util.ArrayList;

public class ManagementCart {
    private Context context ;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomaine item){
        ArrayList<FoodDomaine> listFood = getListCart();
        boolean existAlready = false ;
        int n=0;
        for (int i =0 ; i< listFood.size();i++)
        {
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n=i;
                break;
            }
        }
        if (existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listFood.add(item);
        }
        tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomaine> getListCart(){
        return tinyDB.getListObject("CartList");
    }
    public void plusNumberFood(ArrayList<FoodDomaine> listFood , int position , ChangeNumberItemListener changeNumberItemListener) {
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList" ,listFood);
        changeNumberItemListener.changed();
    }
        public void minusNumberFood(ArrayList<FoodDomaine> listFood , int position , ChangeNumberItemListener changeNumberItemListener){
        if(listFood.get(position).getNumberInCart()==1){
            listFood.remove(position);

        }
        else{
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);
        }
       tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<FoodDomaine> listfood = getListCart();
        double fee = 0;
        for(int i =0 ; i < listfood.size(); i ++){
            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberInCart());
        }
        return  fee ;
    }
}
