package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPlus, btnMinus, btnTimes, btnDivide, btnClear, btnDecimal, btnEquals;
    TextView display;
    // values that will be operated on
    double val1, val2;
    // true after pressing btnEquals, so that the answer disappears when a number is input again
    boolean clearOnNextDigit;

    enum Operator{none, ADD, MINUS, MULTIPLY, DIVIDE}
    Operator op;

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

        op = Operator.none;
        val1 = 0;
        val2 = 0;
        clearOnNextDigit = false;

        // =====================  buttons for digits  =====================
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // only append the zero if it is not a repeated leading zero
                if (!checkLeadingZero()) {
                    digitBtnClicked(0);
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

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
               display.setText(display.getText() + ".");
            }
        });

        // =====================  operation buttons  =====================
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Double.parseDouble(display.getText().toString());
                clearScreen();
                op = Operator.ADD;

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Double.parseDouble(display.getText().toString());
                clearScreen();
                op = Operator.MINUS;
            }
        });

        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Double.parseDouble(display.getText().toString());
                clearScreen();
                op = Operator.MULTIPLY;
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Double.parseDouble(display.getText().toString());
                clearScreen();
                op = Operator.DIVIDE;
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val2 = Double.parseDouble(display.getText().toString());
                switch(op) {
                    case ADD:
                        display.setText(String.valueOf(val1 + val2));
                        break;
                    case MINUS:
                        display.setText(String.valueOf(val1 - val2));
                        break;
                    case MULTIPLY:
                        display.setText(String.valueOf(val1 * val2));
                        break;
                    case DIVIDE:
                        display.setText(String.valueOf(val1 / val2));
                        break;
                } // end of switch
                op = Operator.none;
                val1 = 0;
                val2 = 0;
                clearOnNextDigit = true;
            }
        });

        // =====================  other buttons  =====================
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                op = Operator.none;
                val1 = 0;
                val2 = 0;
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
        if (clearOnNextDigit) {
            clearScreen();
            clearOnNextDigit = false;
        }
        // if there is only a leading zero, it will be replaced with the digit
        if (checkLeadingZero()) {
            clearScreen();
        }

        display.setText(display.getText() + String.valueOf(digit));
    } // end of digitBtnClicked()


    /**
     * Clears all characters and digits off the screen
     * Called by OnClickListener of C button
     */
    protected void clearScreen() {
        display.setText("");
    } // end of clearScreen()

    /**
     * Checks if the current value is a single leading zero.
     *
     * @return true if it is, false otherwise.
     */
    protected boolean checkLeadingZero() {
        CharSequence currText = display.getText();

        return currText.length() == 1 && currText.charAt(0) == '0';
    } // end of checkLeadingZero()

} // end of MainActivity.java