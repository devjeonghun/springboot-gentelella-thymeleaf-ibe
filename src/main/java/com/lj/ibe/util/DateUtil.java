package com.lj.ibe.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {
    /**
     * @param String openDate("YYYY-MM-DD-HH-SS")
     * @param String offDate("YYYY-MM-DD-HH-SS")
     * @return Boolean
     */
    public static Boolean isControlledTime(String openDate, String offDate) {
        ZoneId seoul = ZoneId.of("Asia/Seoul");
        ZonedDateTime systemDateTime = ZonedDateTime.now(seoul);
        String[] strOpenDate = openDate.split("-");
        String[] strOffDate = offDate.split("-");
        ZonedDateTime openTime = ZonedDateTime.of(Integer.parseInt(strOpenDate[0]), Integer.parseInt(strOpenDate[1]), Integer.parseInt(strOpenDate[2]), Integer.parseInt(strOpenDate[3]), Integer.parseInt(strOpenDate[4]), 0, 0, seoul);
        ZonedDateTime offTime = ZonedDateTime.of(Integer.parseInt(strOffDate[0]), Integer.parseInt(strOffDate[1]), Integer.parseInt(strOffDate[2]), Integer.parseInt(strOffDate[3]), Integer.parseInt(strOffDate[4]), 0, 0, seoul);
        return systemDateTime.isAfter(openTime) && systemDateTime.isBefore(offTime);
    }
}
