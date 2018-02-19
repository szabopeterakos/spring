package com.petertailor.belsokonyveles.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DateIntervalValidator {

    public DateIntervalValidator() {
    }

    public static Date[] intervallCreator(String s){
        Date[] dates = new Date[2];

        // string silces
        String[] stringDate = new String[2];
        stringDate[0] = s.substring(0,s.indexOf("-"));
        stringDate[1] = s.substring(s.indexOf("-")+1);

        //date parsing and store
        dates[0] = DateCreater.dateParser(stringDate[0]);
        dates[1] = DateCreater.dateParser(stringDate[1]);

        return dates;
    }

}
