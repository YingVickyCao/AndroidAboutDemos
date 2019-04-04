package com.example.hades.tdd.junit.matchers_and_assertthat;

import com.example.hades.tdd.junit.Calculator;
import com.example.hades.tdd.junit.Stu;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class TestMatchersAssertThat {

    @Test
    public void moreReadableAndTypeable() {
        int x = 3;
//        String s = "colour";
        String s = "colourKL";
        List<String> myList = new ArrayList<>();
        myList.add("4");
        myList.add("3");
        myList.add("5");

        Assert.assertThat(x, is(3));
        Assert.assertThat(x, not(4));
        Assert.assertThat(x, is(not(4))); // is(not(value)) = not(value)

        /**
         * ERROR:java.lang.AssertionError:
         Expected: is <com.example.hades.tdd.junit.Calculator@3f8f9dd6>
         but: was <com.example.hades.tdd.junit.Calculator@aec6354>
         */
        Assert.assertThat(new Calculator(), not(new Calculator()));
        Assert.assertThat(new Stu("A", 10), is(new Stu("A", 10)));

        Assert.assertNotEquals(new Calculator(), new Calculator());
        Assert.assertEquals(new Stu("A", 10), new Stu("A", 10));
    }

    @Test
    public void combinations() {
//        String s = "colour";
        String s = "colourKL";
        List<String> list = new ArrayList<>();
        list.add("4");
        list.add("3");
        list.add("5");

        Assert.assertThat(s, either(containsString("color")).or(containsString("colour")));
        Assert.assertThat(list, hasItem("3"));
    }

    // Readable failure messages
    @Test
    public void readableFailureMessages() {
        String string = "Please choose a font";

//         ==> failure message:
//         java.lang.AssertionError:
        Assert.assertTrue(string.contains("color") || string.contains("colour"));


//        failure message:
//        java.lang.AssertionError:
//        Expected :(a string containing "color" or a string containing "colour")
//        Actual   :"Please choose a font"
        Assert.assertThat(string, anyOf(containsString("color"), containsString("colour")));
    }
}
