package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        
        String logout = request.getParameter("logout");
        request.setAttribute("logout", logout);

       if (name == null || name.equals("")) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }
 
        if (logout != null) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
            .forward(request, response);
         
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        request.setAttribute("action", action);

        if (action.equals("register")) {
            user = new User();
            String username = request.getParameter("username");
            user.setUsername(username);
            session.setAttribute("user", user);
            
            if (username == null || username.equals("")) {
                request.setAttribute("message", "Enter a valid name");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
            }
        }
        
        if (action.equals("add")) {
            String item = request.getParameter("item");
            ArrayList<String> listItem = user.getListItem();
            
            if (item == null || item.equals("")){
                request.setAttribute("message", "Enter a valid item");
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                .forward(request, response);
            }
            else{
                user.getListItem().add(item);
                session.setAttribute("user", user);
            }

        }
        
        if (action.equals("delete")) {
            String item = request.getParameter("item");
            user.getListItem().remove(item);
            session.setAttribute("user", user);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                .forward(request, response);

    }
}
