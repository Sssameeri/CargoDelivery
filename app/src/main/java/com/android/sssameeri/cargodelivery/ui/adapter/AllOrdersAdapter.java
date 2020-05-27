package com.android.sssameeri.cargodelivery.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.interfaces.OnTransporterClickButton;
import com.android.sssameeri.cargodelivery.model.OrderWithCustomerData;

import java.util.List;

public class AllOrdersAdapter extends RecyclerView.Adapter<AllOrdersAdapter.AllOrdersViewHolder> {

    private OnTransporterClickButton listener;

    private List<OrderWithCustomerData> orderList;

    public void setList(List<OrderWithCustomerData> orderList) {
        this.orderList = orderList;
    }

    public void setOnBtnClick(OnTransporterClickButton listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AllOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_orders_item, parent, false);
        return new AllOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllOrdersViewHolder holder, int position) {
        OrderWithCustomerData order = orderList.get(position);

        holder.addressFrom.setText(order.getAddressFrom());
        holder.addressTo.setText(order.getAddressTo());
        holder.cityFrom.setText(order.getCityFrom());
        holder.cityTo.setText(order.getCityTo());
        holder.dateFrom.setText(order.getDateFrom());
        holder.dateTo.setText(order.getDateTo());
        holder.orderDescription.setText(order.getOrderDescription());
        holder.customerPhone.setText(order.getCustomerPhone());
        holder.customerName.setText(order.getCustomerName());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    listener.onClick((int) order.getId(), holder.editText.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class AllOrdersViewHolder extends RecyclerView.ViewHolder {

        private TextView addressFrom;
        private TextView addressTo;
        private TextView cityFrom;
        private TextView cityTo;
        private TextView dateFrom;
        private TextView dateTo;
        private TextView orderDescription;
        private TextView customerPhone;
        private TextView customerName;
        private Button button;
        private EditText editText;

        public AllOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            addressFrom = itemView.findViewById(R.id.addressFromTxtViewAll);
            addressTo = itemView.findViewById(R.id.addressToTxtViewAll);
            cityFrom = itemView.findViewById(R.id.cityFromTxtViewAll);
            cityTo = itemView.findViewById(R.id.cityTomTxtViewAll);
            dateFrom = itemView.findViewById(R.id.dateFromTxtViewAll);
            dateTo = itemView.findViewById(R.id.dateToTxtViewAll);
            orderDescription = itemView.findViewById(R.id.orderDescriptionTxtViewAll);
            customerPhone = itemView.findViewById(R.id.customerPhoneAll);
            customerName = itemView.findViewById(R.id.customerNameAll);
            button = itemView.findViewById(R.id.acceptOrderBtn);
            editText = itemView.findViewById(R.id.allOrdersEditTxt);
        }
    }
}
