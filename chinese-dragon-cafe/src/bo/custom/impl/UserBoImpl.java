package bo.custom.impl;

import java.util.ArrayList;

import bo.custom.UserBo;
import dao.DaoFactory;
import dao.custom.UserDAO;
import dto.UserDTO;
import entity.User;

public class UserBoImpl implements UserBo{

    private UserDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.USER);

    @Override
    public boolean saveUser(UserDTO dto) throws Exception {
        return dao.save(new User(dto.getUsername(), dto.getPassword(), dto.getRole()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        return dao.update(new User(dto.getUsername(), dto.getPassword(), dto.getRole()));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public UserDTO getUser(String id) throws Exception {
       
        User user = dao.get(id);
        if (user != null) {
            return new UserDTO(user.getUsername(), user.getPassword(), user.getRole());
        }
        return null;
    }

    @Override
    public ArrayList<UserDTO> getAllUser() throws Exception {
        
        ArrayList<User> userList = dao.getAll();
        ArrayList<UserDTO> dtoList = new ArrayList<>();

        for (User user : userList) {
            dtoList.add(new UserDTO(user.getUsername(), user.getPassword(), user.getRole()));
        }
        return dtoList;
    }
    
}
