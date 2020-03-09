package br.com.guilherme.gastos.utils;

import java.util.Arrays;
import java.util.List;

import br.com.guilherme.gastos.TesteUnitario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IterableToCollectionTest implements TesteUnitario {

    @Test
    void toList() {
        Iterable<String> iterable = Arrays.asList("Teste1", "Teste2");

        IterableToCollection<String> iterableToCollection = new IterableToCollection<>();

        List<String> strings = iterableToCollection.toList(iterable);

        assertEquals(2, strings.size(), "Tamanho da lista diferente do esperado");
        assertEquals("Teste1", strings.get(0), "Item 1 da lista diferente do esperado");
        assertEquals("Teste2", strings.get(1), "Item 2 da lista diferente do esperado");
    }
}