package com.kozyrev.simbirtraineeship.search_fragment.search_nco_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.NCOAdapter;
import com.kozyrev.simbirtraineeship.model.NCO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NCOsFragment extends Fragment {

    private List<NCO> randomNCO = new ArrayList<>();
    private List<NCO> ncos;

    private NCOAdapter NCOAdapter;
    private TextView tvNcosResult;

    public NCOsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ncos, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View view){
        randomNCO.add(new NCO("Благотворительный фонд Алины Кабаевой"));
        randomNCO.add(new NCO("Во имя жизни"));

        randomNCO.add(new NCO("Благотворительный фонд В. Потанина"));
        randomNCO.add(new NCO("Детские домики"));
        randomNCO.add(new NCO("Мозаика счастья"));
        randomNCO.add(new NCO("Форум НКО Добрососедство"));
        randomNCO.add(new NCO("Проект Неслучайные встречи"));

        int eventsCount = (int) (Math.random() * 7);
        ncos = new LinkedList<>();
        for (int i = 0; i < eventsCount; i++) ncos.add(randomNCO.get(i));

        tvNcosResult = view.findViewById(R.id.tv_ncos_result);
        tvNcosResult.setText("Ключевые слова: мастер-класс, помощь\nРезультаты поиска: " + ncos.size() + " результатов");

        RecyclerView recyclerView = view.findViewById(R.id.rv_ncos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);
        DividerItemDecoration horizontalDecorator = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        horizontalDecorator.setDrawable(getResources().getDrawable(R.drawable.horizontal_divider));
        recyclerView.addItemDecoration(horizontalDecorator);

        NCOAdapter = new NCOAdapter(ncos);
        recyclerView.setAdapter(NCOAdapter);
    }
}
