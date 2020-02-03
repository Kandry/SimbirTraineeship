package com.kozyrev.simbirtraineeship.profile_fragment;

import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.User;

public interface View extends ViewBase {

    void setDataToViews(User user);
}
