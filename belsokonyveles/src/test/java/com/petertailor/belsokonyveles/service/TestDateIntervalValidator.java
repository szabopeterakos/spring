package com.petertailor.belsokonyveles.service;

import com.petertailor.belsokonyveles.utilities.DateIntervalValidator;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestDateIntervalValidator {

    @Test
    public void validDates(){
        Date[] array = DateIntervalValidator.intervallCreator("20080808-20090909");
        assertEquals(array.length,2);
    }

    @Test
    public void validDates2(){
        Date[] array = DateIntervalValidator.intervallCreator("2008.08.08-2009.09.09");
        assertEquals(array.length,2);
    }

    @Test
    public void validDates3(){
        Date[] array = DateIntervalValidator.intervallCreator("2008 08 08-2009 09 09");
        assertEquals(array.length,2);
    }

    @Test
    public void validDates4(){
        Date[] array = DateIntervalValidator.intervallCreator("08 08 08-09 09 09");
        assertEquals(array.length,2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException(){
        Date[] array = DateIntervalValidator.intervallCreator("08-08-08 09-09-09");
        assertEquals(array.length,2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException2(){
        Date[] array = DateIntervalValidator.intervallCreator("08.08.08 09.09.09");
        assertEquals(array.length,2);
    }


}
