package dao.custom.impl;

import java.util.ArrayList;

import dao.custom.LoginDAO;
import entity.Login;

public class LoginDaoImpl implements LoginDAO{

    @Override
    public boolean save(Login t) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(Login t) throws Exception {
       return false;
    }

    @Override
    public Login get(String id) throws Exception {
       return null;
    }

    @Override
    public ArrayList<Login> getAll() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
}
