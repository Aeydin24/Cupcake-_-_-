/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import com.mysql.cj.util.StringUtils;
import entity.Cupcake;
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
import mapper.DataMapperCupcake;
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
    
    /** send the request from the JSP site to the right method and executes it.
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @throws java.sql.SQLException */
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
                cupcakeToCart(user, request, response);
                break;
            case "AddBalance":
                addBalance(request, user, response);
            case "Checkout":
                checkout(user, request, response);
                break;
            default:
                throw new AssertionError();
        }
    }
    /** Registrate a new user with the given parameters. */
    private void registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        /* Get username, email and password from parameters in url */
        String username = (String) request.getParameter("username");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        
        /* Make an instance of the datamapperUsers to get acces to its methods. */ 
        DataMapperUsers dbu = new DataMapperUsers();

        /* Isert the new user information into the sql database. */ 
        dbu.createUser(username, password, email);
        
        /* Forward user to login page. */
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
        rd.forward(request, response);
    }
    
    /** Logs in the new if the user has valid username and password. */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {        
        
        /** Get parameters from Url (Username & Password) (From HTTP request). */
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        
        UserValidation uV = new UserValidation();
        boolean valid = false;
        
        /** Checks if user exsist in Database. */
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
                /** Get user from Database. */
                Users user = (Users) uV.getUser(username);
                /** Set user session. */
                sess.setAttribute("user", user);
                /** Send user to shop if valid. */
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
    /** Forwards request to shop.jsp */
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
    
    /** Takes user from index page to registration through link on the index.html page. */
    private void forwardRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /* Forwards the user to register. */
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/register.jsp");
        rd.forward(request, response);
    }
    /** Takes user from index page to log in through link on the index.html page. */
    private void forwardLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
    /** if username or password is wrong redirects to log in page. */
    private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("jsp/login.jsp");
    }
    
    /** Adds cupcake to cart. */
    private void cupcakeToCart(Users user, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
    {
        /** Get info about cupcake. */
        String topName = (String) request.getParameter("top");
        String botName = (String) request.getParameter("bottom");

        /** make cupcake. */
        DataMapperCupcake dmc = new DataMapperCupcake();
        Cupcake cupcake = dmc.makeCupcake(topName, botName);

       /** Make lineitem. */
        LineItem lineitem = new LineItem(cupcake);
        int qty = (int) Integer.parseInt(
                (String) request.getParameter("qty")
        );
        lineitem.addQuantity(qty);

        /** Grab shoppingcart to put cupcake in. */
        ShoppingCart cart = new ShoppingCart();
        ShoppingCart usercart = null;

        boolean cupcakeincart = false;

        if (user.getCart() != null) {
            usercart = user.getCart();
            /** If cupcake exists in Cart. */
            for (LineItem item : usercart.getLineItems()) {
                if (item.equals(lineitem)) {
                    item.addQuantity(lineitem.getQuantity());
                    cupcakeincart = true;
                }
            }
            /** If cupcake exists in Cart end. */
        }

        if (usercart != null && usercart.getLineItems() != null) {
            List<LineItem> items = usercart.getLineItems();
            for (LineItem item : items) {
                cart.addLineItem(item);
            }
        }

        /** Adds item to the cart. */
        if (cupcakeincart == false) {
            cart.addLineItem(lineitem);
        }

        /** Put cart back on User */
        user.setCart(cart);

        response.sendRedirect("jsp/shop.jsp");
    }
    /** Adds money to the users balance */
    private void addBalance(HttpServletRequest request, Users user, HttpServletResponse response) throws NumberFormatException, SQLException, ServletException, IOException {
        /** Get parameter. */
        String amount = (String) request.getParameter("amount");
       if(amount != null && !amount.isEmpty())
       {
        /** Parse to int. */
        int money = Integer.parseInt(amount);
        /** Add balance to user in session. */
        user.addBalance(money);
        /** Add balance to database. */
        DataMapperUsers dbu = new DataMapperUsers();
        dbu.addBalance(user.getUserName(), user.getBalance());
       
       }
       else 
       {
            String errormessage = "Wrong input in Add Balance field. Try again.";
            HttpSession session = request.getSession();
            session.setAttribute("errormessage", errormessage);
       }
    }
    /** Checks out the user and the carts content and returns a new empty cart. */
    private void checkout(Users user, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int userbalance = user.getBalance();
        int cartPrice = user.getTotalPrice();
        /** If user doesnt have enough money for the purchase. */
        if (userbalance < cartPrice) {
            /** Send errormessage to User. */
            response.sendRedirect("jsp/insufficientAmount.jsp");
        } else {
            /** If user have enough money. */
            DataMapperUsers dbu = new DataMapperUsers();

            /** Removes the money from the Balance of the User in database. */
            dbu.addBalance(user.getUserName(), user.getBalance()-cartPrice);
           /** Updates balance on html. */
            user.setBalance(user.getBalance()-cartPrice);
            
            dbu.addInvoice(user);
            
            /** Makes a new empty shoppingcart and adds that to user. */
            /** effectively resetting the cart. */
            ShoppingCart emptyCart = new ShoppingCart();
            user.setCart(emptyCart);
            response.sendRedirect("jsp/shop.jsp");
        }
    }

}
