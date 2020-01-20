package com.kozyrev.simbirtraineeship.news_fragment;

import android.content.Context;

import com.kozyrev.simbirtraineeship.base.ModelBase;
import com.kozyrev.simbirtraineeship.base.PresenterBase;
import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

public interface NewsFragmentContract {

    interface Model extends ModelBase {

        interface OnFinishedListener extends ModelBase.OnFinishedListener{

            void onFinished(List<Event> events);
        }

        void getEvents(OnFinishedListener onFinishedListener);

        void getEventsAsyncTask(OnFinishedListener onFinishedListener);

        void getEventsExecutors(OnFinishedListener onFinishedListener);

        void getEventsIntentService(OnFinishedListener onFinishedListener);

        List<Category> getCategories();

        void clearCategories();
    }

    interface View extends ViewBase {

        void setDataToRecyclerView (List<Event> events);

        void showEmptyView();

        void hideEmptyView();
    }

    interface Presenter extends PresenterBase {

        void requestDataFromFile();

        void clearCategories();
    }
}
