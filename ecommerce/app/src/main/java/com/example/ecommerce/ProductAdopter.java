package com.example.ecommerce;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.PrivateKey;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

public class ProductAdopter extends RecyclerView.Adapter<ProductAdopter.ViewHolder>{

    private Context context;
    private List<Product> list;
    protected onProductClickListener pOnProductClickListener;

    public ProductAdopter(Context context, List<Product> list, onProductClickListener pOnProductClickListener) {
        this.context = context;
        this.list = list;
        this.pOnProductClickListener = pOnProductClickListener;
    }

    @NonNull
    @Override
    public ProductAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_product, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v, this.pOnProductClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdopter.ViewHolder viewHolder, int i) {
        Product product = list.get(i);
        viewHolder.textName.setText(product.Name);
        viewHolder.textPrice.setText(new StringBuilder().append("EUR ").append(product.Price).toString());
//        viewHolder.textPrice.setColor(Color.parseColor("#FF0000"));

        try {
            ImageLoad imageLoad = new ImageLoad();
            String imageUrl = new StringBuilder().append("http://msitmp.herokuapp.com").append(product.ProductPicUrl).toString();
            Bitmap result = imageLoad.execute(imageUrl).get();
            viewHolder.imgProduct.setImageBitmap(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textName, textPrice;
        public ImageView imgProduct;
        onProductClickListener onProductClickListener;

        public ViewHolder(View itemView, onProductClickListener onProductClickListener) {
            super(itemView);

            textName = itemView.findViewById(R.id.prod_name);
            textPrice = itemView.findViewById(R.id.prod_price);
            imgProduct = itemView.findViewById(R.id.prod_image);
            this.onProductClickListener = onProductClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onProductClickListener.onProductClick(getAdapterPosition());
        }
    }

    public interface onProductClickListener {
        void onProductClick(int position);
    }
}
