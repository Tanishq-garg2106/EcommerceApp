package com.tanishq.ecommerce;

import static com.tanishq.ecommerce.R.layout.activity_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class login_Activity extends AppCompatActivity {

    Button signIn;
    EditText email,password;
    TextView signUp;
    FirebaseAuth auth;
    ProgressBar progressBar;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        progressBar =findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        signIn = findViewById(R.id.login_btn);
        email = findViewById(R.id.email_login);
        password =findViewById(R.id.password_login);
        signUp =findViewById(R.id.sign_up);
        image =findViewById(R.id.reg_img);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_Activity.this,registration_Activity.class));
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
                progressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    private void loginUser() {

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();



        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 6){
            Toast.makeText(this, "Password Length must be greater than 6 letter ", Toast.LENGTH_SHORT).show();
            return;
        }
        //Login User
        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()){
                       progressBar.setVisibility(View.GONE);
                       Toast.makeText(login_Activity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(login_Activity.this,MainActivity.class));

                   }else{
                       progressBar.setVisibility(View.GONE);
                       Toast.makeText(login_Activity.this, "Error"+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                   }
                    }
                });
    }
}


