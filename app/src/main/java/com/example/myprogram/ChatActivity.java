package com.example.myprogram;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myprogram.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    ChatAdapter adapter;
    ListView listChat;

    TextView nic_name;
    Button send;
    EditText chat;

    private FirebaseDatabase mAuth;
    FirebaseUser user;
    private DatabaseReference reference;
    FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().show();

        Intent intent = getIntent();
        String logo = intent.getStringExtra("logo");

        nic_name = (TextView)findViewById(R.id.nic_name);
        nic_name.setText(logo);
        send = (Button)findViewById(R.id.send);
        chat = (EditText)findViewById(R.id.chat);
        listChat = (ListView)findViewById(R.id.listChat);

        adapter = new ChatAdapter(getBaseContext(),R.layout.list_massenger);
        listChat.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("chat");


        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMesagge msg = dataSnapshot.getValue(ChatMesagge.class);
                adapter.add(msg);
                scrollToBotom();
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


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("nic");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

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

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMesagge msg = new ChatMesagge(nic_name.getText().toString(), chat.getText().toString());
                reference.push().setValue(msg);
                chat.setText("");
                scrollToBotom();
            }
        });





    }
    private void scrollToBotom() {
        listChat.post(new Runnable() {
            @Override
            public void run() {
                listChat.setSelection(adapter.getCount() - 1);
            }
        });
    }


}
