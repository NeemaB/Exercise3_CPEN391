package com.example.neema.tipcalculator.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codekrypt.greendao.db.DaoSession;
import com.codekrypt.greendao.db.Meal;
import com.codekrypt.greendao.db.MealDao;
import com.example.neema.tipcalculator.Adapters.MealListAdapter;
import com.example.neema.tipcalculator.R;
import com.example.neema.tipcalculator.TipCalculatorApplication;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

/**
 * Created by neema on 2017-03-06.
 */
public class MealListFragment extends Fragment {

    MealListAdapter mAdapter;
    ArrayList <Meal> mealList;

    ListView mealListView;


    @Override
    public void onCreate(Bundle onSaveInstanceState){
        super.onCreate(onSaveInstanceState);
        setHasOptionsMenu(true);

        mealList = new ArrayList<>();
        mAdapter = new MealListAdapter(getContext(), 0, mealList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.meal_list_fragment_layout, container, false);

        mealListView = (ListView) view.findViewById(R.id.mealList);

        mealListView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

        DaoSession daoSession = TipCalculatorApplication.getDaoSession();
        MealDao mealDao = daoSession.getMealDao();
        QueryBuilder<Meal> qb = mealDao.queryBuilder();

        mealList.clear();
        for(int i = 0; i < qb.list().size(); i++){
           // Log.i("MEAL_LIST_FRAGMENT", mealList.get(i).getRestaurant_name());
            mealList.add(qb.list().get(i));
        }

        mAdapter.notifyDataSetChanged();
    }





    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        menu.clear();

    }




}
