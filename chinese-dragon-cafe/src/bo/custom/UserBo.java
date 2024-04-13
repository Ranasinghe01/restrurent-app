package bo.custom;

import java.util.ArrayList;

import dto.UserDTO;

public interface UserBo {

    public boolean saveUser(UserDTO registerDTO) throws Exception;

    public boolean updateUser(UserDTO registerDTO) throws Exception;

    public boolean deleteUser(String id) throws Exception;

    public UserDTO getUser(String id) throws Exception;
    
    public ArrayList<UserDTO> getAllUser() throws Exception;

}
