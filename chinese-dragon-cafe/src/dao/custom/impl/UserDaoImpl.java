package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.UserDAO;
import entity.User;

public class UserDaoImpl implements UserDAO{

    @Override
    public boolean save(User user) throws Exception {
        return CrudUtil.execute("INSERT INTO User VALUES (?, ?, ?)",
                user.getUsername(), user.getPassword(), user.getRole());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM User WHERE username=?", id);
    }

    @Override
    public boolean update(User user) throws Exception {
        return CrudUtil.execute("UPDATE User SET username=?, password=?, role=? WHERE username=?",
                user.getUsername(), user.getPassword(), user.getRole());
    }

    @Override
    public User get(String id) throws Exception {
        
        ResultSet set = CrudUtil.execute("SELECT * FROM User WHERE username=?", id);

        while (set.next()) {
            return new User(
                set.getString(1),
                set.getString(2),
                set.getString(3)
            );
        }
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        
        ResultSet set = CrudUtil.execute("SELECT * FROM User");
        ArrayList<User> userList = new ArrayList<>();

        while (set.next()) {
            userList.add(new User(
                set.getString(1),
                set.getString(2),
                set.getString(3)
                ));
        }
        return userList;
    }

}
