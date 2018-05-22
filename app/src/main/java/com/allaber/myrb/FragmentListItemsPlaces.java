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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.allaber.myrb.FragmentAllFunctions.string1;

public class FragmentListItemsPlaces extends Fragment {

    Button button;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    ListView listView;
    List<ClassPlaces> contactList = new ArrayList<ClassPlaces>();
    List<String> contactListNames = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_items, container, false);


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


    public void buttonAction(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {

                ClassPlaces places = contactList.get(position);
                Intent intent = new Intent(getContext(), ActivityPlacesInfo.class);
                intent.putExtra("type", places.getType().toString());
                intent.putExtra("image", places.getImage().toString());
                intent.putExtra("name", places.getName().toString());
                intent.putExtra("description", places.getDescription().toString());
                intent.putExtra("address", places.getAddress().toString());
                intent.putExtra("phone", places.getPhone().toString());
                intent.putExtra("map", places.getMap().toString());
                intent.putExtra("web", places.getWeb().toString());
                startActivity(intent);

            }
        });
    }



    public void readDB(){
        Cursor cursor = mDb.rawQuery("SELECT * FROM places", null);
        cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if((cursor.getString(0)).equals(string1)) {
                ClassPlaces places = new ClassPlaces();
                places.setType(cursor.getString(0));
                places.setImage(cursor.getString(1));
                places.setName(cursor.getString(2));
                places.setDescription(cursor.getString(3));
                places.setAddress(cursor.getString(4));
                places.setPhone(cursor.getString(5));
                places.setMap(cursor.getString(6));
                places.setWeb(cursor.getString(7));
                contactList.add(places);
                contactListNames.add(cursor.getString(2));
            }
                cursor.moveToNext();
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_my_list_view, contactListNames);
        listView.setAdapter(adapter);
    }






    public interface One {
        void onButtonSelected(int fragIndex);
    }
}













