package com.example.ecommerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.*;
import static com.android.volley.Request.Method.GET;

public class ProductsActivity extends AppCompatActivity implements ProductAdopter.onProductClickListener{

    protected RecyclerView pList;

    protected LinearLayoutManager linearLayoutManager;
    protected DividerItemDecoration dividerItemDecoration;
    public List<Product> productsList;
    protected RecyclerView.Adapter adapter;

    protected String url = "https://msitmp.herokuapp.com/getproducts/20186048";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        pList = findViewById(R.id.list_of_products);

        productsList = new ArrayList<>();
        adapter = new ProductAdopter(getApplicationContext(), productsList, this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(pList.getContext(), linearLayoutManager.getOrientation());

        pList.setHasFixedSize(true);
        pList.setLayoutManager(linearLayoutManager);
        pList.addItemDecoration(dividerItemDecoration);
        pList.setAdapter(adapter);

        getDataViaHTTP();
    }

    private void getDataViaHTTP() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        try {
            HttpGetRequest getRequest = new HttpGetRequest();
            String response = getRequest.execute(url).get();
            Log.d("getDataViaHTTP", response);

            if (response != null) {
                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray = json.getJSONArray("ProductCollection");
                Log.d("why",response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Product product = new Product();

                        product.Description = jsonObject.getString("Description");
                        product.Name = jsonObject.getString("Name");
                        product.ProductPicUrl = jsonObject.getString("ProductPicUrl");
                        product.Quantity = jsonObject.getInt("Quantity");
                        product.Price = jsonObject.getInt("Price");
                        productsList.add(product);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }
            adapter.notifyDataSetChanged();
            progressDialog.dismiss();
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("reached_response", response.toString());
                try {
                    JSONObject json1 = new JSONObject(response.toString());
                    JSONArray jsonArray1 = json1.getJSONArray("ProductCollection");                    JSONArray json = new JSONArray(response);
                    Log.e("why",response.toString());
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray1.getJSONObject(i);

                            Product product = new Product();
                            product.ProductId = jsonObject.getString("ProductId");
                            product.Category = jsonObject.getString("Category");
                            product.MainCategory = jsonObject.getString("MainCategory");
                            product.TaxTarifCode = jsonObject.getString("TaxTarifCode");
                            product.SupplierName = jsonObject.getString("SupplierName");
                            product.WeightMeasure = jsonObject.getString("WeightMeasure");
                            product.WeightUnit = jsonObject.getString("WeightUnit");
                            product.Description = jsonObject.getString("Description");
                            product.Name = jsonObject.getString("Name");
                            product.DateOfSale = jsonObject.getString("DateOfSale");
                            product.ProductPicUrl = jsonObject.getString("ProductPicUrl");
                            product.Status = jsonObject.getString("Status");
                            product.Quantity = jsonObject.getInt("Quantity");
                            product.UoM = jsonObject.getString("UoM");
                            product.CurrencyCode = jsonObject.getString("CurrencyCode");
                            product.Price = jsonObject.getInt("Price");
                            product.Width = jsonObject.getString("Width");
                            product.Depth = jsonObject.getString("Depth");
                            product.Height = jsonObject.getString("Height");
                            product.DimUnit = jsonObject.getString("DimUnit");
                            productsList.add(product);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onProductClick(int position) {
        Log.d("childCheck", "Clicked!");
        String product_key = "product";
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(product_key, productsList.get(position));
        startActivity(intent);

    }
}
