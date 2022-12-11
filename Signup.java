package com.example.artexhibition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup, login;
    FavDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        login = (Button) findViewById(R.id.btn_login);
        DB = new FavDB(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("")|| pass.equals("")|| repass.equals("")){
                Toast.makeText(Signup.this,"Please Enter All Information", Toast.LENGTH_SHORT).show();}
                else {
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if (insert==true) {
                                Toast.makeText(Signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent I = new Intent(Signup.this, MainActivity.class);
                                startActivity(I);
                                finish();
                            }else{
                                Toast.makeText(Signup.this,"Registered Failed", Toast.LENGTH_SHORT).show();
                            }
                            }
                        else {
                            Toast.makeText(Signup.this, "User Already Exists, Please Login", Toast.LENGTH_SHORT).show();
                        }
                        }else {
                        Toast.makeText(Signup.this, "Password Not Matching",Toast.LENGTH_SHORT).show();
                    }
                    } }});
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        }); }
}