package com.android.sssameeri.cargodelivery.ui.fragment.authorizationUI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.sssameeri.cargodelivery.R;


public class AuthFragment extends Fragment {

    private NavController navController;
    private Button iAmCustomer, iAmTransporter;

    public AuthFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iAmTransporter = view.findViewById(R.id.iAmTransporterBtn);
        iAmCustomer = view.findViewById(R.id.iAmCustomerBtn);
        navController = Navigation.findNavController(view);

        iAmCustomer.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.customerAuthFragment));
        iAmTransporter.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.transporterAuthFragment));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
