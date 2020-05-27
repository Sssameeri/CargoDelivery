package com.android.sssameeri.cargodelivery.ui.holder.transporterHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;

public class InProcessOrdersHolderT extends RecyclerView.ViewHolder {

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

    public InProcessOrdersHolderT(@NonNull View itemView) {
        super(itemView);
        addressFromTxtView = itemView.findViewById(R.id.addressFromTxtViewInProcess);
        addressToTxtView = itemView.findViewById(R.id.addressToTxtViewInProcess);
        cityFromTxtView = itemView.findViewById(R.id.cityFromTxtViewInProcess);
        cityToTxtView = itemView.findViewById(R.id.cityToTxtViewInProcess);
        dateFromTxtView = itemView.findViewById(R.id.dateStartTxtViewInProcess);
        dateToTxtView = itemView.findViewById(R.id.dateEndTxtViewInProcess);
        orderDescriptionTxtView = itemView.findViewById(R.id.orderDescriptionTxtViewInProcess);
        customerNameTxtView = itemView.findViewById(R.id.transporterNameInProcess);
        customerPhoneTxtView = itemView.findViewById(R.id.transporterPhoneInProcess);
        priceTxtView = itemView.findViewById(R.id.priceTxtViewInProcess);
    }
}
