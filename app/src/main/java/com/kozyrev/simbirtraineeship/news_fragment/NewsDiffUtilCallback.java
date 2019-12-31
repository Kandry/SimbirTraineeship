package com.kozyrev.simbirtraineeship.news_fragment;

import androidx.recyclerview.widget.DiffUtil;

import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

public class NewsDiffUtilCallback extends DiffUtil.Callback {

    private final List<Event> oldList;
    private final List<Event> newList;

    public NewsDiffUtilCallback(List<Event> oldList, List<Event> newList){
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition){
        Event oldEvent = oldList.get(oldItemPosition);
        Event newEvent = oldList.get(newItemPosition);
        return oldEvent.getId() == newEvent.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Event oldEvent = oldList.get(oldItemPosition);
        Event newEvent = newList.get(newItemPosition);

        boolean isNotOldImagesIsEmpty = oldEvent.getImagesUri().size() > 0;
        boolean isNotNewImagesIsEmpty = newEvent.getImagesUri().size() > 0;

        boolean isImagesTheSame = isNotOldImagesIsEmpty && isNotNewImagesIsEmpty ? oldEvent.getImagesUri().get(0).equals(newEvent.getImagesUri().get(0)) : false;

        return oldEvent.getName().equals(newEvent.getName())
                && oldEvent.getDescription().equals(newEvent.getDescription())
                && oldEvent.getStartDate() == newEvent.getStartDate()
                && oldEvent.getEndDate() == newEvent.getEndDate()
                && isImagesTheSame
                && isCategoriesTheSame(oldEvent, newEvent);
    }

    private boolean isCategoriesTheSame(Event oldEvent, Event newEvent){
        List<Integer> oldEventCategories = oldEvent.getCategoriesID();
        List<Integer> newEventCategories = newEvent.getCategoriesID();

        if (oldEventCategories.size() != newEventCategories.size()) return false;

        for (int i = 0; i < oldEventCategories.size(); i++){
            if (!oldEventCategories.get(i).equals(newEventCategories.get(i))) return false;
        }

        return true;
    }
}
