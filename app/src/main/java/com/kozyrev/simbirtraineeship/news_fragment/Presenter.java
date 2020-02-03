package com.kozyrev.simbirtraineeship.news_fragment;

import com.kozyrev.simbirtraineeship.base.PresenterBase;

public interface Presenter extends PresenterBase {

    void requestDataFromFile();

    void clearCategories();
}
