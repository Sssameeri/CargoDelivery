package com.android.sssameeri.cargodelivery.ui.holder.transporterHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;

public class EndedOrdersHolderT extends RecyclerView.ViewHolder {

    public TextView addressFromTxtView;
    public TextView addressToTxtView;
    public TextView cityFromTxtView;
    public TextView cityToTxtView;
    public TextView dateFromTxtView;
    public TextView dateToTxtView;
    public TextView orderDescriptionTxtView;
    public TextView customerNameTxtView;
    public TextView customerPhoneTxtView;
    public TextView transporterNameTxtView;
    public TextView transporterPhoneTxtView;
    public TextView priceTxtView;

    public EndedOrdersHolderT(@NonNull View itemView) {
        super(itemView);
        addressFromTxtView = itemView.findViewById(R.id.addressFromTxtViewEnded);
        addressToTxtView = itemView.findViewById(R.id.addressToTxtViewEnded);
        cityFromTxtView = itemView.findViewById(R.id.cityFromTxtViewEnded);
        cityToTxtView = itemView.findViewById(R.id.cityTomTxtViewEnded);
        dateFromTxtView = itemView.findViewById(R.id.dateFromTxtViewEnded);
        dateToTxtView = itemView.findViewById(R.id.dateToTxtViewEnded);
        orderDescriptionTxtView = itemView.findViewById(R.id.orderDescriptionTxtViewEnded);
        customerNameTxtView = itemView.findViewById(R.id.customerNameEnded);
        customerPhoneTxtView = itemView.findViewById(R.id.customerPhoneEnded);
        transporterNameTxtView = itemView.findViewById(R.id.transporterNameEnded);
        transporterPhoneTxtView = itemView.findViewById(R.id.transporterPhoneEnded);
        priceTxtView = itemView.findViewById(R.id.priceTxtViewEnded);
    }
}
