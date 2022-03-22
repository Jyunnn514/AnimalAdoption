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

/**
 * Servlet implementation class ResetNameServlet
 */
@WebServlet("/rn")
public class ResetNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


UserDaoImpl udi = new UserDaoImpl();
		
		User us = new User();
		

		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		boolean login = udi.login(account, password);
		
		
		if (login) {
		
			us.setName(name);
			us.setAccount(account);
			us.setPassword(password);


		udi.updateName(us);
		
		
		PrintWriter out = response.getWriter();
		out.println("success");
		out.flush();
		out.close();
		
		}else {
			
			PrintWriter out = response.getWriter();
			out.println("輸入錯誤");
			out.flush();
			out.close();
			
		}
		

	}

}
