package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * A simple calculator application that can perform addition, subtraction, multiplication, and division
 *  on numbers.
 *
 * @author Kien Do
 * @author Philip Ostroscki
 * @author Jordan Takefman
 * @author Isaac Kuruvilla
 * @version 1.0 (2021/11/25)
 * @since version 0.0
 */
public class MainActivity extends AppCompatActivity {
    /** Represents the calculator buttons */
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPlus, btnMinus, btnTimes, btnDivide, btnClear, btnDecimal, btnEquals;
    /** Represents the text view that displays what the user has pressed, what the results on screen are, etc. */
    TextView display;
    /** Represents the values that are operated on. */
    double val1, val2;
    /** True after pressing btnEquals so that the answer disappears when a new number is inputted by the user. */
    boolean clearOnNextDigit;

    /** Class that represents the calculator's operators.*/
    enum Operator{none, ADD, MINUS, MULTIPLY, DIVIDE}
    /** Represents the selected calculator operations. */
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
        clearScreen(display); // used to reset the the app everytime the app is closed and then reopened

        // =====================  buttons for digits  =====================
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // only append the zero if it is not a repeated leading zero
                if (!checkLeadingZero(display.getText())) {
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

                // if a decimal is NOT present on screen, proceed...
                if (!display.getText().toString().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            }
        });

        // =====================  operation buttons  =====================
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (op != null && op != Operator.none) {
                    double chainVal = Double.parseDouble(display.getText().toString());
                    equalsMethod(true, chainVal);
                }

                val1 = Double.parseDouble(display.getText().toString());
                clearScreen(display);
                op = Operator.ADD;

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (op != null && op != Operator.none) {
                    double chainVal = Double.parseDouble(display.getText().toString());
                    equalsMethod(true, chainVal);
                }

                val1 = Double.parseDouble(display.getText().toString());
                clearScreen(display);
                op = Operator.MINUS;
            }
        });

        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (op != null && op != Operator.none) {
                    double chainVal = Double.parseDouble(display.getText().toString());
                    equalsMethod(true, chainVal);
                }
                val1 = Double.parseDouble(display.getText().toString());
                clearScreen(display);
                op = Operator.MULTIPLY;
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (op != null && op != Operator.none) {
                    double chainVal = Double.parseDouble(display.getText().toString());
                    equalsMethod(true, chainVal);
                }
                val1 = Double.parseDouble(display.getText().toString());
                clearScreen(display);
                op = Operator.DIVIDE;
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsMethod(false, 0);

            }
        });

        // =====================  other buttons  =====================
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen(display);
                op = Operator.none;
                val1 = 0;
                val2 = 0;
            }
        });

    }

    // ==========================  helper methods  ==========================

    /**
     * Called by onClickListener of number buttons. Updates the display and
     * values.
     * @param digit
     * Passed value integer
     *
     */
    protected void digitBtnClicked(int digit) {
        if (clearOnNextDigit) {
            clearScreen(display);
            clearOnNextDigit = false;
        }
        // if there is only a leading zero, it will be replaced with the digit
        if (checkLeadingZero(display.getText())) {
            clearScreen(display);
        }

        display.setText(concatStrings(display.getText().toString(), String.valueOf(digit)));
    } // end of digitBtnClicked()

    /**
     * Simply concatenates two strings.
     *
     * @param str1 The first substring.
     * @param str2 The second substring, to be concatenated to the back of str1.
     * @return Returns the concatenation of the two given strings, in the order they were given.
     *
     */
    public String concatStrings(String str1, String str2) {
        return str1+str2; // used to be static but changed it since it static methods cannot be tested
    }

    /**
     * This method acts as an equal button
     *
     * Created because btnEquals is not the only button or scenario where this function will need to be used.
     * For example, when the user clicks 1 + 1 + 1, the result will be 2.0.
     * This is because the first 1 gets overwritten when the second + symbol gets pressed.
     * We need this function so that when the user performs the action 1 + 1 + 1, the display
     *      should show "2" (and subsequently, val1 should be set to 2) when the second + symbol is pressed.
     * @param chain Chain marks if there are repeated operators prior to the equals
     * @param chainVal Sum to pass through as needed
     *
     */
    protected void equalsMethod(boolean chain, double chainVal) {
        String result;

        val2 = Double.parseDouble(display.getText().toString());
        clearOnNextDigit = true;

        //Detect operator present

        if (chain) {
            result = doOperation(val1, chainVal, op);

            if (result == null) {
                clearOnNextDigit = false;
                val1 /= chainVal;
            } else {
                display.setText(result);
                val1 = Double.parseDouble(result);
            }

            val2 = 0;
            op = Operator.none;
        }

        else {
            result = doOperation(val1, val2, op);

            if (result == null) {
                clearOnNextDigit = false;
            } else {
                display.setText(result);
            }

            op = Operator.none;
            val1 = 0;
            val2 = 0;
        }

    } // end of equalsMethod()

    /**
     * Does the specified operation onto the two given values, to be returned in String format.
     *
     * @param val1 First double to be used in the operation.
     * @param val2 Second double to be used in the operation.
     * @param op Operator specified to be used on the two numbers.
     * @return String containing the result of the operation on the two numbers.
     *
     */
    public String doOperation(double val1, double val2, Operator op) {
        switch (op) {
            case ADD:
                return String.valueOf(val1 + val2);
            case MINUS:
                return String.valueOf(val1 - val2);
            case MULTIPLY:
                return String.valueOf(val1 * val2);
            case DIVIDE:
                return String.valueOf(val1 / val2);
        } // end of switch

        return null;
    }

    /**
     * Clears all characters and digits off the screen
     * Called by OnClickListener of C button
     *
     * @param tv TextView to make blank.
     *
     */
    protected void clearScreen(TextView tv) {
        tv.setText("");
    } // end of clearScreen()

    /**
     * Checks if the value in a character sequence is a single leading zero.
     *
     * @param text The string to check for the presence of a leading zero in.
     * @return true if it is, false otherwise.
     *
     */
    public boolean checkLeadingZero(CharSequence text) {
        return text.length() == 1 && text.charAt(0) == '0';
    } // end of checkLeadingZero()

} // end of MainActivity.java
