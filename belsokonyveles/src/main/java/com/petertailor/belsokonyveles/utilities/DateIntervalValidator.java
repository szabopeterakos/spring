package com.petertailor.belsokonyveles.utilities;

import java.util.Date;

public class DateIntervalValidator {

    public DateIntervalValidator() {
    }

    public static Date[] intervallCreator(String s) {
        if (!s.contains("-")) {
            throw new IllegalArgumentException("Nem megfelelő a formátum: " + s);
        }

        Date[] dates = new Date[2];

        // string silces
        String[] stringDate = new String[2];
        stringDate[0] = s.substring(0, s.indexOf("-"));
        stringDate[1] = s.substring(s.indexOf("-") + 1);

        //date parsing and store
        dates[0] = DateCreator.dateParser(stringDate[0]);
        dates[1] = DateCreator.dateParser(stringDate[1]);

        return dates;
    }

}
