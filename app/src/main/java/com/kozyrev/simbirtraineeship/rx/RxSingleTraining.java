package com.kozyrev.simbirtraineeship.rx;

import com.kozyrev.simbirtraineeship.exceptions.ExpectedException;

import java.util.List;
import java.util.NoSuchElementException;

import io.reactivex.Observable;
import io.reactivex.Single;

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
                .create(emitter -> {
                    if (value > 0) emitter.onSuccess(value);
                    else emitter.onError(new ExpectedException());
                });
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
        final int[] sum = {0};
        integerObservable.subscribe(integer -> sum[0] += integer);
        return Single.just(sum[0]);
    }

    /**
     * Преобразование последовательности в список
     *
     * @param integerObservable {@link Observable} произвольная последовательность чисел
     * @return {@link Single} который эммитит {@link List} со всеми элементами последовательности
     * {@code integerObservable}
     */
    Single<List<Integer>> collectionOfValues(Observable<Integer> integerObservable) {
        return integerObservable.toList();
    }

    /**
     * Проверка всех элементов на положительность
     *
     * @param integerSingle {@link Observable} произвольная последовательность чисел
     * @return {@link Single} который эммитит {@code true} если все элементы последовательности
     * {@code integerSingle} положительны, {@code false} если есть отрицательные элементы
     */
    Single<Boolean> allElementsIsPositive(Observable<Integer> integerSingle) {
        final boolean[] isPositive = {true};
        integerSingle.subscribe(integer -> {if (integer < 1) isPositive[0] = false;});

        return Single.just(isPositive[0]);
    }

}
