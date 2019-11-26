package com.kozyrev.simbirtraineeship;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    public SearchFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initToolbar();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem = menu.findItem(R.id.top_nav_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.clearFocus();
        searchView.setQueryHint("Введите название организации");
        searchView.setBackgroundResource(R.drawable.search_bg);

        View v = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        v.setBackgroundColor(Color.WHITE);
        //searchView.setBackgroundColor(Color.WHITE);
        /*
        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchView.findViewById(searchPlateId);
        if (searchPlate!=null) {
            searchPlate.setBackgroundColor(Color.DKGRAY);
            int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
            if (searchText!=null) {
                searchText.setTextColor(Color.WHITE);
                searchText.setHintTextColor(Color.WHITE);
            }
        }*/


        EditText searchBox = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchBox.setBackgroundColor(Color.WHITE);
       // searchBox.setBackgroundColor(Color.WHITE);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("");
        toolbarTitle.setVisibility(View.GONE);
        //setHasOptionsMenu(true);

        SearchView searchView = getActivity().findViewById(R.id.toolbar_search);
        searchView.setVisibility(View.VISIBLE);
        searchView.setIconifiedByDefault(false);
        searchView.clearFocus();
        searchView.setQueryHint("Введите название организации");
        View v = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        v.setBackgroundColor(Color.WHITE);
    }
}
