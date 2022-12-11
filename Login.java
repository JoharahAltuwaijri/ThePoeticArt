package com.example.artexhibition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {
    EditText username, password;
    Button btn_login, btnsignup;
    FavDB DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btnsignup = (Button)findViewById(R.id.btnsignup);
        DB = new FavDB(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                { Toast.makeText(Login.this, "Please enter Username and Password", Toast.LENGTH_SHORT).show();}
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent I=new Intent(Login.this, MainActivity.class);
                        startActivity(I);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Invalid Entries", Toast.LENGTH_SHORT).show();
                    } } }
         });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });
    }}
