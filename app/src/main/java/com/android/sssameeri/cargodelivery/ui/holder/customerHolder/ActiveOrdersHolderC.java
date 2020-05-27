package com.android.sssameeri.cargodelivery.ui.holder.customerHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;

public class ActiveOrdersHolderC extends RecyclerView.ViewHolder {

    public TextView addressFromTxtView;
    public TextView addressToTxtView;
    public TextView cityFromTxtView;
    public TextView cityToTxtView;
    public TextView dateFromTxtView;
    public TextView dateToTxtView;
    public TextView orderDescriptionTxtView;

    public ActiveOrdersHolderC(@NonNull View itemView) {
        super(itemView);

        addressFromTxtView = itemView.findViewById(R.id.addressFromTxtViewActive);
        addressToTxtView = itemView.findViewById(R.id.addressToTxtViewActive);
        cityFromTxtView = itemView.findViewById(R.id.cityFromTxtViewActive);
        cityToTxtView = itemView.findViewById(R.id.cityToTxtViewActive);
        dateFromTxtView = itemView.findViewById(R.id.dateFromTxtViewActive);
        dateToTxtView = itemView.findViewById(R.id.dateToTxtViewActive);
        orderDescriptionTxtView = itemView.findViewById(R.id.orderDescriptionTxtViewActive);
    }
}
