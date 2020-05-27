package com.android.sssameeri.cargodelivery.ui.fragment.authorizationUI;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.model.Transport;
import com.android.sssameeri.cargodelivery.viewmodel.CustomerViewModel;
import com.android.sssameeri.cargodelivery.viewmodel.TransporterViewModel;
import com.android.sssameeri.cargodelivery.viewmodel.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileFragment extends Fragment {

    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView transportTextView;
    private LinearLayout linearLayout;

    private CustomerViewModel customerViewModel;
    private UserViewModel userViewModel;
    private TransporterViewModel transporterViewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private OnUserTypeCallback userTypeCallback;

    public ProfileFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            userTypeCallback = (OnUserTypeCallback) context;
        } catch (ClassCastException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameTextView = view.findViewById(R.id.nameTxtView);
        phoneTextView = view.findViewById(R.id.phoneTxtView);
        transportTextView = view.findViewById(R.id.transportTxtView);
        linearLayout = view.findViewById(R.id.transportLinearLayout);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        customerViewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        transporterViewModel = new ViewModelProvider(requireActivity()).get(TransporterViewModel.class);

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(getActivity() != null)
                    getActivity().finish();
            }
        };

        if(getActivity() != null)
            getActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
    }

    @Override
    public void onResume() {
        super.onResume();
        userViewModel.getUserType().observe(getViewLifecycleOwner(), aLong -> {
            if(aLong == 0) {
                userTypeCallback.userType(aLong);
                getTransporterData();
            } else {
                userTypeCallback.userType(aLong);
                getCustomerData();
            }
        });
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void getTransporterData() {
        transporterViewModel.getId().observe(getViewLifecycleOwner(), aLong -> {
            Disposable getTransporterData = transporterViewModel
            .getTransporterById(aLong)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
              result -> {
                  linearLayout.setVisibility(View.VISIBLE);
                  nameTextView.setText(result.getName());
                  phoneTextView.setText(result.getPhone());
                  transportTextView.setText(result.getTransportName());
                    },
                    throwable -> {
                        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
            );
        });
    }

    private void getCustomerData() {
        customerViewModel.getId().observe(getViewLifecycleOwner(), aLong -> {
            Disposable getCustomerData = customerViewModel
                    .getCustomerById(aLong)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            result -> {
                                nameTextView.setText(result.getName());
                                phoneTextView.setText(result.getPhone());
                                //customerViewModel.closeDatabase();
                            },
                            throwable -> {
                                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    );

        });
    }

    public interface OnUserTypeCallback {
        public void userType(long type);
    }
}
