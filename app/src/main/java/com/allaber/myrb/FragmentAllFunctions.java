package com.allaber.myrb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class FragmentAllFunctions extends Fragment implements View.OnClickListener {


    public static String string1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_functions, container,false);

        Button B1 = rootView.findViewById(R.id.B1);
        B1.setOnClickListener(this);

        Button B2 = rootView.findViewById(R.id.B2);
        B2.setOnClickListener(this);

        Button B3 = rootView.findViewById(R.id.B3);
        B3.setOnClickListener(this);

        Button B4 = rootView.findViewById(R.id.B4);
        B4.setOnClickListener(this);

        Button B5 = rootView.findViewById(R.id.B5);
        B5.setOnClickListener(this);

        Button B6 = rootView.findViewById(R.id.B6);
        B6.setOnClickListener(this);

        Button B7 = rootView.findViewById(R.id.B7);
        B7.setOnClickListener(this);

        Button B8 = rootView.findViewById(R.id.B8);
        B8.setOnClickListener(this);

        Button B9 = rootView.findViewById(R.id.B9);
        B9.setOnClickListener(this);

        Button B10 = rootView.findViewById(R.id.B10);
        B10.setOnClickListener(this);

        Button B11 = rootView.findViewById(R.id.B11);
        B11.setOnClickListener(this);

        ImageView image = rootView.findViewById(R.id.settings);
        image.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.B1:
                string1 = "Памятник";
                One listener1 = (One) getActivity();
                listener1.onButtonSelected(4);
                break;
            case R.id.B2:
                string1 = "Театр";
                One listener2 = (One) getActivity();
                listener2.onButtonSelected(4);
                break;
            case R.id.B3:
                string1 = "Музеи";
                One listener3 = (One) getActivity();
                listener3.onButtonSelected(4);
                break;
            case R.id.B4:
                string1 = "Релиогиозное сооружение";
                One listener4 = (One) getActivity();
                listener4.onButtonSelected(4);
                break;
            case R.id.B5:
                string1 = "Спортивное сооружение";
                One listener5 = (One) getActivity();
                listener5.onButtonSelected(4);
                break;
            case R.id.B6:
                string1 = "Парк";
                One listener6 = (One) getActivity();
                listener6.onButtonSelected(4);
                break;
            case R.id.B7:
                string1 = "ТЦ";
                One listener7 = (One) getActivity();
                listener7.onButtonSelected(4);
                break;
            case R.id.B8:
                string1 = "Отель";
                One listener8 = (One) getActivity();
                listener8.onButtonSelected(4);
                break;

            case R.id.B9:
                One listener9 = (One) getActivity();
                listener9.onButtonSelected(5);
                break;

            case R.id.B10:
                One listener10 = (One) getActivity();
                listener10.onButtonSelected(8);
                break;

            case R.id.B11:
                One listener11 = (One) getActivity();
                listener11.onButtonSelected(6);
                break;
            case R.id.settings:
                One listener12 = (One) getActivity();
                listener12.onButtonSelected(9);
                break;
        }
    }

    public interface One {
        void onButtonSelected(int fragIndex);
    }
}
























