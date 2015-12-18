package com.webreattivo.webreattivoshopify.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.shopify.buy.dataprovider.BuyClient;
import com.webreattivo.webreattivoshopify.R;
import com.webreattivo.webreattivoshopify.application.WebReattivoApplication;
import retrofit.RetrofitError;

public class BaseActivity extends AppCompatActivity {

    private static final String LOG_TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected WebReattivoApplication getWebReattivoApplication() {
        return (WebReattivoApplication) getApplication();
    }

    protected void onError(RetrofitError error) {
        onError(BuyClient.getErrorBody(error));
    }

    protected void onError(String errorMessage) {
        Log.e(LOG_TAG, "Error: " + errorMessage);
        Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
