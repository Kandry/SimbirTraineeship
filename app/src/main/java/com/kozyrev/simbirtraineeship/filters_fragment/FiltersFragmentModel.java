package com.kozyrev.simbirtraineeship.filters_fragment;

import android.content.Context;

import com.kozyrev.simbirtraineeship.model.Category;

import java.util.ArrayList;
import java.util.List;

public class FiltersFragmentModel implements FiltersFragmentContract.Model {

    private static final String FILE_NAME = "categories.json"; //app/src/main/assets/categories.json
    private Context context;

    FiltersFragmentModel(Context context){
        this.context = context;
    }

    @Override
    public void getFilters(OnFinishedListener onFinishedListener) {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Деньги"));
        categories.add(new Category("Вещи"));
        categories.add(new Category("Проф. помощь"));
        categories.add(new Category("Волонтерство"));
        onFinishedListener.onFinished(categories);//JSONHelperCategory.importFromJSON(context, FILE_NAME));
    }
}
