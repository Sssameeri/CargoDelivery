package com.android.sssameeri.cargodelivery.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.sssameeri.cargodelivery.model.Customer;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CustomerDao {
    @Insert
    Single<Long> insertCustomer(Customer customer);

    @Query("SELECT * FROM customer WHERE customer_phone = :phone AND customer_password = :password")
    Single<Customer> getCustomerData(String phone, String password);

    @Query("SELECT * FROM customer WHERE customer_id = :id")
    Single<Customer> getCustomerById(long id);
}
