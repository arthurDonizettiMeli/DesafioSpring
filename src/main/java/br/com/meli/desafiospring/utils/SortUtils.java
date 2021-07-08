package br.com.meli.desafiospring.utils;

import java.util.Collections;
import java.util.List;

public class SortUtils {

    /**
     * Ordena uma lista de acordo com o parametro order
     * @param list Lista a ser ordenada
     * @param order Descrição da ordenação
     * @param <T> Generics do tipo da lista
     */
    public static <T> void sort(List<T> list, String order) {
        if (order == null || order.contains("asc")) {
            list.sort(null);
            return;
        }
        list.sort(Collections.reverseOrder());
    }

}
