/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.mysql.cj.util.StringUtils;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mapper.DataMapperUsers;
import shopping.LineItem;
import shopping.ShoppingCart;

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
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
       
        String origin = request.getParameter("origin");
        switch (origin) {
            case "Registration":
                registration(request, response);
                break;
            case "Login":
                login(request, response);
                break;
            case "Shop":
                shop(request, response);
                break;
            case "ForwardRegistration":
                forwardRegistration(request, response);
                break;
            case "ForwardLogin":
                forwardLogin(request, response);
                break;
            case "Error":
                error(request, response);
                break;
            case "AddProduct":
                cupcakeToCart(user, request);
                break;
            case "AddBalance":
                addBalance(request, user);
            case "Checkout":
                checkout(user, request);
                break;
            case "RemoveItem":
                removeitem(user, request);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        /* Get username, email and password from parameters in url */
        String username = (String) request.getParameter("username");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        
        /* Make an instance of the datamapperUsers to get acces to its methods */ 
        DataMapperUsers dbu = new DataMapperUsers();

        /* Isert the new user information into the sql database */ 
        dbu.createUser(username, password, email);
        
        /* Forward user to login page */
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
        rd.forward(request, response);
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {        
        
        // Get parameters from Url (Username & Password) (From HTTP request)
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        
        UserValidation uV = new UserValidation();
        boolean valid = false;
        
        //Checks if user exsist in Database.
        if(!StringUtils.isNullOrEmpty(username) && !StringUtils.isNullOrEmpty(password))
        {
            try {
                valid = uV.isValid(username, password);
            } catch (SQLException e) {
               Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        if (valid)
        {
            try {
                HttpSession sess = request.getSession();
                //Get user from Database.
                Users user = (Users) uV.getUser(username);
                //Set user session.
                sess.setAttribute("user", user);
                //Send user to shop if valid
                response.sendRedirect("jsp/shop.jsp");
            } catch (Exception e) 
            {
                Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        else
        {
            response.sendRedirect("jsp/errorpage.jsp");
        }
    }
    
    private void shop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /* Show shop page */
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

    private void forwardRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /* Forwards the user to register */
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/register.jsp");
        rd.forward(request, response);
    }

    private void forwardLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("jsp/login.jsp");
    }

    private void cupcakeToCart(Users user, HttpServletRequest request) throws SQLException {
        double userbalance = user.getBalance();
        int cartPrice = user.getTotalPrice();
        /* If user does NOT have enough money for the purchase */
        if (userbalance < cartPrice) {
            // Send errormessage to User
            String errormessage = "Not enough money on your balance "
                    + "for this purchase";
            request.setAttribute("errormessage", errormessage);
        } else {
            //if user has money:
            /* Removes the money from the Balance of the User */
            user.addBalance(-cartPrice);
            /* Makes a new empty shoppingcart and adds that to user
            effectively resetting the cart. */
            ShoppingCart emptyCart = new ShoppingCart();
            user.setCart(emptyCart);
        }
    }

    private void addBalance(HttpServletRequest request, Users user) throws NumberFormatException, SQLException {
        String amount = (String) request.getParameter("amount");
        int money = Integer.parseInt(amount);
        user.addBalance(money);
        DataMapperUsers DB = new DataMapperUsers();
        DB.setBalance(user, user.getBalance());
    }

    private void checkout(Users user, HttpServletRequest request) throws SQLException {
        double userbalance = user.getBalance();
        int cartPrice = user.getTotalPrice();
        /* If user does NOT have enough money for the purchase */
        if (userbalance < cartPrice) {
            // Send errormessage to User
            String errormessage = "Not enough money on your balance "
                    + "for this purchase";
            request.setAttribute("errormessage", errormessage);
        } else {
            /* If user DOES have enough money. */
            DataMapperUsers db = new DataMapperUsers();
            /* Removes the money from the Balance of the User */
            user.addBalance(-cartPrice);
            /* Makes a new empty shoppingcart and adds that to user
            effectively resetting the cart. */
            ShoppingCart emptyCart = new ShoppingCart();
            user.setCart(emptyCart);
        }
    }

    private void removeitem(Users user, HttpServletRequest request) {
        /* Get the cart */
        ShoppingCart cart = user.getCart();
        /* Get the parameter from the request */
        String cakename = (String) request.getParameter("cake");
        /* put cart into a List */
        List<LineItem> items = cart.getLineItems();
        /* For each item in the list */
        for (int i = 0; i < items.size(); i++) {
            String tname = items.get(i).getCupcake().getTop().getName();
            String bname = items.get(i).getCupcake().getBottom().getName();
            /* Remove the one that matches the one from the parameter */
            String itemname = bname + tname;
            if (itemname.equals(cakename)) {
                items.remove(i);
            }
        }
        cart.setLineItems(items);
        user.setCart(cart);
    }

}
