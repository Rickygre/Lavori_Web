package it.tss.blogapp;

import it.tss.blogapp.control.UserStore;
import it.tss.blogapp.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tss
 */
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    
    @Inject //inserire e ritornare lista utenti
    UserStore store;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        System.out.println("servlet OK!");
        List<User> users = store.all();
        PrintWriter writer = resp.getWriter();
        users.forEach(v -> writer.println(v.getLastName())); //per ogni utente prendo il last-name
        //metodo getWriter() per scrivere in rete a chi ci ha fatto la request
        
        
    }
    
}
