package com.kozyrev.simbirtraineeship.rx;

import com.kozyrev.simbirtraineeship.model.Entity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * @author Arthur Korchagin (artur.korchagin@simbirsoft.com)
 * @since 13.11.18
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RxTransformingTraining {

    /* Тренировочные методы */

    /**
     * Преобразование чисел в строки
     *
     * @param intObservable - источник
     * @return {@link Observable<String>} - который эммитит строки,
     * преобразованные из чисел в {@code intObservable}
     */
    public Observable<String> transformIntToString(Observable<Integer> intObservable) {
      /* List<Integer> numbers = new LinkedList<>();
       intObservable.subscribe(numbers::add);

       return Observable.fromArray(numbers.toArray(new Integer[0])).map(new Function<Integer, String>() {
           @Override
           public String apply(Integer integer) throws Exception {
               return String.valueOf(integer);
           }
       });*/
      return intObservable
              .flatMap((Function<Integer, Observable<String>>) integer -> Observable.just(String.valueOf(integer)));
    }

    /**
     * Преобразование {@link Observable<Integer>} эммитящих идентификаторы сущностей в сами
     * сущности, которые должны быть получены с помощью метода {@link #requestApiEntity(int)}
     *
     * @param idObservable - идентификаторы сущностей
     * @return {@link Observable<Entity>} эммитит сущности, соответствующие идентификаторам из
     * {@code idObservable}
     */
    public Observable<Entity> requestEntityById(Observable<Integer> idObservable) {
       /* List<Integer> numbers = new ArrayList<>();
        idObservable.subscribe(numbers::add);

        Observable<Entity>[] observables = new Observable[numbers.size()];
        for (int i = 0; i < numbers.size(); i++){
            observables[i] = requestApiEntity(numbers.get(i));
        }

        return Observable.concatArray(observables);*/
       return idObservable
               .flatMap((Function<Integer, Observable<Entity>>) integer -> requestApiEntity(integer));
    }

    /**
     * Распределение имён из {@code namesObservable} по первой букве имени, в отдельные
     * {@link GroupedObservable}
     *
     * @param namesObservable - {@link Observable<String>} с именами
     * @return {@link Observable} который эммитит {@link GroupedObservable} - сгруппированный
     * поток имён объединённых первой буквой в имени
     */
    public Observable<GroupedObservable<Character, String>> distributeNamesByFirstLetter(Observable<String> namesObservable) {

        return namesObservable
                .groupBy(s -> s.charAt(0));
    }

    /**
     * Объединить элементы, полученные из {@code intObservable} в списки {@link List} с максимальным
     * размером {@code listsSize}
     *
     * @param listsSize      максимальный размер списка элементов
     * @param intObservable  {@link Observable} с произвольным количеством рандомных чисел
     * @return {@code Observable} который эммитит списки чисел из {@code intObservable}
     */
    public Observable<List<Integer>> collectsIntsToLists(int listsSize, Observable<Integer> intObservable) {
        return intObservable
                .buffer(listsSize);
    }

    /* Вспомогательные методы */

    /**
     * Выполнение HTTP запроса и эммит полученной сущности, соответствующей заданному идентификатору
     * (Вспомогательный метод! Не изменять!)
     *
     * @param id - Идентификатор сущности {@link Entity}
     * @return {@link Observable<Entity>} который эммитит полученную сущность
     */
    Observable<Entity> requestApiEntity(int id) {
        // Выполнение запроса и эммит сущности
        return Observable.just(new Entity(id));
    }

}
