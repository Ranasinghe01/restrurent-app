package bo;

import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.OrderBoImpl;
import bo.custom.impl.OrderDetailBoImpl;
import bo.custom.impl.UserBoImpl;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getInstance() {

        return (boFactory == null) ? (boFactory = new BoFactory()) : boFactory;
    }

    public enum BoType {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL, USER
    }

    @SuppressWarnings("unchecked")
    public <T> T getBo(BoType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerBoImpl();

            case ITEM:
                return (T) new ItemBoImpl();

            case ORDER:
                return (T) new OrderBoImpl();

            case ORDER_DETAIL:
                return (T) new OrderDetailBoImpl();

            case USER:
                return (T) new UserBoImpl();

            default:
                return null;
        }
    }

}
