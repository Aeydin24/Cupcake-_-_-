/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmain.testmain;


import database.connector.DataSourceMysql;
import mapper.UserMapper;
import entity.User;

public class testmain
{
    public static void main(String[] args)
    {
        UserMapper userMapper = new UserMapper();
        
        DataSourceMysql dataSourceMysql = new DataSourceMysql();
        
        userMapper.setDataSource(dataSourceMysql.getDataSource());
        
        userMapper.registerUser("xyz", "999", 1000, false);
        
        User user = userMapper.validateUser("xyz", "999");
        
        userMapper.increaseBalance(user, 234);
        
        System.out.println("GetUser: " + userMapper.getUser(3));
    }
}