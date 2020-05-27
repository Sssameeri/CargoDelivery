package com.android.sssameeri.cargodelivery.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.model.Status;
import com.android.sssameeri.cargodelivery.ui.adapter.AllOrdersAdapter;
import com.android.sssameeri.cargodelivery.interfaces.OnTransporterClickButton;
import com.android.sssameeri.cargodelivery.viewmodel.TransporterViewModel;
import com.google.android.material.snackbar.Snackbar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class TransporterAllOrdersFragment extends Fragment implements OnTransporterClickButton {

    private RecyclerView allOrdersRecyclerView;
    private AllOrdersAdapter allOrdersAdapter;
    private TransporterViewModel transporterViewModel;
    private OnTransporterClickButton listener;
    private CompositeDisposable compositeDisposable;

    public TransporterAllOrdersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener = this;
        compositeDisposable = new CompositeDisposable();

        transporterViewModel = new ViewModelProvider(requireActivity()).get(TransporterViewModel.class);

        allOrdersAdapter = new AllOrdersAdapter();
        allOrdersRecyclerView = view.findViewById(R.id.allOrdersRecyclerView);
        allOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        transporterViewModel.getTransportId().observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Disposable getOrdersWithStatusDisposable =
                        transporterViewModel.getOrdersByStatusWithOwnTransport(Status.ACTIVE, aLong)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                result -> {
                                    allOrdersAdapter.setOnBtnClick(listener);
                                    allOrdersAdapter.setList(result);
                                    allOrdersRecyclerView.setAdapter(allOrdersAdapter);
                                },
                                throwable -> {
                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        );
            }
        });
    }

    @Override
    public void onClick(int position, String price) {
        if(TextUtils.isEmpty(price) || !price.matches("\\d+")) {
            Snackbar.make(getView(), R.string.input_price, Snackbar.LENGTH_SHORT).show();
        } else {
            transporterViewModel.getId().observe(getViewLifecycleOwner(), new Observer<Long>() {
                @Override
                public void onChanged(Long aLong) {
                    Disposable disposable = transporterViewModel.updateOrder(Status.APPROVED, Double.parseDouble(price), aLong, position)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    result -> {
                                        Snackbar.make(getView(), R.string.status_updated, Snackbar.LENGTH_SHORT).show();
                                    },
                                    throwable -> {
                                        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            );
                    allOrdersAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
