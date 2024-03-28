package dao.custom.impl;

import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.Order;

public class OrderDaoImpl implements OrderDAO {

    @Override
    public boolean save(Order order) throws Exception {
        return CrudUtil.execute("INSERT INTO Orders VALUES (?,?,?)",
                order.getOrderID(), order.getOrderDate(),
                order.getCustomerID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(Order t) throws Exception {
        return false;
    }

    @Override
    public Order get(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws Exception {
        return null;
    }

}
