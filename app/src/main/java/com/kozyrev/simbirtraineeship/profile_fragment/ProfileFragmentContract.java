package com.kozyrev.simbirtraineeship.profile_fragment;

import com.kozyrev.simbirtraineeship.base.ModelBase;
import com.kozyrev.simbirtraineeship.base.PresenterBase;
import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.User;

public interface ProfileFragmentContract {

    interface Model extends ModelBase{

        interface OnFinishedListener extends ModelBase.OnFinishedListener{

            void onFinished(User user);
        }

        void getUserData(OnFinishedListener onFinishedListener, int id);
    }

    interface View extends ViewBase{

        void setDataToViews(User user);
    }

    interface Presenter extends PresenterBase{

        void requestUserData(int id);
    }
}
