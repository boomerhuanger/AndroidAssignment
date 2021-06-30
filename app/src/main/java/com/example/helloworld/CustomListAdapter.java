package com.example.helloworld;

import android.content.Context;
import android.view.View;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<User> {

    private Context mContext;

    int mResource;

    public CustomListAdapter(Context context, int resource, ArrayList<User> users) {
        super(context, resource, users);
        mContext = context;
        mResource = resource;
    }

    //getting the view and attach it to the ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String email = getItem(position).getEmail();
        String phone = getItem(position).getPhone();
        int id = getItem(position).getId();
        Integer.toString(id);

        User user = new User(id, name, email, phone);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        TextView tvId =  (TextView) convertView.findViewById(R.id.id);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.email);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.phone);

        String idToSet = "ID: " + String.valueOf(id);
        tvId.setText(idToSet);
        String nameToSet = "Name: " + name;
        tvName.setText(nameToSet);
        String emailToSet = "Email: " + email;
        tvEmail.setText(emailToSet);
        String phoneToSet = "Phone: " + phone;
        tvPhone.setText(phoneToSet);

        return convertView;

    }




}
