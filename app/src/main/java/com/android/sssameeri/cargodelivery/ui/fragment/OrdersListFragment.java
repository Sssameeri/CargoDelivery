package com.android.sssameeri.cargodelivery.ui.fragment;

import android.os.Bundle;

import androidx.annotation.LongDef;
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
import com.android.sssameeri.cargodelivery.model.Order;
import com.android.sssameeri.cargodelivery.model.Status;
import com.android.sssameeri.cargodelivery.ui.adapter.OrderAdapter;
import com.android.sssameeri.cargodelivery.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrdersListFragment extends Fragment {

    private RecyclerView ordersRecyclerView;
    private CustomerViewModel customerViewModel;
    private List<Order> orderList;
    private OrderAdapter orderAdapter;
    private Spinner spinner;
    private String orderStatus = null;

    public OrdersListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        customerViewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);

        spinner = view.findViewById(R.id.statusSpinner);

        ordersRecyclerView = view.findViewById(R.id.ordersList);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                orderStatus = parent.getItemAtPosition(position).toString();

                customerViewModel.getId().observe(getViewLifecycleOwner(), new Observer<Long>() {
                    @Override
                    public void onChanged(Long aLong) {
                        Disposable disposable =
                                customerViewModel.getCustomerOrdersByStatus(aLong, spinner.getSelectedItem().toString())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                result -> {
                                                    orderList = new ArrayList<>();
                                                    orderList.addAll(result);
                                                    orderAdapter = new OrderAdapter(orderList);
                                                    ordersRecyclerView.setAdapter(orderAdapter);
                                                },
                                                throwable -> {
                                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                        );
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                orderStatus = Status.ACTIVE.getStatus();
            }
        });
    }
}
