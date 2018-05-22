package com.allaber.myrb;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

public class ActivityChat extends AppCompatActivity {

    private static final String TAG = "FragmentСhat";
    private FirebaseListAdapter<ClassChatMessage> adapter;
    DatabaseReference myRef;
    DatabaseReference myRef1;
    String nameLastname = "name";
    ListView list;
    String strText;
    Toolbar toolbar;
    MediaPlayer note;
    EmojiconEditText inputText;
    EmojIconActions emojIcon;
    ImageView emojiImageView;
    View activity_main_chat;
    boolean message = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbar = findViewById(R.id.toolbar2);

        Intent intent = getIntent();
        strText = intent.getStringExtra("placeChat");
        toolbar.setTitle(strText + " район");
        note = MediaPlayer.create(this, R.raw.note);
        inputText = findViewById(R.id.input);
        list = findViewById(R.id.list_of_messages);
        activity_main_chat = findViewById(R.id.activity_main_chat);
        emojiImageView = findViewById(R.id.imageView);

        emojIcon = new EmojIconActions(this, activity_main_chat, inputText, emojiImageView);
        emojIcon.ShowEmojIcon();
        emojIcon.setIconsIds(R.drawable.keyboard, R.drawable.emoji_smiley);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String ans1 = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef = database.getReference("users/" + ans1);
        myRef1 = database.getReference("custom_message/" + strText);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseClassUser value = dataSnapshot.getValue(FirebaseClassUser.class);
                nameLastname = value.name + " " + value.lastname;
                displayChatMessages();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Не удалось прочитать значение", error.toException());
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.input);
                if (input.length() != 0) {
                    FirebaseDatabase.getInstance().getReference("custom_message/" + strText).push().setValue(new ClassChatMessage(input.getText().toString(), nameLastname));
                }
                input.setText("");
                message = false;
            }
        });


    }

    public void displayChatMessages() {
        ListView listOfMessages = findViewById(R.id.list_of_messages);
        adapter = new FirebaseListAdapter<ClassChatMessage>(this, ClassChatMessage.class, R.layout.custom_message, FirebaseDatabase.getInstance().getReference("custom_message/" + strText)) {
            @Override
            protected void populateView(View v, ClassChatMessage model, int position) {
                LinearLayout frame = v.findViewById(R.id.frame);
                RelativeLayout relative = v.findViewById(R.id.relative);

                if (model.getMessageUser().equals(nameLastname)) {
                    frame.setBackgroundResource(R.drawable.message_my);
                    relative.setGravity(Gravity.RIGHT);
                } else {
                    frame.setBackgroundResource(R.drawable.message_their);
                    relative.setGravity(Gravity.LEFT);
                }


                EmojiconTextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("HH:mm dd/MM/yy", model.getMessageTime()));

            }
        };
        listOfMessages.setAdapter(adapter);

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (message) {
                    long mills = 300L;
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(mills);
                    note.start();
                }
                message = true;
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Не удалось прочитать значение", error.toException());
            }
        });
    }
}