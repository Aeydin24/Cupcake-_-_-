/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author christianwulff
 */
public abstract class Command 
{
    public abstract void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    public static Command from(HttpServletRequest request) 
    {
        
        Command c;
        
        String origin = request.getParameter("command");
        
        Map<String, Command> commands = new HashMap<>();
                
        commands.put("Login", new Login());
        commands.put("InputUser", new InputUser());
        
        c = commands.getOrDefault(origin, new UnknownCommand());
        
        return c;
        }
}
