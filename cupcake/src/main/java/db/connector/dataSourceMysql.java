/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.connector;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 *
 * @author ndupo
 */

public class dataSourceMysql 
{
    private MysqlDataSource dataSource = new MysqlDataSource();
    
    // make connection to a MySQL server
    public dataSourceMysql()
    {
        try
        {
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("cupcake");
            dataSource.setUser("root");
            dataSource.setPassword("1234");
            dataSource.setUseSSL(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // connect to mySQL server
    public MysqlDataSource getDataSource()
    {
        return dataSource;
    }
}
