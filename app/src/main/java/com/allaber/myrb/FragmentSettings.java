package com.allaber.myrb;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentSettings extends Fragment implements View.OnClickListener {
    private static final String TAG = "FragmentInfo";
    TextView text1;
    TextView text2;
    TextView text3;
    DatabaseReference myRef;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("Настройки");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        SharedPreferences SharedPreferencesID;
        SharedPreferencesID = getActivity().getSharedPreferences("sID", Context.MODE_PRIVATE);
        String ans1 = SharedPreferencesID.getString("sID", String.valueOf(Context.MODE_PRIVATE));
        myRef = database.getReference("users/" + ans1);


        Button btn2 = rootView.findViewById(R.id.exit);
        Button btn3 = rootView.findViewById(R.id.change_user);
        Button btn5 = rootView.findViewById(R.id.info);

        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn5.setOnClickListener(this);

        text1 = rootView.findViewById(R.id.email);
        text2 = rootView.findViewById(R.id.name);
        text3 = rootView.findViewById(R.id.lastname);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseClassUser value = dataSnapshot.getValue(FirebaseClassUser.class);
                text1.setText(value.email);
                text2.setText(value.name);
                text3.setText(value.lastname);

            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Не удалось прочитать значение", error.toException());
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_user:
                FirebaseAuth.getInstance().signOut();

                SharedPreferences SharedPreferencesID;
                SharedPreferencesID = getActivity().getSharedPreferences("sID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = SharedPreferencesID.edit();
                editor2.putString("sID", null);
                editor2.apply();

                One listener1 = (One) getActivity();
                listener1.onButtonSelected(2);


                break;
            case R.id.info:
                One listener2 = (One) getActivity();
                listener2.onButtonSelected(10);
                break;
            case R.id.exit:
                getActivity().finish();
                break;

        }
    }
    public interface One {
        void onButtonSelected(int fragIndex);
    }

}
