package com.android.sssameeri.cargodelivery.ui.fragment.authorizationUI;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.model.Transport;
import com.android.sssameeri.cargodelivery.model.Transporter;
import com.android.sssameeri.cargodelivery.ui.fragment.AddOrderFragment;
import com.android.sssameeri.cargodelivery.viewmodel.TransporterViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TransporterAuthFragment extends Fragment {

    //UI components
    private EditText nameEditTxt;
    private EditText phoneEditTxt;
    private EditText passwordEditText;
    private Button transporterSignUpBtn;
    private Spinner transportSpinner;
    private SpinnerAdapter adapter;
    private TextView switchAuthMode;
    private NavController navController;

    private CompositeDisposable compositeDisposable;
    private TransporterViewModel transporterViewModel;

    //Data variables
    private Transporter transporter;
    private String name;
    private String phone;
    private String password;
    private long transportId;
    private List<Transport> transportList;

    private boolean authMode = false; //if false then registration else login

    public TransporterAuthFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transporter_auth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transporterViewModel = new ViewModelProvider(requireActivity()).get(TransporterViewModel.class);

        navController = Navigation.findNavController(view);

        nameEditTxt = view.findViewById(R.id.nameEditTxtT);
        phoneEditTxt = view.findViewById(R.id.phoneEditTxtT);
        passwordEditText = view.findViewById(R.id.passwordEditTxtT);
        transportSpinner = view.findViewById(R.id.transportSpinner);
        transporterSignUpBtn = view.findViewById(R.id.transporterSignUpBtn);
        switchAuthMode = view.findViewById(R.id.switchAuthModeT);
        compositeDisposable = new CompositeDisposable();

        initSpinner(); //spinner with list of transport
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

        transportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transportId = parent.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        transporterSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authMode) {
                    getTransporterData();
                } else {
                    insertTransporter();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void getTransporterData() {
        phone = phoneEditTxt.getText().toString();
        password = passwordEditText.getText().toString();

        Disposable getTransporterDataDisposable = transporterViewModel.getTransporterData(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            transporterViewModel.setId(result.getId());
                            navController.navigate(R.id.profileFragment);
                        },
                        throwable -> {
                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                );
        compositeDisposable.add(getTransporterDataDisposable);
    }

    private void insertTransporter() {
        name = nameEditTxt.getText().toString();
        phone = phoneEditTxt.getText().toString();
        password = passwordEditText.getText().toString();

        transporter = new Transporter();
        transporter.setName(name);
        transporter.setPhone(phone);
        transporter.setPassword(password);
        transporter.setIdTransport(transportId);

        Disposable disposable = transporterViewModel.insertTransporter(transporter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            transporterViewModel.setId(result);
                            navController.navigate(R.id.profileFragment);
                        },
                        throwable -> {
                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                );
    }

    private void initSpinner() {
        transportList = new ArrayList<>();
        Disposable getAllTransportDisposable =
                transporterViewModel.getAllTransport()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                result -> {
                                    transportList.addAll(result);
                                    adapter = new SpinnerAdapter(
                                            getActivity(),
                                            R.layout.spinner_item,
                                            transportList);

                                    transportSpinner.setAdapter(adapter);
                                },
                                throwable -> {
                                    Log.d("TAG", throwable.getMessage());
                                }
                        );
        compositeDisposable.add(getAllTransportDisposable);
    }

    private void authMode() {
        if(!authMode) {
            nameEditTxt.setVisibility(View.GONE);
            transporterSignUpBtn.setText("Увійти");
            switchAuthMode.setText(getString(R.string.register));
            transportSpinner.setVisibility(View.GONE);
            authMode = true;
        } else {
            nameEditTxt.setVisibility(View.VISIBLE);
            transporterSignUpBtn.setText(getString(R.string.register));
            switchAuthMode.setText(getString(R.string.login));
            transportSpinner.setVisibility(View.VISIBLE);
            authMode = false;
        }
    }

    //Spinner with list of transport
    private class SpinnerAdapter extends ArrayAdapter<Transport> {

        private List<Transport> list;

        public SpinnerAdapter(@NonNull Context context, int resource, List<Transport> list) {
            super(context, resource);
            this.list = list;
        }

        @Override
        public long getItemId(int position) {
            return list.get(position).getId();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public Transport getItem(int position) {
            return list.get(position);
        }
    }
}
