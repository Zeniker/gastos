package br.com.guilherme.gastos.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IterableToCollection<T> {

    public List<T> toList(Iterable<T> iterable){
        return StreamSupport.stream(iterable.spliterator(), false)
                        .collect(Collectors.toList());
    }

}
