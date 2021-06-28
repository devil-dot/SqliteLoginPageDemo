package com.kmtstudio.mysqlitedatabasedemo10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    private EditText nameEditTxt, emailEditTxt, userNameEditTxt, passwordEditTxt;
    private Button signUpBtn;

    DbHelper dbHelper;
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dbHelper = new DbHelper(this);
        userDetails = new UserDetails();


        nameEditTxt = findViewById(R.id.nameEditTextID);
        emailEditTxt = findViewById(R.id.signUpEmailEditTextID);
        userNameEditTxt = findViewById(R.id.signUpUserNameEditTextID);
        passwordEditTxt = findViewById(R.id.signUpPasswordEditTextID);

        signUpBtn = findViewById(R.id.signUpBtnID);


        signUpBtn.setOnClickListener(v -> {

            String name = nameEditTxt.getText().toString();
            String email = emailEditTxt.getText().toString();
            String username = userNameEditTxt.getText().toString();
            String password = passwordEditTxt.getText().toString();


            userDetails.setName(name);
            userDetails.setEmail(email);
            userDetails.setUsername(username);
            userDetails.setPassword(password);


            long rowID = dbHelper.insertData(userDetails);


            if (rowID > 0) {

                Toast.makeText(getApplicationContext(), "Row" + rowID + " is successfully inserted", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(getApplicationContext(), "Row data insertion failed", Toast.LENGTH_SHORT).show();

            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}