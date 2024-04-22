package com.example.careercounsellor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;

import com.example.careercounsellor.ModelClasses.SignupModel;
import com.example.careercounsellor.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    DBHelper dbHelper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String userId = prefs.getString("user_id", ""); // Retrieve user ID
        boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);

        if(isLoggedIn){
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


//        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
//        String userId = prefs.getString("user_id", ""); // Retrieve user ID
//        boolean isLoggedIn = prefs.getBoolean("is_logged_in", false); // Retrieve login status
//
//        if(!isLoggedIn){
//            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        getSupportActionBar().hide();

        String text="Login";
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        binding.txtLogin.setText(content);

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.name.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();

                dbHelper.signupAdd(new SignupModel(name,email,password));

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}