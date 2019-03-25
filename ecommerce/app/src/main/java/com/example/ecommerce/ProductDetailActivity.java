package com.example.ecommerce;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActivity extends AppCompatActivity {

    protected TextView titleDisplay, descDisplay, priceDisplay;
    protected ImageView imageDisplay;
    Button addToCart, checkOut;
    EditText quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleDisplay = findViewById(R.id.detail_title);
        descDisplay = findViewById(R.id.detail_desc);
        priceDisplay = findViewById(R.id.detail_price);
        imageDisplay = findViewById(R.id.detail_image);

        Intent intent = getIntent();
        final Product product = intent.getParcelableExtra("product");

        titleDisplay.setText(product.Name);
        descDisplay.setText(product.Description);
        priceDisplay.setText(new StringBuilder().append("EUR ").append(product.Price).toString());

        try {
            ImageLoad imageLoad = new ImageLoad();
            String imageUrl = new StringBuilder().append("http://msitmp.herokuapp.com").append(product.ProductPicUrl).toString();
            Bitmap result = imageLoad.execute(imageUrl).get();
            imageDisplay.setImageBitmap(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        quantity = findViewById(R.id.detail_cart_quantity);

        addToCart = findViewById(R.id.detail_add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String prodQuantity = quantity.getText().toString();
                if (prodQuantity.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Quantity can't be zero", Toast.LENGTH_LONG);
                    toast.show();
                } else if (Integer.parseInt(prodQuantity) > product.Quantity) {
                    String availableQuantity = "Only " + product.Quantity + " items available";
                    Toast toast = Toast.makeText(getApplicationContext(), availableQuantity, Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    if (GlobalClass.cart.containsKey(product.Name)) {
                        CartItem cartPrevious = GlobalClass.cart.get(product.Name);
                        cartPrevious.increaseQuantity(Integer.parseInt(prodQuantity));
                        GlobalClass.cart.put(product.Name, cartPrevious);
                    } else {
                        CartItem cartItem = new CartItem(product.Name, Integer.parseInt(prodQuantity), product.Price);
                        GlobalClass.cart.put(product.Name, cartItem);
                    }
                    Log.d("CartCheckAdd",GlobalClass.cart.toString());
                    quantity.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Added to Cart!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        checkOut = findViewById(R.id.detail_check_out_btn);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }

}
