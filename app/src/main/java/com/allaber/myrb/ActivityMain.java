package com.allaber.myrb;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityMain extends AppCompatActivity implements FragmentRegister.One, FragmentEnter.One, FragmentAllFunctions.One, FragmentExcursion.One, FragmentSettings.One, FragmentReference.One {
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        prefs = getSharedPreferences("com.allaber.myrb", MODE_PRIVATE);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fr = fragmentManager.beginTransaction();


        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FragmentAllFunctions fragment3 = new FragmentAllFunctions();
            fr.replace(R.id.container, fragment3, "fragment_all_functions");
        } else {
            FragmentEnter fragment1 = new FragmentEnter();
            fr.replace(R.id.container, fragment1, "fragment_enter");
        }
        fr.commit();
    }


    public void onButtonSelected(int fragIndex) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (fragIndex) {
            case 1:
                FragmentRegister fragment1 = new FragmentRegister();
                ft.replace(R.id.container, fragment1, "fragment_register");
                break;
            case 2:
                FragmentEnter fragment2 = new FragmentEnter();
                ft.replace(R.id.container, fragment2, "fragment_enter");
                break;
            case 3:
                FragmentAllFunctions fragment3 = new FragmentAllFunctions();
                ft.replace(R.id.container, fragment3, "fragment_all_functions");
                break;
            case 4:
                FragmentListItemsPlaces fragment4 = new FragmentListItemsPlaces();
                ft.replace(R.id.container, fragment4, "fragment_list_items");
                break;
            case 5:
                FragmentListItemsLegend fragment5 = new FragmentListItemsLegend();
                ft.replace(R.id.container, fragment5, "fragment_list_items");
                break;
            case 6:
                FragmentExcursion fragment6 = new FragmentExcursion();
                ft.replace(R.id.container, fragment6, "fragment_list_items");
                break;
            case 7:
                FragmentListItemsExcursion fragment7 = new FragmentListItemsExcursion();
                ft.replace(R.id.container, fragment7, "fragment_list_items");
                break;
            case 8:
                FragmentListPlacesChat fragment8 = new FragmentListPlacesChat();
                ft.replace(R.id.container, fragment8, "fragment_list_items");
                break;
            case 9:
                FragmentSettings fragment9 = new FragmentSettings();
                ft.replace(R.id.container, fragment9, "fragment_settings");
                break;
            case 10:
                FragmentReference fragment10 = new FragmentReference();
                ft.replace(R.id.container, fragment10, "fragment_reference");
                break;
            case 11:
                FragmentReferenceInfo fragment11 = new FragmentReferenceInfo();
                ft.replace(R.id.container, fragment11, "fragment_reference_info");
                break;
        }
        if(fragIndex != 2) {
            ft.addToBackStack(null);
        }else{
            FragmentManager fm = getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
        }
        ft.commit();
    }
}


















