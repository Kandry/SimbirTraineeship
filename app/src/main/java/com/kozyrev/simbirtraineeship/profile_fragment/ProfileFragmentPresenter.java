package com.kozyrev.simbirtraineeship.profile_fragment;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerUser;
import com.kozyrev.simbirtraineeship.model.User;

public class ProfileFragmentPresenter implements Presenter, OnFinishedListenerUser {

    private View profileFragmentView;
    private Model profileFragmentModel;

    public ProfileFragmentPresenter(View profileFragmentView){
        this.profileFragmentView = profileFragmentView;
        this.profileFragmentModel = new ProfileFragmentModel();
    }

    @Override
    public void requestUserData(int id) {
        profileFragmentModel.getUserData(this, id);
    }

    @Override
    public void onFinished(User user) {
        profileFragmentView.setDataToViews(user);
    }

    @Override
    public void onFailure(Throwable throwable) {
        profileFragmentView.onResponseFailure(throwable);
    }

    @Override
    public void onDestroy() {
        profileFragmentView = null;
    }
}
