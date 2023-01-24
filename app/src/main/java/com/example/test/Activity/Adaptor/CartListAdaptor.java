package com.example.test.Activity.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test.Activity.Domaine.FoodDomaine;
import com.example.test.Activity.Helper.ManagementCart;
import com.example.test.Activity.Interface.ChangeNumberItemListener;
import com.example.test.Activity.ShowDetailActivity;
import com.example.test.R;

import java.util.ArrayList;

public class CartListAdaptor extends RecyclerView.Adapter<CartListAdaptor.ViewHolder> {

    private ArrayList<FoodDomaine> foodDomaines;
    private ManagementCart managementCart ;
    private ChangeNumberItemListener changeNumberItemListener;

    public CartListAdaptor(ArrayList<FoodDomaine> foodDomaines, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.foodDomaines = foodDomaines;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart ,parent , false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(foodDomaines.get(position).getTitle());
       holder.feeEachItem.setText(String.valueOf(foodDomaines.get(position).getFee()));
       holder.totalEachItem.setText(String.valueOf(Math.round((foodDomaines.get(position).getNumberInCart() * foodDomaines.get(position).getFee()) * 100) /100));
       holder.num.setText(String.valueOf(foodDomaines.get(position).getNumberInCart()));

       int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomaines.get(position).getPic(),
               "drawable" ,holder.itemView.getContext().getPackageName()   );
       Glide.with(holder.itemView.getContext())
               .load(drawableResourceId)
               .into(holder.pic);
       holder.plusItem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                managementCart.plusNumberFood(foodDomaines, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
           }
       });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(foodDomaines, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return foodDomaines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView title , feeEachItem ;
       ImageView pic , plusItem , minusItem ;
       TextView totalEachItem , num ;
       public ViewHolder(View itemView){
              super(itemView);
              title = itemView.findViewById(R.id.titleTxt);
              feeEachItem = itemView.findViewById(R.id.feeEatchItem);
              pic = itemView.findViewById(R.id.picCart);
              totalEachItem = itemView.findViewById(R.id.totalEachItem);
              num = itemView.findViewById(R.id.numberitemTxt);
              plusItem = itemView.findViewById(R.id.plusCartBtn);
              minusItem = itemView.findViewById(R.id.minusCartBtn);
       }
    }
}
