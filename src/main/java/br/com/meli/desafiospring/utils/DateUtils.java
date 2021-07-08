package br.com.meli.desafiospring.utils;

import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static long getDifferenceBetweenEpochsInDays(long startTime, long endTime) {
        long difference = endTime - startTime;
        return TimeUnit.MILLISECONDS.toDays(difference);
    }

}
