package br.com.guilherme.gastos.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IterableToCollectionTest {

    @SuppressWarnings("unchecked")
    @Test
    void toList() {
        Iterable iterable = Arrays.asList("Teste1", "Teste2");

        IterableToCollection<String> iterableToCollection = new IterableToCollection<>();

        List<String> strings = iterableToCollection.toList(iterable);

        assertEquals(2, strings.size(), "Tamanho da lista diferente do esperado");
        assertEquals("Teste1", strings.get(0), "Item 1 da lista diferente do esperado");
        assertEquals("Teste2", strings.get(1), "Item 2 da lista diferente do esperado");
    }
}