package com.petertailor.belsokonyveles.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCreater {

    public DateCreater() {
    }

    public static Date dateParser(String date){
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date c = null;

        if (date.matches("^[1-2][0-9]{3}[0-1][0-9][0-3][0-9]$")) {
            sdf = new SimpleDateFormat("yyyyMMdd");
        } else if (date.matches("^[0-9]{2}[0-1][0-9][0-3][0-9]$")) {
            sdf = new SimpleDateFormat("yyMMdd");
        }

        else if (date.matches("^[1-2][0-9]{3}-[0-1][0-9]-[0-3][0-9]$")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else if (date.matches("^[1-2][0-9]{3}\\.[0-1][0-9]\\.[0-3][0-9]$")) {
            sdf = new SimpleDateFormat("yyyy.MM.dd");
        } else if (date.matches("^[0-9]{2}\\-[0-1][0-9]\\-[0-3][0-9]$")) {
            sdf = new SimpleDateFormat("yy-MM-dd");
        }

        else if (date.matches("^[0-9]{2}-[0-1][0-9]-[0-3][0-9]$")) {
            sdf = new SimpleDateFormat("yy.MM.dd");
        } else if (date.matches("^[0-9]{2}\\.[0-1][0-9]\\.[0-3][0-9]$")) {
            sdf = new SimpleDateFormat("yy.MM.dd");
        }

        else if (date.matches("^[0-9]{2} [0-1][0-9] [0-3][0-9]$")) {
            sdf = new SimpleDateFormat("yy MM dd");
        } else if (date.matches("^[1-2][0-9]{3} [0-1][0-9] [0-3][0-9]$")) {
            sdf = new SimpleDateFormat("yyyy MM dd");
        }

        // parsing with correct simpleDateFormant
        try {
            c = sdf.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("A dátum formátuma nem megfelelő");
        }
         return c;
    }

}
