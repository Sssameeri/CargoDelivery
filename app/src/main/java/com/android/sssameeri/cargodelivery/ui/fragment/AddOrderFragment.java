package com.android.sssameeri.cargodelivery.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.model.Order;
import com.android.sssameeri.cargodelivery.model.Status;
import com.android.sssameeri.cargodelivery.model.Transport;
import com.android.sssameeri.cargodelivery.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddOrderFragment extends Fragment {

    private EditText addressFromEditTxt;
    private EditText addressToEditTxt;
    private EditText cityFromEditTxt;
    private EditText cityToEditTxt;
    private EditText dateStartEditTxt;
    private EditText dateEndEditTxt;
    private EditText orderDescriptionEditTxt;
    private Button createOrderBtn;
    private CustomerViewModel customerViewModel;
    private Order order;
    private String addressFrom;
    private String addressTo;
    private String cityFrom;
    private String cityTo;
    private String dateStart;
    private String dateEnd;
    private String orderDescription;
    private String orderStatus;
    private long transportId;
    private List<Transport> transportList;
    private Spinner transportSpinner;
    private SpinnerAdapter adapter;
    private CompositeDisposable compositeDisposable;

    public AddOrderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize CustomerViewModel
        customerViewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);

        compositeDisposable = new CompositeDisposable();

        addressFromEditTxt = view.findViewById(R.id.addressFromEditTxt);
        addressToEditTxt = view.findViewById(R.id.addressToEditTxt);
        cityFromEditTxt = view.findViewById(R.id.cityFromEditTxt);
        cityToEditTxt = view.findViewById(R.id.cityToEditTxt);
        dateStartEditTxt = view.findViewById(R.id.dateStartEditTxt);
        dateEndEditTxt = view.findViewById(R.id.dateEndEditTxt);
        orderDescriptionEditTxt = view.findViewById(R.id.orderDescriptionEditTxt);
        createOrderBtn = view.findViewById(R.id.createOrderBtn);
        transportSpinner = view.findViewById(R.id.orderTransportSpinner);

       // transportList = customerViewModel.getAllTransport();
        transportList = new ArrayList<>();
        Disposable getAllTransportDisposable =
                customerViewModel.getAllTransport()
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

    @Override
    public void onResume() {
        super.onResume();

        transportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transportId = parent.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        createOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order = new Order();

                addressFrom = addressFromEditTxt.getText().toString();
                addressTo = addressToEditTxt.getText().toString();
                cityFrom = cityFromEditTxt.getText().toString();
                cityTo = cityToEditTxt.getText().toString();
                dateStart = dateStartEditTxt.getText().toString();
                dateEnd = dateEndEditTxt.getText().toString();
                orderDescription = orderDescriptionEditTxt.getText().toString();
                orderStatus = Status.ACTIVE.getStatus();

                Log.d("TAG", transportId + " ");

                customerViewModel.getId().observe(getViewLifecycleOwner(), new Observer<Long>() {
                    @Override
                    public void onChanged(Long aLong) {
                        order.setAddressFrom(addressFrom);
                        order.setAddressTo(addressTo);
                        order.setCityFrom(cityFrom);
                        order.setCityTo(cityTo);
                        order.setDateFrom(dateStart);
                        order.setDateTo(dateEnd);
                        order.setOrderDescription(orderDescription);
                        order.setStatus(orderStatus);
                        order.setIdTransporter(null);
                        order.setIdTransport(transportId);
                        order.setIdCustomer(aLong);

                        Disposable insertOrderDisposable = customerViewModel.insertOrder(order)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        result -> {
                                            clearFields();
                                            Toast.makeText(getContext(), "Order added", Toast.LENGTH_SHORT).show();
                                        },
                                        throwable -> {
                                            Log.d("TAG", throwable.getMessage());
                                        }
                                );
                        compositeDisposable.add(insertOrderDisposable);
                    }
                });
            }
        });
    }

    private void clearFields() {
        addressFromEditTxt.getText().clear();
        addressToEditTxt.getText().clear();;
        cityFromEditTxt.getText().clear();
        cityToEditTxt.getText().clear();
        dateStartEditTxt.getText().clear();
        dateEndEditTxt.getText().clear();
        orderDescriptionEditTxt.getText().clear();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

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
