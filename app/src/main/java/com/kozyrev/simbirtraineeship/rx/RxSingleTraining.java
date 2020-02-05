package com.kozyrev.simbirtraineeship.rx;

import com.kozyrev.simbirtraineeship.exceptions.ExpectedException;

import java.util.List;
import java.util.NoSuchElementException;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;

/**
 * @author Arthur Korchagin (artur.korchagin@simbirsoft.com)
 * @since 20.11.18
 */
public class RxSingleTraining {

    /* Тренировочные методы */

    /**
     * Эммит только 1 положительного элемента либо ошибка {@link ExpectedException}
     *
     * @param value любое произвольное число
     * @return {@code Single} который эммитит значение {@code value} если оно положительное,
     * либо ошибку {@link ExpectedException} если оно отрицательное
     */
    Single<Integer> onlyOneElement(Integer value) {
       return Single
               .just(value)
               .filter(integer -> integer > 0)
               .switchIfEmpty((SingleSource<Integer>) observer -> observer.onError(new ExpectedException()));
    }

    /**
     * Преобразование последовательности {@code Observable} в {@code Single}
     *
     * @param integerObservable {@link Observable} произвольная последовательность чисел
     * @return {@link Single} который эммитит либо самый первый элемент последовательности
     * {@code integerObservable}, либо ошибку {@link NoSuchElementException} в случае, если
     * последовательность пустая
     */
    Single<Integer> onlyOneElementOfSequence(Observable<Integer> integerObservable) {
        return integerObservable.firstOrError();
    }

    /**
     * Сумма всех элементов последовательности
     *
     * @param integerObservable {@link Observable} произвольная последовательность чисел
     * @return {@link Single} который эммитит сумму всех элементов, либо 0 если последовательность
     * пустая
     */
    Single<Integer> calculateSumOfValues(Observable<Integer> integerObservable) {
        return Single
                .just(
                        integerObservable
                                .defaultIfEmpty(0)
                                .reduce(0, (integer, integer2) -> integer + integer2)
                                .blockingGet()
                );
    }

    /**
     * Преобразование последовательности в список
     *
     * @param integerObservable {@link Observable} произвольная последовательность чисел
     * @return {@link Single} который эммитит {@link List} со всеми элементами последовательности
     * {@code integerObservable}
     */
    Single<List<Integer>> collectionOfValues(Observable<Integer> integerObservable) {
        return integerObservable
                .toList();
    }

    /**
     * Проверка всех элементов на положительность
     *
     * @param integerSingle {@link Observable} произвольная последовательность чисел
     * @return {@link Single} который эммитит {@code true} если все элементы последовательности
     * {@code integerSingle} положительны, {@code false} если есть отрицательные элементы
     */
    Single<Boolean> allElementsIsPositive(Observable<Integer> integerSingle) {
        return integerSingle
                .all(integer -> integer > 0);
    }

}
