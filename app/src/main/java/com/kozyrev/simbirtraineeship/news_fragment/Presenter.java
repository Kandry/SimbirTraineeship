package com.kozyrev.simbirtraineeship.news_fragment;

import com.kozyrev.simbirtraineeship.base.PresenterBase;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

public interface Presenter extends PresenterBase {

    void requestDataFromFile();

    //void clearCategories();
}
