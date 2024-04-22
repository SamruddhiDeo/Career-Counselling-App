package com.example.careercounsellor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Toast;

import com.example.careercounsellor.ModelClasses.LoginModel;
import com.example.careercounsellor.ModelClasses.SignupModel;
import com.example.careercounsellor.databinding.ActivityLoginBinding;
import com.example.careercounsellor.databinding.ActivitySignUpBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DBHelper dbHelper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        String text="Sign Up";
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        binding.txtSignup.setText(content);

        binding.txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.name.getText().toString();
                String password = binding.password.getText().toString();

                boolean checkUserExists = dbHelper.checkUserExists(new LoginModel(name,password));
                
                if(checkUserExists){
                    boolean isAuthenticated = dbHelper.authenticateUser(new LoginModel(name,password),LoginActivity.this);
                    if(isAuthenticated){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Name", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}