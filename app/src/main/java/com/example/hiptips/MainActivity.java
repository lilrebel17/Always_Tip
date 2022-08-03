package com.example.hiptips;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    SeekBar tipSlider;
    TextView tipPercent;
    TextView tipAmount;
    TextView totalAmount;
    EditText priceInput;


    //The onCreate method fires when the application starts up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This line is just telling our program what content to show on screen.
        //In this case R. stands for resource. So were looking in Resources/Layout/ for activity_main
        //in res/layout we have the matching .xml file
        setContentView(R.layout.activity_main);

        //Initializing variables for all of our labels/inputs
        tipSlider = findViewById(R.id.seekTipPercent_Slider);
        tipPercent = findViewById(R.id.tvTipPercent_Label);
        tipAmount = findViewById(R.id.tvTip_Amount);
        totalAmount = findViewById(R.id.tvTotal_Amount);
        priceInput = findViewById(R.id.tvPrice_Input);

        //Gives us a number format for the labels.
        NumberFormat dollarFormat = new DecimalFormat("#0.00");

        tipSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //Just takes the tipPercent. and sets the text to % + i.
                //i = whatever the slider is on.
                tipPercent.setText(i + "%");
                UpdateTipLabel(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            //Updates the label for the tip, as you slide the TipSlider
            public void UpdateTipLabel(Integer percent) {
                //Variable for user inputted price.
                String inputValue = priceInput.getText().toString();
                //A formatting variable to help keep everything with $0.00 format.

                if(inputValue.isEmpty() == true) {
                    Log.d("ERROR", "UpdateTipLabel:inputValue: NO INPUT VALUE");
                }
                else {
                    //We parse the user input to a double so they can input decimals.
                    Float price = Float.parseFloat(inputValue);
                    //We then divide our percent variable which we get passed into this function from the slider
                    //We must do 100.0 here or it will try to divide an int and a double.
                    //This would result in it being 0 until it reaches 100/100 because Ints dont have decimal places.
                    Float rawResult = (percent*price)/100;
                    //Setting a String variable with the proper dollar format.
                    String formattedResult = "$"+dollarFormat.format(rawResult);
                    //Finally we put our formatted result in a string to be written to the screen.
                    tipAmount.setText(formattedResult);
                    //Then we update the total.
                    UpdateTotalLabel(rawResult,price);
                }
            }

            public void UpdateTotalLabel(Float rawResult, Float price) {
                Float rawTotal = rawResult + price;
                String formattedTotal = "$"+dollarFormat.format(rawTotal);

                totalAmount.setText(formattedTotal);
            }
        });

    }

}