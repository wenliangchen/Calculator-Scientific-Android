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
    //decimalFormatf
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalcPower = new CalcPower();
        // new Calc Power for Calc
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
        // Save change
        outState.putDouble("OPERAND", CalcPower.getResult());
        outState.putDouble("MEMORY", CalcPower.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore change
        CalcPower.setOperand(savedInstanceState.getDouble("OPERAND"));
        CalcPower.setMemory(savedInstanceState.getDouble("MEMORY"));
        Display.setText(decimalFormatf.format(CalcPower.getResult()));
    }

    @Override
    public void onClick(View v) {
        //OnClick View
        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            if (isInTheMiddleOfTypingANumber) {
                //protected
                if (buttonPressed.equals(".") && Display.getText().toString().contains(".")) {
                } else {
                    Display.append(buttonPressed);
                }

            } else {
                //protected
                if (buttonPressed.equals(".")) {
                    Display.setText(0 + buttonPressed);
                } else {
                    Display.setText(buttonPressed);
                }

                isInTheMiddleOfTypingANumber = true;
            }

        } else {
            if (isInTheMiddleOfTypingANumber) {
                //protected
                CalcPower.setOperand(Double.parseDouble(Display.getText().toString()));
                isInTheMiddleOfTypingANumber = false;
            }
            CalcPower.theOperation(buttonPressed);
            Display.setText(decimalFormatf.format(CalcPower.getResult()));

        }
    }
}

class CalcPower {
    // the Calc Power for the Calc
    private double operand;
    private double waitingOperand;
    private String waitingOperator;
    private double calculatorMemory;

    // types
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final String CLEAR = "C" ;
    public static final String SQUAREROOT = "√";
    public static final String SQUARED = "x²";
    public static final String INVERT = "1/x";
    public static final String TOGGLESIGN = "+/-";
    public static final String SINE = "sin";
    public static final String COSINE = "cos";
    public static final String TANGENT = "tan";

    public CalcPower() {
        // initialize variables
        operand = 0;
        waitingOperand = 0;
        waitingOperator = "";
        calculatorMemory = 0;
    }

    public void setOperand(double operand) {
        this.operand = operand;
    }

    public double getResult() {
        return operand;
    }

    public void setMemory(double calculatorMemory) {
        this.calculatorMemory = calculatorMemory;
    }

    public double getMemory() {
        return calculatorMemory;
    }


    //calc Operation
    public double theOperation(String operator) {

        if (operator.equals(CLEAR)) {
            operand = 0;
            waitingOperator = "";
            waitingOperand = 0;
            // calculatorMemory = 0;
        } else if (operator.equals(SQUAREROOT)) {
            operand = Math.sqrt(operand);

        } else if (operator.equals(SQUARED)) {
            operand = operand * operand;

        } else if (operator.equals(INVERT)) {
            if (operand != 0) {
                operand = 1 / operand;
            }
        } else if (operator.equals(TOGGLESIGN)) {
            operand = -operand;
        } else if (operator.equals(SINE)) {
            operand = Math.sin(Math.toRadians(operand));
        } else if (operator.equals(COSINE)) {
            operand = Math.cos(Math.toRadians(operand));
        } else if (operator.equals(TANGENT)) {
            operand = Math.tan(Math.toRadians(operand));
        } else {
            theWaitingOperation();
            waitingOperator = operator;
            waitingOperand = operand;
        }

        return operand;
    }
    //waiting Operation
    public void theWaitingOperation() {

        if (waitingOperator.equals(ADD)) {
            operand = waitingOperand + operand;
        } else if (waitingOperator.equals(SUBTRACT)) {
            operand = waitingOperand - operand;
        } else if (waitingOperator.equals(MULTIPLY)) {
            operand = waitingOperand * operand;
        } else if (waitingOperator.equals(DIVIDE)) {
            if (operand != 0) {
                operand = waitingOperand / operand;
            }
        }

    }

    public String toString() {
        return Double.toString(operand);
    }
}


