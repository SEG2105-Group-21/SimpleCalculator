package com.example.simplecalculator;

import static org.junit.Assert.*;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

public class MainActivityTest {

    MainActivity a;

    @Before
    public void setUp(){
        a = new MainActivity();
    }
    @Test
    public void testConcatStrings() {
        String s1 = "5";
        String s2 = "7";
        String s3 = "9";
        String s4 = "10";
        String s5 = "-1";
        String s6 = "23";


        assertEquals("57", a.concatStrings(s1,s2));
        assertEquals("910", a.concatStrings(s3,s4));
        assertEquals("-123", a.concatStrings(s5,s6));

    }

    @Test
    public void testDoOperation() {
        double var1 = 6;
        double var2 = 3;
        double var3 = 2;
        double var4 = 1;
        assertEquals("9.0", a.doOperation(var1,var2, MainActivity.Operator.ADD));
        assertEquals("3.0", a.doOperation(var1,var2, MainActivity.Operator.MINUS));
        assertEquals("18.0", a.doOperation(var1,var2, MainActivity.Operator.MULTIPLY));
        assertEquals("2.0", a.doOperation(var1,var2, MainActivity.Operator.DIVIDE));
        assertEquals("-1.0", a.doOperation(var4,var3, MainActivity.Operator.MINUS));
    }

    @Test
    public void testClearScreen() {

    }

    @Test
    public void testCheckLeadingZero() {
        CharSequence text = "0";
        CharSequence text2 = "10";
        CharSequence text3 = "1";
        CharSequence text4 = "-0";
        assertTrue(a.checkLeadingZero(text));
        assertFalse(a.checkLeadingZero(text2));
        assertFalse(a.checkLeadingZero(text3));
        assertFalse(a.checkLeadingZero(text4));

    }
}