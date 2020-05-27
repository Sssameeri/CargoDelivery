package com.android.sssameeri.cargodelivery.ui.holder.transporterHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;

public class ApprovedOrdersHolderT extends RecyclerView.ViewHolder {

    public TextView addressFromTxtView;
    public TextView addressToTxtView;
    public TextView cityFromTxtView;
    public TextView cityToTxtView;
    public TextView dateFromTxtView;
    public TextView dateToTxtView;
    public TextView orderDescriptionTxtView;
    public TextView customerNameTxtView;
    public TextView customerPhoneTxtView;
    public TextView priceTxtView;
    public Button button;

    public ApprovedOrdersHolderT(@NonNull View itemView) {
        super(itemView);

        addressFromTxtView = itemView.findViewById(R.id.addressFromTxtViewApproved);
        addressToTxtView = itemView.findViewById(R.id.addressToTxtViewApproved);
        cityFromTxtView = itemView.findViewById(R.id.cityFromTxtViewApproved);
        cityToTxtView = itemView.findViewById(R.id.cityToTxtViewApproved);
        dateFromTxtView = itemView.findViewById(R.id.dateFromTxtViewApproved);
        dateToTxtView = itemView.findViewById(R.id.dateToTxtViewApproved);
        orderDescriptionTxtView = itemView.findViewById(R.id.orderDescriptionTxtViewApproved);
        customerNameTxtView = itemView.findViewById(R.id.transporterNameApproved);
        customerPhoneTxtView = itemView.findViewById(R.id.transporterPhoneApproved);
        priceTxtView = itemView.findViewById(R.id.priceTxtViewApproved);
        button = itemView.findViewById(R.id.approveOrderBtn);
    }
}
