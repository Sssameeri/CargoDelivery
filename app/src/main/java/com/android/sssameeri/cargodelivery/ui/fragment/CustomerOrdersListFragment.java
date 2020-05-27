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
import com.android.sssameeri.cargodelivery.interfaces.OnChangeOrderStatusToEnded;
import com.android.sssameeri.cargodelivery.model.OrderWithUsersInfo;
import com.android.sssameeri.cargodelivery.model.Status;
import com.android.sssameeri.cargodelivery.interfaces.OnCustomerOrdersClickButton;
import com.android.sssameeri.cargodelivery.ui.adapter.OrderAdapter;
import com.android.sssameeri.cargodelivery.viewmodel.CustomerViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerOrdersListFragment extends Fragment implements OnCustomerOrdersClickButton, OnChangeOrderStatusToEnded {

    private RecyclerView ordersRecyclerView;
    private CustomerViewModel customerViewModel;
    private List<OrderWithUsersInfo> orderList;
    private OrderAdapter orderAdapter;
    private Spinner spinner;
    private OnCustomerOrdersClickButton listener;
    private OnChangeOrderStatusToEnded changeListener;
    private CompositeDisposable compositeDisposable;

    public CustomerOrdersListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener = this;
        changeListener = this;

        compositeDisposable = new CompositeDisposable();

        customerViewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);

        spinner = view.findViewById(R.id.statusSpinner);

        orderAdapter = new OrderAdapter();
        orderAdapter.setOnBtnClick(listener);
        orderAdapter.setChangeListener(changeListener);
        ordersRecyclerView = view.findViewById(R.id.customerOrdersList);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customerViewModel.getId().observe(getViewLifecycleOwner(), new Observer<Long>() {
                    @Override
                    public void onChanged(Long aLong) {
                        Disposable disposable =
                                customerViewModel.getCustomerOrdersByStatus(aLong, spinner.getSelectedItem().toString())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                result -> {
                                                    orderAdapter.setOrderList(result);
                                                    ordersRecyclerView.setAdapter(orderAdapter);
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

    @Override
    public void onClick(int position) {
        Disposable disposable = customerViewModel.updateCustomerOrder(Status.IN_PROCESS, position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            Snackbar.make(getView(), R.string.status_updated, Snackbar.LENGTH_SHORT).show();
                        },
                        throwable -> {
                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                );
        compositeDisposable.add(disposable);
    }


    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onChangeStatus(int position) {
        Disposable disposable = customerViewModel.updateCustomerOrder(Status.ENDED, position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            Snackbar.make(getView(), R.string.status_updated, Snackbar.LENGTH_SHORT).show();
                        },
                        throwable -> {
                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                );
        compositeDisposable.add(disposable);
    }
}
