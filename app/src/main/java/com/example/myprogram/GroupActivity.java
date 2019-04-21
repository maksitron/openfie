package com.example.myprogram;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GroupActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuntListener;
    FirebaseUser user;

    EditText etEmail, etPassword, etNic_name;
    Button angry_bt_avtorizacia, angry_bt_reg, btGoToChat;
    TextView test;
    String displayName;
    ImageButton alpha;

    private DatabaseReference reference;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
        getSupportActionBar().show();


        alpha = (ImageButton) findViewById(R.id.alpha);
        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this, AlphaActivity.class);
                startActivity(intent);

            }
        });
//    finde


        mAuntListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                //метод getCurrentUser() щоб отримати FirebaseUser обєкт, який містить інформацію про зареєстрованого користувача
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Intent intent = new Intent(GroupActivity.this, ChatActivity.class);
                    intent.putExtra("logo", displayName);
                    startActivity(intent);
                } else {
                    // User is signed out
                }
            }
        };

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("nic");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                displayName = (String) (dataSnapshot.getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

//        mAuntListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                user = firebaseAuth.getCurrentUser();
//                if (user != null){
//                    Intent intent = new Intent(GroupActivity.this, ChatActivity.class);
//                    intent.putExtra("logo", displayName);
//                    startActivity(intent);
//                }else{
//            }
//        }
//    };
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.angry_bt){
            signin(etEmail.getText().toString(),etPassword.getText().toString());
            etNic_name.setText(displayName);

        }if (v.getId() == R.id.angry_bt){
            registration(etEmail.getText().toString(),etPassword.getText().toString(), etNic_name.getText().toString());
            reference.push().setValue(etNic_name.getText().toString());
        }
        if (v.getId() == R.id.chat){
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("logo", displayName);
            startActivity(intent);
        }


    }



    void signin(String email , String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(GroupActivity.this, "Aвторизація пройшла успішно", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(GroupActivity.this, "Aвторизація провалена", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void registration(String email , String password, String nic){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(GroupActivity.this, "Реєстрація пройшла успешно", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(GroupActivity.this, "Реєстрація провалена", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

