package com.example.artexhibition.ui.notifications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.artexhibition.Login;
import com.example.artexhibition.MainActivity;
import com.example.artexhibition.R;
import com.example.artexhibition.Signup;

public class NotificationsFragment extends Fragment {
    Button btnlogout;
    EditText username, email;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        btnlogout = root.findViewById(R.id.btnlogout);
        username = root.findViewById(R.id.username);
        email = root.findViewById(R.id.email);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });


        return root;
    }

}