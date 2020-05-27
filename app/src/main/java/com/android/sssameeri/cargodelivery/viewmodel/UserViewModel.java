package com.android.sssameeri.cargodelivery.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private MutableLiveData<Long> userType = new MutableLiveData<>();

    public void setUserType(long type) {
        userType.postValue(type);
    }

    public LiveData<Long> getUserType() {
        return  userType;
    }
}
