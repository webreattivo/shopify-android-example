package com.webreattivo.webreattivoshopify.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.shopify.buy.model.Collection;
import com.webreattivo.webreattivoshopify.R;
import com.webreattivo.webreattivoshopify.activity.base.BaseActivity;
import com.webreattivo.webreattivoshopify.adapter.CollectionAdapeter;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //populateUsersList();

        getWebReattivoApplication().getCollections(new Callback<List<Collection>>() {

            @Override
            public void success(List<Collection> collections, Response response) {

                CollectionAdapeter adapter = new CollectionAdapeter(
                        getApplicationContext(),
                        onFetchedCollections(collections)
                );
                ListView listView = (ListView) findViewById(R.id.list_view);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(), "id: " + view.getTag(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
                onError(error);
            }
        });
    }

    private ArrayList onFetchedCollections(final List<Collection> collections) {

        ArrayList<Collection> listCollections = new ArrayList<Collection>();

        // Add an 'All Products' collection just in case there are products that do not belong to a collection
        for (Collection collection : collections) {
            listCollections.add(collection);
        }

        return listCollections;
    }
}
