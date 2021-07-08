package br.com.meli.desafiospring.utils;

import java.util.concurrent.TimeUnit;

public class DateUtils {

    /**
     * Obtem a diferença entre duas êpocas em dias
     * @param startTime Êpoca inicial
     * @param endTime Êpoca final
     * @return diferença entre as duas épocas em dias
     */
    public static long getDifferenceBetweenEpochsInDays(long startTime, long endTime) {
        long difference = endTime - startTime;
        return TimeUnit.MILLISECONDS.toDays(difference);
    }

}
