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
    //Setting up Variables to be initialized later on.
    SeekBar tipSlider;
    TextView tipPercent;
    TextView tipAmount;
    TextView totalAmount;
    EditText priceInput;
    TextView qualityText;


    //The onCreate method fires when the application starts up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView just takes a layout, and tells the program what to show.
        setContentView(R.layout.activity_main);

        //Initializing variables for all of our labels/inputs
        tipSlider = findViewById(R.id.seekTipPercent_Slider);
        tipPercent = findViewById(R.id.tvTipPercent_Label);
        tipAmount = findViewById(R.id.tvTip_Amount);
        totalAmount = findViewById(R.id.tvTotal_Amount);
        priceInput = findViewById(R.id.tvPrice_Input);
        qualityText = findViewById(R.id.qualityText);

        //Gives us a number format for the labels.
        //Since its dollars, were formatting things with 0.00
        NumberFormat dollarFormat = new DecimalFormat("#0.00");

        //We take the SeekBar and and give it a change listener so our program is always listening
        //for any changes.
        tipSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //Just takes the tipPercent. and sets the text to % + i.
                //i = whatever the slider is on.

                //This just takes i and sets it as the percent text, also adds % at the end.
                tipPercent.setText(i + "%");
                //We then update the tip label
                UpdateTipLabel(i);
                //Finally we update the quality label.
                UpdateQualityLabel(i);
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
                    //The equation is simple. We take the %(the current place of the slider)
                    //and multiply it by the price the user inputs. then divide by 100.
                    Float rawResult = (percent*price)/100;
                    //This is taking our raw result, which can have a lot of decimal places.
                    //And uses our dollar format that we setup on line 41
                    String formattedResult = "$"+dollarFormat.format(rawResult);
                    //Finally we put our formatted result in a string to be written to the screen.
                    tipAmount.setText(formattedResult);
                    //Then we update the total.
                    UpdateTotalLabel(rawResult,price);
                }
            }

            public void UpdateTotalLabel(Float rawResult, Float price) {
                //To update the total we just add the price and rawResult from UpdateTipLabel.
                Float rawTotal = rawResult + price;
                //Then we format the rawResult with our dollarformat.
                String formattedTotal = "$"+dollarFormat.format(rawTotal);
                //Finally we just update the text to the proper textview.
                totalAmount.setText(formattedTotal);
            }

            public void UpdateQualityLabel(Integer i) {

                switch(i) {
                    case 0:
                        qualityText.setText("");
                        break;
                    case 1:
                        qualityText.setText(R.string.qual_bad);
                        qualityText.setTextColor(getResources().getColor(R.color.qual_0));
                        break;
                    case 3:
                        qualityText.setTextColor(getResources().getColor(R.color.qual_3));
                        break;
                    case 5:
                        qualityText.setText(R.string.qual_bad_better);
                        qualityText.setTextColor(getResources().getColor(R.color.qual_5));
                        break;
                    case 7:
                        qualityText.setTextColor(getResources().getColor(R.color.qual_7));
                        break;
                    case 9:
                        qualityText.setText(R.string.qual_fine);
                        qualityText.setTextColor(getResources().getColor(R.color.qual_9));
                        break;
                    case 11:
                        qualityText.setTextColor(getResources().getColor(R.color.qual_11));
                        break;
                    case 12:
                        qualityText.setTextColor(getResources().getColor(R.color.qual_12));
                        break;
                    case 13:
                        qualityText.setTextColor(getResources().getColor(R.color.qual_13));
                        break;
                    case 14:
                        qualityText.setTextColor(getResources().getColor(R.color.qual_14));
                        break;
                    case 15:
                        qualityText.setText(R.string.qual_average);
                        qualityText.setTextColor(getResources().getColor(R.color.qual_15));
                        break;
                    case 18:
                        qualityText.setText(R.string.qual_good);
                        qualityText.setTextColor(getResources().getColor(R.color.qual_18));
                        break;
                    case 25:
                        qualityText.setText(R.string.qual_great);
                        qualityText.setTextColor(getResources().getColor(R.color.qual_25));
                        break;
                    case 30:
                        qualityText.setText(R.string.qual_insane);
                        qualityText.setTextColor(getResources().getColor(R.color.qual_30));
                        break;
                    case 40:
                        qualityText.setTextColor(getResources().getColor(R.color.qual_40));
                        break;
                    case 50:
                        qualityText.setText(R.string.qual_wow);
                        qualityText.setTextColor(getResources().getColor(R.color.qual_50));
                        break;
                    case 70:
                        qualityText.setTextColor(getResources().getColor(R.color.qual_70));
                        break;
                    case 90:
                        qualityText.setTextColor(getResources().getColor(R.color.qual_90));
                        break;
                }
            }
        });

    }

}