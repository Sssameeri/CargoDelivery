package com.android.sssameeri.cargodelivery.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.interfaces.OnChangeOrderStatusToEnded;
import com.android.sssameeri.cargodelivery.interfaces.OnCustomerOrdersClickButton;
import com.android.sssameeri.cargodelivery.model.OrderWithUsersInfo;
import com.android.sssameeri.cargodelivery.model.Status;
import com.android.sssameeri.cargodelivery.ui.holder.customerHolder.ActiveOrdersHolderC;
import com.android.sssameeri.cargodelivery.ui.holder.customerHolder.ApprovedOrdersHolderC;
import com.android.sssameeri.cargodelivery.ui.holder.customerHolder.EndedOrdersHolderC;
import com.android.sssameeri.cargodelivery.ui.holder.customerHolder.InProcessOrdersHolderC;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter {

    private List<OrderWithUsersInfo> orderList;

    private OnCustomerOrdersClickButton listener;
    private OnChangeOrderStatusToEnded changeListener;

    public void setOnBtnClick(OnCustomerOrdersClickButton listener) {
        this.listener = listener;
    }

    public void setOrderList(List<OrderWithUsersInfo> orderList) {
        this.orderList = orderList;
    }
    public void setChangeListener(OnChangeOrderStatusToEnded changeListener) {
        this.changeListener = changeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_order_item, parent, false);
                return new ActiveOrdersHolderC(view);
            } case 1: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.approve_orders_item, parent, false);
                return new ApprovedOrdersHolderC(view);
            } case 2: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_in_process_item, parent, false);
                return new InProcessOrdersHolderC(view);
            } case 3: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ended_orders_item, parent, false);
                return new EndedOrdersHolderC(view);
            }
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ActiveOrdersHolderC) {
            OrderWithUsersInfo order = orderList.get(position);
            ((ActiveOrdersHolderC) holder).addressFromTxtView.setText(order.getAddressFrom());
            ((ActiveOrdersHolderC) holder).addressToTxtView.setText(order.getAddressTo());
            ((ActiveOrdersHolderC) holder).cityFromTxtView.setText(order.getCityFrom());
            ((ActiveOrdersHolderC) holder).cityToTxtView.setText(order.getCityTo());
            ((ActiveOrdersHolderC) holder).dateFromTxtView.setText(order.getDateFrom());
            ((ActiveOrdersHolderC) holder).dateToTxtView.setText(order.getDateTo());
            ((ActiveOrdersHolderC) holder).orderDescriptionTxtView.setText(order.getOrderDescription());
        } else if(holder instanceof ApprovedOrdersHolderC) {
             OrderWithUsersInfo order = orderList.get(position);
            ((ApprovedOrdersHolderC) holder).addressFromTxtView.setText(order.getAddressFrom());
            ((ApprovedOrdersHolderC) holder).addressToTxtView.setText(order.getAddressTo());
            ((ApprovedOrdersHolderC) holder).cityFromTxtView.setText(order.getCityFrom());
            ((ApprovedOrdersHolderC) holder).cityToTxtView.setText(order.getCityTo());
            ((ApprovedOrdersHolderC) holder).dateFromTxtView.setText(order.getDateFrom());
            ((ApprovedOrdersHolderC) holder).dateToTxtView.setText(order.getDateTo());
            ((ApprovedOrdersHolderC) holder).orderDescriptionTxtView.setText(order.getOrderDescription());
            ((ApprovedOrdersHolderC) holder).transporterNameTxtView.setText(order.getTransporterName());
            ((ApprovedOrdersHolderC) holder).transporterPhoneTxtView.setText(order.getTransporterPhone());
            ((ApprovedOrdersHolderC) holder).priceTxtView.setText(String.valueOf(order.getPrice()));
            ((ApprovedOrdersHolderC) holder).setListener(listener, (int) order.getId());
        } else if(holder instanceof InProcessOrdersHolderC) {
            OrderWithUsersInfo order = orderList.get(position);
            ((InProcessOrdersHolderC) holder).addressFrom.setText(order.getAddressFrom());
            ((InProcessOrdersHolderC) holder).addressTo.setText(order.getAddressTo());
            ((InProcessOrdersHolderC) holder).cityFrom.setText(order.getCityFrom());
            ((InProcessOrdersHolderC) holder).cityTo.setText(order.getCityTo());
            ((InProcessOrdersHolderC) holder).dateFrom.setText(order.getDateFrom());
            ((InProcessOrdersHolderC) holder).dateTo.setText(order.getDateTo());
            ((InProcessOrdersHolderC) holder).orderDescription.setText(order.getOrderDescription());
            ((InProcessOrdersHolderC) holder).transporterName.setText(order.getTransporterName());
            ((InProcessOrdersHolderC) holder).transporterPhone.setText(order.getTransporterPhone());
            ((InProcessOrdersHolderC) holder).orderPrice.setText(String.valueOf(order.getPrice()));
            ((InProcessOrdersHolderC) holder).button.setVisibility(View.VISIBLE);
            ((InProcessOrdersHolderC) holder).setListener(changeListener, (int) order.getId());
        } else {
            OrderWithUsersInfo order = orderList.get(position);
            ((EndedOrdersHolderC) holder).addressFromTxtView.setText(order.getAddressFrom());
            ((EndedOrdersHolderC) holder).addressToTxtView.setText(order.getAddressTo());
            ((EndedOrdersHolderC) holder).cityFromTxtView.setText(order.getCityFrom());
            ((EndedOrdersHolderC) holder).cityToTxtView.setText(order.getCityTo());
            ((EndedOrdersHolderC) holder).dateFromTxtView.setText(order.getDateFrom());
            ((EndedOrdersHolderC) holder).dateToTxtView.setText(order.getDateTo());
            ((EndedOrdersHolderC) holder).orderDescriptionTxtView.setText(order.getOrderDescription());
            ((EndedOrdersHolderC) holder).transporterNameTxtView.setText(order.getTransporterName());
            ((EndedOrdersHolderC) holder).transporterPhoneTxtView.setText(order.getTransporterPhone());
            ((EndedOrdersHolderC) holder).customerNameTxtView.setText(order.getCustomerName());
            ((EndedOrdersHolderC) holder).customerPhoneTxtView.setText(order.getCustomerPhone());
            ((EndedOrdersHolderC) holder).priceTxtView.setText(String.valueOf(order.getPrice()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        String status = orderList.get(position).getOrderStatus();

        switch (status) {
            case Status.ACTIVE: {
                return 0;
            } case Status.APPROVED: {
                return 1;
            } case Status.IN_PROCESS: {
                return 2;
            } case  Status.ENDED: {
                return 3;
            }
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
