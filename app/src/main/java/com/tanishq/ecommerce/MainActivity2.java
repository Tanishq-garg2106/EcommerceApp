package com.tanishq.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    Button skip;
    ProgressBar progressBar;
    FirebaseAuth auth;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        skip=findViewById(R.id.skip_btn);
        auth= FirebaseAuth.getInstance();
        progressBar =findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        image=findViewById(R.id.imageView2);

        if(auth.getCurrentUser() !=null){
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "please wait you are already logged in", Toast.LENGTH_SHORT).show();
        }
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
            }
        });

    }

    public void login(View view){
        startActivity(new Intent(MainActivity2.this,login_Activity.class));

    }
    public void registration(View view) {
        startActivity(new Intent(MainActivity2.this,registration_Activity.class));
    }

}