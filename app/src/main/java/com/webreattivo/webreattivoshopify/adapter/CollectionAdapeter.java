package com.webreattivo.webreattivoshopify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.shopify.buy.model.Collection;
import com.squareup.picasso.Picasso;
import com.webreattivo.webreattivoshopify.R;
import java.util.ArrayList;

public class CollectionAdapeter extends ArrayAdapter<Collection> {

    private static final String LOG_TAG = CollectionAdapeter.class.getSimpleName();

    public CollectionAdapeter(Context context, ArrayList<Collection> colletions) {
        super(context, 0, colletions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Collection collection = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title_row);
        title.setText(collection.getTitle());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_row);
        Picasso.with(getContext())
                .load(collection.getImageUrl())
                .resize(100, 100)
                .centerCrop()
                .into(imageView);

        convertView.setTag(collection.getCollectionId());

        return convertView;
    }
}
