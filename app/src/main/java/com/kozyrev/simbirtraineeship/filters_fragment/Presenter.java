package com.kozyrev.simbirtraineeship.filters_fragment;

import com.kozyrev.simbirtraineeship.base.PresenterBase;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public interface Presenter extends PresenterBase {

    void requestDataFromFile();

    //void setDataToFile(List<Category> categories);
}
