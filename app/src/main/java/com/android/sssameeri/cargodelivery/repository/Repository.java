package com.android.sssameeri.cargodelivery.repository;

import android.app.Application;
import android.util.Log;

import com.android.sssameeri.cargodelivery.data.dao.CustomerDao;
import com.android.sssameeri.cargodelivery.data.dao.OrderDao;
import com.android.sssameeri.cargodelivery.data.dao.TransportDao;
import com.android.sssameeri.cargodelivery.data.dao.TransporterDao;
import com.android.sssameeri.cargodelivery.data.database.Database;
import com.android.sssameeri.cargodelivery.model.Customer;
import com.android.sssameeri.cargodelivery.model.Order;
import com.android.sssameeri.cargodelivery.model.OrderWithCustomerData;
import com.android.sssameeri.cargodelivery.model.OrderWithUsersInfo;
import com.android.sssameeri.cargodelivery.model.Transport;
import com.android.sssameeri.cargodelivery.model.Transporter;
import com.android.sssameeri.cargodelivery.model.TransporterWithTransport;

import java.security.Signature;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private Database database;
    private CustomerDao customerDao;
    private OrderDao orderDao;
    private TransportDao transportDao;
    private TransporterDao transporterDao;

    public Repository(Application application) {
        database = Database.getInstance(application);
        customerDao = database.getCustomerDao();
        orderDao = database.getOrderDao();
        transportDao = database.getTransportDao();
        transporterDao = database.getTransporterDao();
    }

    public Single<Long> insertCustomer(Customer customer) { return customerDao.insertCustomer(customer); }

    public Single<Customer> getCustomerDataById(long id) {
        return customerDao.getCustomerById(id);
    }

    public Single<Customer> getCustomerData(String phone, String password) { return customerDao.getCustomerData(phone, password); }

    public Single<Long> insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    public Single<TransporterWithTransport> getTransporterById(long id) {
        return transporterDao.getTransporterById(id);
    }

    public Single<Transporter> getTransporterData(String phone, String password) {
        return transporterDao.getTransporterData(phone, password);
    }

    public Single<Integer> updateOrder(String status, double price, long transporterId, long orderId) {
        return orderDao.updateOrder(status, price, transporterId, orderId);
    }

    public Single<Long> insertTransporter(Transporter transporter) {
        return transporterDao.insertTransporter(transporter);
//        Disposable disposable =
//                        transporterDao.insertTransporter(transporter)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                result -> {
//                                    Log.d("TAG", result.toString());
//                                },
//                                throwable -> {
//                                    Log.d("TAG", throwable.getMessage());
//                                }
//                        );
    }


    public Flowable<List<OrderWithUsersInfo>> getCustomerOrdersByStatus(long id, String status) {
        return orderDao.getCustomerOrdersByStatus(id, status);
    }

    public Single<Integer> updateCustomerOrder(String status, long orderId) {
        return orderDao.updateCustomerOrder(status, orderId);
    }

    public Flowable<List<Transport>> getAllTransport() {
        return transportDao.getAllTransport();
    }

    public Flowable<List<OrderWithCustomerData>> getOrdersByStatusWithOwnTransport(String status, long id) {
        return orderDao.getOrdersByStatusWithOwnTransport(status, id);
    }

    public Flowable<List<OrderWithUsersInfo>> getTransporterOrdersByStatus(long id, String status) {
        return orderDao.getTransporterOrdersByStatus(id, status);
    }

    public void closeDatabase() {
        database.close();
    }
}
