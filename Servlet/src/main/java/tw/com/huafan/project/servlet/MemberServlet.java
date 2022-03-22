package tw.com.huafan.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.huafan.project.dao.daoimpl.UserDaoImpl;
import tw.com.huafan.project.projo.User;

@WebServlet("/ms")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");

		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		User us = new User();
		
		UserDaoImpl udi = new UserDaoImpl();
		
		us.setAccount(account);
		us.setPassword(password);
		
		String name = udi.getName(account);
		 
		PrintWriter out = response.getWriter();
		out.println(name);
		out.flush();
		out.close();

		System.out.println(name);
		
	}

}
