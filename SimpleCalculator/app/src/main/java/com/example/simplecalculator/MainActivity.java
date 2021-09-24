package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPlus, btnMinus, btnTimes, btnDivide, btnClear, btnDecimal, btnEquals;
    TextView display;
    // values that will be operated on
    double val1, val2;
    // whether the operator has been clicked and val2 is now being edited
    boolean optrClicked;
    // whether clicking btn0 would cause a useless leading zero
    boolean leadingZero;
    // how many decimal places are present so far on the currently-edited value
    int decimalDigit;

    enum Operator{none, ADD, MINUS, MULTIPLY, DIVIDE}
    Operator op = Operator.none;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        btn0 = findViewById(R.id.Btn0);
        btn1 = findViewById(R.id.Btn1);
        btn2 = findViewById(R.id.Btn2);
        btn3 = findViewById(R.id.Btn3);
        btn4 = findViewById(R.id.Btn4);
        btn5 = findViewById(R.id.Btn5);
        btn6 = findViewById(R.id.Btn6);
        btn7 = findViewById(R.id.Btn7);
        btn8 = findViewById(R.id.Btn8);
        btn9 = findViewById(R.id.Btn9);
        btnPlus = findViewById(R.id.BtnPlus);
        btnMinus = findViewById(R.id.BtnMinus);
        btnTimes = findViewById(R.id.BtnTimes);
        btnDivide = findViewById(R.id.BtnDivide);
        btnClear = findViewById(R.id.BtnClear);
        btnDecimal = findViewById(R.id.BtnDecimal);
        btnEquals = findViewById(R.id.BtnEquals);

        // =====================  buttons for digits  =====================
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentTextOnScreen = display.getText().toString();
                // only append the zero if it is not a repeated leading zero
                if (!leadingZero) {
                    digitBtnClicked(0);
                    // if pressing btn0 again would cause a useless leading zero
                    if ((!optrClicked && val1 == 0) || (optrClicked && val2 == 0)) {
                        leadingZero = true;
                    }
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitBtnClicked(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitBtnClicked(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitBtnClicked(3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitBtnClicked(4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitBtnClicked(5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitBtnClicked(6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitBtnClicked(7);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitBtnClicked(8);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitBtnClicked(9);
            }
        });

        // =====================  operation buttons  =====================
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optrClicked = true;
                val1 = Double.parseDouble(display.getText().toString());
                clearScreen();
                op = Operator.ADD;
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val2 = Double.parseDouble(display.getText().toString());
                switch(op) {
                    case ADD:
                        optrClicked = false;
                        op = Operator.none;
                        display.setText(String.valueOf(val1 + val2));
                        val1 = 0;
                        val2 = 0;
                    default:
                        // empty default
                } // end of switch

                //TODO: Clear the screen here when the next digit is typed.
            }
        });

        // =====================  other buttons  =====================
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
            }
        });

    }

    /**
     * Called by onClickListener of number buttons. Updates the display and
     * values.
     *
     * @param digit is the number associated with the pressed button.
     */
    protected void digitBtnClicked(int digit) {
        display.setText(display.getText() + String.valueOf(digit));

        if (optrClicked) {
            if (decimalDigit == 0) {
                val2 = 10 * val2 + digit;
            } else {
                val2 += digit/Math.pow(10, decimalDigit);
            }
        } else {
            if (decimalDigit == 0) {
                val1 = 10 * val1 + digit;
            } else {
                val1 += digit/Math.pow(10, decimalDigit);
            }
        }

        // any following zeros will not be leading zeros
        leadingZero = false;
    } // end of digitBtnClicked()


    /**
     * Clears all characters and digits off the screen
     * Called by OnClickListener of C button
     */
    protected void clearScreen() {
        display.setText("");
    } // end of clearScreen()

} // end of MainActivity.java