/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import entity.Users;
import java.sql.SQLException;
import mapper.DataMapperUsers;

/**
 *
 * @author ndupo
 */

public class UserValidation 
{
    public UserValidation()
    {}
    
    /** Checks if user exsist in Database or not.
     * @param username
     * @param password
     * @return 
     * @throws java.sql.SQLException */
    
    public boolean isValid(String username, String password) throws SQLException 
    {
        Users user = getUser(username);
        if (password == null) {
            return false;
        }
        if (user.getPassword() == null) {
            return false;
        }
        return password.equals(user.getPassword());
    }
        
    /** Pull user from Database.
     * @param username
     * @return 
     * @throws java.sql.SQLException */
    public Users getUser(String username) throws SQLException 
    {
        DataMapperUsers db = new DataMapperUsers();
        return db.getUser(username);
    }
}
