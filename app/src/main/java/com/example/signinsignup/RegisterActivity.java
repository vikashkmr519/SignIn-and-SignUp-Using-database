package com.example.signinsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText etUsername;
    EditText etPassword;
    Button btnSignUp;
    TextView tvAlreadyRegistered;
    TextView tvLogin;
    EditText etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        db = new DatabaseHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvAlreadyRegistered = findViewById(R.id.tvAlreadyRegistered);
        tvLogin= findViewById(R.id.tvLogin);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(loginIntent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                String confirmPwd = etConfirmPassword.getText().toString().trim();

                if(!pwd.equals(confirmPwd)){
                    Toast.makeText(RegisterActivity.this,"Make Password and confirm Password same",Toast.LENGTH_SHORT).show();
                }

                boolean res  = db.checkUser(user,pwd);
                if(res){
                        Toast.makeText(RegisterActivity.this,"Your account already Exists",Toast.LENGTH_SHORT);


                }else{
                   long newuser =  db.addUser(user,pwd);

                   if(newuser>0){
                       Toast.makeText(RegisterActivity.this,"SuccessFully registered",Toast.LENGTH_SHORT);
                       Intent loginIntent = new Intent(RegisterActivity.this,MainActivity.class);
                       startActivity(loginIntent);

                   }else{
                       Toast.makeText(RegisterActivity.this,"Registeration Fail",Toast.LENGTH_SHORT);
                   }

                }


            }
        });
    }
}
