package com.petertailor.belsokonyveles.service;

import org.junit.Test;
import java.util.Date;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DateCreaterTest {

    private final String EXPECTED_DATE = "Fri Mar 23 00:00:00 CET 1990";

    @Test(expected = IllegalArgumentException.class)
    public void testException() {

        String date = "01333";
        Date dateObject = DateCreater.dateParser(date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionNoNumbers() {

        String date = "Hali";
        Date dateObject = DateCreater.dateParser(date);
    }

    @Test(expected = NullPointerException.class)
    public void testNull() {

        String date = null;
        Date dateObject = DateCreater.dateParser(date);
    }

    @Test
    public void testNoExceptionOver() {

        String date = "1990.13.33";
        Date dateObject = DateCreater.dateParser(date);
        assertThat(dateObject.toString(), equalTo("Sat Feb 02 00:00:00 CET 1991"));
    }

    @Test
    public void testCaseWithNoSeparators() {
        String date = "19900323";
        Date dateObject = DateCreater.dateParser(date);
        assertThat(dateObject.toString(), equalTo(EXPECTED_DATE));
    }

    @Test
    public void testCaseWithNoSeparators2() {
        String date = "900323";
        Date dateObject = DateCreater.dateParser(date);
        assertThat(dateObject.toString(), equalTo(EXPECTED_DATE));
    }

    @Test
    public void testCaseWithSeparator1() {
        String date = "1990-03-23";
        Date dateObject = DateCreater.dateParser(date);
        assertThat(dateObject.toString(), equalTo(EXPECTED_DATE));
    }

    @Test
    public void testCaseWithSeparator2() {
        String date = "1990 03 23";
        Date dateObject = DateCreater.dateParser(date);
        assertThat(dateObject.toString(), equalTo(EXPECTED_DATE));
    }

    @Test
    public void testCaseWithSeparator3() {
        String date = "1990.03.23";
        Date dateObject = DateCreater.dateParser(date);
        assertThat(dateObject.toString(), equalTo(EXPECTED_DATE));
    }


    @Test
    public void testCaseWithSeparator5() {
        String date = "90-03-23";
        Date dateObject = DateCreater.dateParser(date);
        assertThat(dateObject.toString(), equalTo(EXPECTED_DATE));
    }

    @Test
    public void testCaseWithSeparator6() {
        String date = "90 03 23";
        Date dateObject = DateCreater.dateParser(date);
        assertThat(dateObject.toString(), equalTo(EXPECTED_DATE));
    }

    @Test
    public void testCaseWithSeparator7() {
        String date = "90.03.23";
        Date dateObject = DateCreater.dateParser(date);
        assertThat(dateObject.toString(), equalTo(EXPECTED_DATE));
    }

}
