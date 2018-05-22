package com.allaber.myrb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentListPlacesChat extends Fragment {

    String[] places = {
            "Абзелиловский",
            "Альшеевский",
            "Архангельский",
            "Аскинский",
            "Аургазинский",
            "Баймакский",
            "Бакалинский",
            "Балтачевский",
            "Белебеевский",
            "Белокатайский",
            "Белорецкий",
            "Бижбулякский",
            "Бирский",
            "Благоварский",
            "Благовещенский",
            "Буздякский",
            "Бураевский",
            "Бурзянский",
            "Гафурийский",
            "Давлекановский",
            "Дуванский",
            "Дюртюлинский",
            "Ермекеевский",
            "Зианчуринский",
            "Зилаирский",
            "Иглинский",
            "Илишевский",
            "Ишимбайский",
            "Калтасинский",
            "Караидельский",
            "Кармаскалинский",
            "Кигинский",
            "Краснокамский",
            "Кугарчинский",
            "Куюргазинский",
            "Кушнаренковский",
            "Мелеузовский",
            "Мечетлинский",
            "Мишкинский",
            "Миякинский",
            "Нуримановский",
            "Салаватский",
            "Стерлибашевский",
            "Стерлитамакский",
            "Татышлинский",
            "Туймазинский",
            "Уфимский",
            "Учалинский",
            "Федоровский",
            "Хайбуллинский",
            "Чекмагушевский",
            "Чишминский",
            "Шаранский",
            "Янаульский"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_items, container, false);
        ListView listView = rootView.findViewById(R.id.list);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {

                TextView textView = (TextView) itemClicked;
                Intent intent = new Intent(getContext(), ActivityChat.class);
                intent.putExtra("placeChat", textView.getText().toString());
                startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_my_list_view, places);
        listView.setAdapter(adapter);
        return rootView;
    }
}



































