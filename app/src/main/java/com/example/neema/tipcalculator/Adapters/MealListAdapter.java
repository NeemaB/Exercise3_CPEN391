package com.example.neema.tipcalculator.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Utility.DataUtil;
import com.codekrypt.greendao.db.Meal;
import com.example.neema.tipcalculator.R;

import java.util.ArrayList;

/**
 * Created by neema on 2017-03-08.
 */
public class MealListAdapter extends ArrayAdapter<Meal> {

    ArrayList <Meal> meals;
    public MealListAdapter(Context context, int resourceId, ArrayList <Meal> meals){
        super(context, resourceId, meals);
        this.meals = meals;
    }

    private static class ViewHolder {
        TextView restaurant_name;
        TextView date;
        ImageView restaurant_image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Meal meal = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.meal_list_item, parent, false);
            viewHolder.restaurant_name = (TextView) convertView.findViewById(R.id.restaurant_name);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.restaurant_image = (ImageView) convertView.findViewById(R.id.restaurant_image);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.restaurant_name.setText(meal.getRestaurant_name());
        viewHolder.date.setText(DataUtil.getTime(meal));

        return convertView;
    }
}
