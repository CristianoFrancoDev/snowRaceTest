package it.contrader.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.service.RegisterService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

    /**
     * 
     */
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	final HttpSession session = request.getSession(); 
    	session.setAttribute("utenteReg", null);
    	
    	//registerService...
    	RegisterService registerService = new RegisterService();
    
    
    
    
    }
}
