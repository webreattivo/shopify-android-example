package com.webreattivo.webreattivoshopify.application;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import com.shopify.buy.dataprovider.BuyClient;
import com.shopify.buy.dataprovider.BuyClientFactory;
import com.shopify.buy.model.Collection;
import com.shopify.buy.model.Product;
import com.shopify.buy.model.Shop;
import com.webreattivo.webreattivoshopify.R;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class WebReattivoApplication extends Application {

    private BuyClient buyClient;
    private Shop shop;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeBuyClient();
    }

    private void initializeBuyClient() {

        String shopUrl = getString(R.string.shop_url);

        if (TextUtils.isEmpty(shopUrl)) {
            throw new IllegalArgumentException("You must populate the 'shop_url' entry in strings.xml, in the form '<myshop>.myshopify.com'");
        }

        String shopifyApiKey = getString(R.string.shopify_api_key);
        if (TextUtils.isEmpty(shopifyApiKey)) {
            throw new IllegalArgumentException("You must populate the 'shopify_api_key' entry in strings.xml");
        }

        String channelId = getString(R.string.channel_id);
        if (TextUtils.isEmpty(channelId)) {
            throw new IllegalArgumentException("You must populate the 'channel_id' entry in the strings.xml");
        }

        String applicationName = getPackageName();

        /**
         * Create the BuyClient
         */
        buyClient = BuyClientFactory.getBuyClient(shopUrl, shopifyApiKey, channelId, applicationName);

        buyClient.getShop(new Callback<Shop>() {
            @Override
            public void success(Shop shop, Response response) {
                WebReattivoApplication.this.shop = shop;
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(WebReattivoApplication.this, R.string.shop_error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getCollections(final Callback<List<Collection>> callback) {
        buyClient.getCollections(callback);
    }

    public void getAllProducts(final Callback<List<Product>> callback) {
        // For this sample app, "all" products will just be the first page of products
        buyClient.getProductPage(1, callback);
    }

    public void getProducts(String collectionId, Callback<List<Product>> callback) {
        // For this sample app, we'll just fetch the first page of products in the collection
        buyClient.getProducts(1, collectionId, callback);
    }
}
