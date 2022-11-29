package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText edtMessageInput;
    private TextView txtChattingWith;
    private ProgressBar progressBar;
    private ImageView imgToolbar,imgSend;


    private ArrayList<Message> messages;

    String usernameofTheRoommate, emailofRoommate,chatRoomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        usernameofTheRoommate=getIntent().getStringExtra("username_of_roommate");
        emailofRoommate=getIntent().getStringExtra("email_of_roommate");


        recyclerView = findViewById(R.id.recyclerMessages);
        imgSend=findViewById(R.id.imageSendMessage);
        edtMessageInput=findViewById(R.id.edtText);
        txtChattingWith=findViewById(R.id.txtChattingWith);
        progressBar=findViewById(R.id.progessMessages);
        imgToolbar=findViewById(R.id.img_toolbar);

        txtChattingWith.setText(usernameofTheRoommate);
        messages=new ArrayList<>();
    }
}