package com.webreattivo.webreattivoshopify.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.shopify.buy.model.Collection;
import com.webreattivo.webreattivoshopify.R;
import com.webreattivo.webreattivoshopify.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {

    protected ListView listView;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);

        getWebReattivoApplication().getCollections(new Callback<List<Collection>>() {
            @Override
            public void success(List<Collection> collections, Response response) {
                List<String> collectionTitles = onFetchedCollections(collections);
                listView.setAdapter(new ArrayAdapter<>(MainActivity.this, R.layout.simple_list_item, collectionTitles));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
                onError(error);
            }
        });
    }

    private List onFetchedCollections(final List<Collection> collections) {

        List<String> collectionTitles = new ArrayList<String>();

        // Add an 'All Products' collection just in case there are products that do not belong to a collection
        for (Collection collection : collections) {
            collectionTitles.add(collection.getTitle());
        }

        return collectionTitles;
    }
}
