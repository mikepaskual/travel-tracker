package com.mikepaskual.traveltracker.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {

    private final Supplier<T> supplier;
    private final List<Consumer<T>> modifiers;

    private GenericBuilder(Supplier<T> supplier) {
        this.supplier = supplier;
        this.modifiers = new ArrayList<>();
    }

    public static <T> GenericBuilder<T> of(Supplier<T> supplier) {
        return new GenericBuilder<T>(supplier);
    }

    public <P> GenericBuilder<T> with(BiConsumer<T, P> consumer, P value) {
        modifiers.add(instance -> consumer.accept(instance, value));
        return this;
    }

    public T  build() {
        T value = supplier.get();
        modifiers.forEach(consumer -> consumer.accept(value));
        return value;
    }
}
