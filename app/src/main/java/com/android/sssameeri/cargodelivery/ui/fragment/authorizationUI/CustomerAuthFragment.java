package com.android.sssameeri.cargodelivery.ui.fragment.authorizationUI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.model.Customer;
import com.android.sssameeri.cargodelivery.viewmodel.CustomerViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerAuthFragment extends Fragment {

    //UI components
    private EditText nameEditTxt;
    private EditText phoneEditTxt;
    private EditText passwordEditTxt;
    private TextView switchAuthMode;
    private Button customerSignUpBtn;
    private NavController navController;

    private CompositeDisposable compositeDisposable;
    private CustomerViewModel customerViewModel;

    private boolean authMode = false; //if false then registration else login

    //Data variables
    private String name;
    private String phone;
    private String password;
    private Customer customer;

    public CustomerAuthFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customer_auth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        compositeDisposable = new CompositeDisposable();

        navController = Navigation.findNavController(view);

        customerViewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);

        //Bind UI components
        nameEditTxt = view.findViewById(R.id.nameEditTxt);
        phoneEditTxt = view.findViewById(R.id.phoneEditTxt);
        passwordEditTxt = view.findViewById(R.id.passwordEditTxt);
        customerSignUpBtn = view.findViewById(R.id.customerSignUpBtn);
        switchAuthMode = view.findViewById(R.id.customerSignInTxtView);
    }

    @Override
    public void onResume() {
        super.onResume();
        switchAuthMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authMode();
            }
        });

        customerSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authMode) {
                    getCustomerData();
                } else {
                    insertCustomer();
                }
            }
        });
    }

    //Check auth mode -> if false then user registration else user login
    private void authMode() {
        if(!authMode) {
            nameEditTxt.setVisibility(View.GONE);
            customerSignUpBtn.setText("Увійти");
            switchAuthMode.setText(getString(R.string.register));
            authMode = true;
        } else {
            nameEditTxt.setVisibility(View.VISIBLE);
            customerSignUpBtn.setText(getString(R.string.register));
            switchAuthMode.setText(getString(R.string.login));
            authMode = false;
        }
    }

    //Get customer data by phone and password
    private void getCustomerData() {
        phone = phoneEditTxt.getText().toString();
        password = passwordEditTxt.getText().toString();

        Disposable getCustomerDataDisposable = customerViewModel.getCustomerData(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            customerViewModel.setId(result.getId());
                            customerViewModel.closeDatabase();
                            navController.navigate(R.id.profileFragment);
                        },
                        throwable -> {
                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                );
        compositeDisposable.add(getCustomerDataDisposable);
    }

    //Insert customer into table "customer"
    private void insertCustomer() {
        name = nameEditTxt.getText().toString();
        phone = phoneEditTxt.getText().toString();
        password = passwordEditTxt.getText().toString();

        customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setPassword(password);

        Disposable insertCustomerDisposable = customerViewModel.insertCustomer(customer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            Log.d("TAG", result.toString());
                            customerViewModel.setId(result);
                            customerViewModel.closeDatabase();
                            navController.navigate(R.id.profileFragment);
                        },
                        throwable -> {
                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                );
        compositeDisposable.add(insertCustomerDisposable);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
