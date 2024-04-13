package dao;

import dao.custom.impl.CustomerDaoImpl;
import dao.custom.impl.ItemDaoImpl;
import dao.custom.impl.OrderDaoImpl;
import dao.custom.impl.OrderDetailDaoImpl;
import dao.custom.impl.UserDaoImpl;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DaoFactory()) : daoFactory;
    }

    public enum DAOType {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL, USER
    }

    @SuppressWarnings("unchecked")
    public <T> T getDao(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerDaoImpl();

            case ITEM:
                return (T) new ItemDaoImpl();

            case ORDER:
                return (T) new OrderDaoImpl();

            case ORDER_DETAIL:
                return (T) new OrderDetailDaoImpl();

            case USER:
                return (T) new UserDaoImpl();

            default:
                return null;
        }
    }

}
