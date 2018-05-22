package com.allaber.myrb;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;


public class ActivityPlacesInfo extends AppCompatActivity {


    FloatingActionButton fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;

    private boolean FAB_Status = false;

    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;

    boolean fabWeb = true;
    boolean fabPhone = true;
    boolean fabMap = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);

        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab_1);
        fab2 = findViewById(R.id.fab_2);
        fab3 = findViewById(R.id.fab_3);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String address = intent.getStringExtra("address");
        final String phone = intent.getStringExtra("phone");
        String map = intent.getStringExtra("map");
        final String web = intent.getStringExtra("web");


        TextView addressTextView = findViewById(R.id.address);
        addressTextView.setText("Адрес: " + address);

        if(address.equals("no")){
            addressTextView.setVisibility(View.GONE);
        }


        if (web.equals("no")) {
            fabWeb = false;
        }
        if (phone.equals("no")) {
            fabPhone = false;
        }
        if (map.equals("no")) {
            fabMap = false;
        }

        if((web.equals("no")) && (phone.equals("no")) && (map.equals("no"))){
            fab.setVisibility(View.GONE);
        }


        final String mapAdress = "google.streetview:cbll=" + map;

        TextView textView = findViewById(R.id.textInfo);

        textView.setText(Html.fromHtml("<big><b>" + name + "</big></b><p>" + description + "</p>" + "<br><br>"));

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(" ");

        InputStream ims = getClass().getResourceAsStream("/assets/img/" + image);


        AppBarLayout app_bar = findViewById(R.id.app_bar);
        app_bar.setBackground(Drawable.createFromStream(ims, null));





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FAB_Status == false) {
                    showFAB();
                    FAB_Status = true;
                } else {
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CallIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(CallIntent);

            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent WebIntent = new Intent(Intent.ACTION_VIEW);
                WebIntent.setData(Uri.parse(web));
                startActivity(WebIntent);

            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(mapAdress);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    private void showFAB() {
        //Floating Action Button 1
        if (fabPhone) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
            layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
            layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
            fab1.setLayoutParams(layoutParams);
            fab1.startAnimation(show_fab_1);
            fab1.setClickable(true);
        }
        //Floating Action Button 2
        if (fabWeb) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
            layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.5);
            layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.5);
            fab2.setLayoutParams(layoutParams2);
            fab2.startAnimation(show_fab_2);
            fab2.setClickable(true);
        }
        //Floating Action Button 3
        if (fabMap) {
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
            layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.25);
            layoutParams3.bottomMargin += (int) (fab3.getHeight() * 1.7);
            fab3.setLayoutParams(layoutParams3);
            fab3.startAnimation(show_fab_3);
            fab3.setClickable(true);
        }
    }

    private void hideFAB() {
        //Floating Action Button 1
        if (fabPhone) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
            layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
            layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
            fab1.setLayoutParams(layoutParams);
            fab1.startAnimation(hide_fab_1);
            fab1.setClickable(false);
        }
        //Floating Action Button 2
        if (fabWeb) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
            layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.5);
            layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
            fab2.setLayoutParams(layoutParams2);
            fab2.startAnimation(hide_fab_2);
            fab2.setClickable(false);
        }
        //Floating Action Button 3
        if (fabMap) {
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
            layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.25);
            layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
            fab3.setLayoutParams(layoutParams3);
            fab3.startAnimation(hide_fab_3);
            fab3.setClickable(false);
        }
    }
}


























