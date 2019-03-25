package com.example.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {

    private Context context;
    private List<CartItem> productsInCart;

    public CartAdaptor(Context context) {

        this.context = context;
        productsInCart = new ArrayList<>();
        for (String productName: GlobalClass.cart.keySet()) {
            productsInCart.add(GlobalClass.cart.get(productName));
        }
        Log.d("CheckAdaptorConstructor", productsInCart.toString());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_cart_item, viewGroup, false);
        final CartAdaptor.ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdaptor.ViewHolder viewHolder, int i) {
        final CartItem item = productsInCart.get(i);
        Log.d("CheckCartViewBind", item.toString());
        viewHolder.cartNameDisplay.setText(item.ProductName);
        viewHolder.cartPriceDisplay.setText(new StringBuilder().append("EUR ").append(String.valueOf(item.TotalPrice)).toString());
        viewHolder.cartQuantityDisplay.setText(String.valueOf(item.ProductQuantity));
        viewHolder.cartItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.cart.remove(item.ProductName);
                Log.d("RemoveClick", item.ProductName);

                Intent intent = new Intent(context, CartActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsInCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cartNameDisplay, cartPriceDisplay, cartQuantityDisplay;
        public Button cartItemDelete;

        public ViewHolder(View cartView) {
            super(cartView);
            cartNameDisplay = cartView.findViewById(R.id.cart_item_name);
            cartPriceDisplay = cartView.findViewById(R.id.cart_item_price);
            cartQuantityDisplay = cartView.findViewById(R.id.cart_item_quantity);
            cartItemDelete = cartView.findViewById(R.id.cart_item_delete_btn);
        }
    }
}
