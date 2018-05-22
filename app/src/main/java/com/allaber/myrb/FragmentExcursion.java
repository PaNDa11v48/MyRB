package com.allaber.myrb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragmentExcursion extends Fragment {
    SeekBar cost;
    Button next;
    TextView money;
    RadioGroup rad1;
    RadioGroup rad2;

    static String timeNext = "2";
    static String moneyNext = "5000";
    static String typeNext = "Автобусный";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_excursion, container, false);

        cost = rootView.findViewById(R.id.cost);
        next = rootView.findViewById(R.id.next);
        money = rootView.findViewById(R.id.money);
        rad1 = rootView.findViewById(R.id.radioGroup1);
        rad2 = rootView.findViewById(R.id.radioGroup2);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                One listener1 = (One) getActivity();
                listener1.onButtonSelected(7);
            }
        });


        cost.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                money.setText("Цена до " + (2700 + i) + " руб.");
                moneyNext = String.valueOf(money.getText().subSequence(8, 12));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rad1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton1:
                        timeNext = "2";
                        break;
                    case R.id.radioButton2:
                        timeNext = "3";
                        break;
                    case R.id.radioButton3:
                        timeNext = "4";
                        break;
                    case R.id.radioButton4:
                        timeNext = "5";
                        break;
                }
            }
        });

        rad2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton5:
                        typeNext = "Автобусный";
                        break;
                    case R.id.radioButton6:
                        typeNext = "Пеший";
                        break;
                }
            }
        });
        return rootView;
    }

    public interface One {
        void onButtonSelected(int fragIndex);
    }
}





































