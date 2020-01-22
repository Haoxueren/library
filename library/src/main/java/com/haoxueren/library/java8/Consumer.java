package com.haoxueren.library.java8;

/**
 * CREATE BY HaoMingliang ON 2019/12/7
 */
@FunctionalInterface
public interface Consumer<T> {
    /**
     * Consume the given value.
     *
     * @param t the value
     */
    void accept(T t);

}
