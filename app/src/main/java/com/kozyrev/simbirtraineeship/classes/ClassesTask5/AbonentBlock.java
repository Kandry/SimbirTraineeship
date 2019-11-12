package com.kozyrev.simbirtraineeship.classes.ClassesTask5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class AbonentBlock {
    private List<Abonent> abonents = new ArrayList<>(5);

    public AbonentBlock(){
        abonents.add(new Abonent(0, "Пупкин", "Ваилий", "Иванович", "Самара, Самарская 15-1", "88888", 1, 1, 567, 154897));
        abonents.add(new Abonent(1, "Петров", "Ваилий", "Иванович", "Самара, Самарская 15-1", "5646512318", 1, 1, 624657, 0));
        abonents.add(new Abonent(2, "Андропов", "Ваилий", "Иванович", "Самара, Ставропольская 29-10", "777777", 1, 1, 0, 10));
        abonents.add(new Abonent(3, "Завьялова", "Василиса", "Ивановна", "Самара, Самарская 15-1", "472725 7457", 1, 1, 44664, 4));
        abonents.add(new Abonent(4, "Пупкин", "Владимир", "Иванович", "Самара, Самарская 15-1", "88889", 10, 10, 7594324, 7787));
    }

    public void getAbonentsManyInCityTimeSpeak(long time){
        List<Abonent> speakers = new LinkedList<>();
        for (Abonent abonent: abonents) {
            if (abonent.getInCityTimeSpeak() > time) speakers.add(abonent);
        }
        System.out.println("Абоненты, у которых время городских переговоров превышает заданное: " + speakers.toString());
    }

    public void getAbonentsOutCitySpeaked(){
        List<Abonent> speakers = new LinkedList<>();
        for (Abonent abonent: abonents) {
            if (abonent.getOutCityTimeSpeak() > 0) speakers.add(abonent);
        }
        System.out.println("Абоненты, которые пользовались междугородней связью: " + speakers.toString());
    }

    public void sortAbonents(){
        Collections.sort(abonents, new Comparator<Abonent>() {
            @Override
            public int compare(Abonent abonent1, Abonent abonent2) {
                return abonent1.compareTo(abonent2);
            }
        });

        System.out.println("Абоненты в алфавитном порядке: " + abonents.toString());
    }
}
