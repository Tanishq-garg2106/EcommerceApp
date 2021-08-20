package com.tanishq.ecommerce;

import static java.util.Objects.requireNonNull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.tanishq.ecommerce.fragment.account_circleFragment;
import com.tanishq.ecommerce.fragment.chat_bubble_Fragment;
import com.tanishq.ecommerce.fragment.homeFragment;
import com.tanishq.ecommerce.fragment.shopping_cart_Fragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_drawer);

        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new  BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.nav_home:
                        transaction.replace(R.id.cotainer, new homeFragment());
                        break;
                    case R.id.nav_chat_bubble:
                        transaction.replace(R.id.cotainer, new chat_bubble_Fragment());
                        break;
                    case R.id.nav_shopping_cart:
                        transaction.replace(R.id.cotainer, new shopping_cart_Fragment());
                        break;
                    case R.id.nav_account_circle:
                        transaction.replace(R.id.cotainer, new account_circleFragment());
                        break;
                }
                transaction.commit();
                return true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_order:
                Toast.makeText(this, "My orders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rewards:
                Toast.makeText(this, "My rewards", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_wishlist:
                Toast.makeText(this, "wishlist", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Policy:
                Toast.makeText(this, "Privacy Policy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_account:
                Toast.makeText(this, "account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}