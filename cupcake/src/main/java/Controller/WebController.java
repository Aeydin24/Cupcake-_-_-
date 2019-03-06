/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mapper.DataMapperUsers;

/**
 *
 * @author ndupo
 */
@WebServlet(name = "WebController", urlPatterns = {"/Controller"})
public class WebController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        // test that the parameter gets send to server (parameter = value)
//        String username = request.getParameter("username");
//        if("hanne".equals(username))
//        {
//            login(request, response);
//        }
        String origin = request.getParameter("origin");
        switch (origin) {
            case "Register":
                registration(request, response);
                break;
            case "Login":
                login(request, response);
                break;
            case "Shop":
                shop(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        /* Get the User info from URL Parameters.*/
        String username = (String) request.getParameter("username");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        
        DataMapperUsers dbu = new DataMapperUsers();
        /* Insert User into SQL Database */
        dbu.createUser(username, password, email);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
        rd.forward(request, response);
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {        
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
    
    private void shop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/shop.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
