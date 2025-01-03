package com.example.dogfood.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.dogfood.R;
import com.example.dogfood.models.EducationItem;

import java.util.List;

public class EducationAdapter extends ArrayAdapter<EducationItem> {

    public EducationAdapter(Context context, List<EducationItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_education, parent, false);
        }

        EducationItem item = getItem(position);

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        textViewTitle.setText(item.getTitle());

        convertView.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
            getContext().startActivity(intent);
        });

        return convertView;
    }
}
