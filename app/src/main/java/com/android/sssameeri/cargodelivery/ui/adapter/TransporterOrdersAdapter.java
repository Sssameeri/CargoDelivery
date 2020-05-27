package com.android.sssameeri.cargodelivery.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.model.OrderWithUsersInfo;
import com.android.sssameeri.cargodelivery.model.Status;
import com.android.sssameeri.cargodelivery.ui.holder.customerHolder.EndedOrdersHolderC;
import com.android.sssameeri.cargodelivery.ui.holder.transporterHolder.ApprovedOrdersHolderT;
import com.android.sssameeri.cargodelivery.ui.holder.transporterHolder.EndedOrdersHolderT;
import com.android.sssameeri.cargodelivery.ui.holder.transporterHolder.InProcessOrdersHolderT;

import java.util.List;

public class TransporterOrdersAdapter extends RecyclerView.Adapter {

    private List<OrderWithUsersInfo> orderWithUsersInfoList;

    public void setOrderWithUsersInfoList(List<OrderWithUsersInfo> orderWithUsersInfoList) {
        this.orderWithUsersInfoList = orderWithUsersInfoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case 0: {
                view = LayoutInflater.from(parent.getContext()).inflate( R.layout.approve_orders_item, parent, false);
                return new ApprovedOrdersHolderT(view);
            } case 1: {
                view = LayoutInflater.from(parent.getContext()).inflate( R.layout.orders_in_process_item, parent, false);
                return new InProcessOrdersHolderT(view);
            } case 2: {
                view = LayoutInflater.from(parent.getContext()).inflate( R.layout.ended_orders_item, parent, false);
                return new EndedOrdersHolderT(view);
            }
            default:
                throw new IllegalStateException("Unexpected value:" + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ApprovedOrdersHolderT) {
            OrderWithUsersInfo order = orderWithUsersInfoList.get(position);
            ((ApprovedOrdersHolderT) holder).addressFromTxtView.setText(order.getAddressFrom());
            ((ApprovedOrdersHolderT) holder).addressToTxtView.setText(order.getAddressTo());
            ((ApprovedOrdersHolderT) holder).cityFromTxtView.setText(order.getCityFrom());
            ((ApprovedOrdersHolderT) holder).cityToTxtView.setText(order.getCityTo());
            ((ApprovedOrdersHolderT) holder).dateFromTxtView.setText(order.getDateFrom());
            ((ApprovedOrdersHolderT) holder).dateToTxtView.setText(order.getDateTo());
            ((ApprovedOrdersHolderT) holder).orderDescriptionTxtView.setText(order.getOrderDescription());
            ((ApprovedOrdersHolderT) holder).customerNameTxtView.setText(order.getCustomerName());
            ((ApprovedOrdersHolderT) holder).customerPhoneTxtView.setText(order.getCustomerPhone());
            ((ApprovedOrdersHolderT) holder).priceTxtView.setText(String.valueOf(order.getPrice()));
            ((ApprovedOrdersHolderT) holder).button.setVisibility(View.GONE);
        } else if(holder instanceof InProcessOrdersHolderT) {
            OrderWithUsersInfo order = orderWithUsersInfoList.get(position);
            ((InProcessOrdersHolderT) holder).addressFromTxtView.setText(order.getAddressFrom());
            ((InProcessOrdersHolderT) holder).addressToTxtView.setText(order.getAddressTo());
            ((InProcessOrdersHolderT) holder).cityFromTxtView.setText(order.getCityFrom());
            ((InProcessOrdersHolderT) holder).cityToTxtView.setText(order.getCityTo());
            ((InProcessOrdersHolderT) holder).dateFromTxtView.setText(order.getDateFrom());
            ((InProcessOrdersHolderT) holder).dateToTxtView.setText(order.getDateTo());
            ((InProcessOrdersHolderT) holder).orderDescriptionTxtView.setText(order.getOrderDescription());
            ((InProcessOrdersHolderT) holder).customerNameTxtView.setText(order.getCustomerName());
            ((InProcessOrdersHolderT) holder).customerPhoneTxtView.setText(order.getCustomerPhone());
            ((InProcessOrdersHolderT) holder).priceTxtView.setText(String.valueOf(order.getPrice()));
        } else {
            OrderWithUsersInfo order = orderWithUsersInfoList.get(position);
            ((EndedOrdersHolderT) holder).addressFromTxtView.setText(order.getAddressFrom());
            ((EndedOrdersHolderT) holder).addressToTxtView.setText(order.getAddressTo());
            ((EndedOrdersHolderT) holder).cityFromTxtView.setText(order.getCityFrom());
            ((EndedOrdersHolderT) holder).cityToTxtView.setText(order.getCityTo());
            ((EndedOrdersHolderT) holder).dateFromTxtView.setText(order.getDateFrom());
            ((EndedOrdersHolderT) holder).dateToTxtView.setText(order.getDateTo());
            ((EndedOrdersHolderT) holder).orderDescriptionTxtView.setText(order.getOrderDescription());
            ((EndedOrdersHolderT) holder).transporterNameTxtView.setText(order.getTransporterName());
            ((EndedOrdersHolderT) holder).transporterPhoneTxtView.setText(order.getTransporterPhone());
            ((EndedOrdersHolderT) holder).customerNameTxtView.setText(order.getCustomerName());
            ((EndedOrdersHolderT) holder).customerPhoneTxtView.setText(order.getCustomerPhone());
            ((EndedOrdersHolderT) holder).priceTxtView.setText(String.valueOf(order.getPrice()));
        }

    }

    @Override
    public int getItemViewType(int position) {
        String status = orderWithUsersInfoList.get(position).getOrderStatus();
        switch (status) {
            case Status.APPROVED: {
                return 0;
            } case Status.IN_PROCESS: {
                return 1;
            } case Status.ENDED: {
                return 2;
            }
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return orderWithUsersInfoList.size();
    }
}
