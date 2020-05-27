package com.android.sssameeri.cargodelivery.ui.holder.customerHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sssameeri.cargodelivery.R;
import com.android.sssameeri.cargodelivery.interfaces.OnChangeOrderStatusToEnded;
import com.android.sssameeri.cargodelivery.interfaces.OnCustomerOrdersClickButton;

public class InProcessOrdersHolderC extends RecyclerView.ViewHolder {

    public TextView addressFrom;
    public TextView addressTo;
    public TextView cityFrom;
    public TextView cityTo;
    public TextView dateFrom;
    public TextView dateTo;
    public TextView orderDescription;
    public TextView transporterPhone;
    public TextView transporterName;
    public TextView orderPrice;
    public Button button;

    public InProcessOrdersHolderC(@NonNull View itemView) {
        super(itemView);
        addressFrom = itemView.findViewById(R.id.addressFromTxtViewInProcess);
        addressTo = itemView.findViewById(R.id.addressToTxtViewInProcess);
        cityFrom = itemView.findViewById(R.id.cityFromTxtViewInProcess);
        cityTo = itemView.findViewById(R.id.cityToTxtViewInProcess);
        dateFrom = itemView.findViewById(R.id.dateStartTxtViewInProcess);
        dateTo = itemView.findViewById(R.id.dateEndTxtViewInProcess);
        orderDescription = itemView.findViewById(R.id.orderDescriptionTxtViewInProcess);
        transporterPhone = itemView.findViewById(R.id.transporterPhoneInProcess);
        transporterName = itemView.findViewById(R.id.transporterNameInProcess);
        orderPrice = itemView.findViewById(R.id.priceTxtViewInProcess);
        button = itemView.findViewById(R.id.endButton);
    }

    public void setListener(OnChangeOrderStatusToEnded listener, int position) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onChangeStatus(position);
            }
        });
    }
}
