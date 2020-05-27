package com.android.sssameeri.cargodelivery.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.repository.Repository;
import com.android.sssameeri.cargodelivery.ui.fragment.authorizationUI.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ProfileFragment.OnUserTypeCallback {

    private Repository repository;
    private NavController navController;

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNav = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNav, navController);

        repository = new Repository(getApplication());

        navController.navigate(R.id.authFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
           switch (destination.getId()) {
               case R.id.authFragment:
               case R.id.customerAuthFragment:
               case R.id.transporterAuthFragment:
                   bottomNav.setVisibility(View.GONE);
                   break;
               default:
                   bottomNav.setVisibility(View.VISIBLE);
           }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void userType(long type) {
        if(type == 0) {
            bottomNav.getMenu().clear();
            bottomNav.inflateMenu(R.menu.transporter_bottom_menu);
        } else {
            bottomNav.getMenu().clear();
            bottomNav.inflateMenu(R.menu.customer_bottom_menu);
        }
    }
}
