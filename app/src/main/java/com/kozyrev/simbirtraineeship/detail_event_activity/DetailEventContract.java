package com.kozyrev.simbirtraineeship.detail_event_activity;

import com.kozyrev.simbirtraineeship.base.ModelBase;
import com.kozyrev.simbirtraineeship.base.PresenterBase;
import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.Event;

public interface DetailEventContract {

    interface Model extends ModelBase{

        interface OnFinishedListener extends ModelBase.OnFinishedListener{

            void onFinished(Event event);
        }

        void getEventDetails(OnFinishedListener onFinishedListener, int id);

        void getEventDetailsAsyncTask(OnFinishedListener onFinishedListener, int id);

        void getEventDetailsExecutor(OnFinishedListener onFinishedListener, int id);

        void getEventDetailsIntentService(OnFinishedListener onFinishedListener, int id);

        void updateEvent(Event event);

    }

    interface View extends ViewBase{

        void setDataToViews(Event event);

        void showEmptyView();

        void hideEmptyView();
    }

    interface Presenter extends PresenterBase{

        void requestEventData(int id);
    }
}
