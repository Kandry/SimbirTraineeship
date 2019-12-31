package com.kozyrev.simbirtraineeship.profile_fragment;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ProfileFragmentModel implements ProfileFragmentContract.Model {

    @Override
    public void getUserData(OnFinishedListener onFinishedListener, int id) {
        User user = new User(100000, R.drawable.image_man, "Константинов Денис");
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        try {
            user.setDateBirthTime(format.parse("1-Feb-1990").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setProfession("Хирургия, травмотология");
        user.setPush(true);
        List<User> friends = new LinkedList<>();
        friends.add(new User(100001, R.drawable.avatar_3, "Дмитрий Валерьевич"));
        friends.add(new User(100002, R.drawable.avatar_2, "Евгений Александров"));
        friends.add(new User(100003, R.drawable.avatar_1, "Виктор Кузнецов"));
        user.setFriends(friends);
        onFinishedListener.onFinished(user);
    }
}
