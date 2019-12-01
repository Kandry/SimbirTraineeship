package com.kozyrev.simbirtraineeship.search_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.NCOAdapter;
import com.kozyrev.simbirtraineeship.model.NCO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NCOsFragment extends Fragment {

    List<NCO> randomNCO = new ArrayList<>();

    NCOAdapter NCOAdapter;

    List<NCO> ncos;

    public NCOsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ncos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view){
        randomNCO.add(new NCO("Благотворительный фонд Алины Кабаевой"));
        randomNCO.add(new NCO("Во имя жизни"));
        randomNCO.add(new NCO("Благотворительный фонд В. Потанина"));
        randomNCO.add(new NCO("Детские домики"));
        randomNCO.add(new NCO("Мозаика счастья"));
        randomNCO.add(new NCO("Форум НКО Добрососедство"));
        randomNCO.add(new NCO("Проект Неслучайные встречи"));

        int eventsCount = (int) Math.random() * 7;
        ncos = new LinkedList<>();
        for (int i = 0; i < eventsCount; i++) ncos.add(randomNCO.get(i));

        RecyclerView recyclerView = view.findViewById(R.id.rv_ncos);

        NCOAdapter = new NCOAdapter(ncos);
        recyclerView.setAdapter(NCOAdapter);
    }
}
