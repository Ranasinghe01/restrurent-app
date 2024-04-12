package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import entity.OrderDetail;

public class OrderDetailDaoImpl implements OrderDetailDAO {

    @Override
    public boolean save(OrderDetail od) throws Exception {
        return CrudUtil.execute("INSERT INTO OrderDetail VALUES (?,?,?,?)",
                od.getOrderID(), od.getCode(), od.getQty(), od.getUnitPrice());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(OrderDetail t) throws Exception {
        return false;
    }

    @Override
    public OrderDetail get(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws Exception {

        ResultSet set =  CrudUtil.execute("SELECT * FROM OrderDetail");
        ArrayList<OrderDetail> orderDetailsList = new ArrayList<>();

        while (set.next()) {
            orderDetailsList.add(new OrderDetail(
                set.getString(1),
                set.getString(2),
                set.getInt(3),
                set.getDouble(4)
            ));
        }
        return orderDetailsList;
    }
}
