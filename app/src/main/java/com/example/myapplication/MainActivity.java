package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsername,edtPassword,edtEmail;
    private Button btnSubmit;
    private TextView txtLoginInfo;

    private Boolean isSigningUp=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername=findViewById(R.id.edtUsername);
        edtEmail=findViewById(R.id.edtMail);
        edtPassword=findViewById(R.id.editPassword);
        btnSubmit=findViewById(R.id.btnSubmit);
        txtLoginInfo=findViewById(R.id.logintxtinfo);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
            finish();
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtEmail.getText().toString().isEmpty()||edtPassword.getText().toString().isEmpty()){
                    if(isSigningUp&&edtUsername.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this,"INVALID INPUT",Toast.LENGTH_SHORT).show();
                        return ;
                    }
                }
                if(isSigningUp){
                    handlesignup();
                }
                else{
                    handlelogin();
                }
            }
        });
        txtLoginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSigningUp){
                    isSigningUp=false;
                    edtUsername.setVisibility(View.GONE);
                    btnSubmit.setText("LogIn");
                    txtLoginInfo.setText("Don't have an Account?? Then Bloody Sign Up");
                }
                else{
                    isSigningUp=true;
                    edtUsername.setVisibility(View.VISIBLE);
                    btnSubmit.setText("Sign Up");
                    txtLoginInfo.setText("Already have an account?? Then Bloody Log In");
                }
            }
        });
    }
    private void handlesignup(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference("user/"+FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new user(edtUsername.getText().toString(),edtPassword.getText().toString(),"",edtEmail.getText().toString()));
                    startActivity(new Intent(MainActivity.this,MainActivity2.class));
                    Toast.makeText(MainActivity.this,"Signed up Successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void handlelogin(){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(MainActivity.this,MainActivity2.class));
                        Toast.makeText(MainActivity.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"LoggedIn Successfully",Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}