/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author ndupo
 */
public class databaseconnect 
{
    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public databaseconnect()
    {
    }

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    
    public void open() throws SQLException
    {
        if(connection == null || connection.isClosed())
        {
            connection = dataSource.getConnection();
        }
    }
    
    public void close() throws SQLException
    {
        if(resultSet != null)
        {
            resultSet.close();
        }
        
        if(statement != null)
        {
            statement.close();
        }
        
        if(connection != null && !connection.isClosed())
        {
            connection.close();
            connection = null;
        }
    }
    
    public PreparedStatement preparedStatement(String sql) throws SQLException
    {
        return connection.prepareStatement(sql);
    }
    
    public PreparedStatement preparedStatement(String sql, int indicator) throws SQLException
    {
        return connection.prepareStatement(sql, indicator);
    }
}
