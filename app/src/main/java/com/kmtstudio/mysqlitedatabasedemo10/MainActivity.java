package com.kmtstudio.mysqlitedatabasedemo10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signInUserName, signInPassword;
    private Button signInBTn, signUpHereBtn;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).hide();



        dbHelper = new DbHelper(this);
        SQLiteDatabase liteDatabase = dbHelper.getWritableDatabase();


        signInUserName = findViewById(R.id.signInUserEdtTxtID);
        signInPassword = findViewById(R.id.signInPasswordEdtTxtID);

        signInBTn = findViewById(R.id.signInBtnID);
        signUpHereBtn = findViewById(R.id.signUpHereBtnID);


        signInBTn.setOnClickListener(this);
        signUpHereBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String username = signInUserName.getText().toString();
        String password = signInPassword.getText().toString();


        if (v.getId() == R.id.signInBtnID) {

            boolean result = dbHelper.getUserDetails(username, password);

            if (result) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            } else {

                Toast.makeText(getApplicationContext(), "Sorry! username password didn't match", Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == R.id.signUpHereBtnID) {

            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);

        }

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Closing application");
        builder.setMessage("Are you sure you want to exit!");

        builder.setPositiveButton("Yes", (dialog, which) -> finish());

        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        builder.setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
