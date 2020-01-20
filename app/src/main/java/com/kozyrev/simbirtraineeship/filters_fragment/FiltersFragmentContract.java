package com.kozyrev.simbirtraineeship.filters_fragment;

import com.kozyrev.simbirtraineeship.base.ModelBase;
import com.kozyrev.simbirtraineeship.base.PresenterBase;
import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public interface FiltersFragmentContract {

    interface Model extends ModelBase {

        interface OnFinishedListener extends ModelBase.OnFinishedListener{

            void onFinished(List<Category> categories);
        }

        void getFilters(OnFinishedListener onFinishedListener);

        void getFiltersAsyncTask(OnFinishedListener onFinishedListener);

        void getFiltersExecutors(OnFinishedListener onFinishedListener);

        void getFiltersIntentService(OnFinishedListener onFinishedListener);

        void setFilters(List<Category> categories);
    }

    interface View extends ViewBase {

        void setDataToRecyclerView(List<Category> categories);

        void showEmptyView();

        void hideEmptyView();
    }

    interface Presenter extends PresenterBase {

        void requestDataFromFile();

        void setDataToFile(List<Category> categories);
    }
}
