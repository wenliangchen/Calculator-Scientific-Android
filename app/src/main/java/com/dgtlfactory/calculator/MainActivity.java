package com.dgtlfactory.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView Display;
    private Boolean isInTheMiddleOfTypingANumber = false;
    private CalcPower CalcPower;
    private static final String DIGITS = "0123456789.";

    DecimalFormat decimalFormatf = new DecimalFormat("@###########");

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalcPower = new CalcPower();
        Display = (TextView) findViewById(R.id.textView1);

        decimalFormatf.setMinimumFractionDigits(0);
        decimalFormatf.setMinimumIntegerDigits(1);
        decimalFormatf.setMaximumIntegerDigits(8);

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonToggleSign).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);

        
        if (findViewById(R.id.buttonSquareRoot) != null) {
            findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonSquared) != null) {
            findViewById(R.id.buttonSquared).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonInvert) != null) {
            findViewById(R.id.buttonInvert).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonSine) != null) {
            findViewById(R.id.buttonSine).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonCosine) != null) {
            findViewById(R.id.buttonCosine).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonTangent) != null) {
            findViewById(R.id.buttonTangent).setOnClickListener(this);
        }
    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save variables on screen orientation change
        outState.putDouble("OPERAND", CalcPower.getResult());
        outState.putDouble("MEMORY", CalcPower.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore variables on screen orientation change
        CalcPower.setOperand(savedInstanceState.getDouble("OPERAND"));
        CalcPower.setMemory(savedInstanceState.getDouble("MEMORY"));
        Display.setText(decimalFormatf.format(CalcPower.getResult()));
    }

    @Override
    public void onClick(View v) {
        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            // digit was pressed
            if (isInTheMiddleOfTypingANumber) {

                if (buttonPressed.equals(".") && Display.getText().toString().contains(".")) {
                } else {
                    Display.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {
                    Display.setText(0 + buttonPressed);
                } else {
                    Display.setText(buttonPressed);
                }

                isInTheMiddleOfTypingANumber = true;
            }

        } else {
            if (isInTheMiddleOfTypingANumber) {

                CalcPower.setOperand(Double.parseDouble(Display.getText().toString()));
                isInTheMiddleOfTypingANumber = false;
            }

            CalcPower.theOperation(buttonPressed);
            Display.setText(decimalFormatf.format(CalcPower.getResult()));

        }
    }
}
