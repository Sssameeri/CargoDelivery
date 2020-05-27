package com.android.sssameeri.cargodelivery.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.model.OrderWithUsersInfo;
import com.android.sssameeri.cargodelivery.ui.adapter.TransporterOrdersAdapter;
import com.android.sssameeri.cargodelivery.viewmodel.TransporterViewModel;
import com.google.android.material.snackbar.Snackbar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class TransporterOrdersListFragment extends Fragment {

    private RecyclerView ordersRecyclerView;
    private TransporterViewModel transporterViewModel;
    private TransporterOrdersAdapter transporterOrdersAdapter;
    private Spinner spinner;
    private CompositeDisposable compositeDisposable;


    public TransporterOrdersListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transporter_orders_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        compositeDisposable = new CompositeDisposable();
        spinner = view.findViewById(R.id.transporterStatusSpinner);

        transporterViewModel = new ViewModelProvider(requireActivity()).get(TransporterViewModel.class);

        transporterOrdersAdapter = new TransporterOrdersAdapter();
        ordersRecyclerView = view.findViewById(R.id.transporterOrdersList);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transporterViewModel.getId().observe(getViewLifecycleOwner(), new Observer<Long>() {
                    @Override
                    public void onChanged(Long aLong) {
                        Disposable disposable = transporterViewModel.getTransporterOrdersByStatus(aLong, spinner.getSelectedItem().toString())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        result -> {
                                            transporterOrdersAdapter.setOrderWithUsersInfoList(result);
                                            ordersRecyclerView.setAdapter(transporterOrdersAdapter);
                                        },
                                        throwable -> {
                                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                );
                        compositeDisposable.add(disposable);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}

