package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;


@WebServlet(name = "helloworld",loadOnStartup =1, urlPatterns = {"/hello","/helloWorld","/login" })
public class HelloWorldController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public HelloWorldController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		System.out.println("hello world"); //will disply at server consol
		
		PrintWriter out = response.getWriter();
		out.println("Hello World !!!!"); //writes in response object which will be send to browser
		
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = null;
		
		if(username.equals("root")&& password.equals("root@123")) {
			//out.println("<h2>Login successful. Welcome "+username+"<h2>");
		    dispatcher = request.getRequestDispatcher("success.html");
			
		}
		else {
			dispatcher = request.getRequestDispatcher("error.html");
		}
		dispatcher.forward(request, response);
			//out.println("<h2>Invalid username or password <h2>");
			
		//out.close();
	}

}
