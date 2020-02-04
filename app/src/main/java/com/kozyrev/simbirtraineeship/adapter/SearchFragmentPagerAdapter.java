package com.kozyrev.simbirtraineeship.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kozyrev.simbirtraineeship.search_fragment.search_events_fragment.SearchEventsFragmentView;
import com.kozyrev.simbirtraineeship.search_fragment.search_nco_fragment.NCOsFragment;

public class SearchFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles = new String[]{"По мероприятиям", "По НКО"};

    public SearchFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SearchEventsFragmentView();
            case 1:
                return new NCOsFragment();

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
