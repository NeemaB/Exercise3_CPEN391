package com.example.neema.tipcalculator.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codekrypt.greendao.db.Meal;
import com.codekrypt.greendao.db.MealDao;
import com.example.neema.tipcalculator.R;
import com.example.neema.tipcalculator.TipCalculatorApplication;
import com.joanzapata.iconify.widget.IconButton;

import org.greenrobot.greendao.query.QueryBuilder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by neema on 2017-03-06.
 */
public class TipCalculatorFragment extends Fragment implements View.OnClickListener{
    
    private NumberFormat form;
    private Double scale;
    private Integer lastInput;

    private Double totalBillValue;
    private Double totalToPayValue;
    private Double totalTipValue;
    private Double totalPerPersonValue;
    private Integer tipPercentValue;
    private Integer splitBillValue;

    private TextView totalBill;
    private TextView tipPercent;
    private TextView splitBill;
    private TextView totalToPay;
    private TextView totalTip;
    private TextView totalPerPerson;

    private IconButton minusButton;
    private IconButton minusButton2;
    private IconButton plusButton;
    private IconButton plusButton2;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;

    private Button buttonReset;
    private Button buttonDelete;

    @Override
    public void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);

        form = new DecimalFormat("0.00");
        form.setRoundingMode(RoundingMode.FLOOR);
        scale = 0.001;
        totalBillValue = 0.00;
        totalToPayValue = 0.00;
        totalTipValue = 0.00;
        totalPerPersonValue = 0.00;
        tipPercentValue = 0;
        splitBillValue = 1;

        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tip_calculator_layout, container, false);

        totalBill = (TextView) view.findViewById(R.id.total_bill);
        tipPercent = (TextView) view.findViewById(R.id.tip_percent);
        splitBill = (TextView) view.findViewById(R.id.split_bill);
        totalToPay = (TextView) view.findViewById(R.id.total_to_pay);
        totalTip = (TextView) view.findViewById(R.id.total_tip);
        totalPerPerson = (TextView) view.findViewById(R.id.total_per_person);

        minusButton = (IconButton) view.findViewById(R.id.minus);
        minusButton2 = (IconButton) view.findViewById(R.id.minus2);

        plusButton = (IconButton) view.findViewById(R.id.plus);
        plusButton2 = (IconButton) view.findViewById(R.id.plus2);

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);
        button6 = (Button) view.findViewById(R.id.button6);
        button7 = (Button) view.findViewById(R.id.button7);
        button8 = (Button) view.findViewById(R.id.button8);
        button9 = (Button) view.findViewById(R.id.button9);
        button0 = (Button) view.findViewById(R.id.button0);

        buttonReset = (Button) view.findViewById(R.id.button_reset);
        buttonDelete = (Button) view.findViewById(R.id.button_del);

        minusButton.setOnClickListener(this);
        minusButton2.setOnClickListener(this);

        plusButton.setOnClickListener(this);
        plusButton2.setOnClickListener(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);

        buttonDelete.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        //minusButton = (IconButton) findViewById(R.id.minus);
        //minusButton.setText("{fa-plus}");

        return view;
    }


    @Override
    public void onStart(){
        super.onStart();
        setText();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.plus:
                tipPercentValue++;
                tipPercent.setText(tipPercentValue.toString());
                updateAmounts();
                break;

            case R.id.minus:
                if (tipPercentValue == 0)
                    break;

                tipPercentValue--;
                tipPercent.setText(tipPercentValue.toString());
                updateAmounts();
                break;

            case R.id.plus2:
                splitBillValue++;
                splitBill.setText(splitBillValue.toString());
                updateAmounts();
                break;

            case R.id.minus2:

                if (splitBillValue == 1)
                    break;

                splitBillValue--;
                splitBill.setText(splitBillValue.toString());
                updateAmounts();
                break;

            case R.id.button_del:

                totalBillValue /= 10.00;
                totalBillValue = Math.floor(totalBillValue * 100.00) / 100.00;
                if (totalBillValue < 0.01) {
                    totalBillValue = 0.00;
                }

                totalBill.setText("$" + form.format(totalBillValue));
                updateAmounts();
                break;

            case R.id.button_reset:

                totalBillValue = 0.00;
                tipPercentValue = 0;
                splitBillValue = 1;

                totalBill.setText("$" + form.format(totalBillValue));
                splitBill.setText(splitBillValue.toString());
                tipPercent.setText(tipPercentValue.toString());

                updateAmounts();
                break;

            default:

                Integer value = Integer.parseInt((String) ((Button) v).getText());
                totalBillValue *= 10.00;
                totalBillValue += value * 0.01;
                totalBill.setText("$" + form.format(totalBillValue));
                updateAmounts();
                break;

        }
    }

    private void updateAmounts() {

        totalToPayValue = totalBillValue + (totalBillValue * (tipPercentValue / 100.00));
        totalTipValue = totalBillValue * (tipPercentValue / 100.0);
        totalPerPersonValue = totalToPayValue / splitBillValue;

        totalToPay.setText("$" + form.format(totalToPayValue));
        totalTip.setText("$" + form.format(totalTipValue));
        totalPerPerson.setText("$" + form.format(totalPerPersonValue));

    }

    private void setText(){

        totalBill.setText("$" + form.format(totalBillValue));
        tipPercent.setText(tipPercentValue.toString());
        totalToPay.setText("$" + form.format(totalToPayValue));
        totalTip.setText("$" + form.format(totalTipValue));
        totalPerPerson.setText("$" + form.format(totalPerPersonValue));

    }

    public void saveMeal(){

        MealDao mealDao = TipCalculatorApplication.getDaoSession().getMealDao();
        Meal meal = new Meal();

        Calendar calendar = Calendar.getInstance();

        meal.setYear(calendar.get(Calendar.YEAR));
        meal.setMonth(calendar.get(Calendar.MONTH));
        meal.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        meal.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        meal.setMinute(calendar.get(Calendar.MINUTE));

        meal.setTip(totalTipValue);
        meal.setPrice(totalToPayValue);
        meal.setRestaurant_name("default_name");
        meal.setStars(5);

        mealDao.insert(meal);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.save_meal) {

            AlertDialog.Builder builder = new AlertDialog.Builder(
                    new ContextThemeWrapper(getActivity(), R.style.DialogTheme));


            builder.setView(R.layout.save_meal_dialog)
                    .setTitle("Meal Info")
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveMeal();

                            Toast toast = Toast.makeText(getActivity(),
                                    "Saved Meal Info",
                                    Toast.LENGTH_SHORT);

                            toast.show();
                            dialog.dismiss();
                        }
                    });


            AlertDialog save_meal_dialog = builder.create();
            save_meal_dialog.setCanceledOnTouchOutside(true);

            restaurant
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.restaurant_names, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            restaurantNames.setAdapter(adapter);

            save_meal_dialog.show();


        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


}
