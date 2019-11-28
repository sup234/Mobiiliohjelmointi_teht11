package com.example.teht11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    private Context context;
    private String name;
    private TextView t1;
    private ArrayList<String> list = new ArrayList<String>();

    public CustomAdapter(@NonNull Context context, int resource, String name) {
        super(context, resource);
        this.context = context;
        this.name = name;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v;
        if (convertView == null) {
            v = LayoutInflater.from(context).inflate(R.layout.listview, parent, false);
            t1 = v.findViewById(R.id.textview1);
            t1.setText(list.get(position));

        }
        else {
            v = convertView;
        }

        return v;

    }
}
