package br.com.meli.desafiospring.utils;

import java.util.Collections;
import java.util.List;

public class SortUtils {

    public static <T> void sort(List<T> list, String order) {
        if (order == null || order.contains("asc")) {
            list.sort(null);
        } else {
            list.sort(Collections.reverseOrder());
        }
    }

}
