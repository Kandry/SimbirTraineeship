package com.kozyrev.simbirtraineeship.helps_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.HelpsAdapter;
import com.kozyrev.simbirtraineeship.model.HelpItem;

import java.util.LinkedList;
import java.util.List;

public class HelpsFragment extends Fragment {

    private List<HelpItem> helps;

    public HelpsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        initToolbar();
        initHelps();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvHelps = getView().findViewById(R.id.rv_helps);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        rvHelps.setLayoutManager(layoutManager);

        HelpsAdapter helpsAdapter = new HelpsAdapter(helps);
        rvHelps.setAdapter(helpsAdapter);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        //toolbarTitle.setVisibility(View.VISIBLE);
        toolbarTitle.setText(R.string.nav_help);
        SimpleSearchView searchView = getActivity().findViewById(R.id.toolbar_search);
        searchView.setVisibility(View.GONE);
    }

    private void initHelps(){
        helps = new LinkedList<>();
        helps.add(new HelpItem(R.drawable.children, "Дети"));
        helps.add(new HelpItem(R.drawable.adults, "Взрослые"));
        helps.add(new HelpItem(R.drawable.such_adults, "Пожилые"));
        helps.add(new HelpItem(R.drawable.animals, "Животные"));
        helps.add(new HelpItem(R.drawable.events, "Мероприятия"));
    }
}
