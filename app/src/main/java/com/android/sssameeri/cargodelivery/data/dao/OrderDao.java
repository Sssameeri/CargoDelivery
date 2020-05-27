package com.android.sssameeri.cargodelivery.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.sssameeri.cargodelivery.model.Order;
import com.android.sssameeri.cargodelivery.model.OrderWithCustomerData;
import com.android.sssameeri.cargodelivery.model.OrderWithUsersInfo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface OrderDao {
    @Insert
    Single<Long> insertOrder(Order order);

    @Query("SELECT * FROM orders " +
            "LEFT JOIN transporter ON transporter.transporter_id = orders.transporter_id " +
            "INNER JOIN customer ON customer.customer_id = orders.customer_id " +
            "WHERE orders.customer_id = :id AND order_status = :status")
    Flowable<List<OrderWithUsersInfo>> getCustomerOrdersByStatus(long id, String status);

    @Query("SELECT * FROM orders " +
            "LEFT JOIN customer ON customer.customer_id = orders.customer_id " +
            "INNER JOIN transporter ON transporter.transporter_id = orders.transporter_id " +
            "WHERE orders.transporter_id = :id AND order_status = :status")
    Flowable<List<OrderWithUsersInfo>> getTransporterOrdersByStatus(long id, String status);

    @Query("SELECT * FROM orders INNER JOIN customer ON orders.customer_id = customer.customer_id WHERE order_status = :status AND transport_id = :id")
    Flowable<List<OrderWithCustomerData>> getOrdersByStatusWithOwnTransport(String status, long id);

    @Query("UPDATE orders SET transporter_id = :transporterId, order_status = :status, order_price = :price WHERE order_id = :orderId")
    Single<Integer> updateOrder(String status, double price,  long transporterId, long orderId);

    @Query("UPDATE orders SET order_status = :status WHERE order_id = :orderId")
    Single<Integer> updateCustomerOrder(String status, long orderId);
}
