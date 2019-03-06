/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entity.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mapper.DataMapperUsers;

/**
 *
 * @author ndupo
 */
public class UserRegister extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        DataMapperUsers userMapper = new DataMapperUsers();
        userMapper.setDataSource(new dataSourceMysql().getDataSource());
        
        Users user = userMapper.createUser(username, password, 0, true, username);
        
        if (user == null)
        {
            request.setAttribute("errormessage", "User not registered...");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
        else
        {
            session.setAttribute("userloggedin", user);
            response.sendRedirect("jsp/userinfo.jsp");
        }
    }
    
    
}
