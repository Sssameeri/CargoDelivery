package com.android.sssameeri.cargodelivery.ui.holder.customerHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.interfaces.OnCustomerOrdersClickButton;

public class ApprovedOrdersHolderC extends RecyclerView.ViewHolder {

    public TextView addressFromTxtView;
    public TextView addressToTxtView;
    public TextView cityFromTxtView;
    public TextView cityToTxtView;
    public TextView dateFromTxtView;
    public TextView dateToTxtView;
    public TextView orderDescriptionTxtView;
    public TextView transporterNameTxtView;
    public TextView transporterPhoneTxtView;
    public TextView priceTxtView;
    private Button approve;

    public ApprovedOrdersHolderC(@NonNull View itemView) {
        super(itemView);

        addressFromTxtView = itemView.findViewById(R.id.addressFromTxtViewApproved);
        addressToTxtView = itemView.findViewById(R.id.addressToTxtViewApproved);
        cityFromTxtView = itemView.findViewById(R.id.cityFromTxtViewApproved);
        cityToTxtView = itemView.findViewById(R.id.cityToTxtViewApproved);
        dateFromTxtView = itemView.findViewById(R.id.dateFromTxtViewApproved);
        dateToTxtView = itemView.findViewById(R.id.dateToTxtViewApproved);
        orderDescriptionTxtView = itemView.findViewById(R.id.orderDescriptionTxtViewApproved);
        transporterNameTxtView = itemView.findViewById(R.id.transporterNameApproved);
        transporterPhoneTxtView = itemView.findViewById(R.id.transporterPhoneApproved);
        priceTxtView = itemView.findViewById(R.id.priceTxtViewApproved);
        approve = itemView.findViewById(R.id.approveOrderBtn);
    }

    public void setListener(OnCustomerOrdersClickButton listener, int position) {
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }
}
