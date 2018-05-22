package com.allaber.myrb;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentRegister extends Fragment implements View.OnClickListener {
    private static final String TAG = "FragmentRegister";
    private FirebaseAuth mAuth;
    EditText edEmail;
    EditText edPassword;
    EditText edName;
    EditText edLastname;
    DatabaseReference myRef;
    String AuthenticationID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        edEmail = rootView.findViewById(R.id.ED_email);
        edPassword = rootView.findViewById(R.id.ED_password);
        edName = rootView.findViewById(R.id.ED_name);
        edLastname = rootView.findViewById(R.id.ED_surname);

        Button btnEnter = rootView.findViewById(R.id.B_register);
        btnEnter.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.B_register:
                if (checkField(view) == true) {
                    onCreateAcc(String.valueOf(edEmail.getText()), String.valueOf(edPassword.getText()));
                }
                break;
        }

    }

    public interface One {
        void onButtonSelected(int fragIndex);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(com.google.firebase.auth.FirebaseUser currentUser) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }

    public void onCreateAcc(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    com.google.firebase.auth.FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    AuthenticationID = user.getUid();
                    writeNewUser();
                    One listener1 = (One) getActivity();
                    listener1.onButtonSelected(3);

                    SharedPreferences SharedPreferencesID;
                    SharedPreferencesID = getActivity().getSharedPreferences("sID", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = SharedPreferencesID.edit();
                    editor2.putString("sID", AuthenticationID);
                    editor2.apply();

                } else {
                    Snackbar.make(getView(), "Ошибка: некорректный Email", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    updateUI(null);
                }
            }
        });
    }

    private void writeNewUser() {
        FirebaseClassUser user = new FirebaseClassUser();
        user.email = String.valueOf(edEmail.getText());
        user.password = String.valueOf(edPassword.getText());
        user.lastname = String.valueOf(edLastname.getText());
        user.name = String.valueOf(edName.getText());
        myRef.child("users/" + AuthenticationID).setValue(user);

    }

    public boolean checkField(View view) {
        boolean boolInf = false;
        if ((edName.getText().length() != 0)) {
            if ((edLastname.getText().length() != 0)) {
                if ((edEmail.getText().length() != 0)) {
                    if ((edPassword.getText().length() != 0)) {
                        boolInf = true;
                    } else {
                        Snackbar.make(view, "Ошибка: заполните поле \"Пароль\"", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                } else {
                    Snackbar.make(view, "Ошибка: заполните поле \"Email\"", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            } else {
                Snackbar.make(view, "Ошибка: заполниет поле \"Фамилия\"", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        } else {

            Snackbar.make(view, "Ошибка: заполниет поле \"Имя\"", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        return boolInf;
    }
}
