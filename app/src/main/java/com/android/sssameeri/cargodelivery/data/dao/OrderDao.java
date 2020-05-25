package com.android.sssameeri.cargodelivery.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.sssameeri.cargodelivery.model.Order;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface OrderDao {
    @Insert
    Single<Long> insertOrder(Order order);

    @Query("SELECT * FROM orders WHERE customer_id = :id AND order_status = :status")
    Flowable<List<Order>> getCustomerOrdersByStatus(long id, String status);
}
