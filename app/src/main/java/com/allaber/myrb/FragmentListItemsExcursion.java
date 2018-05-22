package com.allaber.myrb;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.allaber.myrb.FragmentExcursion.moneyNext;
import static com.allaber.myrb.FragmentExcursion.timeNext;
import static com.allaber.myrb.FragmentExcursion.typeNext;

public class FragmentListItemsExcursion extends Fragment {

    Button button;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    ListView listView;
    List<ClassExcursion> contactList = new ArrayList<ClassExcursion>();
    List<String> contactListNames = new ArrayList<String>();
    TextView textNothing;
    Boolean bool = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_items_excursion, container, false);
        textNothing = rootView.findViewById(R.id.textNothing);
        mDBHelper = new DatabaseHelper(getContext());
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        listView = rootView.findViewById(R.id.list);
        readDB();
        buttonAction();
        return rootView;
    }


    public void buttonAction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                ClassExcursion excursion = contactList.get(position);
                Intent intent = new Intent(getContext(), ActivityExcursionInfo.class);
//                intent.putExtra("type", excursion.getType().toString());
                intent.putExtra("money", excursion.getMoney().toString());
                intent.putExtra("name", excursion.getName().toString());
                intent.putExtra("description", excursion.getDescription().toString());
//                intent.putExtra("time", excursion.getTime().toString());
                intent.putExtra("phone", excursion.getPhone().toString());
                intent.putExtra("kolvochelovek", excursion.getKolvochelovek().toString());
//                intent.putExtra("turoperator", excursion.getTuroperator().toString());
                intent.putExtra("web", excursion.getWeb().toString());
                intent.putExtra("image", excursion.getImage().toString());
                intent.putExtra("route", excursion.getRoute().toString());
                startActivity(intent);
            }
        });
    }

    public void readDB() {
        Cursor cursor = mDb.rawQuery("SELECT * FROM excursion", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if ((cursor.getString(4)).equals(timeNext)){
                if ((cursor.getString(1)).equals(typeNext)) {
                    if (Integer.valueOf((cursor.getString(3))) < (Integer.valueOf(moneyNext))) {
                        ClassExcursion excursion = new ClassExcursion();
                        excursion.setName(cursor.getString(0));
                        excursion.setType(cursor.getString(1));
                        excursion.setWeb(cursor.getString(2));
                        excursion.setMoney(cursor.getString(3));
                        excursion.setTime(cursor.getString(4));
                        excursion.setTuroperator(cursor.getString(5));
                        excursion.setPhone(cursor.getString(6));
                        excursion.setKolvochelovek(cursor.getString(7));
                        excursion.setDescription(cursor.getString(8));
                        excursion.setImage(cursor.getString(9));
                        excursion.setRoute(cursor.getString(12));
                        contactList.add(excursion);
                        contactListNames.add(cursor.getString(0));
                        bool = true;
                    }
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_my_list_view, contactListNames);
        listView.setAdapter(adapter);
        if(bool){
            textNothing.setVisibility(View.GONE);
        }
    }
}













