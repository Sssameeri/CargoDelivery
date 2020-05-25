package com.android.sssameeri.cargodelivery.ui.adapter;

import android.util.LayoutDirection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter  extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.addressFrom.setText(order.getAddressFrom());
        holder.addressTo.setText(order.getAddressTo());
        holder.cityFrom.setText(order.getCityFrom());
        holder.cityTo.setText(order.getCityTo());
        holder.dateFrom.setText(order.getDateFrom());
        holder.dateTo.setText(order.getDateTo());
        holder.orderDescription.setText(order.getOrderDescription());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView addressFrom;
        private TextView addressTo;
        private TextView cityFrom;
        private TextView cityTo;
        private TextView dateFrom;
        private TextView dateTo;
        private TextView orderDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addressFrom = itemView.findViewById(R.id.addressFromTxtView);
            addressTo = itemView.findViewById(R.id.addressToTxtView);
            cityFrom = itemView.findViewById(R.id.cityFromTxtView);
            cityTo = itemView.findViewById(R.id.cityTomTxtView);
            dateFrom = itemView.findViewById(R.id.dateFromTxtView);
            dateTo = itemView.findViewById(R.id.dateToTxtView);
            orderDescription = itemView.findViewById(R.id.orderDescriptionTxtView);
        }
    }
}
